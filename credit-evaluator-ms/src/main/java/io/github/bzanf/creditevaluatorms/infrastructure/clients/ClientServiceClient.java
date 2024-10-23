package io.github.bzanf.creditevaluatorms.infrastructure.clients;

import io.github.bzanf.creditevaluatorms.application.responses.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client-microservice", path = "/clients")
public interface ClientServiceClient {

    @GetMapping(params = "idCode")
    ResponseEntity<ClientResponse> findByIdCode(@RequestParam("idCode") String idCode);

}
