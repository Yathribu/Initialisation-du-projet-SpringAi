package com.example.prototypeai.ai.repository;

import com.example.prototypeai.enums.AiProvider;
import com.example.prototypeai.ai.model.AiRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiInteractionRepository extends JpaRepository<AiRequest, Long> {

    String save(String request, AiProvider.RequestType requestType);
}
