package com.abhi.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HearingInterpreter {

	@Autowired
	WordProcessor wp;

	public String speakIt() {
		return wp.speakIt();
	}
}
