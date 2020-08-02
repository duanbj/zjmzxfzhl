package com.zjmzxfzhl.modules.monitor.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseController;
import com.zjmzxfzhl.modules.monitor.server.Server;

/**
 * 服务器监控
 *
 * @author 庄金明
 */
@RestController
@RequestMapping("/monitor/server")
public class MonitorServerController extends BaseController {
    @PreAuthorize("@elp.single('monitor:server:getServerInfo')")
    @GetMapping(value = "/getServerInfo")
    public Result getServerInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return Result.ok(server);
    }
}
