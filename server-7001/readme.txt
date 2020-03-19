1、jar依赖
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
2、配置
server:
  port: 7001
spring:
  application:
    name: server
eureka:
  client:
    #要不要去注册中心获取其他服务的地址
    fetch-registry: false
    #自己就是注册中心，不用注册自己
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7002:7002/eureka/,http://eureka7003:7003/eureka/
  instance:
    hostname: localhost
3、启动类增加注解
@EnableEurekaServer
4、访问地址
http://localhost:7001/
