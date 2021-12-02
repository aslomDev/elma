package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.CardOfElma;
import com.mycompany.myapp.domain.enumeration.HUMO;
import com.mycompany.myapp.domain.enumeration.credit;
import com.mycompany.myapp.repository.CardOfElmaRepository;
import com.mycompany.myapp.service.EntityManager;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link CardOfElmaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient
@WithMockUser
class CardOfElmaResourceIT {

    private static final String DEFAULT_PAN = "AAAAAAAAAA";
    private static final String UPDATED_PAN = "BBBBBBBBBB";

    private static final String DEFAULT_MASKED_PAN = "AAAAAAAAAA";
    private static final String UPDATED_MASKED_PAN = "BBBBBBBBBB";

    private static final HUMO DEFAULT_TYPE_CARD = HUMO.HUMO;
    private static final HUMO UPDATED_TYPE_CARD = HUMO.HUMO;

    private static final credit DEFAULT_CARD_TYPE_ELMA = credit.CREDIT;
    private static final credit UPDATED_CARD_TYPE_ELMA = credit.CREDIT;

    private static final LocalDate DEFAULT_EXPIRE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/card-of-elmas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CardOfElmaRepository cardOfElmaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CardOfElma cardOfElma;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CardOfElma createEntity(EntityManager em) {
        CardOfElma cardOfElma = new CardOfElma()
            .pan(DEFAULT_PAN)
            .maskedPan(DEFAULT_MASKED_PAN)
            .typeCard(DEFAULT_TYPE_CARD)
            .cardTypeElma(DEFAULT_CARD_TYPE_ELMA)
            .expireDate(DEFAULT_EXPIRE_DATE)
            .balance(DEFAULT_BALANCE);
        return cardOfElma;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CardOfElma createUpdatedEntity(EntityManager em) {
        CardOfElma cardOfElma = new CardOfElma()
            .pan(UPDATED_PAN)
            .maskedPan(UPDATED_MASKED_PAN)
            .typeCard(UPDATED_TYPE_CARD)
            .cardTypeElma(UPDATED_CARD_TYPE_ELMA)
            .expireDate(UPDATED_EXPIRE_DATE)
            .balance(UPDATED_BALANCE);
        return cardOfElma;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CardOfElma.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        cardOfElma = createEntity(em);
    }

    @Test
    void getAllCardOfElmasAsStream() {
        // Initialize the database
        cardOfElmaRepository.save(cardOfElma).block();

        List<CardOfElma> cardOfElmaList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CardOfElma.class)
            .getResponseBody()
            .filter(cardOfElma::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(cardOfElmaList).isNotNull();
        assertThat(cardOfElmaList).hasSize(1);
        CardOfElma testCardOfElma = cardOfElmaList.get(0);
        assertThat(testCardOfElma.getPan()).isEqualTo(DEFAULT_PAN);
        assertThat(testCardOfElma.getMaskedPan()).isEqualTo(DEFAULT_MASKED_PAN);
        assertThat(testCardOfElma.getTypeCard()).isEqualTo(DEFAULT_TYPE_CARD);
        assertThat(testCardOfElma.getCardTypeElma()).isEqualTo(DEFAULT_CARD_TYPE_ELMA);
        assertThat(testCardOfElma.getExpireDate()).isEqualTo(DEFAULT_EXPIRE_DATE);
        assertThat(testCardOfElma.getBalance()).isEqualByComparingTo(DEFAULT_BALANCE);
    }

    @Test
    void getAllCardOfElmas() {
        // Initialize the database
        cardOfElmaRepository.save(cardOfElma).block();

        // Get all the cardOfElmaList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(cardOfElma.getId().intValue()))
            .jsonPath("$.[*].pan")
            .value(hasItem(DEFAULT_PAN))
            .jsonPath("$.[*].maskedPan")
            .value(hasItem(DEFAULT_MASKED_PAN))
            .jsonPath("$.[*].typeCard")
            .value(hasItem(DEFAULT_TYPE_CARD.toString()))
            .jsonPath("$.[*].cardTypeElma")
            .value(hasItem(DEFAULT_CARD_TYPE_ELMA.toString()))
            .jsonPath("$.[*].expireDate")
            .value(hasItem(DEFAULT_EXPIRE_DATE.toString()))
            .jsonPath("$.[*].balance")
            .value(hasItem(sameNumber(DEFAULT_BALANCE)));
    }

    @Test
    void getCardOfElma() {
        // Initialize the database
        cardOfElmaRepository.save(cardOfElma).block();

        // Get the cardOfElma
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, cardOfElma.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(cardOfElma.getId().intValue()))
            .jsonPath("$.pan")
            .value(is(DEFAULT_PAN))
            .jsonPath("$.maskedPan")
            .value(is(DEFAULT_MASKED_PAN))
            .jsonPath("$.typeCard")
            .value(is(DEFAULT_TYPE_CARD.toString()))
            .jsonPath("$.cardTypeElma")
            .value(is(DEFAULT_CARD_TYPE_ELMA.toString()))
            .jsonPath("$.expireDate")
            .value(is(DEFAULT_EXPIRE_DATE.toString()))
            .jsonPath("$.balance")
            .value(is(sameNumber(DEFAULT_BALANCE)));
    }

    @Test
    void getNonExistingCardOfElma() {
        // Get the cardOfElma
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
