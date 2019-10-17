package com.xzjie.cms.service.impl;

import com.xzjie.cms.model.Navigation;
import com.xzjie.cms.repository.NavigationRepository;
import com.xzjie.cms.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationRepository navigationRepository;

    @Override
    public List<Navigation> getNavigation() {
        return navigationRepository.findByPid(0L);
    }
}
