1、jar依赖
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
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
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
    </dependencies>
2、配置
bootstrap.yml
spring:
  cloud:
    config:
      name: config-client
      profile: dev
      label: master
      uri: http://localhost:6001
application.yml(可忽略)
spring:
  application:
    name: config-client02
3、启动类
@SpringBootApplication
public class ConfigClient6002Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient6002Application.class,args);
    }
}
4、controller
@RestController
public class ConfigController {

    @Value("${spring.application.name}")
    private String name;

    @GetMapping("/name")
    public String name(){
        return name;
    }
}
5、测试
地址：http://localhost:8081/name
浏览器返回
config-dev
6、手动刷新
application.yml增加配置
management:
  endpoints:
    web:
      exposure:
        include: refresh
github修改config-client.yml中的spring.application.name
post访问 http://localhost:8081/actuator/refresh
[
    "config.client.version",
    "spring.application.name"
]