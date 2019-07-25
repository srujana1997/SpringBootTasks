package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Exceptions.TrackExist;
import com.stackroute.Muzix.Exceptions.TrackNotFound;
import com.stackroute.Muzix.Repository.MuzixRepository;
import com.stackroute.Muzix.Track;
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
    @Value("${track.1.name:default}")
    String name1;
    @Value("${track.1.comments:default}")
    String comments1;
    @Value("${track.2.name:default}")
    String name2;
    @Value("${track.2.comments:default}")
    String comments2;

    MuzixRepository muzixRepository;
    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    public MuzixServiceImpl() {
    }

    @Override
    public boolean saveTrack(Track track)throws TrackExist {
        if(muzixRepository.existsById(track.getId())) {
            throw new TrackExist("id already exists");
        }
            Track saveTrack=muzixRepository.save(track);
        return true;
    }




    @Override
    public boolean deleteTrack(int id) throws TrackNotFound{
        Track track=new Track();
        if(!muzixRepository.findById(id).isPresent()){
            throw new TrackNotFound("id not found");
        }
        muzixRepository.deleteById(id);

        return true;
    }

    @Override
    public List<Track> getAllTracks() {
       return muzixRepository.findAll();

    }

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

    @Override
    public Optional<Track> getTrackById(int id) throws TrackNotFound {
        if(!muzixRepository.findById(id).isPresent()){
            throw new TrackNotFound("id not found");
        }
        return muzixRepository.findById(id);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner Implemented");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        muzixRepository.save(new Track(1,name1,comments1));
        muzixRepository.save(new Track(2,name2,comments2));
    }
}
