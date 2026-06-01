package com.example.prototypeai.admin.service;

import com.example.prototypeai.admin.dto.AiRequestAdminDto;
import com.example.prototypeai.ai.repository.AiRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AiRequestRepository aiRequestRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Page<AiRequestAdminDto> getAiUsersStats() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        return aiRequestRepository.getAllRequest(pageRequest);
    }

}
