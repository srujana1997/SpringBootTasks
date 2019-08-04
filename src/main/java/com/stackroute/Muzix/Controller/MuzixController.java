package com.stackroute.Muzix.Controller;

import com.stackroute.Muzix.Service.MuzixService;
import com.stackroute.Muzix.Track;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;
import java.util.List;
//Controller class that handles requests and sends a response
@RestController
@RequestMapping(value = "api/v1")
public class MuzixController {
    //Autowired to inject the muzixService dependency
    @Autowired
    MuzixService muzixService;
    public MuzixController(MuzixService muzixService) {
        this.muzixService = muzixService;
    }
    @PostMapping("track")
      //handler to save track
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            muzixService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch(Exception exception){
            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("track")
      //handler to get all tracks
    public ResponseEntity<?> getAllTracks(){
     return new ResponseEntity<List<Track>>(muzixService.getAllTracks(),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
      //handler to delete track
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
      //handler to update track
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
