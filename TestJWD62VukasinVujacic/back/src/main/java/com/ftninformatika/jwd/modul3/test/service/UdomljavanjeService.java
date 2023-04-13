package com.ftninformatika.jwd.modul3.test.service;

import com.ftninformatika.jwd.modul3.test.model.Udomljavanje;

public interface UdomljavanjeService {

	Udomljavanje findOne(Long id);
	
	Udomljavanje save(Udomljavanje udomljavanje);
}
