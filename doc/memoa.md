AOP（面向切面编程）
      面向切面编程（AOP是Aspect Oriented Program的首字母缩写） ，我们知道，面向对象的特点是继承、多态和封装。而封装就要求将功能分散到不同的对象中去，这在软件设计中往往称为职责分配。实际上也就是说，让不同的类设计不同的方法。这样代码就分散到一个个的类中去了。这样做的好处是降低了代码的复杂程度，使类可重用。

      但是人们也发现，在分散代码的同时，也增加了代码的重复性。什么意思呢？比如说，我们在两个类中，可能都需要在每个方法中做日志。按面向对象的设计方法，我们就必须在两个类的方法中都加入日志的内容。也许他们是完全相同的，但就是因为面向对象的设计让类与类之间无法联系，而不能将这些重复的代码统一起来。

    也许有人会说，那好办啊，我们可以将这段代码写在一个独立的类独立的方法里，然后再在这两个类中调用。但是，这样一来，这两个类跟我们上面提到的独立的类就有耦合了，它的改变会影响这两个类。那么，有没有什么办法，能让我们在需要的时候，随意地加入代码呢？这种在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程。

       一般而言，我们管切入到指定类指定方法的代码片段称为切面，而切入到哪些类、哪些方法则叫切入点。有了AOP，我们就可以把几个类共有的代码，抽取到一个切片中，等到需要时再切入对象中去，从而改变其原有的行为。这样看来，AOP其实只是OOP的补充而已。OOP从横向上区分出一个个的类来，而AOP则从纵向上向对象中加入特定的代码。有了AOP，OOP变得立体了。如果加上时间维度，AOP使OOP由原来的二维变为三维了，由平面变成立体了。从技术上来说，AOP基本上是通过代理机制实现的。      AOP在编程历史上可以说是里程碑式的，对OOP编程是一种十分有益的补充。

       AOP不一定都像Spring AOP那样，是在运行时生成代理对象来织入的，还可以在编译期、类加载期织入，比如AspectJ。AOP像OOP一样，只是一种编程范式，AOP并没有规定说，实现AOP协议的代码，要用什么方式去实现。

什么是代理模式，就是我再生成一个代理类，去代理UserController的saveUser()方法，代码大概就长这样：

class UserControllerProxy {
    private UserController userController;
 
    public void saveUser() {
        checkAuth();
        userController.saveUser();
    }
}
       代理分为静态代理和动态代理，静态代理，顾名思义，就是你自己写代理对象，动态代理，则是在运行期，生成一个代理对象。Spring AOP就是基于动态代理的，但是不是所有AOP的实现都是在运行时进行织入的，因为这样效率太低了，而且只能针对方法进行AOP，无法针对构造函数、字段进行AOP。我完全可以在编译成class时就织入啊，比如AspectJ，当然AspectJ还提供了后编译器织入和类加载期织入。
AOP概念

　　— 方面（Aspect）：一个关注点的模块化，这个关注点实现可能另外横切多个对象。事务管理是J2EE应用中一个很好的横切关注点例子。方面用Spring的Advisor或拦截器实现。

　　— 连接点（Joinpoint）：程序执行过程中明确的点，如方法的调用或特定的异常被抛出。

　　— 通知（Advice）：在特定的连接点，AOP框架执行的动作。各种类型的通知包括“around”、“before”和“throws”通知。通知类型将在下面讨论。许多AOP框架包括Spring都是以拦截器做通知模型，维护一个“围绕”连接点的拦截器链。

　　— 切入点（Pointcut）：指定一个通知将被引发的一系列连接点的集合。AOP框架必须允许开发者指定切入点，例如，使用正则表达式。

　　— 引入（Introduction）：添加方法或字段到被通知的类。Spring允许引入新的接口到任何被通知的对象。例如，你可以使用一个引入使任何对象实现IsModified接口，来简化缓存。

　　— 目标对象（Target Object）：包含连接点的对象，也被称作被通知或被代理对象。

