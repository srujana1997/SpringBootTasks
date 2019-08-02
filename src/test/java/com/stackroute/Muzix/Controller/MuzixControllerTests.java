package com.stackroute.Muzix.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Muzix.Service.MuzixService;
import com.stackroute.Muzix.domain.Track;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MuzixControllerTests {
  @Autowired
  private MockMvc mockMvc;
  @Mock
  private MuzixService muzixService;
  private Track track;
  @InjectMocks
  MuzixController muzixController;
  private List<Track> list =null;
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(muzixController).build();
  }

  @Test
  public void saveTrackTest() throws Exception {
    Track track = new Track(1,"ram","abcd");
    when(muzixService.saveTrack(track)).thenReturn(track);
    mockMvc.perform(post("/api/v1/track")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(track)))
            .andExpect(status().isCreated());
    verify(muzixService, times(1)).saveTrack(Mockito.any(Track.class));
    verifyNoMoreInteractions(muzixService);
  }

  @Test
  public void getAllTracks() throws Exception{
    List<Track> trackslist = new ArrayList<>();
    Track track= new Track(1,"maatevindhuga","sid sriram");
    trackslist.add(track);
    when(muzixService.getAllTracks()).thenReturn(trackslist);
    mockMvc.perform(post("/api/v1/track")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(track)))
            .andExpect(status().isCreated());
    // verify(trackService, times(1)).getAllTracks();
    // verifyNoMoreInteractions(trackService);
  }

  @Test
  public void deleteTrackTest() throws Exception
  {
    when(muzixService.deleteTrack(1)).thenReturn(true);
    mockMvc.perform(delete("/api/v1/track/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    verify(muzixService, times(1)).deleteTrack(1);
    verifyNoMoreInteractions(muzixService);
  }


  @Test
  public void updateTrackTest() throws Exception
  {
    Track track1 = new Track(5,"maatevindhuga","sid sriram");
    Track track2 = new Track(4,"maatevindhuga","sid sriram");
    when(muzixService.updateTrack(track1,1)).thenReturn(track2);
    mockMvc.perform(put("/api/v1/track/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(track1)))
            .andExpect(status().isCreated());
    //verify(trackService, times(1)).updateTrack(Mockito.any(Track.class),1);
    //verifyNoMoreInteractions(trackService);
  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
