#server config
server.port=${server_port}

#datasource
spring.datasource.driver-class-name=${driver-class-name}
spring.datasource.url=${url}
spring.datasource.username=${username}
spring.datasource.password=${password}
spring.datasource.tomcat.max-active=${max-active}
spring.datasource.tomcat.test-while-idle=true
spring.datasource.tomcat.validation-query=select 1
spring.datasource.tomcat.default-auto-commit=false
spring.datasource.tomcat.min-idle=${min-idle}
spring.datasource.tomcat.initial-size=${initial-size}

#jpa
#spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jackson.serialization.indent-output=true

#mybatis
mybatis.type-aliases-package=${group}.${artifact}.entity

#logger config
logging.level.root=INFO
logging.level.org.springframework.web=INFO
logging.level.org.hibernate=ERROR
logging.level.org.apache.shiro=INFO
logging.level.org.pac4j=INFO
#logging.path=D:\\study\\temp\\logs
logging.file=${logging_file}