package com.sm.service.impl;

import com.sm.mapper.EmployeeMapper;
import com.sm.mapper.ShoppingMapper;
import com.sm.service.ReportService;
import com.sm.vo.EmployeeReportVO;
import com.sm.vo.ShoppingReportVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ShoppingMapper shoppingMapper;

    /**
     * 根据起始和结束日期获得员工数量数据
     * @param begin
     * @param end
     * @return
     */
    public EmployeeReportVO getEmployeeStatistics(LocalDate begin, LocalDate end) {
        //存放begin-end范围内每天的日期
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while(!begin.equals(end)){
            //日期计算，计算指定日期的后一天对应的日期
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //存放每天的员工总量
        List<Integer> employeeList = new ArrayList<>();
        //存放每天的新增员工数量
        List<Integer> newEmployeeList = new ArrayList<>();

        for (LocalDate date : dateList) {
            //获取当天的LocalDateTime类型时间
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Map map = new HashMap();
            map.put("end",endTime);
            //获取员工总量
            Integer totalEmployee = employeeMapper.countByMap(map);

            map.put("begin",beginTime);
            //获取新增员工数量
            Integer newEmployee = employeeMapper.countByMap(map);

            //放到集合中
            employeeList.add(totalEmployee);
            newEmployeeList.add(newEmployee);
        }

        //数据封装到VO
        EmployeeReportVO employeeReportVO = EmployeeReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .newEmployeeList(StringUtils.join(newEmployeeList, ","))
                .totalEmployeeList(StringUtils.join(employeeList, ","))
                .build();

        return employeeReportVO;
    }

    /**
     * 根据起始和结束日期获得商品数量数据
     * @param begin
     * @param end
     * @return
     */
    public ShoppingReportVO getShoppingStatistics(LocalDate begin, LocalDate end) {
        //存放begin-end范围内每天的日期
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while(!begin.equals(end)){
            //日期计算，计算指定日期的后一天对应的日期
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //存放每天的总量
        List<Integer> shoppingList = new ArrayList<>();
        //存放每天的新增数量
        List<Integer> newShoppingList = new ArrayList<>();

        for (LocalDate date : dateList) {
            //获取当天的LocalDateTime类型时间
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Map map = new HashMap();
            map.put("end",endTime);
            //获取总量
            Integer totalShopping = shoppingMapper.countByMap(map);

            map.put("begin",beginTime);
            //获取新增数量
            Integer newShopping = shoppingMapper.countByMap(map);

            //放到集合中
            shoppingList.add(totalShopping);
            newShoppingList.add(newShopping);
        }

        //数据封装到VO
        ShoppingReportVO shoppingReportVO = ShoppingReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .newShoppingList(StringUtils.join(newShoppingList, ","))
                .totalShoppingList(StringUtils.join(shoppingList, ","))
                .build();

        return shoppingReportVO;
    }
}
