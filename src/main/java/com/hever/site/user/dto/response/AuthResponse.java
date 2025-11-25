package com.hever.site.user.dto.response;

import com.hever.site.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private Long id;
    private String name;
    private String email;
    private String message;

    // constructor to convert userentity to authresponse
    public AuthResponse(UserEntity user, String message){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.message = message;
    }
}
