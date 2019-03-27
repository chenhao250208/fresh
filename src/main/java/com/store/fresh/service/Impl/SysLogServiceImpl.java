package com.store.fresh.service.Impl;

import com.store.fresh.entity.SysLog;
import com.store.fresh.entity.SysLogExample;
import com.store.fresh.mapper.SysLogMapper;
import com.store.fresh.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public int insert(SysLog sysLog) {
        return sysLogMapper.insert(sysLog);
    }

    @Override
    public List<SysLog> select(SysLogExample sysLogExample) {
        return sysLogMapper.selectByExample(sysLogExample);
    }
}
