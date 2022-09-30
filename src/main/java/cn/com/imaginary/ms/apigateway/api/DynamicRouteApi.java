package cn.com.imaginary.ms.apigateway.api;

import cn.com.imaginary.ms.apigateway.core.route.DynamicRouteResolver;
import cn.com.imaginary.ms.apigateway.model.ReturnData;
import cn.com.imaginary.ms.apigateway.util.ReturnDataUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2018/11/12 11:26
 * @description: 路由管理
 */
@Slf4j
@Api(tags = "路由管理")
@RestController
@RequestMapping("/gateway/routes")
public class DynamicRouteApi {

    @Autowired
    private DynamicRouteResolver dynamicRouteResolver;

    @ApiOperation("路由列表")
    @GetMapping()
    public ReturnData list() {
        return ReturnDataUtil.getSuccussReturn(dynamicRouteResolver.getRoutesList());
    }

    @ApiOperation("路由详情")
    @GetMapping("/{id}")
    public ReturnData detail(@PathVariable String id) {
        return ReturnDataUtil.getSuccussReturn(dynamicRouteResolver.getRouteById(id));
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ReturnData add(@RequestBody RouteDefinition definition) {
        try {
            return dynamicRouteResolver.add(definition);
        } catch (Exception e) {
            log.error("e", e);
            return ReturnDataUtil.systemError();
        }
    }

    @ApiOperation("删除")
    @PostMapping("/del")
    public ReturnData delete(@RequestBody JSONObject object) {
        return dynamicRouteResolver.delete(object.getString("id"));
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public ReturnData update(@RequestBody RouteDefinition definition) {
        return dynamicRouteResolver.update(definition);
    }


}
