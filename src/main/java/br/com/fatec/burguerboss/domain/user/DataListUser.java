package br.com.fatec.burguerboss.domain.user;

public record DataListUser(
        String username,
        String password
) {
    public DataListUser (User user){
        this(
                user.getUsername(),
                user.getPassword()
        );
    }
}
