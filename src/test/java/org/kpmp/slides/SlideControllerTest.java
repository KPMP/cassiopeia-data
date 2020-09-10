package org.kpmp.slides;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.logging.LoggingService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SlideControllerTest {

	@Mock
	private SlideService slideService;
	private SlideController controller;
	@Mock
	private LoggingService logger;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new SlideController(slideService, logger);
	}

	@After
	public void tearDown() throws Exception {
		controller = null;
	}

	@Test
	public void testGetSlidesForPatient() {
		List<Slide> slides = Arrays.asList(mock(Slide.class));
		when(slideService.getSlidesForPaarticipant("444")).thenReturn(slides);

		HttpServletRequest request = mock(HttpServletRequest.class);
		List<Slide> result = controller.getSlidesForParticipant("444", request);

		verify(logger).logInfoMessage(controller.getClass(), "Getting slides for participant 444", request);
		assertEquals(slides, result);
	}

	@Test
	public void testGetAllSlides() {
		List<Participant> slides = Arrays.asList(mock(Participant.class));
		when(slideService.getAllParticipants()).thenReturn(slides);
		HttpServletRequest request = mock(HttpServletRequest.class);
		List<Participant> result = controller.getAllParticipants(request);
		verify(logger).logInfoMessage(controller.getClass(), "Getting all participants", request);
		assertEquals(slides, result);
	}

}
