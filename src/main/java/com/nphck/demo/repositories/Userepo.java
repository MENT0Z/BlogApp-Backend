package com.nphck.demo.repositories;

import com.nphck.demo.model_entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userepo extends JpaRepository<User,Integer> {

}
