package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Repository.MuzixRepository;
import com.stackroute.Muzix.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuzixServiceImpl implements MuzixService {
    //MuzixRepository object to perform database
    MuzixRepository muzixRepository;
    //Autowired constructor to inject dependency
    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    public MuzixServiceImpl() {
    }

    @Override
    //method to save track
    public boolean saveTrack(Track track) {
        Track saveTrack=muzixRepository.save(track);
        return true;
    }

    @Override
    //method to dalete track
    public boolean deleteTrack(int id) {

        muzixRepository.deleteById(id);

        return true;
    }

    @Override
    //method to get all tracks
    public List<Track> getAllTracks() {
       return muzixRepository.findAll();

    }

    @Override
    //method to update a track
    public boolean updateTrack(Track track,int id) {
        Optional<Track> useOptional=muzixRepository.findById(id);
        if (!useOptional.isPresent()){
            return false;
        }
        track.setId(id);
        muzixRepository.save(track);
        return true;
    }

    @Override
    //method to get track by id
    public Optional<Track> getTrackById(int id) {
        return muzixRepository.findById(id);
    }
}
