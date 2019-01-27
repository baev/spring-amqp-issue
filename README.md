## Sample project to reproduce Spring-AMQP issue

The project reproduces issue https://github.com/spring-projects/spring-boot/issues/15697

* Clone the project 

* Run

    ```bash
    $ ./gradlew bootRun
    ```
    
    You will see the following error:
    
    ```text
    2019-01-27 22:10:24.428 ERROR 5042 --- [  restartedMain] o.s.boot.SpringApplication               : Application run failed
    
    java.lang.IllegalAccessError: class org.springframework.amqp.rabbit.listener.$Proxy55 cannot access its superinterface org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer$ContainerDelegate
        at java.lang.reflect.Proxy.defineClass0(Native Method) ~[na:1.8.0_91]
        at java.lang.reflect.Proxy.access$300(Proxy.java:228) ~[na:1.8.0_91]
        at java.lang.reflect.Proxy$ProxyClassFactory.apply(Proxy.java:642) ~[na:1.8.0_91]
        at java.lang.reflect.Proxy$ProxyClassFactory.apply(Proxy.java:557) ~[na:1.8.0_91]
        at java.lang.reflect.WeakCache$Factory.get(WeakCache.java:230) ~[na:1.8.0_91]
        at java.lang.reflect.WeakCache.get(WeakCache.java:127) ~[na:1.8.0_91]
        at java.lang.reflect.Proxy.getProxyClass0(Proxy.java:419) ~[na:1.8.0_91]
        at java.lang.reflect.Proxy.newProxyInstance(Proxy.java:719) ~[na:1.8.0_91]
        at org.springframework.aop.framework.JdkDynamicAopProxy.getProxy(JdkDynamicAopProxy.java:123) ~[spring-aop-5.1.4.RELEASE.jar:5.1.4.RELEASE]
        at org.springframework.aop.framework.ProxyFactory.getProxy(ProxyFactory.java:110) ~[spring-aop-5.1.4.RELEASE.jar:5.1.4.RELEASE]
        at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.initializeProxy(AbstractMessageListenerContainer.java:1135) ~[spring-rabbit-2.1.3.RELEASE.jar:2.1.3.RELEASE]
        at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.initialize(AbstractMessageListenerContainer.java:1161) ~[spring-rabbit-2.1.3.RELEASE.jar:2.1.3.RELEASE]
        at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.afterPropertiesSet(AbstractMessageListenerContainer.java:1109) ~[spring-rabbit-2.1.3.RELEASE.jar:2.1.3.RELEASE]
        at org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry.createListenerContainer(RabbitListenerEndpointRegistry.java:186) ~[spring-rabbit-2.1.3.RELEASE.jar:2.1.3.RELEASE]
        at org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry.registerListenerContainer(RabbitListenerEndpointRegistry.java:154) ~[spring-rabbit-2.1.3.RELEASE.jar:2.1.3.RELEASE]
        at org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry.registerListenerContainer(RabbitListenerEndpointRegistry.java:128) ~[spring-rabbit-2.1.3.RELEASE.jar:2.1.3.RELEASE]
        at org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar.registerAllEndpoints(RabbitListenerEndpointRegistrar.java:140) ~[spring-rabbit-2.1.3.RELEASE.jar:2.1.3.RELEASE]
        at org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar.afterPropertiesSet(RabbitListenerEndpointRegistrar.java:133) ~[spring-rabbit-2.1.3.RELEASE.jar:2.1.3.RELEASE]
        at org.springframework.amqp.rabbit.annotation.RabbitListenerAnnotationBeanPostProcessor.afterSingletonsInstantiated(RabbitListenerAnnotationBeanPostProcessor.java:257) ~[spring-rabbit-2.1.3.RELEASE.jar:2.1.3.RELEASE]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:863) ~[spring-beans-5.1.4.RELEASE.jar:5.1.4.RELEASE]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:863) ~[spring-context-5.1.4.RELEASE.jar:5.1.4.RELEASE]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:546) ~[spring-context-5.1.4.RELEASE.jar:5.1.4.RELEASE]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:775) [spring-boot-2.1.2.RELEASE.jar:2.1.2.RELEASE]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397) [spring-boot-2.1.2.RELEASE.jar:2.1.2.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:316) [spring-boot-2.1.2.RELEASE.jar:2.1.2.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1260) [spring-boot-2.1.2.RELEASE.jar:2.1.2.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1248) [spring-boot-2.1.2.RELEASE.jar:2.1.2.RELEASE]
        at org.example.ReportApplication.main(ReportApplication.java:13) [main/:na]
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_91]
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:1.8.0_91]
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_91]
        at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_91]
        at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) [spring-boot-devtools-2.1.2.RELEASE.jar:2.1.2.RELEASE]
    
    ```
    
Problem only reproduces in case of using spring boot `2.1.2.RELEASE` together with `spring-boot-devtools`