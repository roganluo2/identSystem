package com.sofosofi.identsystemwechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.sofosofi.identsystemwechat.mapper")
public class IdentSystemWechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentSystemWechatApplication.class, args);
	}

}
