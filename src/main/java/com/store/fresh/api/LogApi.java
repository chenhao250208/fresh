package com.store.fresh.api;

import com.store.fresh.entity.SysLog;
import com.store.fresh.entity.SysLogExample;
import com.store.fresh.service.SysLogService;
import com.store.fresh.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogApi {
    @Autowired
    SysLogService sysLogService;

    @GetMapping("/log")
    public @ResponseBody
    ResponseEntity log(){
        SysLogExample sysLogExample = new SysLogExample();
        List<SysLog> sysLogs = sysLogService.select(sysLogExample);
        return ResponseEntity.ok().put("logs",sysLogs);
    }
}
