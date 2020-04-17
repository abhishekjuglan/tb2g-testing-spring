package org.springframework.samples.petclinic.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
class VetControllerTest {

	@InjectMocks
	VetController controller;
	
	@Mock
	ClinicService clinicService;	
	
	@Mock
	Map<String, Object> model;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		Vets vets = new Vets();
		vets.getVetList().add(new Vet());
		vets.getVetList().add(new Vet());
		when(clinicService.findVets()).thenReturn(vets.getVetList());
		
		mockMvc  = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	void testControllerShowVetList() throws Exception{
		mockMvc.perform(get("/vets.html"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("vets"));
	}
	
	@Test
	void testShowVetList() {
		//when
		String viewName = controller.showVetList(model);
		//then
		assertTrue("vets/vetList".equalsIgnoreCase(viewName));
	}

	@Test
	void testShowResourcesVetList() {
		
		//given
		
		//when
		Vets vets = controller.showResourcesVetList();
		//then
		assertThat(2).isEqualTo(vets.getVetList().size());
		
	}

}
