package com.charly.perpetuostudio.dto.response.vacancy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class VacancyListResponse {
    private List<VacancyDetailResponse> list;
}
