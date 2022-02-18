package com.xzjie.cms.service.impl;

import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSS;
import com.xzjie.cms.configure.AliyunConfigure;
import com.xzjie.cms.configure.LocalProperties;
import com.xzjie.cms.enums.UploadType;
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

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PicturesServiceImpl implements PicturesService {
    @Autowired
    private OSS ossClient;
    @Autowired
    private LocalProperties localProperties;
    @Autowired
    private AliyunConfigure aliyunConfig;
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
            ossClient.deleteObject(aliyunConfig.getBucketName(), pictures.getPath());
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
    public Page<Pictures> getPictures(Integer page, Integer size, Pictures pictures) {
        Pageable pageable = PageRequest.of(page, size);
        return picturesRepository.findAll(pageable);
    }
}
