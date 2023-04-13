package com.ftninformatika.jwd.modul3.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kategorija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String naziv;
	
	@OneToMany(mappedBy = "kategorija", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Ljubimac>ljubimci = new ArrayList<Ljubimac>();

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Ljubimac> getLjubimci() {
		return ljubimci;
	}

	public void setLjubimci(List<Ljubimac> ljubimci) {
		this.ljubimci = ljubimci;
	}

	public Long getId() {
		return id;
	}

	public Kategorija(Long id, String naziv, List<Ljubimac>ljubimci) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ljubimci = ljubimci;
	}

	
	public Kategorija() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kategorija other = (Kategorija) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "Kategorija [id=" + id + ", naziv=" + naziv + "]";
	}
	
	
}
