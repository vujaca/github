package com.ftninformatika.jwd.modul3.test.web.dto;

import javax.validation.constraints.Positive;

public class IgracDTO {

	@Positive
	private Long id;
	
	
	private String ime;
	
	private String prezime;
	
	private int postignutiGolovi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getPostignutiGolovi() {
		return postignutiGolovi;
	}

	public void setPostignutiGolovi(int postignutiGolovi) {
		this.postignutiGolovi = postignutiGolovi;
	}
	
	
	
	
}
