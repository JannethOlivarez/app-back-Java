package com.appbackJava.springSecurity.utils;

import com.appbackJava.springSecurity.model.Authority;
import com.appbackJava.springSecurity.model.User;
import com.appbackJava.springSecurity.repositories.AuthorityRepository;
import com.appbackJava.springSecurity.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.List;
@Component
public class Runer  implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public Runer(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.authorityRepository.count() == 0) {
            this.authorityRepository.saveAll(List.of(
                    new Authority(AuthorityName.ADMIN),
                    new Authority(AuthorityName.READ),
                    new Authority(AuthorityName.WRITE)
            ));
        }
        if (this.userRepository.count() == 0) {
            var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            this.userRepository.saveAll(List.of(
                            new User("JannethAdmin", encoders.encode("admin"), List.of(this.authorityRepository.findByName(AuthorityName.ADMIN).get())),
                            new User("JannethLectura", "admin", List.of(this.authorityRepository.findByName(AuthorityName.READ).get())),
                            new User("JannethEscritura", "admin", List.of(this.authorityRepository.findByName(AuthorityName.WRITE).get()))
                    )
            );

        }
    }
}
