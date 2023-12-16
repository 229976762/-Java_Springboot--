package com.ghy.server.response;

import com.ghy.server.generator.domain.AssetCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetCategoryQueryResponse {
    private List<AssetCategory> assetCategoryList;
    private Long total;
}
