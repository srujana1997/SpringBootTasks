package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Track;

import java.util.List;
import java.util.Optional;

public interface MuzixService {
    public boolean saveTrack(Track track);
    public boolean deleteTrack(int id);
    public List<Track> getAllTracks();
    public boolean updateTrack(Track track,int id);
    public Optional<Track> getTrackById(int id);
}
