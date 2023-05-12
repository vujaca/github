package com.ftninformatika.jwd.modul3.test.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
//	@ManyToMany
//	@JoinTable(name= "restaurant_city", joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"),
//	inverseJoinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"))
//	private Set<City> cities= new HashSet<City>();
	
	@Column
	private String contact;
	
	@Column(name = "founding_year")
	private int foundingYear;
	
//	@ManyToMany
//	@JoinTable(name= "restaurant_adress", joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"),
//	inverseJoinColumns = @JoinColumn(name = "adress_id", referencedColumnName = "id"))
//	private Set<Adress> adresses= new HashSet<Adress>();
	
	@Column(name = "about_us")
	private String aboutUs;
	
	@Column(nullable = false)
	private String location;
	
	@OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Meal>meals = new HashSet<Meal>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Set<City> getCities() {
//		return cities;
//	}
//
//	public void setCities(Set<City> cities) {
//		this.cities = cities;
//	}

//	public Set<Adress> getAdresses() {
//		return adresses;
//	}
//
//	public void setAdresses(Set<Adress> adresses) {
//		this.adresses = adresses;
//	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getFoundingYear() {
		return foundingYear;
	}

	public void setFoundingYear(int foundingYear) {
		this.foundingYear = foundingYear;
	}

	public Long getId() {
		return id;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}
	

	public Restaurant(Long id, String name, String contact, int foundingYear, String aboutUs, String location,
			Set<Meal> meals) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.foundingYear = foundingYear;
		this.aboutUs = aboutUs;
		this.location = location;
		this.meals = meals;
	}

	public Restaurant() {
		super();
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
		Restaurant other = (Restaurant) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", contact=" + contact + ", foundingYear=" + foundingYear
				+ "]";
	}	
	
}
