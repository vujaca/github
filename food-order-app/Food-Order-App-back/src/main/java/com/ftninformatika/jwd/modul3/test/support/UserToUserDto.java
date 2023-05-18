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
    public UserDTO convert(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.seteMail(user.geteMail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setUsername(user.getUsername());

        return dto;
    }

    public List<UserDTO> convert(List<User> users){
        List<UserDTO> korisnikDTOS = new ArrayList<>();

        for(User u : users) {
            UserDTO dto = convert(u);
            korisnikDTOS.add(dto);
        }

        return korisnikDTOS;
    }
}
