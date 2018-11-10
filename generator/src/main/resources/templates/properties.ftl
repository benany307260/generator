#server config
server.port=${server_port}

#datasource
spring.datasource.driver-class-name=${ds_driver_class}
spring.datasource.url=${ds_url}
spring.datasource.username=${ds_username}
spring.datasource.password=${ds_password}
spring.datasource.tomcat.max-active=${ds_max_active}
spring.datasource.tomcat.test-while-idle=true
spring.datasource.tomcat.validation-query=select 1
spring.datasource.tomcat.default-auto-commit=false
spring.datasource.tomcat.min-idle=${ds_min_idle}
spring.datasource.tomcat.initial-size=${ds_initial_size}

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