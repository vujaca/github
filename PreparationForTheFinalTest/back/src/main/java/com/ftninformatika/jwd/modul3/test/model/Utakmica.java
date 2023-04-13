package com.ftninformatika.jwd.modul3.test.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Utakmica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	private Reprezentacija reprezentacijaA;
	
	@ManyToOne
	private Reprezentacija reprezentacijaB;
	
	@Column(name = "broj_golova_a")
	private int brojGolovaA;
	
	@Column(name = "broj_golova_b")
	private int brojGolovaB;

	public Utakmica(Long id, Reprezentacija reprezentacijaA, Reprezentacija reprezentacijaB, int brojGolovaA,
			int brojGolovaB) {
		super();
		this.id = id;
		this.reprezentacijaA = reprezentacijaA;
		this.reprezentacijaB = reprezentacijaB;
		this.brojGolovaA = brojGolovaA;
		this.brojGolovaB = brojGolovaB;
	}

	public Utakmica() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reprezentacija getReprezentacijaA() {
		return reprezentacijaA;
	}

	public void setReprezentacijaA(Reprezentacija reprezentacijaA) {
		this.reprezentacijaA = reprezentacijaA;
	}

	public Reprezentacija getReprezentacijaB() {
		return reprezentacijaB;
	}

	public void setReprezentacijaB(Reprezentacija reprezentacijaB) {
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
		Utakmica other = (Utakmica) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Utakmica [id=" + id + ", brojGolovaA=" + brojGolovaA + ", brojGolovaB=" + brojGolovaB + "]";
	}
	
	
	
	
}
