package com.ghy.server.response;

import com.ghy.form.ListQuery;
import com.ghy.server.generator.domain.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetQueryResponse {
    private List<ListQuery> assetList;
    private Long total;
    private List<String> sortOptions;
}
