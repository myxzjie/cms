package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.dto.AdPositionRequest;
import com.xzjie.cms.dto.AdRequest;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.model.AdPosition;
import com.xzjie.cms.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @ResponseBody
    public Map<String, Object> create(@Valid @RequestBody AdRequest adRequest) {
        Ad ad = adRequest.toAd();
        adService.save(ad);
        return MapUtils.success();
    }


    @PutMapping("/update/{id}")
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @Valid @RequestBody AdRequest adRequest) {
        Ad ad = adRequest.toAd();
        ad.setId(id);
        adService.update(ad);
        return MapUtils.success();
    }

    @DeleteMapping(value = "/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        adService.delete(id);
        return MapUtils.success();
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> getAd(AdRequest adRequest) {

        Page<Ad> adPage = adService.getAd(adRequest.getPage(), adRequest.getSize(), adRequest.toAd());

        return MapUtils.success(adPage.getContent(), adPage.getTotalElements());
    }

    /**
     * 分页获得广告位置的数据
     *
     * @param positionRequest
     * @return
     */
    @GetMapping("/position/list")
    @ResponseBody
    public Map<String, Object> getPosition(AdPositionRequest positionRequest) {

        Page<AdPosition> adPositionPage = adService.getPosition(positionRequest.getPage(), positionRequest.getSize(), positionRequest.toAdPosition());

        return MapUtils.success(adPositionPage.getContent(), adPositionPage.getTotalElements());
    }

    @GetMapping("/position/data")
    @ResponseBody
    public Map<String, Object> getPositionData() {

        List<AdPosition> positions = adService.getPositionData();

        return MapUtils.success(positions);
    }

    @PostMapping("/position/create")
    @ResponseBody
    public Map<String, Object> createPosition(@Valid @RequestBody AdPositionRequest positionRequest) {
        AdPosition position = positionRequest.toAdPosition();
        position.setUserId(SecurityUtils.getUserId());
        adService.savePosition(position);
        return MapUtils.success();
    }


    @PutMapping("/position/update/{id}")
    @ResponseBody
    public Map<String, Object> updatePosition(@PathVariable Long id, @Valid @RequestBody AdPositionRequest positionRequest) {
        AdPosition position = positionRequest.toAdPosition();
        position.setId(id);
        adService.updatePosition(position);
        return MapUtils.success();
    }

    @DeleteMapping(value = "/position/delete/{id}")
    public Map<String, Object> deletePosition(@PathVariable Long id) {
        adService.deletePosition(id);
        return MapUtils.success();
    }
}
