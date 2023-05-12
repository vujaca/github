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
//public class Street {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@Column(nullable = false)
//	private String name;
//	
//	@ManyToOne
//	private City city;
//
//	public Street(Long id,String name, City city) {
//		super();
//		this.id= id;
//		this.name = name;
//		this.city = city;
//	}
//
//	public Street() {
//		super();
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
//	public City getCity() {
//		return city;
//	}
//
//	public void setCity(City city) {
//		this.city = city;
//	}
//
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
//		Street other = (Street) obj;
//		return Objects.equals(id, other.id);
//	}
//
//	@Override
//	public String toString() {
//		return "Street [name=" + name + ", city=" + city + "]";
//	}
//	
//	
//}
