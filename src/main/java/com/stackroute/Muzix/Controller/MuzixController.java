package com.stackroute.Muzix.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Muzix.Exceptions.TrackExist;
import com.stackroute.Muzix.Exceptions.TrackNotFound;
import com.stackroute.Muzix.Service.MuzixService;
import com.stackroute.Muzix.domain.Result;
import com.stackroute.Muzix.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class MuzixController {
    MuzixService muzixService;

    public MuzixController(MuzixService muzixService)
    {
        this.muzixService = muzixService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            muzixService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("alltracks")
    public ResponseEntity<?> saveAllTrack(@RequestBody List<Track> trackList) throws TrackExist
    {
        List<Track> savedTrackList = new ArrayList<Track>();
        for (Track track:trackList) {
            Track track1 = muzixService.saveTrack(track);
            savedTrackList.add(track1);
        }
        return new ResponseEntity<List<Track>>(savedTrackList, HttpStatus.CREATED);
    }

//    @GetMapping("trackByName")
//    public ResponseEntity<?> getTrackByName(@RequestParam String name) throws TrackNotFound
//    {
//        return new ResponseEntity<List<Track>>(muzixService.getTracksByName(name), HttpStatus.OK);
//    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {
        ResponseEntity responseEntity;

        try {
            responseEntity = new ResponseEntity<List<Track>>(muzixService.getAllTracks(), HttpStatus.OK);
        }
        catch (Exception exception) {

            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrack(@RequestBody Track track, @PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            muzixService.updateTrack(track, id);
            responseEntity = new ResponseEntity<List<Track>>(muzixService.getAllTracks(), HttpStatus.CREATED);
        } catch (Exception exception1) {
            responseEntity = new ResponseEntity<String>(exception1.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            muzixService.deleteTrack(id);
            responseEntity = new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

//    @GetMapping("searchTracks")
//    public ResponseEntity<?> searchTracks(@RequestParam("searchString") String searchString)
//    {
//        ResponseEntity responseEntity;
//        try{
//            muzixService.searchTracks(searchString);
//            responseEntity= new ResponseEntity<List<Track>>(muzixService.searchTracks(searchString),HttpStatus.OK);
//        }catch (Exception exception){
//            responseEntity = new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
//        }
//        return responseEntity;
//    }

    @GetMapping("getLastFmTracks")
    public ResponseEntity<?> getLastFmTracks(@RequestParam String url) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String string = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = objectMapper.readValue(string, Result.class);
        List<Track> trackList = result.results.trackmatches.track;
        List<Track> savedTrackList = new ArrayList<>();
        for (Track track:trackList) {
            Track track1 = muzixService.saveTrack(track);
            savedTrackList.add(track1);
        }
        return new ResponseEntity<>(savedTrackList,HttpStatus.OK);
    }


}
