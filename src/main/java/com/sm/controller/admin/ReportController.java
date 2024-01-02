package com.sm.controller.admin;

import com.sm.result.Result;
import com.sm.service.ReportService;
import com.sm.vo.EmployeeReportVO;
import com.sm.vo.ShoppingReportVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 员工统计
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/employeeStatistics")
    public Result<EmployeeReportVO> employeeStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("员工数据统计：{},{}",begin,end);
        EmployeeReportVO employeeReportVO = reportService.getEmployeeStatistics(begin,end);
        return Result.success(employeeReportVO);
    }

    /**
     * 商品数据统计
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/shoppingStatistics")
    public Result<ShoppingReportVO> shoppingStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("商品数据统计：{},{}",begin,end);
        ShoppingReportVO shoppingReportVO = reportService.getShoppingStatistics(begin,end);
        return Result.success(shoppingReportVO);
    }
}
