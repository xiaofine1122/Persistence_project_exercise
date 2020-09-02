创建基本 web框架和 整合freemarker 在coolmeeting下README已写

##### 集成 mybatis-plus

在原继承mybatis的情况下进行修改

[MyBatis-Plus]: https://baomidou.com/guide/

参照 mybatis-plus官网

将原mybatis的依赖修改

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.0</version>
        </dependency>



修改sessionFactoryBean 为mybatis-plus的

```
 <bean class="com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean" id="sessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="typeAliasesPackage" value="com.xiaofine.meeting.model"/>
        <property name="mapperLocations">
            <value>
                classpath*:com/xiaofine/meeting/mapper/*.xml
            </value>
        </property>
    </bean>
```

其他可按原mybatis的同样使用



想使用mybatis-plus的crud接口  

mapper需要继承 BaseMapper 如下面例子所示

```
public interface DepartmentMapper extends BaseMapper<Department> 
```

service需要继承IService 如下面例子所示

```
public interface DepartmentService extends IService<Department> 
```

```
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService 
```

具体方法参见官网文档



使用样例如下：



```
 employeeService.lambdaQuery().eq(Employee::getStatus,PENDING_APPROVE).list();
```

```
departmentService.list()
```

```
departmentService.save(department)
```

```
LambdaQueryChainWrapper<Employee> wrapper = employeeService.lambdaQuery();if(employee.getEmployeename()!=null && employee.getEmployeename().length()>0){    wrapper.eq(Employee::getEmployeename,employee.getEmployeename());}if(employee.getUsername()!=null && employee.getUsername().length()>0){    wrapper.eq(Employee::getUsername,employee.getUsername());}wrapper.eq(Employee::getStatus,employee.getStatus());IPage<Employee> iPage = employeeService.page(new Page<Employee>(page,PAGE_SIZE),wrapper.getWrapper());List<Employee> employees =iPage.getRecords();
```



注意可能存在的情况

不设置id类型情况 保存新内容时会报错 需要设置@TableId



```
@TableName("MeetingParticipants")public class MeetingParticipants {    @TableId(value = "id",type = IdType.AUTO)    
private Integer id;
```

长表名保存时会默认分词  如meetingroom 会认成 meeting_room 需要手动设置表名

```
@TableName("meetingroom")public class MeetingRoom {
```



