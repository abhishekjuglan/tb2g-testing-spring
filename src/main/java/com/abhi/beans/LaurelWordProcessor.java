package com.abhi.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component

public class LaurelWordProcessor implements WordProcessor{

	@Value("${Laurel.text}")
	String value;
	
	@Override
	public String speakIt() {
		return value;
	}

	
}
