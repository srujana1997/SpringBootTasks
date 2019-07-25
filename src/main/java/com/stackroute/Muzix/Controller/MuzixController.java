package com.stackroute.Muzix.Controller;

import com.stackroute.Muzix.Exceptions.TrackExist;
import com.stackroute.Muzix.Service.MuzixService;
import com.stackroute.Muzix.Track;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class MuzixController {
    @Autowired
    MuzixService muzixService;
    public MuzixController(MuzixService muzixService) {
        this.muzixService = muzixService;
    }
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackExist {
       /* ResponseEntity responseEntity;
        try{
            muzixService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch(Exception exception){
            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;*/
        muzixService.saveTrack(track);
        return new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);


    }
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks(){
     return new ResponseEntity<List<Track>>(muzixService.getAllTracks(),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id){
        ResponseEntity responseEntity;
        try{
            muzixService.deleteTrack(id);
            responseEntity=new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        }
        catch(Exception exception){
            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateTrack(@RequestBody Track track, @PathVariable int id){
        ResponseEntity responseEntity;
        try{
            muzixService.updateTrack(track,id);
            responseEntity=new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        }
        catch(Exception exception){
            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


}
