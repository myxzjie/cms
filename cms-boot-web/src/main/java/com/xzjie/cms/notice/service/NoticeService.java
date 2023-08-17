package com.xzjie.cms.notice.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.notice.dto.NoticeQueryDto;
import com.xzjie.cms.notice.model.Notice;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoticeService extends BaseService<Notice> {
    List<Notice> getNotices();

    Page<Notice> getNotices(NoticeQueryDto query);
}
