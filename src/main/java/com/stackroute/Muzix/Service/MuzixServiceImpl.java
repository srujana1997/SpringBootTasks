package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Exceptions.TrackExist;
import com.stackroute.Muzix.Exceptions.TrackNotFound;
import com.stackroute.Muzix.Repository.MuzixRepository;
import com.stackroute.Muzix.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuzixServiceImpl implements MuzixService, ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
    MuzixRepository muzixRepository;

    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository)
    {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackExist {

        if(muzixRepository.existsById(track.getId()))
        {
            throw new TrackExist("Track already exists");
        }
        Track savedTrack = muzixRepository.save(track);

        if(savedTrack == null)
        {
            throw new TrackExist("Track already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {

        return muzixRepository.findAll();
    }



//    @Override
//    public List<Track> getTracksByName(String name) {
//
//      //  return muzixRepository.getTrackByName(name);
//
//    }

    public Track updateTrack(Track track, int id) throws TrackNotFound
    {
        Optional<Track> track1 = muzixRepository.findById(id);

        if(!track1.isPresent())
        {
            throw new TrackNotFound("Track Not Found");
        }

        track.setId(id);

        Track savedTrack = muzixRepository.save(track);
        return savedTrack;
    }

    public boolean deleteTrack(int id) throws TrackNotFound
    {
        Optional<Track> track1 = muzixRepository.findById(id);

        if(!track1.isPresent())
        {
            throw new TrackNotFound("Track Not Found");
        }

        try {

            muzixRepository.delete(track1.get());

            return true;

        }
        catch (Exception exception)
        {
            return false;
        }
    }


//    @Override
//    public List<Track> searchTracks(String searchString) {
//
//       // return muzixRepository.searchTracks(searchString);
//    }


    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
