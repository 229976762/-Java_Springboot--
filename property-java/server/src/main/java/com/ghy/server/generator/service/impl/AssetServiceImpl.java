package com.ghy.server.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghy.exceptionHandler.MyException;
import com.ghy.exceptionHandler.RCode;
import com.ghy.form.CategoryListQuery;
import com.ghy.form.ListQuery;
import com.ghy.server.generator.domain.Asset;
import com.ghy.server.generator.domain.AssetCategory;
import com.ghy.server.generator.domain.Employee;
import com.ghy.server.generator.mapper.AssetCategoryMapper;
import com.ghy.server.generator.mapper.EmployeeMapper;
import com.ghy.server.generator.service.AssetService;
import com.ghy.server.generator.mapper.AssetMapper;
import com.ghy.server.response.AssetCategoryQueryResponse;
import com.ghy.server.response.AssetQueryResponse;
import com.ghy.util.Photo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
* @author aaa
* @description 针对表【asset】的数据库操作Service实现
* @createDate 2023-12-11 12:14:19
*/
@Service
@Slf4j
public class AssetServiceImpl extends ServiceImpl<AssetMapper, Asset>
    implements AssetService{

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private AssetCategoryMapper categoryMapper;

    @Override
    public AssetQueryResponse getAssetList(ListQuery listQuery) {

        List<ListQuery> list = assetMapper.getAssetList(listQuery);
        List<String> sortOptions = categoryMapper.sortOptions();
        // 计算起始索引和结束索引
        int startIndex = (listQuery.getPage() - 1) * listQuery.getLimit();
        int endIndex = Math.min(startIndex + listQuery.getLimit(), list.size());

        List<ListQuery> pageData = list.subList(startIndex, endIndex);
        // TODO 优化
        for (int i = 0; i < pageData.size(); i++) {
            if(pageData.get(i).getAssetImage() != null) {
                    byte[] bytes = pageData.get(i).getAssetImage();
                    log.info("{}",i);
                    pageData.get(i).setBase64Photo(Photo.getBase64Photo(bytes));
            }
        }

        return new AssetQueryResponse(pageData, (long) list.size(),sortOptions);
    }

    @Override
    public AssetQueryResponse addAsset(ListQuery listQuery) throws MyException {

        log.info("添加资产：{}",listQuery);
        Asset asset = assetMapper.select(listQuery.getAssetId());
        if(asset != null)
            throw new MyException(RCode.ASSET_ADD_ERROR);

        QueryWrapper<Employee> query = Wrappers.query();
        query.eq("employee_name",listQuery.getGuardianName());
        Employee employee = employeeMapper.selectOne(query);
        if(employee == null)
            throw new MyException(RCode.EMPLOYEES_INFO_ERROR);

        QueryWrapper<AssetCategory> query1 = Wrappers.query();
        query1.eq("category_name",listQuery.getCategoryName());
        AssetCategory assetCategory = categoryMapper.selectOne(query1);

        Asset newAsset = new Asset();

        BeanUtils.copyProperties(listQuery,newAsset);
        log.info("{}{}",listQuery,newAsset);
        newAsset.setCategoryId(assetCategory.getCategoryId());
        newAsset.setGuardianId(employee.getEmployeeId());


        assetMapper.insert(newAsset);
        ListQuery query2 = new ListQuery();
        query2.setPage(listQuery.getPage());
        query2.setLimit(listQuery.getLimit());
        return getAssetList(query2);
    }

    @Override
    public AssetQueryResponse updateAsset(Long assetId, ListQuery listQuery) throws MyException {

        Asset asset = assetMapper.selectById(listQuery.getAssetId());
        if(asset != null && assetId.longValue() != listQuery.getAssetId())
            throw new MyException(RCode.ASSET_ADD_ERROR);

        QueryWrapper<Employee> query = Wrappers.query();
        query.eq("employee_name",listQuery.getGuardianName());
        Employee employee = employeeMapper.selectOne(query);
        if(employee == null)
            throw new MyException(RCode.EMPLOYEES_INFO_ERROR);

        QueryWrapper<AssetCategory> query1 = Wrappers.query();
        query1.eq("category_name",listQuery.getCategoryName());
        AssetCategory assetCategory = categoryMapper.selectOne(query1);

        Asset newAsset = new Asset();
        BeanUtils.copyProperties(listQuery,newAsset);
        log.info("{}{}",listQuery,newAsset);
        newAsset.setCategoryId(assetCategory.getCategoryId());
        newAsset.setGuardianId(employee.getEmployeeId());

        assetMapper.deleteById(assetId);
        assetMapper.insert(newAsset);

        ListQuery query2 = new ListQuery();
        query2.setPage(listQuery.getPage());
        query2.setLimit(listQuery.getLimit());
        return getAssetList(query2);
    }

    @Override
    public AssetQueryResponse deleteAsset(List<Long> ids) {

        assetMapper.deleteBatchIds(ids);

        ListQuery query2 = new ListQuery();
        query2.setPage(1);
        query2.setLimit(10);
        return getAssetList(query2);
    }

    @Override
    public AssetCategoryQueryResponse assetCategoryList(CategoryListQuery listQuery) {


        QueryWrapper<AssetCategory> query = Wrappers.query();
        if (listQuery.getCategoryId() != null && listQuery.getCategoryId() != 0) {
            query.eq("category_id", listQuery.getCategoryId());
        }
        if (StringUtils.isNotBlank(listQuery.getCategoryName())) {
            query.like("category_name", listQuery.getCategoryName());
        }
        if (StringUtils.isNotBlank(listQuery.getCategoryDescription())) {
            query.like("category_description", listQuery.getCategoryDescription());
        }

        List<AssetCategory> list = categoryMapper.selectList(query);

        // 计算起始索引和结束索引
        int startIndex = (listQuery.getPage() - 1) * listQuery.getLimit();
        int endIndex = Math.min(startIndex + listQuery.getLimit(), list.size());

        List<AssetCategory> pageData = list.subList(startIndex, endIndex);

        return new AssetCategoryQueryResponse(pageData, (long) list.size());
    }

    @Override
    public AssetCategoryQueryResponse updateCategory(Long categoryId, CategoryListQuery listQuery) throws MyException {

        AssetCategory newCategory = new AssetCategory();
        BeanUtils.copyProperties(listQuery,newCategory);

        AssetCategory assetCategory = categoryMapper.selectById(listQuery.getCategoryId());
        if(categoryId.longValue() != listQuery.getCategoryId()) {

            if(assetCategory != null)
                throw new MyException(RCode.CATE_ADD_ERROR);

            categoryMapper.insert(newCategory);

            if(categoryId != -1L){
                assetMapper.updateCategory(categoryId,newCategory.getCategoryId());
                categoryMapper.deleteById(categoryId);
            }

        }else
            categoryMapper.updateById(newCategory);

        CategoryListQuery query = new CategoryListQuery();
        query.setPage(1);
        query.setLimit(10);
        return assetCategoryList(query);
    }

    @Override
    public AssetCategoryQueryResponse deleteCategory(List<Long> ids) {

        assetMapper.deleteCategory(ids);


        categoryMapper.deleteBatchIds(ids);


        CategoryListQuery query = new CategoryListQuery();
        query.setPage(1);
        query.setLimit(10);
        return assetCategoryList(query);    }

    @Override
    public AssetQueryResponse uploadFile(MultipartFile file, Long assetId) throws IOException {


        Asset asset = assetMapper.selectById(assetId);
        if(asset == null)
            return null;


        byte[] pic = file.getBytes();

        asset.setAssetImage(pic);
        assetMapper.updateById(asset);
        ListQuery query2 = new ListQuery();
        query2.setPage(1);
        query2.setLimit(10);
        AssetQueryResponse response = getAssetList(query2);

        return response;
    }

}




