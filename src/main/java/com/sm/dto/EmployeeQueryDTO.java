package com.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeQueryDTO {

    private Integer page;

    private Integer pageSize;

    private String username;

    private String name;

    private String sex;
}
