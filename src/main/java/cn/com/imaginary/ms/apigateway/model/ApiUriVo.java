package cn.com.imaginary.ms.apigateway.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ApiUriVo {

    @ApiModelProperty(value = "uri")
    @NotBlank(message = "url不能为空")
    private String uri;

}
