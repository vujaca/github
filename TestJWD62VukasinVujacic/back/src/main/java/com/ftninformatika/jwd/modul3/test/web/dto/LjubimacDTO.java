package com.ftninformatika.jwd.modul3.test.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


public class LjubimacDTO {

	@Positive
	private Long id;
	
	@NotBlank
	private String ime;
	
	private int starost;
	
	private boolean vakcinisan;
	
	private String pol;
	
	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}
	
	@Positive
	private double tezina;
	
	private String opis;
	
	private Long kategorijaId;
	
	private String kategorijaNaziv;

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

	public int getStarost() {
		return starost;
	}

	public void setStarost(int starost) {
		this.starost = starost;
	}

	public boolean isVakcinisan() {
		return vakcinisan;
	}

	public void setVakcinisan(boolean vakcinisan) {
		this.vakcinisan = vakcinisan;
	}


	public double getTezina() {
		return tezina;
	}

	public void setTezina(double tezina) {
		this.tezina = tezina;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Long getKategorijaId() {
		return kategorijaId;
	}

	public void setKategorijaId(Long kategorijaId) {
		this.kategorijaId = kategorijaId;
	}

	public String getKategorijaNaziv() {
		return kategorijaNaziv;
	}

	public void setKategorijaNaziv(String kategorijaNaziv) {
		this.kategorijaNaziv = kategorijaNaziv;
	}
	
	
}
