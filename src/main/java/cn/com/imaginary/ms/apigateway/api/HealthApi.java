package cn.com.imaginary.ms.apigateway.api;

import cn.com.imaginary.ms.apigateway.model.ReturnData;
import cn.com.imaginary.ms.apigateway.util.ReturnDataUtil;
import io.swagger.annotations.Api;
import org.springframework.boot.actuate.health.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2018/11/15 14:12
 */
@Api(tags = "健康检查")
@RestController
public class HealthApi {
    @GetMapping("/gateway/health")
    public ReturnData health() {
        return ReturnDataUtil.getSuccussReturn(new Status("UP"));
    }
}
