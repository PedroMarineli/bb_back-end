package br.com.fatec.burguerboss;

import br.com.fatec.burguerboss.principal.Principal;
import br.com.fatec.burguerboss.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BurguerbossApplication implements CommandLineRunner {
	@Autowired
	private TableRepository repositorio;

	public static void main(String[] args){
		SpringApplication.run(BurguerbossApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.showMenu();
	}
}
