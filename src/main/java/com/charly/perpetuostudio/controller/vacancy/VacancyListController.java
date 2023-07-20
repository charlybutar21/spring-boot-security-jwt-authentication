package com.charly.perpetuostudio.controller.vacancy;

import com.charly.perpetuostudio.dto.response.vacancy.VacancyListResponse;
import com.charly.perpetuostudio.service.vacancy.VacancyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacancyListController {

    @Autowired
    VacancyListService vacancyListService;

    @GetMapping("/api/vacancies")
    public ResponseEntity<VacancyListResponse> getVacancies() {
        VacancyListResponse response = vacancyListService.process();

        return ResponseEntity.ok(response);
    }
}
