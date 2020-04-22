# Demo

1. 商品列表页获取秒杀商品列表
2. 进入商品详情页获取秒杀商品详情
3. 秒杀开始后进入下单确认页下单并支付



### 搭建

创建maven工程,导入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.miaoshaproject</groupId>
  <artifactId>miaosha</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>miaosha</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.2.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <!-- clean lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#clean_Lifecycle -->
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- default lifecycle, jar packaging: see https://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_jar_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-jar-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
        <!-- site lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#site_Lifecycle -->
        <plugin>
          <artifactId>maven-site-plugin</artifactId>
          <version>3.7.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-project-info-reports-plugin</artifactId>
          <version>3.0.0</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>

```

添加注解,将bean设置为自动化配置,设置映射

```java
@EnableAutoConfiguration
@RestController
public class App {
    @RequestMapping("/")
    public String  home(){
        return "hello world";
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}
```

在pom.xml中集成mybatis(mysql连接驱动,数据库连接池,mybatis对springboot的支持)

```xml
<dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.41</version>
    </dependency>
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.3</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>1.3.1</version>
    </dependency>
```

application.properties文件中配置Mapper映射文件的位置

```properties
server.port=8090
mybatis.mapper-locations=classpath:mapping/*.xml
```

导入mybatis自动生成映射工具的依赖

```xml
<plugin>
          <groupId>org.mybatis.generator</groupId>
          <artifactId>mybatis-generator-maven-plugin</artifactId>
          <version>1.3.5</version>
          <dependencies>
            <dependency>
              <groupId>org.mybatis.generator</groupId>
              <artifactId>mybatis-generator-core</artifactId>
              <version>1.3.5</version>
            </dependency>
              <!--对哪种数据库进行解析-->
            <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.41</version>
            </dependency>
          </dependencies>
    	  <!--执行的动作-->
          <executions>
            <execution>
              <id>mybatis generator</id>
              <phase>package</phase>
              <goals>
                <goal>generate</goal>
              </goals>
            </execution>
          </executions>
          <configuration>
            <!--允许移动文件-->
            <verbose>true</verbose>
            <!--允许覆盖-->
            <overwrite>true</overwrite>
            <configurationFile>
              src/main/resources/mybatis-generator.xml
            </configurationFile>
          </configuration>
        </plugin>
```

编写mybatis-generator.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
<!--    <classPathEntry location="/Program Files/IBM/SQLLIB/java/db2java.zip" />-->

    <context id="DB2Tables" targetRuntime="MyBatis3">
        <!--数据库连接地址以及账号密码-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://127.0.0.1:3306/miaosha"
                        userId="root"
                        password="1234">
        </jdbcConnection>

        <javaTypeResolver >
            <property name="forceBigDecimals" value="false" />
        </javaTypeResolver>
        <!--生成DataObject类的存放位置-->
        <javaModelGenerator targetPackage="com.miaoshaproject.dataobject" targetProject="src/main/java">
            <property name="enableSubPackages" value="true" />
            <property name="trimStrings" value="true" />
        </javaModelGenerator>
        <!--生成映射文件存放的位置-->
        <sqlMapGenerator targetPackage="mapping"  targetProject="src/main/resources">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>
        <!--生成dao类的存放位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.miaoshaproject.dao"  targetProject="src/main/java">
            <property name="enableSubPackages" value="true" />
        </javaClientGenerator>

        <!--生成对应表及类名-->
        <table tableName="user_info" domainObjectName="UserDo"></table>
        <table tableName="user_password" domainObjectName="UserPasswordDo"></table>

    </context>
</generatorConfiguration>
```

Run -> Edit Configurations -> maven -> command-line:mybatis-generator:generate,自动生成,dao和映射文件

配置数据库连接池,application.properties

```properties
#DataSource
spring.datasource.name=miaosha
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/miaosha
spring.datasource.username=root
spring.datasource.password=1234

#使用druid数据源
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

重新编写

```java
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject"})
@RestController
@MapperScan("com.miaoshaproject.dao")
public class App {

    @Autowired(required = false)
    private UserDoMapper userDoMapper;
    @RequestMapping("/")
    public String  home(){
        UserDo userDo = userDoMapper.selectByPrimaryKey(1);
        if(userDo == null){
            return "用户对象不存在";
        }else {
            return userDo.getName();
        }

    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}
```

**使用SpringMVC方式开发用户信息**

**service**

1.UserServiceImpl

```java

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserDoMapper userDoMapper;

    @Autowired(required = false)
    private UserPasswordDoMapper userPasswordDoMapper;
    @Override
    public UserModel getUserById(Integer id) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(1);
        if(userDo == null){
            return null;
        }
        //通过用户id用户加密密码信息
        UserPasswordDo userPasswordDo = userPasswordDoMapper.selectByUserId(userDo.getId());

        return convertFromDataObject(userDo,userPasswordDo);
    }

    /**
     * 用户密码和userDo在数据库中是分开存放的,但是逻辑上来说应该是一个实体
     *  因此要新建一个实体用来封装UserPasswordDo和UserDo
     * @param userDo
     * @param userPasswordDo
     * @return
     */
    private UserModel convertFromDataObject(UserDo userDo, UserPasswordDo userPasswordDo){
       if(userDo == null){
           return null;
       }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDo,userModel);
        if(userPasswordDo != null ){
            userModel.setEncryptPassword(userPasswordDo.getEncrptPassword());
        }
        return userModel;
    }
}
```

2. model

   用于封装逻辑上属于一个对象但是存储在两张表中的信息

   > 本项目中用户密码和用户的其他信息是分开存放的

   ```java
   public class UserModel {
       private Integer id;
       private String name;
       private Byte gender;
       private Integer age;
       private String telephone;
       private String registerMode;
       private String thirdPartyid;
   
       private String encryptPassword;
   
       public String getEncryptPassword() {
           return encryptPassword;
       }
   
       public void setEncryptPassword(String encryptPassword) {
           this.encryptPassword = encryptPassword;
       }
   
       public Integer getId() {
           return id;
       }
   
       public void setId(Integer id) {
           this.id = id;
       }
   
       public String getName() {
           return name;
       }
   
       public void setName(String name) {
           this.name = name;
       }
   
       public Byte getGender() {
           return gender;
       }
   
       public void setGender(Byte gender) {
           this.gender = gender;
       }
   
       public Integer getAge() {
           return age;
       }
   
       public void setAge(Integer age) {
           this.age = age;
       }
   
       public String getTelephone() {
           return telephone;
       }
   
       public void setTelephone(String telephone) {
           this.telephone = telephone;
       }
   
       public String getRegisterMode() {
           return registerMode;
       }
   
       public void setRegisterMode(String registerMode) {
           this.registerMode = registerMode;
       }
   
       public String getThirdPartyid() {
           return thirdPartyid;
       }
   
       public void setThirdPartyid(String thirdPartyid) {
           this.thirdPartyid = thirdPartyid;
       }
   
   
   }
   ```

   

**controller**

1. UserVO

   直接将查询出来的数据全部返回给前端是不安全的,因此只需要返回一部分

   ```java
   public class UserVO {
       private Integer id;
       private String name;
       private Byte gender;
       private Integer age;
       private String telephone;
   
       public Integer getId() {
           return id;
       }
   
       public void setId(Integer id) {
           this.id = id;
       }
   
       public String getName() {
           return name;
       }
   
       public void setName(String name) {
           this.name = name;
       }
   
       public Byte getGender() {
           return gender;
       }
   
       public void setGender(Byte gender) {
           this.gender = gender;
       }
   
       public Integer getAge() {
           return age;
       }
   
       public void setAge(Integer age) {
           this.age = age;
       }
   
       public String getTelephone() {
           return telephone;
       }
   
       public void setTelephone(String telephone) {
           this.telephone = telephone;
       }
   }
   ```

2. controller

   ```java
   @Controller("user")
   @RequestMapping("/user")
   public class UserController {
   
       @Autowired
       private UserService userService;
       @RequestMapping("/get")
       @ResponseBody
       public UserVO  getUser(@RequestParam(name = "id") Integer id){//将地址栏上输入的参数和方法形参绑定
           //调用service获取对应用户对象
           UserModel userModel = userService.getUserById(id);
           return convertFromModel(userModel);
       }
   
       /**
        * 将核心领域模型用户对象装换为可供UI使用的viewobject
        * @param userModel
        * @return
        */
       public UserVO convertFromModel(UserModel userModel){
           if(userModel == null){
               return null;
           }
           UserVO userVO = new UserVO();
           BeanUtils.copyProperties(userModel,userVO);
           return userVO;
       }
   }
   ```

   测试:

   ```txt
   http://localhost:8090/user/get?id=1
   ```

   最后在页面上返回一串json串

   **返回参数归一化**

   自定义CommunReturnType,统一返回类型

   ```java
   public class CommonReturnType {
       //若status为success,则data内为前端需要的json数据
       //若status为fail,则data内使用通用的错误码格式
       private String status;
       private Object data;
   
       public static CommonReturnType create(Object result) {
           return CommonReturnType.create(result,"SUCCESS");
       }
   
       public static CommonReturnType create(Object result,String status) {
           CommonReturnType type = new CommonReturnType();
           type.setStatus(status);
           type.setData(result);
           return type;
       }
   
       public String getStatus() {
           return status;
       }
   
       public void setStatus(String status) {
           this.status = status;
       }
   
       public Object getData() {
           return data;
       }
   
       public void setData(Object data) {
           this.data = data;
       }
   }
   
   ```

   自定义EnumBusinessException,统一错误码处理

   ```java
   public enum EnumBusinessError implements CommonError{
       //通用错误类型10001
       PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
       UNKNOWN_ERROR(10002,"未知错误"),
       //20000开头是用户信息相关错误定义
        USER_NOT_EXIST(10001,"用户不存在")
       ;
   
       private EnumBusinessError(int errCode, String errMsg) {
           this.errCode = errCode;
           this.errMsg = errMsg;
       }
   
       private int  errCode;
       private String errMsg;
   
       @Override
       public int getErrCode() {
           return this.errCode;
       }
   
       @Override
       public String getErrMsg() {
           return this.errMsg;
       }
   
       @Override
       public CommonError setErrMsg(String errMsg) {
           this.errMsg = errMsg;
           return this;
       }
   }
   ```

   controller层的处理

   ```java
   public class BaseController {
       //异常抛出到容器层后需要用Handler处理(自定义的业务异常不会被controller接受)
       @ExceptionHandler(Exception.class)
       @ResponseStatus(HttpStatus.OK)
       @ResponseBody
       public Object handlerException(HttpServletRequest request, Exception ex){
           BusinessException businessException = (BusinessException)ex;
           Map<String,Object> responseData = new HashMap<>();
           if(ex instanceof  BusinessException){
               responseData.put("errCode",businessException.getErrCode());
               responseData.put("errMsg",businessException.getErrMsg());
           }else {
               responseData.put("errCode", EnumBusinessError.UNKNOWN_ERROR.getErrCode());
               responseData.put("errMsg",EnumBusinessError.UNKNOWN_ERROR.getErrMsg());
           }
           return CommonReturnType.create(responseData,"fail");
       }
   }
   ```

   验证码模块

   ```java
    @Autowired
       private HttpServletRequest httpServletRequest;
   
       //用户获取opt验证码短信接口
       @RequestMapping("/getopt")
       @ResponseBody
       public CommonReturnType getOpt(@RequestParam(name = "telephone")String telephone){
           //1.按照一定的规则生成OPT验证码
           Random random = new Random();
           int randomInt = random.nextInt(99999);
           randomInt += 10000;
           String optCode = String.valueOf(randomInt);
           //2.将OPT验证码同对应用户的手机号关联,使用httpSession的方式将验证码和telephone关联
           httpServletRequest.getSession().setAttribute(telephone,optCode);
   
           //3.将OPT验证码通过短信发送给用户(暂时不做)
           System.out.println("telephone = "+telephone+"& optCode = "+optCode);
   
           return CommonReturnType.create(null);
       }
   ```

   校验模块
   
   继承javax的validator接口,自己实现一个校验器.在UserModel的需要校验的字段上注明错误类型和错误信息即可
   
   ```java
   
   /**
    * 自定义校验类
    */
   @Component
   public class ValidatorImpl implements InitializingBean {
       private Validator validator;
       //实现校验方法并返回校验结果
       public ValidationResult validate(Object  bean){
           ValidationResult result = new ValidationResult();
           //如果不符合校验规则,set就不为空
           Set<ConstraintViolation<Object>> constraintViolationSet =  validator.validate(bean);
           if(constraintViolationSet.size() > 0){
               result.setHasErrors(true);
               //遍历校验结果set,提取那个字段出现那个错误
               constraintViolationSet.forEach(constraintViolation -> {
                   String errMsg = constraintViolation.getMessage();
                   String propertyName = constraintViolation.getPropertyPath().toString();
                   result.getErrorMsgMap().put(propertyName,errMsg);
               });
           }
           return result;
       }
   
       //Bean初始化完成之后回调这个函数
       @Override
       public void afterPropertiesSet() throws Exception {
           //将hibernate validator通过工厂的初始化方式使其初始化
           this.validator = Validation.buildDefaultValidatorFactory().getValidator();
       }
   }
   //
    private Integer id;
       @NotBlank(message = "用户名不能为空")
       private String name;
       @NotNull(message = "性别不能不填写")
       private Byte gender;
       @NotNull(message = "年龄不能不填")
       @Min(value=0,message = "年龄必须大于0")
       @Max(value = 150,message = "年龄必须小于150岁")
       private Integer age;
       @NotNull(message = "手机号不能为空")
       private String telephone;
       private String registerMode;
       private String thirdPartyid;
   
       @NotNull(message = "密码不能为空")
       private String encryptPassword;
   
   ```
   
   >先开发领域模型(UserModel),再尝试设计数据库表
   >
   >库存





