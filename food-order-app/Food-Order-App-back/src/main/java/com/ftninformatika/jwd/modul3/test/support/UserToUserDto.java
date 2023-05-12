package com.ftninformatika.jwd.modul3.test.support;
import com.ftninformatika.jwd.modul3.test.model.User;
import com.ftninformatika.jwd.modul3.test.web.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToUserDto implements Converter<User, UserDTO>{

    @Override
    public UserDTO convert(User korisnik) {
        UserDTO korisnikDTO = new UserDTO();

        korisnikDTO.setId(korisnik.getId());
        korisnikDTO.seteMail(korisnik.geteMail());
        korisnikDTO.setIme(korisnik.getIme());
        korisnikDTO.setPrezime(korisnik.getPrezime());
        korisnikDTO.setKorisnickoIme(korisnik.getKorisnickoIme());

        return korisnikDTO;
    }

    public List<UserDTO> convert(List<User> korisnici){
        List<UserDTO> korisnikDTOS = new ArrayList<>();

        for(User k : korisnici) {
            UserDTO dto = convert(k);
            korisnikDTOS.add(dto);
        }

        return korisnikDTOS;
    }
}
