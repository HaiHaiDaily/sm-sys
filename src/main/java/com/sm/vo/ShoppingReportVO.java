package com.sm.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingReportVO {

    private String dateList;

    private String totalShoppingList;

    private String newShoppingList;
}
