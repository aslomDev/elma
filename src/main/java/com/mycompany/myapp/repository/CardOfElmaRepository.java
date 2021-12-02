package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CardOfElma;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the CardOfElma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CardOfElmaRepository extends R2dbcRepository<CardOfElma, Long>, CardOfElmaRepositoryInternal {
    // just to avoid having unambigous methods
    @Override
    Flux<CardOfElma> findAll();

    @Override
    Mono<CardOfElma> findById(Long id);

    @Override
    <S extends CardOfElma> Mono<S> save(S entity);
}

interface CardOfElmaRepositoryInternal {
    <S extends CardOfElma> Mono<S> insert(S entity);
    <S extends CardOfElma> Mono<S> save(S entity);
    Mono<Integer> update(CardOfElma entity);

    Flux<CardOfElma> findAll();
    Mono<CardOfElma> findById(Long id);
    Flux<CardOfElma> findAllBy(Pageable pageable);
    Flux<CardOfElma> findAllBy(Pageable pageable, Criteria criteria);
}
