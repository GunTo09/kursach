package ru.uvarov.ystu.kursach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@SpringBootApplication
public class KursachApplication {

	public static void main(String[] args) {
		SpringApplication.run(KursachApplication.class, args);
	}

}
