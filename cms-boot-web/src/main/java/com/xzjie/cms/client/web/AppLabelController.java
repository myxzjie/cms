package com.xzjie.cms.client.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.LabelQueryDto;
import com.xzjie.cms.label.model.Label;
import com.xzjie.cms.label.service.LabelService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/app/label")
@Api(value = "前端-标签管理", tags = "前端-标签管理")
public class AppLabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/data")
    @ResponseBody
    public Map<String, Object> getLabel() {
        LabelQueryDto query = new LabelQueryDto();
        query.setPage(0);
        query.setSize(15);
        Page<Label> labelPage = labelService.getLabel(query);
        return MapUtils.success(labelPage.getContent(), labelPage.getTotalElements());
    }

}
