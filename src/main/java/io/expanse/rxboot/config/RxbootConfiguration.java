/*
 * Copyright 2016
 * Released under the Apache 2 license
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @authors Ryan Scott, Greg Whitaker
 */
package io.expanse.rxboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring application configuration. This class configures Spring's {@link RequestMappingHandlerAdapter}
 * by adding {@link ObservableReturnValueHandler} and {@link FlowableReturnValueHandler}. This creates a return value mapping for controllers returning
 * straight {@link io.reactivex.Observable} and {@link io.reactivex.Flowable} types.
 */
@Configuration
public class RxbootConfiguration {

    @Resource
    private RequestMappingHandlerAdapter mappingHandlerAdapter;

    @PostConstruct
    public void init() {
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(
                mappingHandlerAdapter.getReturnValueHandlers()
        );
        handlers.add(0, observMethodReturnValueHandler());
        handlers.add(1, flowableMethodReturnValueHandler());

        mappingHandlerAdapter.setReturnValueHandlers(handlers);
    }

    @Bean
    public HandlerMethodReturnValueHandler observMethodReturnValueHandler() {
        return new ObservableReturnValueHandler();
    }

    @Bean
    public HandlerMethodReturnValueHandler flowableMethodReturnValueHandler() {
        return new FlowableReturnValueHandler();
    }
}
