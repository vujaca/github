package com.ftninformatika.jwd.modul3.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Korisnik;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Optional<Korisnik> findFirstByKorisnickoIme(String korisnickoIme);

    Optional<Korisnik> findFirstByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);
}
