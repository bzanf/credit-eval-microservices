package io.github.bzanf.creditevaluatorms.infrastructure.clients;

import io.github.bzanf.creditevaluatorms.application.responses.CardByIncomeResponse;
import io.github.bzanf.creditevaluatorms.application.responses.CardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "card-microservice", path = "/cards")
public interface CardServiceClient {

    @GetMapping(params = "idCode")
    ResponseEntity<List<CardResponse>> findByClientIdCode(@RequestParam("idCode") String idCode);

    @GetMapping(params = "income")
    ResponseEntity<List<CardByIncomeResponse>> findCardsWithIncomeLessThanOrEqual(@RequestParam("income") Long income);

}
