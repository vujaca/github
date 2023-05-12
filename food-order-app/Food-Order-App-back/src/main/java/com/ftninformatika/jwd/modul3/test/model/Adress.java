//package com.ftninformatika.jwd.modul3.test.model;
//
//import java.util.Objects;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class Adress {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@ManyToOne
//	private Street street;
//	
//	@Column
//	private String number;
//
//	public Adress(Long id,  Street street, String number) {
//		super();
//		this.id = id;
//		this.street = street;
//		this.number = number;
//	}
//
//	public Adress() {
//		super();
//	}
//
//	public Street getStreet() {
//		return street;
//	}
//
//	public void setStreet(Street street) {
//		this.street = street;
//	}
//
//	public String getNumber() {
//		return number;
//	}
//
//	public void setNumber(String number) {
//		this.number = number;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Adress other = (Adress) obj;
//		return Objects.equals(id, other.id);
//	}
//
//	@Override
//	public String toString() {
//		return "Adress [id=" + id + ", street=" + street + ", number=" + number + "]";
//	}
//	
//}
