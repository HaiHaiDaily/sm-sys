package com.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingInsertDTO {

    private Long id;

    private String name;

    private Long categoryId;

    private Double price;

    private String image;

    private String description;

    private Integer number;

    private String employeeName;

}
