package com.ghy.server.response;

import com.ghy.form.EmployeesListQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployessQueryResponse {
    private List<EmployeesListQuery> emplyoessList;
    private Long total;
    private List<String> sortOptions;
}
