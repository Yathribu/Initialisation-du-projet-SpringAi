package com.example.prototypeai.user.repository;

import com.example.prototypeai.user.entity.AiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IAiUserRepository extends JpaRepository<AiUser, Long> {

    Optional<AiUser> findAiUserByEmail(String email);

    Optional<AiUser> findAiUserByName(String username);

}
