package br.com.fatec.burguerboss.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCrypt;

public record DataUpdateUser(
        @NotNull
        Integer id,
        @NotBlank
        String username,
        @NotNull
        String password
) {
    public DataUpdateUser {
        password =  BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
