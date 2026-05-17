package com.example.prototypeai.admin.controller;

import com.example.prototypeai.admin.dto.AiRequestAdminDto;
import com.example.prototypeai.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/userstats")
    public ResponseEntity<Page<AiRequestAdminDto>> getUserStats() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAiUsersStats());
    }






}
