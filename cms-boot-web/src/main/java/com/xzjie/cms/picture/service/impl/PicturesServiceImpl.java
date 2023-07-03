package com.xzjie.cms.picture.service.impl;

import com.xzjie.cms.enums.Sorting;
import com.xzjie.cms.picture.dto.PictureQueryDto;
import com.xzjie.cms.enums.UploadType;
import com.xzjie.cms.store.local.configure.LocalProperties;
import com.xzjie.cms.store.minio.service.MinioService;
import com.xzjie.cms.picture.model.Pictures;
import com.xzjie.cms.picture.model.PicturesGroup;
import com.xzjie.cms.core.persistence.SpecSearchCriteria;
import com.xzjie.cms.picture.repository.PicturesGroupRepository;
import com.xzjie.cms.picture.repository.PicturesRepository;
import com.xzjie.cms.picture.service.PicturesService;
import com.xzjie.cms.store.oss.service.OssService;
import com.xzjie.cms.store.qiniu.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PicturesServiceImpl implements PicturesService {
    @Autowired
    private OssService ossService;
    @Autowired
    private QiniuService qiniuService;
    @Autowired
    private MinioService minioService;
    @Autowired
    private LocalProperties localProperties;
    @Autowired
    private PicturesRepository picturesRepository;
    @Autowired
    private PicturesGroupRepository picturesGroupRepository;

    @Override
    public List<PicturesGroup> getPicturesGroup() {
        return picturesGroupRepository.findAll();
    }

    @Override
    public boolean deleteGroup(Long id) {
        picturesGroupRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(PicturesGroup picturesGroup) {
        Optional<PicturesGroup> optionalPicturesGroup = picturesGroupRepository.findById(picturesGroup.getId());
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
    public boolean delete(Long id) {
        boolean hasDelete = false;
        Pictures pictures = picturesRepository.findById(id).orElseGet(Pictures::new);
        if (UploadType.LOCAL.name().equals(pictures.getOrigin())) {
            String path = localProperties.getPath() + pictures.getPath();
            File dest = new File(path);
            hasDelete = dest.delete();
        } else if (UploadType.ALIYUN.name().equals(pictures.getOrigin())) {
            ossService.deleteObject(pictures.getPath());
            hasDelete = true;
        } else if (UploadType.MINIO.name().equals(pictures.getOrigin())) {
            minioService.deleteObject(pictures.getPath());
            hasDelete = true;
        }
        if (hasDelete) {
            picturesRepository.delete(pictures);
            return true;
        }
        return false;
    }

    @Override
    public boolean save(PicturesGroup picturesGroup) {
        picturesGroup.setState(1);
        picturesGroupRepository.save(picturesGroup);
        return true;
    }

    @Override
    public Page<Pictures> getPictures(PictureQueryDto query) {
        Sort sort = Sort.by("id").descending();
        if (Sorting.asc.equals(query.getSorting())) {
            sort = Sort.by("id").ascending();
        }
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), sort);
        Specification<Pictures> specification = SpecSearchCriteria.builder(query);

        return picturesRepository.findAll(specification, pageable);
    }
}
