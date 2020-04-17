package com.abhi.beans;

import org.springframework.stereotype.Component;

@Component
public class YannyWordProcessor implements WordProcessor{

	@Override
	public String speakIt() {
		return "Yanny";
	}

	
}
