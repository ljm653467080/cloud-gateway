package cn.com.imaginary.ms.apigateway.api;

import cn.com.imaginary.ms.apigateway.core.whiteblacklist.IpBlacklistResolver;
import cn.com.imaginary.ms.apigateway.model.IpVo;
import cn.com.imaginary.ms.apigateway.model.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2018/11/16 17:37
 * @description: IP黑名单管理
 */
@Api(tags = "IP黑名单管理")
@RestController
@RequestMapping("/gateway/blacklist/ip")
public class IpBlackListApi {

    @Autowired
    private IpBlacklistResolver ipBlacklistResolver;

    @ApiOperation("IP黑名单列表")
    @GetMapping
    public ReturnData getIpBlacklist() {
        return ipBlacklistResolver.getIpList();
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ReturnData addIp(@RequestBody @Validated IpVo ipVo) {
        return ipBlacklistResolver.addIp(ipVo.getIp(), ipVo.getRemark());
    }

    @ApiOperation("删除")
    @PostMapping("/del")
    public ReturnData delIp(@RequestBody IpVo ipVo) {
        return ipBlacklistResolver.delIp(ipVo.getIp());
    }

}
