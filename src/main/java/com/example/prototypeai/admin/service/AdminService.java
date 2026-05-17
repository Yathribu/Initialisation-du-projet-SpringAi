package com.example.prototypeai.admin.service;

import com.example.prototypeai.admin.dto.AiRequestAdminDto;
import org.springframework.data.domain.Page;

public interface AdminService {

    Page<AiRequestAdminDto> getAiUsersStats();
}
