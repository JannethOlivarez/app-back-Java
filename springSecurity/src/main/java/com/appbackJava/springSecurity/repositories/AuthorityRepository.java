package com.appbackJava.springSecurity.repositories;

import com.appbackJava.springSecurity.model.Authority;
import com.appbackJava.springSecurity.utils.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Optional<Authority> findByName(AuthorityName name);
}
