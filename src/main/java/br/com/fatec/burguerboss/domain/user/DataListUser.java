package br.com.fatec.burguerboss.domain.user;

public record DataListUser(
        Integer id,
        String username,
        String password,
        UserRole role
) {
    public DataListUser (User user){
        this(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
}
