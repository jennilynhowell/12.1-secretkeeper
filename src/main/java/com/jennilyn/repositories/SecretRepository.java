package com.jennilyn.repositories;

import com.jennilyn.models.Secret;
import com.jennilyn.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretRepository extends CrudRepository<Secret, Long> {
    List<Secret> findAllByUser(User user);
}
