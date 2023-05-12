package com.ftninformatika.jwd.modul3.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByKorisnickoIme(String korisnickoIme);

    Optional<User> findFirstByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);
}
