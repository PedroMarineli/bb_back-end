package br.com.fatec.burguerboss;

import br.com.fatec.burguerboss.domain.menu.Menu;
import br.com.fatec.burguerboss.domain.menu.MenuRepository;
import br.com.fatec.burguerboss.domain.order.OrderRepository;
import br.com.fatec.burguerboss.domain.user.User;
import br.com.fatec.burguerboss.domain.user.UserRepository;
import br.com.fatec.burguerboss.domain.user.UserRole;
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
	public CommandLineRunner initializeData(UserRepository repository, MenuRepository menuRepository) {
		return args -> {
			if (repository.count() == 0) {
				User user = new User("admin", "root", UserRole.ADMIN);
				repository.save(user);
			}
			if (menuRepository.count() == 0) {
				Menu menu = new Menu();
				menuRepository.save(menu);
			}
		};
	}
}
