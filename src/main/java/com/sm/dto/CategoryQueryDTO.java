package com.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryQueryDTO {

    private Integer page;

    private Integer pageSize;

    private String name;

    private String employeeName;

    private Integer status;

}
