package br.com.fatec.burguerboss;

import br.com.fatec.burguerboss.domain.user.User;
import br.com.fatec.burguerboss.domain.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BurguerbossApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurguerbossApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeData(UserRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				User user = new User("admin", "root");
				repository.save(user);
			}
		};
	}
}
