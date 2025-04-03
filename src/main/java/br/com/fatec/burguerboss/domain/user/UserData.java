package br.com.fatec.burguerboss.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserData(
        String username,
        String password
) {
}
