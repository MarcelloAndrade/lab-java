package com.java.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(url = "${path.url}", name = "name-client")
public interface ApiClient {


    @PostMapping(path = "/object")
    Object createObject(
            @RequestBody ObjectBody body,
            @RequestHeader(value = "integrationToken", required = true) String integrationToken,
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "token", required = false) String token);

    @GetMapping(path = "/object/{objectUID}")
    Object getObject(
            @RequestHeader(value = "integrationToken", required = true) String integrationToken,
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "token", required = false) String token,
            @PathVariable("objectUID") UUID objectUID,
            @RequestParam(value = "repository-full-name", required = false) String repositoryFullName);

    @GetMapping(path = "/object/{modelUID}")
    Object searchObject(
            @RequestHeader(value = "integrationToken", required = true) String integrationToken,
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "token", required = false) String token,
            @PathVariable("modelUID") UUID modelUID,
            @RequestParam(value = "fields") String fields);

}