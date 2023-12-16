package com.ghy.server.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * @TableName asset
 */
@TableName(value ="asset")
@Data
public class Asset implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.INPUT)
    private Long assetId;
    /**
     *
     */
    private String assetName;
    /**
     *
     */
    private String assetSpecification;
    /**
     *
     */
    private BigDecimal price;
    /**
     *
     */
    private LocalDate purchaseDate;
    /**
     *
     */
    private String location;
    /**
     *
     */
    private Integer categoryId;
    /**
     *
     */
    private byte[] assetImage;
    /**
     *
     */
    private Long guardianId;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }
        Asset asset = (Asset) that;
        return assetId != null && assetId.equals(asset.assetId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId=" + assetId +
                ", assetName='" + assetName + '\'' +
                ", assetSpecification='" + assetSpecification + '\'' +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                ", location='" + location + '\'' +
                ", categoryId=" + categoryId +
                ", guardianId=" + guardianId +
                '}';
    }
}