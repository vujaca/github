package com.ftninformatika.jwd.modul3.test.web.dto;

import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;

@Component
public class UtakmicaDTO {

	@Positive
	private Long id;
	
	private ReprezentacijaDTO reprezentacijaA;
	
	private ReprezentacijaDTO reprezentacijaB;
	
	private int brojGolovaA;
	
	private int brojGolovaB;
	
	

	
	
	public ReprezentacijaDTO getReprezentacijaA() {
		return reprezentacijaA;
	}

	public void setReprezentacijaA(ReprezentacijaDTO reprezentacijaA) {
		this.reprezentacijaA = reprezentacijaA;
	}

	public ReprezentacijaDTO getReprezentacijaB() {
		return reprezentacijaB;
	}

	public void setReprezentacijaB(ReprezentacijaDTO reprezentacijaB) {
		this.reprezentacijaB = reprezentacijaB;
	}

	public int getBrojGolovaA() {
		return brojGolovaA;
	}

	public void setBrojGolovaA(int brojGolovaA) {
		this.brojGolovaA = brojGolovaA;
	}

	public int getBrojGolovaB() {
		return brojGolovaB;
	}

	public void setBrojGolovaB(int brojGolovaB) {
		this.brojGolovaB = brojGolovaB;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
