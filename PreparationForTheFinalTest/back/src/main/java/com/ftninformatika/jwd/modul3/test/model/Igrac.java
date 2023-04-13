package com.ftninformatika.jwd.modul3.test.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;

@Entity
public class Igrac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String ime;
	
	@Column(nullable = false)
	private String prezime;
	
	@Column(name = "postignuti_golovi")
	@Positive
	private int postignutiGolovi;
	
	@ManyToOne
	private Reprezentacija reprezentacija;

	public Igrac(Long id, String ime, String prezime, @Positive int postignutiGolovi) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.postignutiGolovi = postignutiGolovi;
	}

	public Igrac() {
		super();
	}

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

	
	
	public Reprezentacija getReprezentacija() {
		return reprezentacija;
	}

	public void setReprezentacija(Reprezentacija reprezentacija) {
		this.reprezentacija = reprezentacija;
	}

	@Override
	public String toString() {
		return "Igrac [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", postignutiGolovi=" + postignutiGolovi
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Igrac other = (Igrac) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
