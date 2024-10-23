package io.github.bzanf.clientmicroservice.infrastructure.rest;

import io.github.bzanf.clientmicroservice.application.commands.SaveClient;
import io.github.bzanf.clientmicroservice.application.services.ClientService;
import io.github.bzanf.clientmicroservice.domain.entities.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService service;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody SaveClient request) {
        Client client = request.toModel();
        service.save(client);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("idCode={idCode}")
                .buildAndExpand(client.getIdCode())
                .toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @GetMapping(params = "idCode")
    public ResponseEntity<Client> findByIdCode(
            @RequestParam(value = "idCode") String idCode
    ) {
        Client client = service.getByIdCode(idCode);
        return ResponseEntity.ok(client);
    }

}
