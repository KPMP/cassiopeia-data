package org.kpmp.error;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.logging.LoggingService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ErrorControllerTest {

	private ErrorController controller;
	@Mock
	private LoggingService logger;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		controller = new ErrorController(logger);
	}

	@AfterEach
	public void tearDown() throws Exception {
		controller = null;
	}

	@Test
	public void testLogError() throws IOException {
		FrontEndError errorMessage = mock(FrontEndError.class);
		when(errorMessage.getError()).thenReturn("error");
		when(errorMessage.getStackTrace()).thenReturn("oh noes...something terrible happened");
		HttpServletRequest request = mock(HttpServletRequest.class);

		ResponseEntity<Boolean> result = controller.logError(errorMessage, request);

		verify(logger).logErrorMessage(ErrorController.class, null,
				"error with stacktrace: oh noes...something terrible happened", request);
		assertEquals(new ResponseEntity<>(true, HttpStatus.OK), result);
	}

}
