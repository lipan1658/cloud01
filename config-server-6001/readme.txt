1、jar依赖
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
    </dependencies>
2、配置
server:
  port: 6001
spring:
  application:
    name: config-server
  cloud:
    config:
      server:
        git:
          uri: https://github.com/panli1988/config-server
          username:
          password:
          search-paths: /cloud01
      label: master
3、启动类
@SpringBootApplication
@EnableConfigServer
public class ConfigServer6001Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer6001Application.class,args);
    }
}
4、测试
启动config-server
http://localhost:6001/config-client.yml
浏览器返回
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001:7001/eureka/,http://eureka7002:7002/eureka/,http://eureka7003:7003/eureka/
  instance:
    hostname: localhost
    instance-id: config-client-9001
    prefer-ip-address: true
info:
  app:
    name: config-client-9001
  build:
    artifactId: ${project.artifactId}
    version: ${project.version}
  company:
    name: www.xxx.com
spring:
  profiles:
    active:
    - dev