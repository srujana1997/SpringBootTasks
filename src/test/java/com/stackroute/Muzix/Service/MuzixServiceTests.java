package com.stackroute.Muzix.Service;

import com.stackroute.Muzix.Exceptions.TrackExist;
import com.stackroute.Muzix.Exceptions.TrackNotFound;
import com.stackroute.Muzix.Repository.MuzixRepository;
import com.stackroute.Muzix.domain.Track;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static jdk.internal.vm.compiler.word.LocationIdentity.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MuzixServiceTests {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private MuzixRepository muzixRepository;

     Track track;

    @InjectMocks
    MuzixServiceImpl muzixServiceImpl;
    private List<Track> list = null;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzixServiceImpl).build();
        track = new Track(1,"maate vinadhuga","sid sriram");
    }

    @Test
    public void saveTrackTest() throws Exception
    {
        when(muzixRepository.save(track)).thenReturn(track);
        Track savedTrack = muzixServiceImpl.saveTrack(track);
        Assert.assertEquals(track,savedTrack);
        verify(muzixRepository,times(1)).save(Mockito.any(Track.class));
    }

    @Test(expected = TrackExist.class)
    public void saveUserTestFailure() throws TrackExist {
        when(muzixRepository.save((Track)any())).thenReturn(null);
        Track savedTrack = muzixServiceImpl.saveTrack(track);
    }

    @Test
    public void getAllTracksTest()
    {
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        when(muzixRepository.findAll()).thenReturn(trackList);
        List<Track> retrievedTracks = muzixServiceImpl.getAllTracks();
        Assert.assertEquals(trackList,retrievedTracks);
        //  verify(trackRepository,times(1)).findAll();
        //verifyNoMoreInteractions(trackRepository);
    }
    @Test
    public void updateTrackTest() throws TrackNotFound
    {
        Optional<Track> optionalTrack = Optional.of(track);
        when(muzixRepository.findById(1)).thenReturn(optionalTrack);
        when(muzixRepository.save(track)).thenReturn(track);
        Track updatedTrack = muzixServiceImpl.updateTrack(track,1);
        Assert.assertEquals(track,updatedTrack);
        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository,times(1)).save(Mockito.any(Track.class));
        //verify(trackRepository,times(1)).findById(1);
        //verifyNoMoreInteractions(trackRepository);
    }

    @Test
    public void deleteTrackTest() throws TrackNotFound
    {
        Optional<Track> optionalTrack = Optional.of(track);
        when(muzixRepository.findById(1)).thenReturn(optionalTrack);
        Boolean result = muzixServiceImpl.deleteTrack(1);
        Assert.assertTrue(result);
        verify(muzixRepository,times(1)).delete(Mockito.any(Track.class));
        //  verify(trackRepository,times(1)).findById(1);
        //verifyNoMoreInteractions(trackRepository);
    }

}

