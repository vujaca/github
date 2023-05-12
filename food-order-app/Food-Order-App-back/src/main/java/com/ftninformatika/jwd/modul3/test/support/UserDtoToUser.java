package com.ftninformatika.jwd.modul3.test.support;

import com.ftninformatika.jwd.modul3.test.model.User;
import com.ftninformatika.jwd.modul3.test.service.UserService;
import com.ftninformatika.jwd.modul3.test.web.dto.UserDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Component
public class UserDtoToUser implements Converter<UserDTO, User> {

    @Autowired
    private UserService korisnikService;

    @Override
    public User convert(UserDTO korisnikDTO) {
        User entity = null;

        if(korisnikDTO.getId() == null) {
            entity = new User();
        }else {
            Optional<User> korisnikOptional = korisnikService.findOne(korisnikDTO.getId());
            if(korisnikOptional.isPresent()){
                entity = korisnikOptional.get();
            }
        }

        if(entity != null) {
            entity.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
            entity.seteMail(korisnikDTO.geteMail());
            entity.setIme(korisnikDTO.getIme());
            entity.setPrezime(korisnikDTO.getPrezime());
        }

        return entity;
    }

}
