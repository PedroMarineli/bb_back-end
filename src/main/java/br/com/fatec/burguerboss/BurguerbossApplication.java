package br.com.fatec.burguerboss;

import br.com.fatec.burguerboss.domain.desk.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BurguerbossApplication{
	@Autowired
	private DeskRepository repository;

	public static void main(String[] args){
		SpringApplication.run(BurguerbossApplication.class, args);
	}

}
