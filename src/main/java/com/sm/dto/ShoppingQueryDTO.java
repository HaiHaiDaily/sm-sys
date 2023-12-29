package com.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingQueryDTO {

    private Integer page;

    private Integer pageSize;

    private String name;

    private Long categoryId;
}
