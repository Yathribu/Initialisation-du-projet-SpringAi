package com.example.prototypeai.repository;

import com.example.prototypeai.enums.AiProvider;
import com.example.prototypeai.model.AiRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiInteractionRepository extends JpaRepository<AiRequest, Long> {

    public String save(String request, AiProvider.RequestType requestType);
}
