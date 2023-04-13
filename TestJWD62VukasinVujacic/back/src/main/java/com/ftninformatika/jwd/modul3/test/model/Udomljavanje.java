package com.ftninformatika.jwd.modul3.test.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Udomljavanje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "datum_i_vreme")
	private LocalDateTime datumIVreme;
	
	@OneToOne
	private Ljubimac ljubimac;

	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public Ljubimac getLjubimac() {
		return ljubimac;
	}

	public void setLjubimac(Ljubimac ljubimac) {
		this.ljubimac = ljubimac;
	}

	public Long getId() {
		return id;
	}

	public Udomljavanje() {
		super();
	}

	public Udomljavanje(Long id, LocalDateTime datumIVreme, Ljubimac ljubimac) {
		super();
		this.id = id;
		this.datumIVreme = datumIVreme;
		this.ljubimac = ljubimac;
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
		Udomljavanje other = (Udomljavanje) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Udomljavanje [id=" + id + ", datumIVreme=" + datumIVreme + "]";
	}
	
	
}
