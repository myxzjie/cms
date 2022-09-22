package com.xzjie.cms.system.ad.web;

import com.xzjie.cms.ad.dto.AdDto;
import com.xzjie.cms.ad.dto.AdPositionDto;
import com.xzjie.cms.ad.model.Ad;
import com.xzjie.cms.ad.service.AdService;
import com.xzjie.cms.ad.vo.AdVo;
import com.xzjie.cms.core.PageResult;
import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.model.AdPosition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ad")
@Api(value = "管理端-广告管理", tags = "管理端-广告管理")
public class SystemAdController {

    @Autowired
    private AdService adService;

    @ApiOperation("广告创建")
    @PostMapping("/create")
    @PreAuthorize("@permission.hasPermission('administrator','ad:all','ad:add')")
    public Result<?> create(@Valid @RequestBody AdDto adRequest) {
        Ad ad = adRequest.toAd();
        ad.setClickCount(0);
        adService.save(ad);
        return Result.success();
    }


    @ApiOperation("广告修改")
    @PutMapping("/update/{id}")
//    @PreAuthorize("hasPermission('/user/user/list',new Object[]{RoleType.ADMINISTRATOR.getCode()})")
    @PreAuthorize("@permission.hasPermission('administrator','ad:all','ad:edit')")
    public Result update(@PathVariable Long id, @Valid @RequestBody AdDto adRequest) {
        Ad ad = adRequest.toAd();
        ad.setId(id);
        adService.update(ad);
        return Result.success();
    }

    @ApiOperation("广告删除")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','ad:all','ad:delete')")
    public Map<String, Object> delete(@PathVariable @ApiParam("广告ID") Long id) {
        adService.delete(id);
        return MapUtils.success();
    }

    @ApiOperation(value = "获得广告的数据列表", response = Ad.class)
    @GetMapping("/list")
    @PreAuthorize("@permission.hasPermission('administrator','ad:all','ad:list')")
    public Result getAdPage(AdDto request) {
        PageResult<AdVo> page = adService.getAdPage(request);
        return Result.data(page);
    }

    /**
     * 分页获得广告位置的数据
     *
     * @param positionRequest
     * @return
     */
    @ApiOperation(value = "获得广告位置的数据列表", response = AdPosition.class)
    @GetMapping("/position/list")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:list')")
    public Result<List<AdPosition>> getPosition(AdPositionDto positionRequest) {
        Page<AdPosition> page = adService.getPosition(positionRequest);
        return Result.data(page.getContent(), page.getTotalElements());
    }

    @ApiOperation(value = "获得广告位置的数据", response = AdPosition.class)
    @GetMapping("/position/data")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:list')")
    public Result<List<AdPosition>> getPositionData() {
        List<AdPosition> positions = adService.getPositionData();
        return Result.data(positions);
    }

    @ApiOperation(value = "获得广告位置创建")
    @PostMapping("/position/create")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:add')")
    public Result<Object> createPosition(@Valid @RequestBody AdPositionDto positionRequest) {
        AdPosition position = positionRequest.toAdPosition();
        position.setUserId(SecurityUtils.getUserId());
        adService.savePosition(position);
        return Result.success();
    }


    @ApiOperation(value = "获得广告位置修改")
    @PutMapping("/position/update/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:edit')")
    public Result<Object> updatePosition(@PathVariable @ApiParam("广告位ID") Long id, @Valid @RequestBody AdPositionDto positionRequest) {
        AdPosition position = positionRequest.toAdPosition();
        position.setId(id);
        adService.updatePosition(position);
        return Result.success();
    }

    @ApiOperation(value = "获得广告位置删除")
    @DeleteMapping(value = "/position/delete/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:delete')")
    public Result<Object> deletePosition(@PathVariable @ApiParam("广告位ID") Long id) {
        adService.deletePosition(id);
        return Result.success();
    }
}
