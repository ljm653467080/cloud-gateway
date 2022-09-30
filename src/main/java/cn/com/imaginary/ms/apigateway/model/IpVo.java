package cn.com.imaginary.ms.apigateway.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class IpVo {

    @ApiModelProperty(value = "ip地址")
    @NotBlank(message = "ip地址不能为空")
    private String ip;

    @ApiModelProperty(value = "备注")
    private String remark;
}
