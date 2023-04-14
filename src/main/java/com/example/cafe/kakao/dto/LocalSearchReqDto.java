package com.example.cafe.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LocalSearchReqDto {
    private String query;
    @Builder.Default
    private int size = 10;


    public MultiValueMap<String, String> toMultiValueMap() {
        var map = new LinkedMultiValueMap<String, String>();
        map.add("query", query);
        map.add("display", String.valueOf(size));


        return map;
    }
}
