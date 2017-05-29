/*
 * Copyright 2016
 * Released under the Apache 2 license
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @authors Greg Whitaker
 */
package io.expanse.rxboot.config;

import io.reactivex.Single;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Handler that ties the {@link Single} returned from a controller to a {@link DeferredResult}.<br>
 * This is used in conjunction with {@link RxbootConfiguration} to allow controllers to
 * return straight {@link Single} types.
 */
public class SingleReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Class parameterType = returnType.getParameterType();

        return Single.class.isAssignableFrom(parameterType);
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        if (returnType == null) {
            mavContainer.setRequestHandled(true);
            return;
        }

        final DeferredResult<Object> deferredResult = new DeferredResult<>();
        Single single = (Single) returnValue;
        single.subscribe(deferredResult::setResult);

        WebAsyncUtils.getAsyncManager(webRequest)
                .startDeferredResultProcessing(deferredResult, mavContainer);
    }
}
