package com.xzjie.cms.client.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/ad")
public class AppAdController {

    @Autowired
    private AdService adService;

    @GetMapping("/data/{positionCode}")
    @ResponseBody
    public Map<String, Object> getAd(@PathVariable String positionCode) {
        List<Ad> ads = adService.getAdByPositionCode(positionCode);
        return MapUtils.success(ads);
    }

}
