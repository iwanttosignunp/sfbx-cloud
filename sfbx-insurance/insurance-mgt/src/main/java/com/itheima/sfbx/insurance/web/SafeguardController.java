package com.itheima.sfbx.insurance.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.itheima.sfbx.framework.commons.basic.ResponseResult;
import com.itheima.sfbx.framework.commons.utils.ResponseResultBuild;
import com.itheima.sfbx.insurance.dto.SafeguardVO;
import com.itheima.sfbx.insurance.service.ISafeguardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "保险保障项")
@RestController
@RequestMapping("/safeguard")
public class SafeguardController {
    @Autowired
    private ISafeguardService safeguardService;

    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "safeguardVO", value = "VO对象", required = true, dataType = "SafeguardVO"),
            @ApiImplicitParam(paramType = "path", name = "pageNum", value = "页码", example = "1", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "pageSize", value = "每页条数", example = "10", required = true, dataType = "Integer")
    })
    @ApiOperationSupport(includeParameters = {"safeguardVO.safeguardType","safeguardVO.safeguardKey","safeguardVO.safeguardVal","safeguardVO.remake"})
    @PostMapping("/page/{pageNum}/{pageSize}")
    public ResponseResult<Page<SafeguardVO>> findSafeguardVOPage(
            @RequestBody SafeguardVO safeguardVO,
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize
    ){
        Page<SafeguardVO> page = safeguardService.findPage(safeguardVO, pageNum, pageSize);
        return ResponseResultBuild.successBuild(page);
    }

    @ApiOperation(value = "新增保障项", notes = "新增保障项")
    @ApiImplicitParam(name = "safeguardVO",value = "VO对象",required = true,dataType = "SafeguardVO")
    @ApiOperationSupport(includeParameters = {"safeguardVO.safeguardType","safeguardVO.safeguardKey","safeguardVO.safeguardVal","safeguardVO.remake", "safeguardVO.dataState", "safeguardVO.sortNo"})
    @PutMapping
    public ResponseResult<SafeguardVO> createSafeguard(@RequestBody SafeguardVO safeguardVO) {
        SafeguardVO result = safeguardService.save(safeguardVO);
        return ResponseResultBuild.successBuild(result);
    }

    @ApiOperation(value = "修改保障项", notes = "修改保障项")
    @ApiImplicitParam(name = "safeguardVO",value = "VO对象",required = true,dataType = "SafeguardVO")
    @ApiOperationSupport(includeParameters = {"safeguardVO.id","safeguardVO.safeguardType","safeguardVO.safeguardKey","safeguardVO.safeguardVal","safeguardVO.remake", "safeguardVO.dataState", "safeguardVO.sortNo"})
    @PatchMapping
    public ResponseResult<Boolean> updateSafeguard(@RequestBody SafeguardVO safeguardVO){
        boolean result = safeguardService.update(safeguardVO);
        return ResponseResultBuild.successBuild(result);
    }

    @ApiOperation(value = "删除保障项", notes = "删除保障项")
    @ApiImplicitParam(name = "safeguardVO",value = "VO对象",required = true,dataType = "SafeguardVO")
    @ApiOperationSupport(includeParameters = {"safeguardVO.checkedIds"})
    @DeleteMapping
    public ResponseResult<Boolean> deleteSafeguard(@RequestBody SafeguardVO safeguardVO) {
        boolean result = safeguardService.delete(safeguardVO.getCheckedIds());
        return ResponseResultBuild.successBuild(result);
    }

}
