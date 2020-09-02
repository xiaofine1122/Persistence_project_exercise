### 搭建过程

##### 搭建web基本框架

创建maven工程

修改 pom packing成war 包
右键项目 open module seetings  选Modules
增加 webapp目录
增加 src/main/webapp/WEB-INF/web.xml
这样就是web工程的样子了

修改 pom依赖  alt+insert 增加依赖快捷键
先添加

```
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-webmvc</artifactId>
<version>5.2.7.RELEASE</version>
</dependency>
```

创建 java class存放的目录  controller model service

创建spring和springmvc 的配置文件

创建 applicationContext.xml

```
<context:component-scan base-package="com.xiaofine.meeting" use-default-filters="false">
<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

创建spring-servlet.xml

```
<context:component-scan base-package="com.xiaofine.meeting" use-default-filters="true">
<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
<mvc:annotation-driven/>
```

配置web.xml

```
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>classpath:applicationContext.xml</param-value>
</context-param>
<listener>
<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<servlet>
<servlet-name>springmvc</servlet-name>
<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
<init-param>
<param-name>contextConfigLocation</param-name>
<param-value>classpath:spring-servlet.xml</param-value>
</init-param>
</servlet>
<servlet-mapping>
<servlet-name>springmvc</servlet-name>
<url-pattern>/</url-pattern>
</servlet-mapping>

<filter>
<filter-name>encodingFilter</filter-name>
<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
<init-param>
<param-name>forceRequestEncoding</param-name>
<param-value>true</param-value>
</init-param>
<init-param>
<param-name>forceResponseEncoding</param-name>
<param-value>true</param-value>
</init-param>
</filter>
<filter-mapping>
<filter-name>encodingFilter</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping>
```



##### 整合freemarker

先添加依赖

```
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-context-support</artifactId>
<version>5.2.7.RELEASE</version>
</dependency>
<dependency>
<groupId>org.freemarker</groupId>
<artifactId>freemarker</artifactId>
<version>2.3.28</version>
```

配置
freemarker-var.properties

```
root=/
```

spring-servlet.xml配置增加

```
<bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
<property name="locations">
<list>
<value>classpath:freemarker-var.properties</value>
</list>
</property>
</bean>

<bean class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
<property name="templateLoaderPath" value="/WEB-INF/ftl/"/>
<property name="defaultEncoding" value="UTF-8"/>
<property name="freemarkerVariables">

<map>
<entry key="root" value="${root}"/>
</map>

</property>
<property name="freemarkerSettings">
<props>
<prop key="template_update_delay">10</prop>
<prop key="locale">zh_CN</prop>
<prop key="datetime_format">yyyy-MM-dd HH:mm:ss</prop>
<prop key="date_format">yyyy-MM-dd</prop>
<prop key="time_format">HH:mm:ss</prop>
<prop key="number_format">#.####</prop>
</props>
</property>
</bean>

<!--视图解析器-->
<bean class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
<property name="viewClass" value="org.springframework.web.servlet.view.freemarker.FreeMarkerView"/>
<property name="suffix" value=".ftl"/>
<property name="allowRequestOverride" value="true"/>
<property name="allowSessionOverride" value="true"/>
<property name="exposeRequestAttributes" value="true"/>
<property name="exposeSessionAttributes" value="true"/>
<property name="contentType" value="text/html;charset=utf-8"/>
</bean>
```



##### 整合mybatis

pom增加 mybatis相关依赖

```
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-jdbc</artifactId>
<version>5.2.7.RELEASE</version>
</dependency>
<dependency>
<groupId>com.alibaba</groupId>
<artifactId>druid</artifactId>
<version>1.1.23</version>
</dependency>
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<version>8.0.20</version>
</dependency>
<dependency>
<groupId>org.mybatis</groupId>
<artifactId>mybatis</artifactId>
<version>3.5.5</version>
</dependency>
<dependency>
<groupId>org.mybatis</groupId>
<artifactId>mybatis-spring</artifactId>
<version>2.0.5</version>
</dependency>
```

配置连接

```
db.properties
db.username=root
db.password=123456
db.url=jdbc:mysql:///meeting?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
```

applicationContext.xml 增加mybatis配置

```
<!-- Mybatis配置-->
<context:property-placeholder location="classpath:db.properties"/>

<bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource">
<property name="username" value="${db.username}"/>
<property name="password" value="${db.password}"/>
<property name="url" value="${db.url}"/>
</bean>

<bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sessionFactoryBean">
<property name="dataSource" ref="dataSource"/>
<property name="typeAliasesPackage" value="com.xiaofine.meeting.model"/>
<property name="mapperLocations">
<value>
classpath*:com/xiaofine/meeting/mapper/*.xml
</value>
</property>
</bean>
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer" id="mapperScannerConfigurer">
<property name="sqlSessionFactoryBeanName" value="sessionFactoryBean"/>
<property name="basePackage" value="com.xiaofine.meeting.mapper"/>
</bean>
```

事务配置

>

```
<!--事务配置-->
<bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="transactionManager">
<property name="dataSource" ref="dataSource"/>
</bean>

<tx:advice id="txAdvice" transaction-manager="transactionManager">
<tx:attributes>
<tx:method name="add*"/>
<tx:method name="insert*"/>
<tx:method name="update*"/>
<tx:method name="delete*"/>
</tx:attributes>
</tx:advice>

<aop:config>
<aop:pointcut id="pcl" expression="execution(* com.xiaofine.meeting.service.*.*(..))"/>
<aop:advisor advice-ref="txAdvice" pointcut-ref="pcl"/>
</aop:config>
```



事务需要的包

```
<dependency>
<groupId>org.aspectj</groupId>
<artifactId>aspectjweaver</artifactId>
<version>1.9.5</version>
</dependency>
<dependency>
<groupId>org.aspectj</groupId>
<artifactId>aspectjrt</artifactId>
<version>1.9.5</version>
</dependency>
```


因为要xml放到类的路径下 所以要修改pom.xml 增加

```
<build>
<resources>
<resource>
<directory>src/main/java</directory>
<includes>
<include>**/*.xml</include>
</includes>
</resource>
<resource>
<directory>src/main/resources</directory>
</resource>
</resources>
</build>
```