　　— AOP代理（AOP Proxy）：AOP框架创建的对象，包含通知。在Spring中，AOP代理可以是JDK动态代理或CGLIB代理。

　　— 编织（Weaving）：组装方面来创建一个被通知对象。这可以在编译时完成（例如使用AspectJ编译器），也可以在运行时完成。Spring和其他纯Java AOP框架一样，在运行时完成织入。

　　各种通知类型包括：

　　—  Around通知：包围一个连接点的通知，如方法调用。这是最强大的通知。Aroud通知在方法调用前后完成自定义的行为，它们负责选择继续执行连接点或通过返回它们自己的返回值或抛出异常来短路执行。

　　—  Before通知：在一个连接点之前执行的通知，但这个通知不能阻止连接点前的执行（除非它抛出一个异常）。

　　—  Throws通知：在方法抛出异常时执行的通知。Spring提供强制类型的Throws通知，因此你可以书写代码捕获感兴趣的异常（和它的子类），不需要从Throwable或Exception强制类型转换。

　　—  After returning通知：在连接点正常完成后执行的通知，例如，一个方法正常返回，没有抛出异常。

　　Around通知是最通用的通知类型。大部分基于拦截的AOP框架（如Nanning和Jboss 4）只提供Around通知。

　　如同AspectJ，Spring提供所有类型的通知，我们推荐你使用最为合适的通知类型来实现需要的行为。例如，如果只是需要用一个方法的返回值来更新缓存，你最好实现一个after returning通知，而不是around通知，虽然around通知也能完成同样的事情。使用最合适的通知类型使编程模型变得简单，并能减少潜在错误。例如，你不需要调用在around通知中所需使用的MethodInvocation的proceed()方法，因此就调用失败。

　　切入点的概念是AOP的关键，它使AOP区别于其他使用拦截的技术。切入点使通知独立于OO的层次选定目标。例如，提供声明式事务管理的around通知可以被应用到跨越多个对象的一组方法上。 因此切入点构成了AOP的结构要素。


Filter（过虑器）
Filter技术是Servlet2.3新增加的功能，Servlet2.3是sun公司于2000年10月发布的，它的开发者包括许多个人和公司团体，充分体现了sun公司所倡导的代码开放性原则。在众多参与者的共同努力下，Servlet2.3比以往功能都强大了很多，而且性能也有了提高。那么Filter有什么功能呢？Filter可以用来设置字符集、控制权限、控制转向等等。简单点说：就是定义在访问某对象之前和访问之后要做的事情。

Interceptor（拦截器）
1，拦截器的概念
    java里的拦截器是动态拦截Action调用的对象，它提供了一种机制可以使开发者在一个Action执行的前后执行一段代码，也可以在一个Action
执行前阻止其执行，同时也提供了一种可以提取Action中可重用部分代码的方式。在AOP中，拦截器用于在某个方法或者字段被访问之前，进行拦截
然后再之前或者之后加入某些操作。目前，我们需要掌握的主要是Spring的拦截器，Struts2的拦截器不用深究，知道即可。

2，拦截器的原理
    大部分时候，拦截器方法都是通过代理的方式来调用的。Struts2的拦截器实现相对简单。当请求到达Struts2的ServletDispatcher时，Struts2
会查找配置文件，并根据配置实例化相对的拦截器对象，然后串成一个列表（List），最后一个一个的调用列表中的拦截器。Struts2的拦截器是可
插拔的，拦截器是AOP的一个实现。Struts2拦截器栈就是将拦截器按一定的顺序连接成一条链。在访问被拦截的方法或者字段时，Struts2拦截器链
中的拦截器就会按照之前定义的顺序进行调用。

3，自定义拦截器的步骤
    第一步：自定义一个实现了Interceptor接口的类，或者继承抽象类AbstractInterceptor。
    第二步：在配置文件中注册定义的拦截器。
    第三步：在需要使用Action中引用上述定义的拦截器，为了方便也可以将拦截器定义为默认的拦截器，这样在不加特殊说明的情况下，所有的
