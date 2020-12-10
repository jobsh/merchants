package com.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PictureApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//            PictureServiceImpl pictureService = (PictureServiceImpl)ctx.getBean("pictureServiceImpl");
//            MultipartFile[] file = null;
//            String type = "";
//            pictureService.tiniuyunUpdate(file,type);
//
//        };
//    }


}
