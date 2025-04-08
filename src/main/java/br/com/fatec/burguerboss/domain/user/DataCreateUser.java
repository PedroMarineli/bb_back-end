package br.com.fatec.burguerboss.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCrypt;

public record DataCreateUser(
        @NotBlank
        String username,
        @NotNull
        String password
) {
        public DataCreateUser {
                password =  BCrypt.hashpw(password, BCrypt.gensalt());
        }
}
