package com.solera.registers;

import com.solera.registers.entity.Register;
import com.solera.registers.repository.RegisterRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.stream.Stream;

@SpringBootApplication
public class RegistersApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistersApplication.class, args);
	}

	@Bean
	ApplicationRunner init(RegisterRepository repository){
		String[][] data = {
				{"Wallet", "1000"},
				{"Savings", "5000"},
				{"Insurance policy", "0"},
				{"Food expenses", "0"}
		};

		for(Register register: repository.findAll()){
			if(register != null) return null;
		}

		return args -> {
			Stream.of(data).forEach(s -> {
				try{
					Register register = new Register(s[0], Integer.parseInt(s[1]));
					repository.save(register);
				}catch (NumberFormatException e){
					e.printStackTrace();
				}
			});
			repository.findAll().forEach(System.out::println);
		};
	}

}
