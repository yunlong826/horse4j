package com.horse.yun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horse.yun.common.constant.Constants;
import com.horse.yun.common.web.base.Result;
import com.horse.yun.common.web.base.Results;
import com.horse.yun.service.biz.TenantService;
import com.horse.yun.service.biz.tenant.TenantQueryReqDTO;
import com.horse.yun.service.biz.tenant.TenantRespDTO;
import com.horse.yun.service.biz.tenant.TenantSaveReqDTO;
import com.horse.yun.service.biz.tenant.TenantUpdateReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:57
 */
@RestController
@RequestMapping(Constants.BASE_PATH)
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @PostMapping("/namespace/query/page")
    public Result<IPage<TenantRespDTO>> queryNameSpacePage(@RequestBody TenantQueryReqDTO reqDTO) {
        return Results.success(tenantService.queryNameSpacePage(reqDTO));
    }

    @GetMapping("/namespace/query/{namespaceId}")
    public Result<TenantRespDTO> queryNameSpace(@PathVariable("namespaceId") String namespaceId) {
        return Results.success(tenantService.getNameSpaceById(namespaceId));
    }

    @PostMapping("/namespace/save")
    public Result saveNameSpace(@RequestBody TenantSaveReqDTO reqDTO) {
        tenantService.saveNameSpace(reqDTO);
        return Results.success();
    }

    @PostMapping("/namespace/update")
    public Result updateNameSpace(@RequestBody TenantUpdateReqDTO reqDTO) {
        tenantService.updateNameSpace(reqDTO);
        return Results.success();
    }

    @DeleteMapping("/namespace/delete/{namespaceId}")
    public Result deleteNameSpace(@PathVariable("namespaceId") String namespaceId) {
        tenantService.deleteNameSpaceById(namespaceId);
        return Results.success();
    }
}
