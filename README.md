## cloud-gateway
基于SpringCloudGateway 实现的网关，包含动态路由、IP黑名单、接口白名单、JWT权限认证等功能，拓展简单，易于上手。

![](https://github.com/Imaginary11/api-gateway/blob/master/apigateway.png)

## 快速上手开发
- 拉源码
- 修改配置文件中的redis地址、nacos地址
- 启动 ApiGatewayApplication

## 组件介绍


### 1.IP黑名单检查
该组件永远会被执行! 用户请求时第一步先经过黑名单检查,会获取用户的IP地址,如果用户的IP地址在全局黑名单中,结束请求并响应状态码:403;反则继续执行处理...
存储形式 redis string
- key : blacklist_ip:{ip}  
- ttl : one day

#### IP黑名单RESTFul 接口
| 接口名称      | 定义       | 协议  |
| ------------- |:-------------:| -----:|
| 获取所有IP黑名单      |  /gateway/blacklist/ip| GET
| 新增IP黑名单 | /gateway/blacklist/ip   |    POST |
| 删除IP黑名单 | /gateway/blacklist/ip/{ip}  |    DELETE |

#### 请求示例
```
{
            "ip": "127.0.0.1", # ip
}
```


### 2.接口白名单检查
支持如下格式
 * /nc/*
 * /nc/sms/*
 * /nc/sms/deliver
 * /nc/sms/{id}
 * /nc/sms/{id}/info

存储形式 redis hash
- whitelist_api
- whitelist_api_pattern
- whitelist_service

#### 接口白名单RESTFul 接口
| 接口名称      | 定义       | 协议  |
| ------------- |:-------------:| -----:|
| 获取所有白名单      |  /gateway/whitelist/api | GET
| 新增白名单 | /gateway/whitelist/api   |    POST |
| 删除白名单 | /gateway/whitelist/api  |    DELETE |

#### 请求示例
```
{
            "api": "/nc/sms/delivery", # api
}
```


### 3.权限认证
当创建API时开启了安全认证,该组件会被执行! 组件会将流程交给权限认证插件,权限认证插件负责做相关处理后决定将流程交给下一个组件处理或结束请求
默认全局开启 jwt 认证 ，接口白名单除外

### 4.动态路由
支持http/lb 协议路由。

#### 动态路由接口
| 接口名称      | 定义       | 协议  |
| ------------- |:-------------:| -----:|
| 获取所有路由      |  /gateway/routes | GET
| 根据路由ID查询路由详情      |   /gateway/routes/{id} | GET| 
| 新增路由 | /gateway/routes   |    POST |
| 修改路由信息 |/gateway/routes/{id}   |    PUT |
| 删除路由 | /gateway/routes/{id}  |    DELETE |

#### 请求示例
```
{
            "id": "nc", # 服务id
            "predicates": [
                {
                    "name": "Path",
                    "args": {
                        "pattern": "/nc/**"  # 匹配规则
                    }
                }
            ],
            "filters": [], # 过滤器
            "uri": "http://127.0.0.1:8081",   # 转发地址 , 如果服务注册到网关，可以使用 lb://nc  ，自动实现负载均衡
            "order": 0 # 排序
}
```
			




## todo
-  参数检查
-  访问限制
-  协议转换
-  前置处理器
-  中心处理器(主处理器)
-  后置处理器
-  异常处理器
-  HTTP/HTTPS
-  自定义服务

# 核心功能 （网关分类:应用网关、流量网关）
-  路由管理(done)
-  JWT鉴权(done)
-  OAUTH2.0(todo)   
-  流量限制(done)
-  接口类型区分登录访问接口(需要令牌token)、内部访问(内部RPC接口调用)以及游客访问接口(白名单的Api)  (pending)
-  灰度发布(park done)
   1)分批次部署（1-2-4-8-16-32-...）
   2)通过业务规则
        2.1)按版本号、按路由规则、按白名单、按地区、按渠道、按百分比等等
        2.2)指定用户访问指定灰度版本。同时客户端HTTP Header传入的版本号可以通过网关层修改请求，
            -动态为HTTP请求加上版本号，无需客户端传入版本号
-  API聚合(todo)
-  API编排(类似AWS Lambda服务) (todo)
-  接口统计(todo)
-  集群化(todo)
