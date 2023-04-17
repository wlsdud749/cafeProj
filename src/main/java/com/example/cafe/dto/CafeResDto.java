package com.example.cafe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CafeResDto {

        private Long idx;

        @JsonProperty(value = "place_name")
        private String name;
        @JsonProperty(value = "phone")
        private String number;
        @JsonProperty(value = "address_name")
        private String addr;
        @JsonProperty(value = "road_address_name")
        private String addr2;
        @JsonProperty(value = "x")
        private String xValue;
        @JsonProperty(value = "y")
        private String yValue;
        private Long user_idx;
        //private LocalDateTime open_form;
        //private LocalDateTime open_to;
    }

