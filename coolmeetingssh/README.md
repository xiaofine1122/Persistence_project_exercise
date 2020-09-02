创建基本 web框架和 整合freemarker 在coolmeeting下README已写
##### 整合hibernate

添加依赖

```
<!-- hibernate核心依赖-->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.2.17.Final</version>
</dependency>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>5.2.0.Final</version>
</dependency>

<!--数据库和连接池-->
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
```

配置applicationContext.xml

```
<import resource="classpath:spring-dao.xml"/>
```

db.properties 配置

```
db.username=root
db.password=123456
db.url=jdbc:mysql:///meeting?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
```

spring-dao.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd">

<!-- 开启注解扫描-->
<context:annotation-config></context:annotation-config>
<context:component-scan base-package="com.xiaofine.meeting.pojo"></context:component-scan>

<!-- 加载配置文件-->
<context:property-placeholder location="classpath:db.properties"/>
<!-- 配置数据源-->
<bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource">
    <property name="username" value="${db.username}"/>
    <property name="password" value="${db.password}"/>
    <property name="url" value="${db.url}"/>
    <property name="initialSize" value="1" />
    <property name="maxActive" value="50" />
    <property name="maxWait" value="30000" />
    <property name="filters" value="stat,wall" />
    <property name="timeBetweenEvictionRunsMillis" value="3000" />
    <property name="minEvictableIdleTimeMillis" value="300000" />
    <property name="validationQuery" value="SELECT 'x'" />
    <property name="testWhileIdle" value="true" />
    <property name="testOnBorrow" value="false" />
    <property name="testOnReturn" value="false" />
    <property name="poolPreparedStatements" value="true" />
    <property name="maxPoolPreparedStatementPerConnectionSize" value="20" />
</bean>

<!--配置sessionFactory-->
<bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
    <property name="packagesToScan" value="com.xiaofine.meeting.pojo"></property>
    <property name="hibernateProperties">
        <props>
            <prop key="hibernate.hbm2ddl.auto">update</prop>
            <prop key="hibernate.dialect">org.hibernate.dialect.MySQL55Dialect</prop>
            <prop key="hibernate.show_sql">true</prop>
            <prop key="hibernate.format_sql">true</prop>
        </props>
    </property>
</bean>
<!-- 配置事务管理-->
<bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
    <property name="sessionFactory" ref="sessionFactory"></property>
</bean>
<!-- 开启注解事务管理 -->
<tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>

<!--配置hibernatetemplate-->
<bean id="hibernateTemplate" class="org.springframework.orm.hibernate5.HibernateTemplate">
    <property name="sessionFactory" ref="sessionFactory"></property>
</bean>

</beans>
```

写dao

```
public interface BaseDao<T> {
    T findById(Serializable key);
    void save(T entity);
    void update(T entity);
    void delete(Serializable key);
    List<T> findAll();
}
```



```
@Transactional
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

public abstract Class getEntityClass();

@Autowired
private SessionFactory sessionFactory;

public SessionFactory getSessionFactory() {
    return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
}

public Session getSession() {
    return sessionFactory.getCurrentSession();
}

public void save(T entity) {
    getSession().persist(entity);
}

public void update(T entity) {
    getSession().update(entity);
}

public T findById(Serializable key) {
    return (T) getSession().get(getEntityClass(), key);
}

public void delete(Serializable key) {
    getSession().delete(findById(key));
}

@SuppressWarnings({ "unchecked", "rawtypes" })
public List<T> findAll() {
    String hql = "from "+getEntityClass().getName();
    Query query = getSession().createQuery(hql);
    return query.list();
}


}


```

```
public interface EmployeeDao extends BaseDao<Employee> {

public Employee findByName(String name);

}


@Repository("employeeDao")
@Transactional
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

public Class getEntityClass() {
    return Employee.class;
}

public Employee findByName(String name) {
    String hsql="FROM Employee where username=?";
    System.out.println(hsql);
    Query query = getSession().createQuery(hsql);
    query.setString(0,name);
    Employee employee = (Employee)query.uniqueResult();
    return employee;
}

}
```

pojo类需要加

```
@Entity
@Table
public class Employee {
@Id
private Integer employeeid;
```

