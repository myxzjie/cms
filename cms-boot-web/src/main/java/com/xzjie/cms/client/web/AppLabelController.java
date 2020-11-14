package com.xzjie.cms.client.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.model.Label;
import com.xzjie.cms.service.AdService;
import com.xzjie.cms.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/label")
public class AppLabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/data")
    @ResponseBody
    public Map<String, Object> getLabel() {
        Page<Label> labelPage = labelService.getLabel(0, 15, new Label());
        return MapUtils.success(labelPage.getContent(), labelPage.getTotalElements());
    }

}
