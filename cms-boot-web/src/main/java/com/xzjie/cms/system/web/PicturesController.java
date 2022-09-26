package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.PicturesGroupRequest;
import com.xzjie.cms.dto.PictureQueryDto;
import com.xzjie.cms.picture.model.Pictures;
import com.xzjie.cms.picture.model.PicturesGroup;
import com.xzjie.cms.picture.service.PicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pictures")
public class PicturesController {

    @Autowired
    private PicturesService picturesService;

    @GetMapping(value = "/list")
    @PreAuthorize("@permission.hasPermission('administrator','picture:all','picture:list')")
    public Map<String, Object> getPictures(PictureQueryDto pictures) {
        Page<Pictures> articlePage = picturesService.getPictures(pictures);
        return MapUtils.success(articlePage.getContent(), articlePage.getTotalElements());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','picture:all','picture:delete')")
    public Map<String, Object> deletePictures(@PathVariable Long id) {
        if (picturesService.delete(id)) {
            return MapUtils.success();
        }
        return MapUtils.error("图片删除失败");
    }

    @GetMapping("/group")
    @PreAuthorize("@permission.hasPermission('administrator','picture:all','picture:list')")
    public Map<String, Object> getPicturesGroup() {
        List<PicturesGroup> picturesGroups = picturesService.getPicturesGroup();
        return MapUtils.success(picturesGroups);
    }

    @PostMapping("/group")
    @PreAuthorize("@permission.hasPermission('administrator','picture:all','picture:add')")
    public Map<String, Object> createPicturesGroup(@RequestBody PicturesGroupRequest group) {
        PicturesGroup picturesGroup = group.toPicturesGroup();
        picturesService.save(picturesGroup);
        return MapUtils.success();
    }

    @PutMapping("/group/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','picture:all','picture:edit')")
    public Map<String, Object> updatePicturesGroup(@PathVariable Long id, @RequestBody PicturesGroupRequest group) {
        PicturesGroup picturesGroup = group.toPicturesGroup();
        picturesGroup.setId(id);

        picturesService.update(picturesGroup);
        return MapUtils.success();
    }

    @DeleteMapping("/group/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','picture:all','picture:delete')")
    public Map<String, Object> deletePicturesGroup(@PathVariable Long id) {
        picturesService.deleteGroup(id);
        return MapUtils.success();
    }
}
