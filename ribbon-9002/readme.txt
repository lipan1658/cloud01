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
2、配置
server:
  port: 9002
spring:
  application:
    name: ribbon
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    instance-id: ribbon-9002
  client:
    service-url:
      defaultZone: http://eureka7001:7001/eureka/,http://eureka7002:7002/eureka/,http://eureka7003:7003/eureka/
#info信息
info:
  app:
    name: ribbon-9002
  company:
    name: www.xxx.com
  build:
    artifactId: @project.artifactId@
    version: @project.version@

3、启动类
    @Bean
    //引入注解
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    //通过IRule实现类指定负载均衡规则
    @Bean
    IRule iRule(){
        return new RandomRule();
    }
4、controller
通过serviceId进行请求
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable  String name){
        return restTemplate.getForObject("http://client/hello/"+name,String.class);
    }
5、测试
http://localhost:9002/hello/sss
多次，返回hello,sss、hello2,sss