package org.kpmp.slides;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SlideServiceTest {

	@Mock
	private ParticipantRepository participantRepository;
	private SlideService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new SlideService(participantRepository);
	}

	@After
	public void tearDown() throws Exception {
		service = null;
	}

	@Test
	public void testGetSlidesForPatient() {
		Participant patient = mock(Participant.class);
		Slide slide = mock(Slide.class);
		Stain stain = mock(Stain.class);
		when(stain.getType()).thenReturn("he");
		when(slide.getStain()).thenReturn(stain);
		List<Slide> slides = Arrays.asList(slide);
		when(patient.getSlides()).thenReturn(slides);
		when(participantRepository.findByKpmpId("345")).thenReturn(patient);

		List<Slide> result = service.getSlidesForParticipant("345");

		assertEquals(slides, result);
	}

	@Test
	public void testGetSlidesForPatient_excludesTOLandFRZandIHC() {
		Participant patient = mock(Participant.class);
		Slide slide1 = mock(Slide.class);
		Slide slide2 = mock(Slide.class);
		Slide slide3 = mock(Slide.class);
		Slide slide4 = mock(Slide.class);
		Stain stain1 = mock(Stain.class);
		Stain stain2 = mock(Stain.class);
		Stain stain3 = mock(Stain.class);
		Stain stain4 = mock(Stain.class);
		when(stain1.getType()).thenReturn("he");
		when(stain2.getType()).thenReturn("tol");
		when(stain3.getType()).thenReturn("frz");
		when(stain4.getType()).thenReturn("ihc");
		when(slide1.getStain()).thenReturn(stain1);
		when(slide2.getStain()).thenReturn(stain2);
		when(slide3.getStain()).thenReturn(stain3);
		when(slide4.getStain()).thenReturn(stain4);
		List<Slide> slides = Arrays.asList(slide1, slide2, slide3, slide4);
		when(patient.getSlides()).thenReturn(slides);
		when(participantRepository.findByKpmpId("345")).thenReturn(patient);

		List<Slide> result = service.getSlidesForParticipant("345");

		assertEquals(Arrays.asList(slide1), result);
	}

	@Test
	public void testGetSlidesForPatient_whenNoSlides() {
		when(participantRepository.findByKpmpId("345")).thenReturn(null);

		List<Slide> result = service.getSlidesForParticipant("345");

		assertEquals(Collections.emptyList(), result);
	}

	@Test
	public void testGetAllParticipantSlides() {
		List<Participant> participantSlidesList = Arrays.asList(mock(Participant.class));
		when(participantRepository.findByOrderByKpmpIdAsc()).thenReturn(participantSlidesList);
		List<Participant> result = service.getAllParticipants();
		assertEquals(participantSlidesList, result);
	}

	@Test
	public void testGetAllParticipantSlides_noResults() {
		when(participantRepository.findByOrderByKpmpIdAsc()).thenReturn(null);
		List<Participant> result = service.getAllParticipants();
		assertEquals(Collections.emptyList(), result);
	}

}
