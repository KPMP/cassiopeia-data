package org.kpmp.shibboleth;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.logging.LoggingService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ShibbolethUserServiceTest {

	private ShibbolethUserService shibbolethUserService;
	@Mock
	private UTF8Encoder utf8Encoder;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		shibbolethUserService = new ShibbolethUserService(utf8Encoder);
	}

	@After
	public void tearDown() throws Exception {
		shibbolethUserService = null;
	}

	@Test
	public void testGetUser() throws UnsupportedEncodingException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeaderNames()).thenReturn(Collections.enumeration(Collections.emptyList()));
		when(request.getHeader("mail")).thenReturn("maninblack@jcash.com");
		when(request.getHeader("givenname")).thenReturn("Johnny");
		when(request.getHeader("sn")).thenReturn("Cash");
		when(request.getHeader("displayname")).thenReturn("Johnny Cash");
		when(request.getHeader("eppn")).thenReturn("shibId");
		when(request.getHeader("studyId")).thenReturn("kpmp-123");
		when(utf8Encoder.convertFromLatin1("Johnny")).thenReturn("Johnny");
		when(utf8Encoder.convertFromLatin1("Cash")).thenReturn("Cash");
		when(utf8Encoder.convertFromLatin1("Johnny Cash")).thenReturn("Johnny Cash");
		when(utf8Encoder.convertFromLatin1("maninblack@jcash.com")).thenReturn("maninblack@jcash.com");
		when(utf8Encoder.convertFromLatin1("shibId")).thenReturn("shibId");
		when(utf8Encoder.convertFromLatin1("kpmp-123")).thenReturn("kpmp-123");

		ShibbolethUser user = shibbolethUserService.getUser(request);

		assertEquals("maninblack@jcash.com", user.getEmail());
		assertEquals("Johnny Cash", user.getDisplayName());
		assertEquals("Cash", user.getLastName());
		assertEquals("Johnny", user.getFirstName());
		assertEquals("shibId", user.getShibId());
		assertEquals("kpmp-123", user.getKpmpId());

	}

}