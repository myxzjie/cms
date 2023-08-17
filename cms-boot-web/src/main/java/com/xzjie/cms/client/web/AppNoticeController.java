package com.xzjie.cms.client.web;

import com.xzjie.cms.core.Result;
import com.xzjie.cms.notice.model.Notice;
import com.xzjie.cms.notice.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vito
 * @date : 2022/9/23 2:23 PM
 */
@Api(value = "前端-通知管理", tags = "前端-通知管理")
@RestController
@RequestMapping("/app/notice")
public class AppNoticeController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation("获得通知数据")
    @GetMapping("/data")
    public Result<List<Notice>> getNoticeList() {
        return Result.data(noticeService.getNotices());
    }
}
