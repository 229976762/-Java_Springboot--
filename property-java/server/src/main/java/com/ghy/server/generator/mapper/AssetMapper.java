package com.ghy.server.generator.mapper;

import com.ghy.form.ListQuery;
import com.ghy.server.generator.domain.Asset;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/**
* @author aaa
* @description 针对表【asset】的数据库操作Mapper
* @createDate 2023-12-11 12:14:19
* @Entity generator.domain.Asset
*/
@Mapper
public interface AssetMapper extends BaseMapper<Asset> {

    List<ListQuery> getAssetList(@Param("listQuery") ListQuery listQuery);


    @Select("select * from asset where asset_id = #{assetId}")
    Asset select(Long assetId);

    @Select("select * from asset where guardian_id = #{id}")
    List<Asset> selectEmployees(Long id);

    @Update("update asset set guardian_id = null where guardian_id = #{id}")
    void setGuardianIdNull(Long id);

    void recoverGuardianId(List<Long> assetsId, Long id);

    @Update("update asset set category_id = #{newCategoryId} where guardian_id = #{categoryId}")
    void updateCategory(Long categoryId, Integer newCategoryId);

    void deleteCategory(List<Long> ids);

    Asset selectPhoto(Long assetId);
}




