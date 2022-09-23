package com.xzjie.cms.notice.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.notice.model.Notice;

import java.util.List;

public interface NoticeService extends BaseService<Notice> {
    List<Notice> getNoticeList();
}
