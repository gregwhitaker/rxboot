/*
 * Copyright 2016
 * Released under the Apache 2 license
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @authors Greg Whitaker
 */
package io.expanse.rxboot.controller;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Example controller that contain endpoints that return straight {@link Single} types.
 */
@RestController
public class SingleController {

    @RequestMapping(value = "/single", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<Map<String, String>> getMessage() {
        return Single.just(Collections.singletonMap("message", "Hello World from Single!"))
                .subscribeOn(Schedulers.computation());
    }
}
