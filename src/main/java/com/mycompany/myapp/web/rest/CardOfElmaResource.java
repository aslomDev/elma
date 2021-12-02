package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.CardOfElma;
import com.mycompany.myapp.repository.CardOfElmaRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.CardOfElma}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CardOfElmaResource {

    private final Logger log = LoggerFactory.getLogger(CardOfElmaResource.class);

    private final CardOfElmaRepository cardOfElmaRepository;

    public CardOfElmaResource(CardOfElmaRepository cardOfElmaRepository) {
        this.cardOfElmaRepository = cardOfElmaRepository;
    }

    /**
     * {@code GET  /card-of-elmas} : get all the cardOfElmas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cardOfElmas in body.
     */
    @GetMapping("/card-of-elmas")
    public Mono<List<CardOfElma>> getAllCardOfElmas() {
        log.debug("REST request to get all CardOfElmas");
        return cardOfElmaRepository.findAll().collectList();
    }

    /**
     * {@code GET  /card-of-elmas} : get all the cardOfElmas as a stream.
     * @return the {@link Flux} of cardOfElmas.
     */
    @GetMapping(value = "/card-of-elmas", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CardOfElma> getAllCardOfElmasAsStream() {
        log.debug("REST request to get all CardOfElmas as a stream");
        return cardOfElmaRepository.findAll();
    }

    /**
     * {@code GET  /card-of-elmas/:id} : get the "id" cardOfElma.
     *
     * @param id the id of the cardOfElma to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cardOfElma, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/card-of-elmas/{id}")
    public Mono<ResponseEntity<CardOfElma>> getCardOfElma(@PathVariable Long id) {
        log.debug("REST request to get CardOfElma : {}", id);
        Mono<CardOfElma> cardOfElma = cardOfElmaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cardOfElma);
    }
}
