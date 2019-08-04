package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Track;

import java.util.List;
import java.util.Optional;
//interface for the service class
public interface MuzixService {
    //method to save a track in database
    public boolean saveTrack(Track track);
    //method to delete a track
    public boolean deleteTrack(int id);
    //method to get all tracks 
    public List<Track> getAllTracks();
    //method to update tracks 
    public boolean updateTrack(Track track,int id);
    //method to get track by id
    public Optional<Track> getTrackById(int id);
}

