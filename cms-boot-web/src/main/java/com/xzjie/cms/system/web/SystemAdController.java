package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.dto.AdPositionDto;
import com.xzjie.cms.dto.AdDto;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.model.AdPosition;
import com.xzjie.cms.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ad")
public class SystemAdController {

    @Autowired
    private AdService adService;

    @PostMapping("/create")
    @PreAuthorize("@permission.hasPermission('administrator','ad:all','ad:add')")
    public Map<String, Object> create(@Valid @RequestBody AdDto adRequest) {
        Ad ad = adRequest.toAd();
        ad.setClickCount(0);
        adService.save(ad);
        return MapUtils.success();
    }


    @PutMapping("/update/{id}")
//    @PreAuthorize("hasPermission('/user/user/list',new Object[]{RoleType.ADMINISTRATOR.getCode()})")
    @PreAuthorize("@permission.hasPermission('administrator','ad:all','ad:edit')")
    public Map<String, Object> update(@PathVariable Long id, @Valid @RequestBody AdDto adRequest) {
        Ad ad = adRequest.toAd();
        ad.setId(id);
        adService.update(ad);
        return MapUtils.success();
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','ad:all','ad:delete')")
    public Map<String, Object> delete(@PathVariable Long id) {
        adService.delete(id);
        return MapUtils.success();
    }

    @GetMapping("/list")
    @PreAuthorize("@permission.hasPermission('administrator','ad:all','ad:list')")
    public Map<String, Object> getAd(AdDto request) {
        Page<Ad> page = adService.getAd(request);
        return MapUtils.success(page.getContent(), page.getTotalElements());
    }

    /**
     * 分页获得广告位置的数据
     *
     * @param positionRequest
     * @return
     */
    @GetMapping("/position/list")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:list')")
    public Map<String, Object> getPosition(AdPositionDto positionRequest) {
        Page<AdPosition> page = adService.getPosition(positionRequest);
        return MapUtils.success(page.getContent(), page.getTotalElements());
    }

    @GetMapping("/position/data")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:list')")
    public Map<String, Object> getPositionData() {
        List<AdPosition> positions = adService.getPositionData();
        return MapUtils.success(positions);
    }

    @PostMapping("/position/create")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:add')")
    public Map<String, Object> createPosition(@Valid @RequestBody AdPositionDto positionRequest) {
        AdPosition position = positionRequest.toAdPosition();
        position.setUserId(SecurityUtils.getUserId());
        adService.savePosition(position);
        return MapUtils.success();
    }


    @PutMapping("/position/update/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:edit')")
    public Map<String, Object> updatePosition(@PathVariable Long id, @Valid @RequestBody AdPositionDto positionRequest) {
        AdPosition position = positionRequest.toAdPosition();
        position.setId(id);
        adService.updatePosition(position);
        return MapUtils.success();
    }

    @DeleteMapping(value = "/position/delete/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','ad-position:all','ad-position:delete')")
    public Map<String, Object> deletePosition(@PathVariable Long id) {
        adService.deletePosition(id);
        return MapUtils.success();
    }
}
