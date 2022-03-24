package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.LabelQueryDto;
import com.xzjie.cms.model.Label;
import com.xzjie.cms.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SystemLabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/label")
    @PreAuthorize("@permission.hasPermission('administrator','label:all','label:list')")
    public Map<String, Object> getLabel() {
        List<Label> list = labelService.getLabelList();
        return MapUtils.success(list);
    }

    @GetMapping(value = "/label/list")
    @PreAuthorize("@permission.hasPermission('administrator','label:all','label:list')")
    public Map<String, Object> getLabel(LabelQueryDto query) {
        Page<Label> labelPage = labelService.getLabel(query);
        return MapUtils.success(labelPage.getContent(), labelPage.getTotalElements());
    }

    @PostMapping("/label")
    @PreAuthorize("@permission.hasPermission('administrator','label:all','label:add')")
    public Map<String, Object> createLabel(@Validated @RequestBody Label model) {
        model.setState(1);
        labelService.save(model);
        return MapUtils.success();
    }

    @PutMapping("/label")
    @PreAuthorize("@permission.hasPermission('administrator','label:all','label:edit')")
    public Map<String, Object> updateLabel(@Validated @RequestBody Label model) {
        labelService.update(model);
        return MapUtils.success();
    }
}
