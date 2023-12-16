package com.ghy.server.generator.control;

import com.ghy.exceptionHandler.MyException;
import com.ghy.form.CategoryListQuery;
import com.ghy.form.EmployeesListQuery;
import com.ghy.form.ListQuery;
import com.ghy.result.Result;
import com.ghy.server.generator.service.AssetService;
import com.ghy.server.generator.service.EmployeeService;
import com.ghy.server.response.AssetCategoryQueryResponse;
import com.ghy.server.response.AssetQueryResponse;
import com.ghy.server.response.EmployessQueryResponse;
import com.ghy.util.Photo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author GHY
 * @date 2023/11/21
 */

@RestController
@RequestMapping("admin")
@Slf4j
public class AdminController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private EmployeeService employeeService;
    /**
     * 添加资产信息
     * @param listQuery
     * @return
     */
    @PutMapping("/addAsset")
    public Result addAsset(@RequestBody ListQuery listQuery) throws MyException {
        log.info("添加资产信息:{}",listQuery);

        AssetQueryResponse list = assetService.addAsset(listQuery);
        return Result.success(list);
    }

    /**
     * 上传文件
     * @param file 要上传的文件
     * @return
     * @throws MyException
     */
    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("assetId") Long assetId) throws IOException {
        log.info("上传文件: {}", file.getOriginalFilename());


        AssetQueryResponse list  = assetService.uploadFile(file,assetId);
        return Result.success(list);
    }

    /**
     * 更新资产信息
     * @param listQuery
     * @return
     */
    @PutMapping("/updateAsset/{assetId}")
    public Result updateAsset(@PathVariable Long assetId,@RequestBody ListQuery listQuery) throws MyException {
        log.info("更新资产信息:{}{}",assetId,listQuery);

        AssetQueryResponse list = assetService.updateAsset(assetId,listQuery);
        return Result.success(list);
    }


    /**
     * 删除资产信息
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteAsset")
    public Result deleteAsset(@RequestParam("ids") List<Long> ids) {
        log.info("删除资产信息:{}",ids);

        AssetQueryResponse list = assetService.deleteAsset(ids);
        return Result.success(list);
    }


    /**
     * 更新资产类别
     * @param listQuery
     * @return
     */
    @PutMapping("/updateCategory/{categoryId}")
    public Result updateCategory(@PathVariable Long categoryId,@RequestBody CategoryListQuery listQuery) throws MyException {
        log.info("更新资产类别:{}{}",categoryId,listQuery);

        AssetCategoryQueryResponse list = assetService.updateCategory(categoryId,listQuery);
        return Result.success(list);
    }

    /**
     * 删除资产类别
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteCategory")
    public Result deleteCategory( @RequestParam("ids") List<Long> ids){
        log.info("删除资产类别:{}{}",ids);

        AssetCategoryQueryResponse list = assetService.deleteCategory(ids);
        return Result.success(list);
    }




    /**
     * 添加员工信息
     * @param listQuery
     * @return
     */
    @PutMapping("/addEmployees")
    public Result addEmployees(@RequestBody EmployeesListQuery listQuery) throws MyException {
        log.info("添加员工信息:{}",listQuery);

        EmployessQueryResponse response = employeeService.addEmployees(listQuery);

        return Result.success(response);
    }


    /**
     * 更新员工信息
     * @param listQuery
     * @return
     */
    @PutMapping("/updateEmployees/{id}")
    public Result updateEmployees(@PathVariable Long id, @RequestBody EmployeesListQuery listQuery) throws MyException {
        log.info("更新员工信息:{}{}",id,listQuery);

        EmployessQueryResponse response = employeeService.updateEmployees(id,listQuery);

        return Result.success(response);
    }
    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @DeleteMapping("/deleteEmployees")
    public Result deleteEmployees(@RequestParam("id") Long id) {
        log.info("删除员工信息:{}",id);

        EmployessQueryResponse response = employeeService.deleteEmployees(id);
        return Result.success(response);
    }
}