Action都被这个拦截器拦截。

4，过滤器与拦截器的区别
    过滤器可以简单的理解为“取你所想取”，过滤器关注的是web请求；拦截器可以简单的理解为“拒你所想拒”，拦截器关注的是方法调用，比如拦截
敏感词汇。
4.1，拦截器是基于java反射机制来实现的，而过滤器是基于函数回调来实现的。（有人说，拦截器是基于动态代理来实现的）
4.2，拦截器不依赖servlet容器，过滤器依赖于servlet容器。
4.3，拦截器只对Action起作用，过滤器可以对所有请求起作用。
4.4，拦截器可以访问Action上下文和值栈中的对象，过滤器不能。
4.5，在Action的生命周期中，拦截器可以多次调用，而过滤器只能在容器初始化时调用一次。

5，Spring拦截器
5.1，抽象类HandlerInterceptorAdapter
    我们如果在项目中使用了Spring框架，那么，我们可以直接继承HandlerInterceptorAdapter.java这个抽象类，来实现我们自己的拦截器。

Spring框架，对java的拦截器概念进行了包装，这一点和Struts2很类似。HandlerInterceptorAdapter继承了抽象接口HandlerInterceptor。

spring boot 配置Filter过滤器
1、通过 @WebFilter 注解来配置
@Component
@WebFilter(urlPatterns = "/webapi/*", filterName = "authFilter")
public class AuthFilter implements Filter {
    ......
}
2、通过 @Bean 注解来配置
我这个是写在带 @SpringBootApplication 注解的类里面的。

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new TestFilter());
        registration.addUrlPatterns("/webapi/*"); //
        registration.addInitParameter("paramName", "paramValue"); //
        registration.setName("testFilter");
        return registration;
    }
那如果有多个过虑器，怎么指定执行的顺序呢？
通过 registration.setOrder(1);  来设置，例如：

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new TestFilter());
        registration.addUrlPatterns("/webapi/*"); //
        registration.addInitParameter("paramName", "paramValue"); //
        registration.setName("testFilter");
        registration.setOrder(1);
        return registration;
    }
 
    @Bean
    public FilterRegistrationBean authFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new AuthFilter());
        registration.addUrlPatterns("/webapi/*"); //
        registration.addInitParameter("paramName", "paramValue"); //
        registration.setName("authFilter");
        registration.setOrder(2);
        return registration;
    }
注意：

1、如果指定了 Order 属性，执行的顺序与注册的顺序是无关的；

2、数字越小，优先级越高；

关于 @Order 注解
有文章提到使用 @Order 注解来指定顺序，亲测无效。

监听器配置

1、新建监听器（只列举一种监听器实现方式，有很多种监听器）

package com.jd.m.tg.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


@Component
public class MyListener implements ServletContextListener {

    private static final Logger LOG= LoggerFactory.getLogger(MyListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("ServletContextListener监听器初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("ServletContextListener监听器销毁");
    }
 }
 2、监听器配置

可以在监听器类上加@WebListener实现配置，但ServletListenerRegistrationBean实现配置更好，原理同上，看习惯；
1
package com.jd.m.tg.config;

import com.jd.m.tg.listener.MyListener;
import com.jd.m.tg.listener.MyListenerTwo;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class ListenerConfig {

    private MyListener myListener;
    private MyListenerTwo myListenerTwo;

    @Bean
    public ServletListenerRegistrationBean myFilterTwoConfig(){
        ServletListenerRegistrationBean<MyListenerTwo> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(myListenerTwo);
        return servletListenerRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListenerConfig(){
        ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(myListener);
        return servletListenerRegistrationBean;
    }
}
注：监听器配置类中对应监听器的配置对象的创建顺序就是执行顺序；

注：filter、listener、servlet都需要在启动类上加上注解@ServletComponentScan

