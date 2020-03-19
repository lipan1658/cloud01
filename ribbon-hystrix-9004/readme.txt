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
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
    </dependencies>
2、配置
spring:
  application:
    name: ribbon-hystrix
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001:7001/eureka/,http://eureka7002:7002/eureka/,http://eureka7003:7003/eureka/
  instance:
    prefer-ip-address: true
    instance-id: ribbon-hystrix-9004
server:
  port: 9004
3、启动类
增加注解@EnableHystrix
@SpringBootApplication
@EnableHystrix
public class RibbonHystrix9004Application {

    public static void main(String[] args) {
        SpringApplication.run(RibbonHystrix9004Application.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
4、controller类

增加注解@HystrixCommand，指定fallbackMethod，创建fallbackMethod对应方法

@RestController
public class RibbonHystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello/{name}")
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(@PathVariable String name){
        return restTemplate.getForObject("http://client/hello/"+name,String.class);
    }

    public String helloError(String name){
        return "helloError,"+name;
    }
}
5、测试
http://localhost:9004/hello/1111
依次启动server、client、ribbon-hystrix，先访问上述地址；
停client，再次访问上述地址