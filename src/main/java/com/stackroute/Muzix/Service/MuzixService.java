package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Exceptions.TrackExist;
import com.stackroute.Muzix.Exceptions.TrackNotFound;
import com.stackroute.Muzix.Track;

import java.util.List;
import java.util.Optional;

public interface MuzixService {
    public boolean saveTrack(Track track) throws TrackExist;
    public boolean deleteTrack(int Id) throws TrackNotFound;
    public Optional<Track> getTrackById(int trackId) throws TrackNotFound;
    public List<Track> getAllTracks();
    public boolean updateTrack(Track track,int id) throws TrackNotFound;
}
