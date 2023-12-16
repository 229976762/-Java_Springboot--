package com.ghy.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListQuery {

    private Integer page;

    private Integer limit;
    private Integer categoryId;

    private String categoryName;

    private String categoryDescription;
}
