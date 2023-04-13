package com.ftninformatika.jwd.modul3.test.web.dto;

import javax.validation.constraints.Positive;

public class UdomljavanjeDTO {

	@Positive
	private Long id;
	
	private Long ljubimacId;
	
	private String ljubimacNaziv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLjubimacId() {
		return ljubimacId;
	}

	public void setLjubimacId(Long ljubimacId) {
		this.ljubimacId = ljubimacId;
	}

	public String getLjubimacNaziv() {
		return ljubimacNaziv;
	}

	public void setLjubimacNaziv(String ljubimacNaziv) {
		this.ljubimacNaziv = ljubimacNaziv;
	}
	
	
	
	
}
