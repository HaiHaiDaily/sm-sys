package com.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private String rePassword;
}
