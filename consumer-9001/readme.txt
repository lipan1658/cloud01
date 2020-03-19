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
    </dependencies>
2、配置文件
server:
  port: 9001
spring:
  application:
    name: consumer
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    instance-id: consumer-9001
  client:
    service-url:
      defaultZone: http://eureka7001:7001/eureka/,http://eureka7002:7002/eureka/,http://eureka7003:7003/eureka/
#info信息
info:
  app:
    name: consumer-9001
  company:
    name: www.xxx.com
  build:
    artifactId: @project.artifactId@
    version: @project.version@
3、启动类
注入RestTemplate
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
4、controller
注入RestTemplate
指定url
@GetMapping("/hello/{name}")
public String hello(@PathVariable String name){
    return restTemplate.getForObject("http://localhost:8001/hello/"+name,String.class);
}
