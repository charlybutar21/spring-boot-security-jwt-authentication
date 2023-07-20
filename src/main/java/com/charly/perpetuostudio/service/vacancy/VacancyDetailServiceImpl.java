package com.charly.perpetuostudio.service.vacancy;

import com.charly.perpetuostudio.dto.response.vacancy.VacancyDetailResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VacancyDetailServiceImpl implements VacancyDetailService{

    private static final String VACANCY_DETAIL_API_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions/%s";

    @Override
    public VacancyDetailResponse process(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(String.format(VACANCY_DETAIL_API_URL, id), String.class);

        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, VacancyDetailResponse.class);
    }
}
