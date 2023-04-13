package com.ftninformatika.jwd.modul3.test.web.dto;


import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ReprezentacijaDTO {

	@Positive
	private Long id;
	
	@NotEmpty
	private String naziv;
	
	@Size(min = 3, max = 3)
	private String skraceniNaziv;
	
	private Map<Long,String>igraci = new HashMap<>();
	
	

	public Map<Long, String> getIgraci() {
		return igraci;
	}

	public void setIgraci(Map<Long, String> igraci) {
		this.igraci = igraci;
	}

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

	public String getSkraceniNaziv() {
		return skraceniNaziv;
	}

	public void setSkraceniNaziv(String skraceniNaziv) {
		this.skraceniNaziv = skraceniNaziv;
	}
	
	
}
