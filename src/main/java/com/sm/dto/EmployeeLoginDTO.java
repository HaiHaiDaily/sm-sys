package com.sm.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLoginDTO {
    @Pattern(regexp = "[a-zA-Z0-9]{5,10}",message = "用户名格式错误：[a-zA-Z0-9]{5,10}")
    private String username;
    @Pattern(regexp = "[a-zA-Z0-9]{6,10}" ,message = "密码格式错误：[a-zA-Z0-9]{6,10}")
    private String password;
}
