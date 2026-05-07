package com.example.prototypeai.ai.repository;

import com.example.prototypeai.ai.entity.AiRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface AiRequestRepository extends JpaRepository<AiRequest, Long> {

    Integer countByUserIdAndCreatedAtAfter(Long userId, Instant afterCreatedAt);

}
