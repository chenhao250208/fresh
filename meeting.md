# 软件工程第二次会议资料
## 会议安排
![5](https://github.com/chenhao250208/fresh/blob/master/blob/5.jpg)
## 开发流程
![4](https://github.com/chenhao250208/fresh/tree/master/blob/4.jpg)

## 开发规范
### 类与对象
1. 类的属性及方法:&nbsp;&nbsp;使用*驼峰命名法：getUserName*
2. 类名:&nbsp;&nbsp;使用*帕斯卡命名法：User*
3. 数据库属性名:&nbsp;&nbsp;*下划线命名：user_name*
### 文件
1. Mapper文件:&nbsp;&nbsp;```MapperName + **Mapper.xml/java**(UserMapper.java)```
2. Service接口文件:&nbsp;&nbsp;ServiceName + **Service.java**(UserLoginService.java LogService.java)
3. Service实现类文件:&nbsp;&nbsp;ServiceName + **ServiceImpl.java**(UserLoginImpl.java LogServiceImpl.java)
4. Controller文件:&nbsp;&nbsp;ControllerName + **Controller.java**(UserController.java UserApi.java)
### URL
1. RESTful命名
```java
@GetMapping("/edit/{userId}")
```
2. 视图返回使用相对路径
```java
    @GetMapping("/index")
    public String index(){
        return "background/user/index";
    }
```
### 返回的json信息
```json
{
   msg : "200",
   info : {
      name1 : attr1,
      name2 : attr2,
      name3 : attr3
   }
}
```

## 使用技术简介
- **Java**
- **SpringBoot**
- **Thymeleaf**
- **Shiro**
- **HTML、CSS、JavaScript**
### Java
> Java开发的基础知识，涉及**类与对象**、**继承与接口**、简单集合的使用、数据类型与变量。<br/>
> Web开发的基本概念，比如**request、response**。
### SpringBoot
&nbsp;&nbsp;&nbsp;&nbsp;首先简单介绍一下**SSM框架**，因为**SpringBoot**是对**SSM**的精简和加强版本。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;SSM框架指的是**Spring、SpringMVC、MyBatis**三个应用框架的集合。与传统的**MVC**开发模式一致，SSM也是标准的MVC框架。其中Model指的是与数据库相关联的模型表示，通俗的说，是与数据库相交互的类，举个简单的例子：User类会对应数据库的user关系表，它们之间是一一对应的，这里我们可以把这一系列类称为DAO、Entity或者POJO，它们的定义中只包括getter、setter方法:
```java
// @Data
public class User {
   private String userId;
   private String name;
   private int age;

   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }
   // 其余属性类似
}
```
&nbsp;&nbsp;&nbsp;&nbsp;完成类的定义后，需要完成对数据库的增删改查等操作，这些不需要再使用原生的SQL语句实现，因为我们使用了MyBatis这一ORM(Object Relational Mapping)框架。在使用框架的前提上，我们可以书写相应的SQL配置文件(\*Mapper.xml文件)，然后定义Mapper接口，就可以创建接口的引用调用同名的方法，从而完成业务中对数据库的操作。更进一步的，这类Mapper配置文件可以部分自动生成，所以我们可以更加关注业务的实现。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;Spring的核心是IOC和DI，详细原理在我们这个框架中可以不去了解，当做黑匣子使用。重点需要掌握业务的实现方法。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;SpringMVC是SSM框架的控制器，它起着控制整个Web应用从Http请求到数据库请求，然后请求相应视图并进行渲染返回给用户的过程。<hr/>
&nbsp;&nbsp;&nbsp;&nbsp;下面进入正题，SpringBoot框架的使用。我们在编码时，可能需要使用不同的插件来完成相应的功能，会导入不同的jar包。举个简单地例子:
```xml
<dependency>
   <groupId>commons-lang</groupId>
   <artifactId>commons-lang</artifactId>
   <version>2.6</version>
</dependency>
```
&nbsp;&nbsp;&nbsp;&nbsp;在pom.xml文件中，类似上述的代码段有很多，通过插入这类代码，就可以将jar包便捷的导入到你的项目中。比如上述是将commons-lang-2.6.jar导入，其中提供了一些很实用的工具类。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;SpringBoot与SSM不同的地方在于，前者需要的配置文件大大减少了，最后只剩下application.yml或.properties，而SSM需要进行大量的配置。其他的地方都与SSM类似，所以要做的是先理解开发流程，然后理解MVC的开发模式，还有Web开发的基本概念，最后完成相应的工作。后面会对开发过程和一些概念做基本介绍。
### Shiro
&nbsp;&nbsp;&nbsp;&nbsp;权限控制系统
![7](https://github.com/chenhao250208/fresh/tree/master/blob/7.png)
![8](https://github.com/chenhao250208/fresh/tree/master/blob/8.png)
### Thymeleaf
&nbsp;&nbsp;&nbsp;&nbsp;模板语言，可以便于前端的开发。与jsp文件不同，thymeleaf作为SpringBoot的标配，比jsp更加简洁，但写出来的是html文件，可以不需要服务端程序也能看到效果。同时也支持页面的重用，比如页边框、菜单栏、页脚均可以使用。具体语法可以在网上找到教程。
### HTML、CSS、JavaScript
&nbsp;&nbsp;&nbsp;&nbsp;前端开发三件套，其中JS最为重要，因为很多框架都可以被我们使用。比如：统计报表功能可以使用ECharts进行绘制相关图表、JQuery在各方面都可以应用(Ajax、Selector)、LayUI可以方便地制作遮罩窗口等等。相关的CSS框架也有很多，比如易于上手的Bootstrap。

## 项目目录介绍
![1](https://github.com/chenhao250208/fresh/tree/master/blob/1.jpg)
![2](https://github.com/chenhao250208/fresh/tree/master/blob/2.jpg)
![3](https://github.com/chenhao250208/fresh/tree/master/blob/3.jpg)
- fresh是项目目录
- src是主要的代码区，这里需要介绍一下Maven。Maven是一款项目构建工具，可以对jar包进行有效的管理，现在所看到的项目目录就是由Maven自动生成的。项目运行依赖于src下的main目录。
- main下的com.store.fresh包下有许多包，里面的内容等会详细介绍。
- src下的resources目录存放操作数据库的配置文件、springboot配置文件、模板文件templates、静态文件static（html、css、js、各类插件） 
### 详细介绍重要目录、文件、概念
> ......

### 开发流程
![6](https://github.com/chenhao250208/fresh/tree/master/blob/6.jpg)

## 鸣谢
```javascript
{
   author : [
            "chen hao", 
            "liang lei"
            ],
   date : "2019-04-14",
   emoji : 🤖🤖😉😉
}
```
