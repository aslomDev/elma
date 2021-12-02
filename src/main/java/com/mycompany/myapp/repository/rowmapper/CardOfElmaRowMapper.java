package com.mycompany.myapp.repository.rowmapper;

import com.mycompany.myapp.domain.CardOfElma;
import com.mycompany.myapp.domain.enumeration.HUMO;
import com.mycompany.myapp.domain.enumeration.credit;
import com.mycompany.myapp.service.ColumnConverter;
import io.r2dbc.spi.Row;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CardOfElma}, with proper type conversions.
 */
@Service
public class CardOfElmaRowMapper implements BiFunction<Row, String, CardOfElma> {

    private final ColumnConverter converter;

    public CardOfElmaRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CardOfElma} stored in the database.
     */
    @Override
    public CardOfElma apply(Row row, String prefix) {
        CardOfElma entity = new CardOfElma();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setPan(converter.fromRow(row, prefix + "_pan", String.class));
        entity.setMaskedPan(converter.fromRow(row, prefix + "_masked_pan", String.class));
        entity.setTypeCard(converter.fromRow(row, prefix + "_type_card", HUMO.class));
        entity.setCardTypeElma(converter.fromRow(row, prefix + "_card_type_elma", credit.class));
        entity.setExpireDate(converter.fromRow(row, prefix + "_expire_date", LocalDate.class));
        entity.setBalance(converter.fromRow(row, prefix + "_balance", BigDecimal.class));
        return entity;
    }
}
