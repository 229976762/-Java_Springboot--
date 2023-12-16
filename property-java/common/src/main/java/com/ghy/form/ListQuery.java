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
public class ListQuery {

    private Integer page;

    private Integer limit;

    private MultipartFile  assetImageIn;
    private byte[]  assetImage;
    private String base64Photo;

    private Long assetId;

    private String assetName;

    private String assetSpecification;

    private BigDecimal price;

    private LocalDate purchaseDate;

    private String location;

    private String categoryName;

    private String guardianName;
}
