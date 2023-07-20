package com.charly.perpetuostudio.controller.vacancy;

import com.charly.perpetuostudio.dto.response.vacancy.VacancyDetailResponse;
import com.charly.perpetuostudio.service.vacancy.VacancyDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacancyDetailController {

    @Autowired
    VacancyDetailService vacancyDetailService;

    @GetMapping("/api/vacancies/{id}")
    public ResponseEntity<VacancyDetailResponse> getVacancy(@PathVariable("id") String id) {
        VacancyDetailResponse response = vacancyDetailService.process(id);

        return ResponseEntity.ok(response);
    }
}
