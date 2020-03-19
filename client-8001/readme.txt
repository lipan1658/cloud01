同client-8001
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
  port: 8001
spring:
  application:
    name: client
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    instance-id: client-8001
  client:
    service-url:
      defaultZone: http://eureka7001:7001/eureka/,http://eureka7002:7002/eureka/,http://eureka7003:7003/eureka/
#info信息
info:
  app:
    name: client-8001
  company:
    name: www.xxx.com
  build:
    artifactId: @project.artifactId@
    version: @project.version@
3、parent pom.xml添加配置
<build>
        <finalName>cloud01</finalName>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <configuration>
                    <delimiters>
                        <delimit>$</delimit>
                    </delimiters>
                </configuration>
            </plugin>
        </plugins>
    </build>
4、查看info信息
http://10.19.50.189:8001/actuator/info
返回（josn格式化后的）
{
	"app": {
		"name": "client-8001"
	},
	"company": {
		"name": "www.xxx.com"
	},
	"build": {
		"artifactId": "client-8001",
		"version": "1.0-SNAPSHOT"
	}
}
5、测试服务请求
http://localhost:8001/hello/zs
返回
hello,zs
