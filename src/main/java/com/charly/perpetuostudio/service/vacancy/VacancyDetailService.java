package com.charly.perpetuostudio.service.vacancy;

import com.charly.perpetuostudio.dto.response.vacancy.VacancyDetailResponse;
import com.charly.perpetuostudio.dto.response.vacancy.VacancyListResponse;

public interface VacancyDetailService {

    VacancyDetailResponse process(String id);
}
