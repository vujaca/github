package com.ftninformatika.jwd.modul3.test.model;

import javax.persistence.*;

import com.ftninformatika.jwd.modul3.test.enumeration.UserRole;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column( unique = true, nullable = false)
    private String eMail;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole uloga;

    public User(){

    }

    public User(Long id, String username, String eMail, String name, String surname, String password, UserRole uloga) {
		super();
		this.id = id;
		this.username = username;
		this.eMail = eMail;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.uloga = uloga;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUloga() {
        return uloga;
    }

    public void setUloga(UserRole uloga) {
        this.uloga = uloga;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Korisnik [id=" + id + ", ime=" + name + ", prezime=" + surname + "]";
    }

}
