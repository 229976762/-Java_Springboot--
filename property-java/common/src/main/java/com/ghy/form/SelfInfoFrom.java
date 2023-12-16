package com.ghy.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfInfoFrom {
    private Long employeeId;
    private String employeeName;
    private String contactNumber;
}
