package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Exceptions.TrackExist;
import com.stackroute.Muzix.Exceptions.TrackNotFound;
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
    @Autowired
    //Autowired constructor to inject dependency
    public MuzixServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    public MuzixServiceImpl() {
    }

    @Override
    //method to save track
    public boolean saveTrack(Track track)throws TrackExist {
        if(muzixRepository.existsById(track.getId())) {
            throw new TrackExist("id already exists");
        }
            Track saveTrack=muzixRepository.save(track);
        return true;
    }
    @Override
    //method to delete a track
    public boolean deleteTrack(int id) throws TrackNotFound{
        Track track=new Track();
        if(!muzixRepository.findById(id).isPresent()){
            throw new TrackNotFound("id not found");
        }
        muzixRepository.deleteById(id);

        return true;
    }
    //method to get all tracks
    @Override
    public List<Track> getAllTracks() {
       return muzixRepository.findAll();

    }
    //method to update a track
    @Override
    public boolean updateTrack(Track track,int id) throws TrackNotFound {
        Optional<Track> useOptional=muzixRepository.findById(id);
        if (!useOptional.isPresent()){
            throw new TrackNotFound("id not found");

        }
        track.setId(id);
        muzixRepository.save(track);
        return true;
    }
    //method to get track by id
    @Override
    public Optional<Track> getTrackById(int id) throws TrackNotFound {
        if(!muzixRepository.findById(id).isPresent()){
            throw new TrackNotFound("id not found");
        }
        return muzixRepository.findById(id);
    }

}
