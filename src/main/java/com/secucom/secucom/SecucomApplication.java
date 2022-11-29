package com.secucom.secucom;

import com.secucom.secucom.Model.AppCollaborateur;
import com.secucom.secucom.Model.AppRole;
import com.secucom.secucom.Model.Erole;
import com.secucom.secucom.Service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecucomApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecucomApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner start(AccountService accountService){
      return args -> {
          accountService.addNewRole(new AppRole(null, Erole.USER));
          accountService.addNewRole(new AppRole(null, Erole.ADMIN));

          accountService.addNewCollab(new AppCollaborateur(null,"Ablo","1234","Diallo","Abdoulaye",new ArrayList<>()));
          accountService.addNewCollab(new AppCollaborateur(null,"user","1234","user","user",new ArrayList<>()));
          accountService.addNewCollab(new AppCollaborateur(null,"user2","1234","user2","user2",new ArrayList<>()));
          accountService.addNewCollab(new AppCollaborateur(null,"user3","1234","user3","user3",new ArrayList<>()));
          accountService.addNewCollab(new AppCollaborateur(null,"user4","1234","user4","user4",new ArrayList<>()));

          accountService.addRoleToCollab("Ablo", "ADMIN");
          accountService.addRoleToCollab("user","USER");
          accountService.addRoleToCollab("user2","USER");
          accountService.addRoleToCollab("user3","USER");
          accountService.addRoleToCollab("user4","USER");
      };
    };
}
