package com.abhi;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.web.VetController;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.abhi.beans.HearingInterpreter;

@TestPropertySource("classpath:config.properties")
@SpringJUnitConfig(classes = {MyConfig.class})
public class WordProcessorTests {

	@Autowired
	HearingInterpreter hi;
	
	@Value("${Laurel.text}")
	String value;
	
	@Autowired
	VetController controller;
	
	@Autowired
	ClinicService clinicService;
	
	/* MockMvc mockMvc; */
	
	/*
	 * @Test public void testShowVetList() throws Exception { mockMvc =
	 * MockMvcBuilders.standaloneSetup(controller).build();
	 * mockMvc.perform(get("/vets.html")); }
	 */	
	@Test
	public void testSpeakIt() {
		assertEquals("Laurel", hi.speakIt());
	}


}
