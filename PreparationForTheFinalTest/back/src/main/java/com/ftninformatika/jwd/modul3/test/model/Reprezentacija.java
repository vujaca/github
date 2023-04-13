package com.ftninformatika.jwd.modul3.test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Reprezentacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String naziv;
	
	@Column(nullable = false, unique = true, length = 3, name = "skraceni_naziv")	
	private String skraceniNaziv;
	
	@OneToMany(mappedBy = "reprezentacija", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Igrac>igraci = new ArrayList<Igrac>();

	public Reprezentacija(Long id, String naziv, String skraceniNaziv, List<Igrac> igraci) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.skraceniNaziv = skraceniNaziv;
		this.igraci = igraci;
	}

	public Reprezentacija() {
		super();
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

	public List<Igrac> getIgraci() {
		return igraci;
	}

	public void setIgraci(List<Igrac> igraci) {
		this.igraci = igraci;
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
		Reprezentacija other = (Reprezentacija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Reprezentacija [id=" + id + ", naziv=" + naziv + ", skraceniNaziv=" + skraceniNaziv +  "]";
	}
	
	
}
