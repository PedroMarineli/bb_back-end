package br.com.fatec.burguerboss.controller;

import br.com.fatec.burguerboss.domain.user.UserData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserData userdata) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userdata.username(),
                        userdata.password()
                )
        );

        return ResponseEntity.ok().build();
    }
}
