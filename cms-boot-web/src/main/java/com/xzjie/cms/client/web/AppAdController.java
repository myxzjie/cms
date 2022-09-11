package com.xzjie.cms.client.web;

import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "前端-广告管理", tags = "前端-广告管理")
@RestController
@RequestMapping("/app/ad")
public class AppAdController {

    @Autowired
    private AdService adService;

    @ApiOperation(value = "前端-广告位的数据列表", notes = "查询广告位的相关数据列表", response = Ad.class)
    @GetMapping("/data/{positionCode}")
    public Result<List<Ad>> getAd(@PathVariable @ApiParam("广告位code") String positionCode) {
        List<Ad> ads = adService.getAdByPositionCode(positionCode);
        return Result.data(ads);
    }

}
