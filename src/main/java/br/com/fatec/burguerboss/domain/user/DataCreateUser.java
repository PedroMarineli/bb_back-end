package br.com.fatec.burguerboss.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCrypt;

public record DataCreateUser(
        @NotBlank(message = "O nome de usuário não pode estar em branco.")
        String username,

        @NotNull(message = "A senha não pode ser nula.")
        String password,

        @NotNull(message = "O cargo não pode estar em branco.")
        UserRole role
) {
        public DataCreateUser {
                password =  BCrypt.hashpw(password, BCrypt.gensalt());
        }
}
