package com.example.prototypeai.ai.repository;

import com.example.prototypeai.ai.entity.AiRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface AiInteractionRepository extends JpaRepository<AiRequest, Long> {

    List<AiRequest> findByUserIdAndCreatedAtAfter(Long userId, Instant afterCreatedAt);

}
