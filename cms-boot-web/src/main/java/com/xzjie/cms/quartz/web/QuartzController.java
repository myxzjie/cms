package com.xzjie.cms.quartz.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.quartz.dto.PauseQuartzRequest;
import com.xzjie.cms.quartz.dto.QuartzRequest;
import com.xzjie.cms.quartz.dto.QuartzResult;
import com.xzjie.cms.quartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @PostMapping(value = "/create")
    public Map<String, Object> create(@Valid @RequestBody QuartzRequest quartzRequest) throws Exception {
        Boolean result = quartzService.createTask(quartzRequest.toQuartzEntity());
        return MapUtils.create().set("code", 0);
    }

    @GetMapping(value = "/list")
    public Map<String, Object> getQuartzList() {
        List<QuartzResult> list = quartzService.getQuartzList();
        return MapUtils.create().set("code", 0).set("data", list);
    }

    @PutMapping(value = "/update")
    public Map<String, Object> update(@Valid @RequestBody QuartzRequest quartzRequest) throws Exception {
        quartzService.updateTask(quartzRequest.toQuartzEntity());
        return MapUtils.create().set("code", 0);
    }

    @PutMapping(value = "/resume")
    public Map<String, Object> resume(@Valid @RequestBody PauseQuartzRequest quartzRequest) throws Exception {
        quartzService.resumeTask(quartzRequest.toQuartzEntity());
        return MapUtils.create().set("code", 0);
    }

    @PutMapping(value = "/pause")
    public Map<String, Object> pause(@Valid @RequestBody PauseQuartzRequest quartzRequest) throws Exception {
        quartzService.pauseTask(quartzRequest.toQuartzEntity());
        return MapUtils.create().set("code", 0);
    }

    @DeleteMapping("/delete")
    public Map<String, Object> delete(@Valid @RequestBody PauseQuartzRequest quartzRequest) {
        quartzService.deleteTask(quartzRequest.getJobName(), quartzRequest.getJobGroup());
        return MapUtils.create().set("code", 0);
    }
}
