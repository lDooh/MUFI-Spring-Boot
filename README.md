# 무인사진관 Mufi Service 서버
`임시 구현`

# [Spring Initializr](https://start.spring.io/) 사용
+ ADD DEPENDENCIES
  + Lombok
  + MySQL
  + Spring Web
  
# 프로젝트 설정 예시
## application.properties 설정
### 포트 설정
```properties
server.port = 8000
```
### MySQL 연결 설정
+ 데이터베이스 생성 후 url에 데이터베이스 이름 설정
+ 데이터베이스 username과 password 설정
+ 데이터베이스 초기화 전략 설정
```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/{database_name}?serverTimezone=UTC
spring.datasource.username=
spring.datasource.password=

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.type.descriptor.sql=trace
spring.jpa.hibernate.ddl-auto=
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

## Lombok 사용 설정
+ &#91;File&#93; - &#91;Settings&#93; - Plugin
  + Marketplace에서 "Lombok" 검색 후 Apply
  + Installed에 이미 Lombok이 있다면 무시
+ &#91;File&#93; - &#91;Settings&#93; - Build, Execution, Deployment - Annotation Processors
  + "Enable annotation processing" 체크 후 Apply
+ `pom.xml` 파일에 Lombok 의존성 추가
  + &lt;dependencies&gt;
    + &lt;dependency&gt;
      + &lt;groupId&gt;org.springframework.boot
      + &lt;artifactId&gt;spring-boot-starter-test
      + &lt;scope&gt;test

# Reference
+ https://github.com/roadbook2/shop