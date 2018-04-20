package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Model;

public class LoginTest {
	private Model model;
	private String username;
	private String password;

	@Before
	public void setUp() throws Exception {
		model = new Model();
		username = "luka";
		password = "123";
	}

	@Test
	public void testAuthenticateUser() {
		model.authenticateUser(username, password);
		assertEquals(username, model.getUser().getUsername());
		assertEquals(password, model.getUser().getPasswordHash());
	}
}
