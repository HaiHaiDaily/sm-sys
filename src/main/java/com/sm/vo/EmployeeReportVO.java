package com.sm.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReportVO {

    //日期，以逗号分隔，例如：2022-10-01,2022-10-02,2022-10-03
    private String dateList;

    //员工总数，以逗号分隔，例如：200,210,220
    private String totalEmployeeList;

    //新增员工，以逗号分隔，例如：20,21,10
    private String newEmployeeList;
}
