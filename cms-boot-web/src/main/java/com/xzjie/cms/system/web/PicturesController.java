package com.xzjie.cms.system.web;

import com.xzjie.cms.dto.ArticleRequest;
import com.xzjie.cms.dto.PicturesGroupRequest;
import com.xzjie.cms.dto.PicturesRequest;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.model.Pictures;
import com.xzjie.cms.model.PicturesGroup;
import com.xzjie.cms.service.PicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pictures")
public class PicturesController {

    @Autowired
    private PicturesService picturesService;

    @GetMapping(value = "/list")
//    @PreAuthorize("hasAuthority('user')")
    public Map<String, Object> getPictures(PicturesRequest pictures) {
        Map<String, Object> map = new HashMap<>();
        Page<Pictures> articlePage = picturesService.getPictures(pictures.getPage(), pictures.getSize(), pictures.toPictures());
        map.put("data", articlePage.getContent());
        map.put("total",articlePage.getTotalPages());
        map.put("code", 0);
        return map;
    }

    @GetMapping("/group")
    public Map<String, Object> getPicturesGroup() {
        Map<String, Object> map = new HashMap<>();
        List<PicturesGroup> picturesGroups = picturesService.getPicturesGroup();
        map.put("code", 0);
        map.put("data", picturesGroups);
        return map;
    }

    @PostMapping("/group")
    public Map<String, Object> createPicturesGroup(@RequestBody PicturesGroupRequest group) {
        Map<String, Object> map = new HashMap<>();
        PicturesGroup picturesGroup = group.toPicturesGroup();
        picturesService.save(picturesGroup);
        map.put("code", 0);
        return map;
    }

    @PutMapping("/group/{id}")
    public Map<String, Object> updatePicturesGroup(@PathVariable Long id, @RequestBody PicturesGroupRequest group) {
        Map<String, Object> map = new HashMap<>();
        PicturesGroup picturesGroup = group.toPicturesGroup();
        picturesGroup.setId(id);

        picturesService.update(picturesGroup);
        map.put("code", 0);
        return map;
    }

    @DeleteMapping("/group/{id}")
    public Map<String, Object> deletePicturesGroup(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        picturesService.delete(id);
        map.put("code", 0);
        return map;
    }
}
