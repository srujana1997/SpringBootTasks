package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Exceptions.TrackExist;
import com.stackroute.Muzix.Exceptions.TrackNotFound;
import com.stackroute.Muzix.domain.Track;

import java.util.List;
import java.util.Optional;

public interface MuzixService {

    public Track saveTrack(Track track) throws TrackExist;

    public List<Track> getAllTracks();

//    public List<Track> getTracksByName(String name);

    public Track updateTrack(Track track, int id) throws TrackNotFound;

    public boolean deleteTrack(int id) throws TrackNotFound;

   // public List<Track> searchTracks(String searchString);
}
