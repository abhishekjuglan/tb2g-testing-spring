package org.springframework.samples.petclinic.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTestMine {

	@Mock
	ClinicService clinicService;
	
	@InjectMocks
	OwnerController controller;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        when(clinicService.findOwnerByLastName(stringArgumentCaptor.capture())).thenAnswer( i ->{
        	List<Owner> owners = new ArrayList<Owner>();
        	String val = stringArgumentCaptor.getValue();
        	if(val.equalsIgnoreCase(""))
        	{
        		owners.add(new Owner());
        		owners.add(new Owner());
            	return owners;
        	}
        	else if(val.equalsIgnoreCase("Juglan")){
        		Owner owner = new Owner();
				owner.setId(5);
				owners.add(owner);
        		return owners;
        	}
        	else if(val.equalsIgnoreCase("Unknown")){
            	return owners;
			}
        	throw new RuntimeException("Invalid Operation");
        });

    
	}
	
	/*
	 * @Test void testInitCreationForm() throws Exception {
	 * mockMvc.perform(get("/owners/new")) .andExpect(status().isOk())
	 * .andExpect(model().attributeExists("owner"))
	 * .andExpect(view().name("owners/createOrUpdateOwnerForm")); }
	 */
	@Test
	void testProcessFindFormMultiRecords() throws Exception {
		mockMvc.perform(get("/owners").param("lastName",""))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("selections"))
		.andExpect(view().name("owners/ownersList"));

	}

	@Test
	void testProcessFindFormOneRecord() throws Exception {
		mockMvc.perform(get("/owners").param("lastName","Juglan"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/5"));
	}
	
	@Test
	void testProcessFindFormWithBlankObject() throws Exception {
		mockMvc.perform(get("/owners"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("selections"))
		.andExpect(view().name("owners/ownersList"));
	}
	
	@Test
	void testProcessFindFormUnknownRecord() throws Exception {
		mockMvc.perform(get("/owners").param("lastName","Unknown"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/findOwners"));
	}
	
	
	

}
