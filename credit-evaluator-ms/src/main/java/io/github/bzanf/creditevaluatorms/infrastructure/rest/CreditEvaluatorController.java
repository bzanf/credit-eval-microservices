package io.github.bzanf.creditevaluatorms.infrastructure.rest;

import io.github.bzanf.creditevaluatorms.application.commands.EvaluateCredit;
import io.github.bzanf.creditevaluatorms.application.commands.RequestCardIssuance;
import io.github.bzanf.creditevaluatorms.application.responses.CreditEvaluatorResponse;
import io.github.bzanf.creditevaluatorms.application.services.CreditEvaluatorService;
import io.github.bzanf.creditevaluatorms.domain.entities.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credit-evaluator")
@RequiredArgsConstructor
public class CreditEvaluatorController {

    private final CreditEvaluatorService creditEvaluatorService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "client-situation", params = "idCode")
    public ResponseEntity<ClientCard> getClientCard(@RequestParam("idCode") String idCode) {
        ClientCard clientCard = creditEvaluatorService.fetchClientCard(idCode);
        return ResponseEntity.ok(clientCard);
    }

    @PostMapping
    public ResponseEntity<CreditEvaluatorResponse> evaluateCredit(@RequestBody EvaluateCredit request) {
        CreditEvaluatorResponse response = creditEvaluatorService
                .evaluateCredit(request.getIdCode(), request.getIncome());

        return ResponseEntity.ok(response);
    }

    @PostMapping("card-issuance")
    public ResponseEntity<String> requestCardIssuance(@RequestBody RequestCardIssuance request) {
        String protocol = creditEvaluatorService.requestCardIssuance(request);
        return ResponseEntity.ok(protocol);
    }

}
