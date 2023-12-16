package com.ghy.server.generator.mapper;

import com.ghy.server.generator.domain.AssetCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
* @author aaa
* @description 针对表【asset_category】的数据库操作Mapper
* @createDate 2023-12-11 12:14:19
* @Entity generator.domain.AssetCategory
*/
@Mapper
public interface AssetCategoryMapper extends BaseMapper<AssetCategory> {

    @Select("SELECT DISTINCT category_name FROM asset_category")
    List<String> sortOptions();
}




