package org.example.config;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author charlie (Dmitry Baev).
 */
@EnableRabbit
@Configuration
public class RabbitConfig {

    @Bean
    public SecurityContextPopulatingListenerAdvice securityContextPopulatingListenerAdvice() {
        return new SecurityContextPopulatingListenerAdvice();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue resultFileQueue() {
        return new Queue("allure_result_file", false);
    }

    @Bean
    public DirectExchange resultFileExchange() {
        return new DirectExchange("result_file");
    }

    @Bean
    public Binding resultFileBinding() {
        return BindingBuilder
                .bind(resultFileQueue())
                .to(resultFileExchange())
                .with("default");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            final ConnectionFactory rabbitConnectionFactory,
            final SimpleRabbitListenerContainerFactoryConfigurer configurer,
            final SecurityContextPopulatingListenerAdvice advice) {
        final SimpleRabbitListenerContainerFactory listenerContainerFactory
                = new SimpleRabbitListenerContainerFactory();
        configurer.configure(listenerContainerFactory, rabbitConnectionFactory);
        final Advice[] adviceChain = listenerContainerFactory.getAdviceChain();
        if (Objects.isNull(adviceChain)) {
            listenerContainerFactory.setAdviceChain(advice);
        } else {
            listenerContainerFactory.setAdviceChain(
                    Stream.concat(Stream.of(adviceChain), Stream.of(advice)).toArray(Advice[]::new)
            );
        }
        return listenerContainerFactory;
    }

    public static class SecurityContextPopulatingListenerAdvice implements MethodInterceptor {

        @Override
        public Object invoke(final MethodInvocation invocation) throws Throwable {
            return invocation.proceed();
        }
    }
}
