package com.xzjie.cms.picture.service;

import com.xzjie.cms.dto.PictureQueryDto;
import com.xzjie.cms.picture.model.Pictures;
import com.xzjie.cms.picture.model.PicturesGroup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PicturesService {
    List<PicturesGroup> getPicturesGroup();

    boolean deleteGroup(Long id);

    boolean update(PicturesGroup picturesGroup);

    boolean save(PicturesGroup picturesGroup);

    boolean save(Pictures pictures);

    boolean delete(Long id);

    Page<Pictures> getPictures(PictureQueryDto query);

}
