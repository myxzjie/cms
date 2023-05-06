package com.xzjie.cms.quartz.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.quartz.dto.PauseQuartzRequest;
import com.xzjie.cms.quartz.dto.QuartzRequest;
import com.xzjie.cms.quartz.dto.QuartzResult;
import com.xzjie.cms.quartz.service.QuartzService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
@Api(value = "管理端-系统任务管理",tags = "管理端-系统任务管理")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @PostMapping(value = "/create")
    @PreAuthorize("@permission.hasPermission('administrator','quartz:all','quartz:add')")
    public Map<String, Object> create(@Valid @RequestBody QuartzRequest quartzRequest) throws Exception {
        Boolean result = quartzService.createTask(quartzRequest.toQuartzEntity());
        return MapUtils.success();
    }

    @GetMapping(value = "/list")
    @PreAuthorize("@permission.hasPermission('administrator','quartz:all','quartz:list')")
    public Map<String, Object> getQuartzList() {
        List<QuartzResult> list = quartzService.getQuartzList();
        return MapUtils.success(list);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("@permission.hasPermission('administrator','quartz:all','quartz:edit')")
    public Map<String, Object> update(@Valid @RequestBody QuartzRequest quartzRequest) throws Exception {
        quartzService.updateTask(quartzRequest.toQuartzEntity());
        return MapUtils.success();
    }

    @PutMapping(value = "/resume")
    @PreAuthorize("@permission.hasPermission('administrator','quartz:all','quartz:edit')")
    public Map<String, Object> resume(@Valid @RequestBody PauseQuartzRequest quartzRequest) throws Exception {
        quartzService.resumeTask(quartzRequest.toQuartzEntity());
        return MapUtils.success();
    }

    @PutMapping(value = "/pause")
    @PreAuthorize("@permission.hasPermission('administrator','quartz:all','quartz:edit')")
    public Map<String, Object> pause(@Valid @RequestBody PauseQuartzRequest quartzRequest) throws Exception {
        quartzService.pauseTask(quartzRequest.toQuartzEntity());
        return MapUtils.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@permission.hasPermission('administrator','quartz:all','quartz:delete')")
    public Map<String, Object> delete(@Valid @RequestBody PauseQuartzRequest quartzRequest) {
        quartzService.deleteTask(quartzRequest.getJobName(), quartzRequest.getJobGroup());
        return MapUtils.success();
    }
}
