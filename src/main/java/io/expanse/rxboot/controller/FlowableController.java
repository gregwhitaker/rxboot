/*
 * Copyright 2016
 * Released under the Apache 2 license
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @authors Greg Whitaker
 */
package io.expanse.rxboot.controller;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class FlowableController {

    private static final Logger LOG = LoggerFactory.getLogger(FlowableController.class);

    @RequestMapping(value = "/flowable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flowable<Map<String, String>> getMessage() {
        return Flowable.just(Collections.singletonMap("message", "Hello World!"))
                .subscribeOn(Schedulers.computation());
    }
}
