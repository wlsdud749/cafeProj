package com.example.cafe.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocalSearchResDto {
    private int total_count;
    private List<DocumentDto> documents;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class DocumentDto {
        private String place_name;
        private String phone;
        private String address_name;
        private String road_address_name;
        private String x;
        private String y;
    }

}
