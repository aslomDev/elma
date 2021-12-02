package com.mycompany.myapp.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CardOfElmaSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("pan", table, columnPrefix + "_pan"));
        columns.add(Column.aliased("masked_pan", table, columnPrefix + "_masked_pan"));
        columns.add(Column.aliased("type_card", table, columnPrefix + "_type_card"));
        columns.add(Column.aliased("card_type_elma", table, columnPrefix + "_card_type_elma"));
        columns.add(Column.aliased("expire_date", table, columnPrefix + "_expire_date"));
        columns.add(Column.aliased("balance", table, columnPrefix + "_balance"));

        return columns;
    }
}
