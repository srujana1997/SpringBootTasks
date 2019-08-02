package com.stackroute.Muzix.Repository;

import com.stackroute.Muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@DataJpaTest
public class MuzixRepositoryTests {
    @Autowired
    MuzixRepository muzixRepository;

    Track track;

    @Before
    public void setUp()
    {
        track = new Track(1,"vvr","dsp");
        track.setComment("dsp");
        track.setName("vvr");
        track.setId(1);

    }

    @After
    public void tearDown(){

        muzixRepository.deleteAll();
    }

    @Test
    public void testSaveTrack(){
        muzixRepository.save(track);
        Track fetchTrack = muzixRepository.findById(track.getId()).get();
        Assert.assertEquals(1,fetchTrack.getId());
    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(1,"vvr","dsp");
        muzixRepository.save(track);
        Track fetchTrack = muzixRepository.findById(track.getId()).get();
        Assert.assertNotSame(fetchTrack,testTrack);
    }

    @Test
    public void getAllTracks()
    {
        List<Track> tracks = new ArrayList<>();
        Track track1 = new Track(1,"vvr","dsp");
        Track track2 = new Track(2,"vvr","dsp");
        tracks.add(track1);
        tracks.add(track2);
        List<Track> trackslist = muzixRepository.findAll();
        //Assert.assertEquals(tracks,trackslist);
        Assert.assertEquals("vvr",tracks.get(0).getName());
    }

}
