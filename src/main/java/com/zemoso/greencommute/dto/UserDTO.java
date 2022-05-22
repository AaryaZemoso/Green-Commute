package com.zemoso.greencommute.dto;

import com.zemoso.greencommute.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO implements Serializable {
    private final int id;
    private final String name;

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName()
        );
    }
}
