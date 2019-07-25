package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Repository.MuzixRepository;
import com.stackroute.Muzix.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuzixServiceImpl implements MuzixService {
    MuzixRepository muzixRepository;
    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    public MuzixServiceImpl() {
    }

    @Override
    public boolean saveTrack(Track track) {
        Track saveTrack=muzixRepository.save(track);
        return true;
    }

    @Override
    public boolean deleteTrack(int id) {

        muzixRepository.deleteById(id);

        return true;
    }

    @Override
    public List<Track> getAllTracks() {
       return muzixRepository.findAll();

    }

    @Override
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
    public Optional<Track> getTrackById(int id) {
        return muzixRepository.findById(id);
    }

}
