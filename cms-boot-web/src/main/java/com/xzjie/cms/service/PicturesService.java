package com.xzjie.cms.service;

import com.xzjie.cms.model.Pictures;
import com.xzjie.cms.model.PicturesGroup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PicturesService {
    List<PicturesGroup> getPicturesGroup();

    boolean delete(Long id);

    boolean update(PicturesGroup picturesGroup);

    boolean save(Pictures pictures);

    boolean save(PicturesGroup picturesGroup);

    Page<Pictures> getPictures(Integer page, Integer size, Pictures pictures);
}
