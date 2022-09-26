package com.xzjie.cms.system.web;

import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.label.model.Label;
import com.xzjie.cms.notice.dto.NoticeQueryDto;
import com.xzjie.cms.notice.model.Notice;
import com.xzjie.cms.notice.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author vito
 * @date : 2022/9/26 7:50 PM
 */
@Api(tags = "管理端-通知管理", value = "管理端-通知管理")
@RestController
@RequestMapping("/api/notice")
public class SystemNoticeController {
    @Autowired
    private NoticeService noticeService;

    @ApiOperation("获得通知列表")
    @GetMapping(value = "/list")
    @PreAuthorize("@permission.hasPermission('administrator','notice:all','notice:list')")
    public Result<?> getNotice(NoticeQueryDto query) {
        Page<Notice> page = noticeService.getNotices(query);
        return Result.data(page.getContent(), page.getTotalElements());
    }

    @ApiOperation("创建通知")
    @PostMapping(value = "/create")
    @PreAuthorize("@permission.hasPermission('administrator','notice:all','notice:add')")
    public Result<?> create(@RequestBody Notice notice) {
        noticeService.save(notice);
        return Result.success();
    }

    @ApiOperation("修改通知")
    @PutMapping(value = "/update")
    @PreAuthorize("@permission.hasPermission('administrator','notice:all','notice:edit')")
    public Result<?> update(@RequestBody Notice notice) {
        noticeService.update(notice);
        return Result.success();
    }

    @ApiOperation("删除通知")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','notice:all','notice:delete')")
    public Result<?> update(@PathVariable Long id) {
        noticeService.delete(id);
        return Result.success();
    }
}
