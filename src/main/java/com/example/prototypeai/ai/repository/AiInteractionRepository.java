package com.example.prototypeai.ai.repository;

import com.example.prototypeai.ai.entity.AiRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiInteractionRepository extends JpaRepository<AiRequest, Long> {

}
