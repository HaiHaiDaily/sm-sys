package com.sm.service;

import com.sm.vo.EmployeeReportVO;
import com.sm.vo.ShoppingReportVO;

import java.time.LocalDate;

public interface ReportService {

    /**
     * 根据起始和结束日期获得员工数量数据
     * @param begin
     * @param end
     * @return
     */
    EmployeeReportVO getEmployeeStatistics(LocalDate begin, LocalDate end);

    /**
     * 根据起始和结束日期获得商品数量数据
     * @param begin
     * @param end
     * @return
     */
    ShoppingReportVO getShoppingStatistics(LocalDate begin, LocalDate end);
}
