package com.cardlatch.hotel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = HotelApplication.class)
public class MultipleEntryPointsTest {

	@Autowired
	private WebApplicationContext webAppCtx;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webAppCtx).addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void whenTestWithoutCredentials_thenUnauthorized() throws Exception {
		mockMvc.perform(get("/rooms/available")).andExpect(status().isUnauthorized());
	}

	@Test
	public void whenTestWithWrongCredentials_thenUnauthorized() throws Exception {
		mockMvc.perform(get("/rooms/available").with(httpBasic("user", "wrongPassword")))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void whenTestWithCredentialsMatchingRole_thenOk() throws Exception {
		mockMvc.perform(get("/rooms/available").with(httpBasic("user", "user"))).andExpect(status().isOk());

		mockMvc.perform(get("/rooms/available").with(httpBasic("owner", "owner"))).andExpect(status().isOk());

		mockMvc.perform(get("/rooms/available").with(user("someUser").password("somePass").roles("MANAGER")))
				.andExpect(status().isOk());
	}

	@Test
	public void whenTestWithCredentialsRoleMismatch_thenForbidden() throws Exception {
		mockMvc.perform(get("/rooms/available").with(user("admin").password("admin").roles("ADMIN")))
				.andExpect(status().isForbidden());
	}
}
