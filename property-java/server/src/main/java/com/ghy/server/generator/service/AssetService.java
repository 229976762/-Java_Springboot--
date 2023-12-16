package com.ghy.server.generator.service;

import com.ghy.exceptionHandler.MyException;
import com.ghy.form.CategoryListQuery;
import com.ghy.form.ListQuery;
import com.ghy.server.generator.domain.Asset;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ghy.server.response.AssetCategoryQueryResponse;
import com.ghy.server.response.AssetQueryResponse;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

/**
* @author aaa
* @description 针对表【asset】的数据库操作Service
* @createDate 2023-12-11 12:14:19
*/
public interface AssetService extends IService<Asset> {

    AssetQueryResponse getAssetList(ListQuery listQuery);

    AssetQueryResponse addAsset(ListQuery listQuery) throws MyException;

    AssetQueryResponse updateAsset(Long assetId, ListQuery listQuery) throws MyException;

    AssetQueryResponse deleteAsset(List<Long> ids);

    AssetCategoryQueryResponse assetCategoryList(CategoryListQuery query);

    AssetCategoryQueryResponse updateCategory(Long categoryId, CategoryListQuery listQuery) throws MyException;

    AssetCategoryQueryResponse deleteCategory(List<Long> ids);

    AssetQueryResponse uploadFile(MultipartFile file, Long assetId) throws IOException;
}
