package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Exceptions.TrackExist;
import com.stackroute.Muzix.Exceptions.TrackNotFound;
import com.stackroute.Muzix.Track;

import java.util.List;
import java.util.Optional;
//interface for the service class
public interface MuzixService {
    //method to save a track
    public boolean saveTrack(Track track) throws TrackExist;
    //method to delete a track
    public boolean deleteTrack(int Id) throws TrackNotFound;
    //method to get track by id
    public Optional<Track> getTrackById(int trackId) throws TrackNotFound;
    //method to get all tracks
    public List<Track> getAllTracks();
    //method to update a track
    public boolean updateTrack(Track track,int id) throws TrackNotFound;
}
