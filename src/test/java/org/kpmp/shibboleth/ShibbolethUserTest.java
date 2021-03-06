package org.kpmp.shibboleth;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShibbolethUserTest {

	private ShibbolethUser testUser;

	@Before
	public void setUp() throws Exception {
		testUser = new ShibbolethUser();
	}

	@After
	public void tearDown() throws Exception {
		testUser = null;
	}

	@Test
	public void testSetFirstName() {
		testUser.setFirstName("Ziggy");
		assertEquals("Ziggy", testUser.getFirstName());
	}

	@Test
	public void testSetLastName() {
		testUser.setLastName("Stardust");
		assertEquals("Stardust", testUser.getLastName());
	}

	@Test
	public void testSetEmail() {
		testUser.setEmail("ziggy@mars.com");
		assertEquals("ziggy@mars.com", testUser.getEmail());
	}

	@Test
	public void testSetDisplayName() {
		testUser.setDisplayName("Space Oddity");
		assertEquals("Space Oddity", testUser.getDisplayName());
	}

	@Test
	public void testSetKpmpId() throws Exception {
		testUser.setKpmpId("KPMP-123");
		assertEquals("KPMP-123", testUser.getKpmpId());
	}

	@Test
	public void testToString() {
		testUser.setDisplayName("Space Oddity");
		testUser.setFirstName("Ziggy");
		testUser.setLastName("Stardust");
		testUser.setEmail("ziggy@mars.com");
		testUser.setShibId("ziggy@mars.com");
		testUser.setKpmpId("KPMP-123");
		assertEquals("firstName: Ziggy" + ", lastName: Stardust" + ", displayName: Space Oddity"
				+ ", email: ziggy@mars.com, shibId: ziggy@mars.com, kpmpId: KPMP-123", testUser.toString());
	}

}