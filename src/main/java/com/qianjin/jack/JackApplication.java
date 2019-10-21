package com.qianjin.jack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.qianjin.jack.mapper")
@EntityScan("com.qianjin.jack.domain")
@EnableTransactionManagement
@EnableWebMvc
public class JackApplication {

	public static void main(String[] args) {
		SpringApplication.run(JackApplication.class, args);
	}

}
