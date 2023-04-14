package com.example.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long idx;

    private String id;
    private String passwd;
    private String name;
    private String nick;
    private String addr;
    private int owner;
}
