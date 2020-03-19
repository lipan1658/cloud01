1、jar依赖
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
    </dependencies>
2、配置
server:
  port: 9003
spring:
  application:
    name: feign
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    instance-id: feign-9002
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka/
#info信息
info:
  app:
    name: feign-9002
  company:
    name: www.xxx.com
  build:
    artifactId: @project.artifactId@
    version: @project.version@
3、启动类
增加注解@EnableFeignClients
4、定义feign对应的service类
CLIENT提供服务的serviceId
@FeignClient(value = "CLIENT")
public interface FeignService {

    //方法同Client中的方法
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name);
}
5、访问地址
http://localhost:9003/hello/XXX
说明：feign默认集成ribbon，修改负载均衡策略同ribbon