package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CardOfElmaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CardOfElma.class);
        CardOfElma cardOfElma1 = new CardOfElma();
        cardOfElma1.setId(1L);
        CardOfElma cardOfElma2 = new CardOfElma();
        cardOfElma2.setId(cardOfElma1.getId());
        assertThat(cardOfElma1).isEqualTo(cardOfElma2);
        cardOfElma2.setId(2L);
        assertThat(cardOfElma1).isNotEqualTo(cardOfElma2);
        cardOfElma1.setId(null);
        assertThat(cardOfElma1).isNotEqualTo(cardOfElma2);
    }
}
