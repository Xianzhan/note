# 服务注册与发现

该模块用于注册服务, 可启动多个服务形成集群实现高可用.

```bash
> java -jar service-registry.jar --spring.profiles.active=eureka-server-01
> java -jar service-registry.jar --spring.profiles.active=eureka-server-02
```