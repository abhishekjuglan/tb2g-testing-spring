package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

	@Mock(lenient=true)
	PetRepository petRepository;
	
	@InjectMocks
	ClinicServiceImpl clinicService;
	
	//@Test
	void testFindPetTypes() {
		//given
		when(petRepository.findPetTypes()).thenReturn(anyListOf(PetType.class));
		//when
		Collection<PetType> types = clinicService.findPetTypes();
		//then
		assertTrue(0==types.size());
	}

}
