package com.ghy.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesListQuery {

    private Integer page;

    private Integer limit;

        private Long employeeId;

        /**
         *
         */
        /**
         *
         */
        private String employeeName;

        /**
         *
         */
        private String contactNumber;

        /**
         *
         */
        private Integer isManager;

        /**
         *
         */
        private Integer departmentId;

        private String departmentName;
}
