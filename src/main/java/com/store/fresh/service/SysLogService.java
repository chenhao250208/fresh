package com.store.fresh.service;

import com.store.fresh.entity.SysLog;
import com.store.fresh.entity.SysLogExample;

import java.util.List;

public interface SysLogService {
    int insert(SysLog sysLog);
    List<SysLog> select(SysLogExample sysLogExample);
}
