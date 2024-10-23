package io.github.bzanf.cardmicroservice.infrastructure.rest;

import io.github.bzanf.cardmicroservice.application.commands.SaveCard;
import io.github.bzanf.cardmicroservice.application.responses.CardsByClientResponse;
import io.github.bzanf.cardmicroservice.application.services.CardService;
import io.github.bzanf.cardmicroservice.application.services.ClientCardService;
import io.github.bzanf.cardmicroservice.domain.entities.Card;
import io.github.bzanf.cardmicroservice.domain.entities.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardResource {

    private final CardService cardService;
    private final ClientCardService clientCardService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Card> findById(@PathVariable Long id) {
        Card card = cardService.findById(id);
        return ResponseEntity.ok(card);
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> findCardsWithIncomeLessThanOrEqual(@RequestParam("income") Long income) {
        List<Card> cards = cardService.findCardsWithIncomeLessThanOrEqual(income);
        return ResponseEntity.ok(cards);
    }

    @GetMapping(params = "idCode")
    public ResponseEntity<List<CardsByClientResponse>> findCardsByClientIdCode(@RequestParam("idCode") String idCode) {
        List<ClientCard> clientCardList = clientCardService.findByIdCode(idCode);

        List<CardsByClientResponse> resultList = clientCardList.stream()
                .map(CardsByClientResponse::fromModel)
                .toList();

        return ResponseEntity.ok(resultList);
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody SaveCard request) {
        Card card = request.toModel();
        Card newCard = cardService.save(card);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCard.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newCard);
    }

}
