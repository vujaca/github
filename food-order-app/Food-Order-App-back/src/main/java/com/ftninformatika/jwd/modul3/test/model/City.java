//package com.ftninformatika.jwd.modul3.test.model;
//
//import java.util.Objects;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//public class City {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@Column(nullable = false, unique = true)
//	private String name;
//
//	@Column(nullable = false, unique = true, name = "zip_code")
//	private String zipCode;
//
//	public City() {
//		super();
//	}
//
//	public City(Long id, String name, String zipCode) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.zipCode = zipCode;
//	}
//
//	
//	public Long getId() {
//		return id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getZipCode() {
//		return zipCode;
//	}
//
//	public void setZipCode(String zipCode) {
//		this.zipCode = zipCode;
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
//		City other = (City) obj;
//		return Objects.equals(id, other.id);
//	}
//
//	@Override
//	public String toString() {
//		return "City [ name=" + name + ", zipCode=" + zipCode + "]";
//	}
//	
//	
//	
//}
