package com.xzjie.cms.service.impl;

import com.xzjie.cms.model.Pictures;
import com.xzjie.cms.model.PicturesGroup;
import com.xzjie.cms.repository.PicturesGroupRepository;
import com.xzjie.cms.repository.PicturesRepository;
import com.xzjie.cms.service.PicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PicturesServiceImpl implements PicturesService {

    @Autowired
    private PicturesRepository picturesRepository;
    @Autowired
    private PicturesGroupRepository picturesGroupRepository;

    @Override
    public List<PicturesGroup> getPicturesGroup() {
        return picturesGroupRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        picturesGroupRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(PicturesGroup picturesGroup) {
        Optional<PicturesGroup> optionalPicturesGroup = picturesGroupRepository.findById(picturesGroup.getId());
//        ValidationUtil.isNull( optionalYxExpress,"YxExpress","id",resources.getId());
        PicturesGroup group = optionalPicturesGroup.get();
        group.copy(picturesGroup);
        picturesGroupRepository.save(group);
        return true;
    }

    @Override
    public boolean save(Pictures pictures) {
        pictures.setState(1);
        pictures.setCreateDate(LocalDateTime.now());
        picturesRepository.save(pictures);
        return true;
    }

    @Override
    public boolean save(PicturesGroup picturesGroup) {
        picturesGroup.setState(1);
        picturesGroupRepository.save(picturesGroup);
        return true;
    }

    @Override
    public Page<Pictures> getPictures(Integer page, Integer size, Pictures pictures) {
        Pageable pageable = PageRequest.of(page, size);
        return picturesRepository.findAll(pageable);
    }
}
