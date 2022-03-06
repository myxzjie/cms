package com.xzjie.cms.service;

import com.xzjie.cms.dto.PicturesRequest;
import com.xzjie.cms.model.Pictures;
import com.xzjie.cms.model.PicturesGroup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PicturesService {
    List<PicturesGroup> getPicturesGroup();

    boolean deleteGroup(Long id);

    boolean update(PicturesGroup picturesGroup);

    boolean save(PicturesGroup picturesGroup);

    boolean save(Pictures pictures);

    boolean delete(Long id);

    Page<Pictures> getPictures(PicturesRequest query);

}
