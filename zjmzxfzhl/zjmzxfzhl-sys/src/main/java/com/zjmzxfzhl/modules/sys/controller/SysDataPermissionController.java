package com.zjmzxfzhl.modules.sys.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjmzxfzhl.common.Result;
import com.zjmzxfzhl.common.aspect.annotation.SysLogAuto;
import com.zjmzxfzhl.common.base.BaseController;
import com.zjmzxfzhl.modules.sys.entity.SysDataPermission;
import com.zjmzxfzhl.modules.sys.service.SysDataPermissionService;

/**
 * 数据权限Controller
 * 
 * @author 庄金明
 */
@RestController
@RequestMapping("/sys/dataPermission")
public class SysDataPermissionController extends BaseController {
    @Autowired
    private SysDataPermissionService sysDataPermissionService;

    /**
     * 自定义查询列表
     * 
     * @param sysDataPermission
     * @param current
     * @param size
     * @return
     */
    @RequiresPermissions("sys:dataPermission:list")
    @GetMapping(value = "/list")
    public Result list(SysDataPermission sysDataPermission, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysDataPermission> pageList = sysDataPermissionService.list(new Page<SysDataPermission>(current, size),
                sysDataPermission);
        return Result.ok(pageList);
    }

    @RequiresPermissions("sys:dataPermission:list")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysDataPermission sysDataPermission = sysDataPermissionService.getById(id);
        return Result.ok(sysDataPermission);
    }

    /**
     * @功能：新增
     * @param sysDataPermission
     * @return
     */
    @SysLogAuto(value = "新增数据权限")
    @RequiresPermissions("sys:dataPermission:save")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysDataPermission sysDataPermission) {
        sysDataPermissionService.save(sysDataPermission);
        return Result.ok();
    }

    /**
     * @功能：修改
     * @param sysDataPermission
     * @return
     */
    @SysLogAuto(value = "修改数据权限")
    @RequiresPermissions("sys:dataPermission:update")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysDataPermission sysDataPermission) {
        sysDataPermissionService.updateById(sysDataPermission);
        return Result.ok();
    }

    /**
     * @功能：批量删除
     * @param ids
     * @return
     */
    @SysLogAuto(value = "删除数据权限")
    @RequiresPermissions("sys:dataPermission:delete")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            sysDataPermissionService.removeByIds(Arrays.asList(idsArr));
        } else {
            sysDataPermissionService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}