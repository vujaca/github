package com.ftninformatika.jwd.modul3.test.web.dto;

import javax.validation.constraints.Positive;

public class KategorijaDTO {

	@Positive
	private Long id;
	
	private String naziv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
}
