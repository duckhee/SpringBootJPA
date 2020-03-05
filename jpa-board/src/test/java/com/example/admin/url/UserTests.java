package com.example.admin.url;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
/** URL Tests Annotations */
@AutoConfigureMockMvc
public class UserTests {

	@Autowired
	private MockMvc mock;
	
	@Test
	public void MainUrlTests() {
		try {
			
			MvcResult result = mock.perform(get("/admin/users"))
					.andExpect(status().is3xxRedirection())
					.andReturn();
			System.out.println(result.getResponse().getStatus());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Main Url Test Failed");
		}
	}
	
	@Test
	public void RegisteUrlTests() {
		try {
			MvcResult result = mock.perform(get("/admin/users/registe"))
					.andExpect(status().isOk())
					.andReturn();
			System.out.println(result.getResponse().getStatus());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Registe Url Test Failed");
		}
	}
	
	@Test
	public void LoginUrlTests() {
		try {
			MvcResult result = mock.perform(get("/admin/users/login"))
					.andExpect(status().isOk())
					.andReturn();
			System.out.println(result.getResponse().getStatus());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Login Url Test Failed");
		}
	}
	
	@Test
	public void ProfileUrlTests() {
		try {
			MvcResult result = mock.perform(get("/admin/users/profile"))
					.andExpect(status().isOk())
					.andReturn();
			System.out.println(result.getResponse().getStatus());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Profile Url Tests Failed");
		}
	}
}
