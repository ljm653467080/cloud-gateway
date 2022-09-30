package cn.com.imaginary.ms.apigateway.api;

import cn.com.imaginary.ms.apigateway.core.whiteblacklist.ApiWhitelistResolver;
import cn.com.imaginary.ms.apigateway.model.ApiUriVo;
import cn.com.imaginary.ms.apigateway.model.ReturnData;
import cn.com.imaginary.ms.apigateway.util.ReturnDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2019/5/14 15:11
 * @description: API白名单管理
 */
@Api(tags = "API白名单管理")
@RestController
@RequestMapping("/gateway/whitelist/api")
public class ApiWhitelistApi {
    @Autowired
    private ApiWhitelistResolver apiWhitelistResolver;

    @ApiOperation("新增")
    @PostMapping("/add")
    public ReturnData addApi(@RequestBody ApiUriVo apiUriVo) {
        return apiWhitelistResolver.addApi(apiUriVo.getUri());
    }

    @ApiOperation("校验")
    @PostMapping("/validate")
    public ReturnData validate(@RequestBody ApiUriVo apiUriVo) {
        return ReturnDataUtil.getSuccussReturn(apiWhitelistResolver.isWhitelistApi(apiUriVo.getUri()));
    }

    @ApiOperation("删除")
    @PostMapping("/del")
    public ReturnData deleApi(@RequestBody ApiUriVo apiUriVo){
        return apiWhitelistResolver.delApi(apiUriVo.getUri());
    }

    @ApiOperation("API白名单列表")
    @GetMapping
    public ReturnData getApis(){
        return apiWhitelistResolver.getWhitelistApis();
    }
}
