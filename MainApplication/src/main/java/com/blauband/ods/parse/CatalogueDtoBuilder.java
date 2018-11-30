package com.blauband.ods.parse;

import com.blauband.ods.ArticleDto;
import com.blauband.ods.CatalogueDto;
import com.blauband.ods.ItemDto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author sergeizenevich
 */
public class CatalogueDtoBuilder {

    private static final String ORDINAL_CATEGORY_NUMBER = "ordinal category number";
    private static final String REFERENCE = "reference";
    private static final String REFERENCE_NO = "reference no.";
    private static final String COLOR = "color";
    private static final String PRODUCT_CATEGORY = "product category";
    private static final String GENDER = "gender";
    private static final String COMPOSITION = "composition";
    private static final String PRICE = "uvp/\nrrp";
    private static final NumberFormat CURRENCY_FORMATER = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    private final List<String> names;

    public CatalogueDtoBuilder(final List<String> names) {
        this.names = names;
    }

    public CatalogueDto build(final List<List<Object>> rows) {
        final Map<String, ItemDto> items = new HashMap<>();
        for (int i = 0; i < rows.size(); i++) {
            final RowHandler rowHandler = new RowHandler(rows.get(i));
            if (items.containsKey(rowHandler.getMarker())) {
                items.get(rowHandler.getMarker()).articles.add(rowHandler.buildArticle());
            } else {
                final ItemDto itemDto = rowHandler.buildItem();
                items.put(rowHandler.getMarker(), itemDto);
            }
        }

        final CatalogueDto result = new CatalogueDto();
        result.items = new ArrayList<>(items.values());
        Collections.sort(result.items);

        for (ItemDto each : result.items) {
            System.out.println(each);
        }

        return result;
    }

    public class RowHandler {

        private final List row;

        public RowHandler(final List row) {
            this.row = row;
        }

        private String getStringValue(final String key) {
            final int index = names.indexOf(key);
            try {
                return (String) row.get(index);
            } catch (ClassCastException e) {
                System.out.println("index: " + index);
                for (int i = 0; i < row.size(); i++) {
                    System.out.println(i + ": " + row.get(i));
                }
                throw new RuntimeException("Problem with column: " + key + " with value " + row.get(index), e);
            }
        }

        private Double getDoubleValue(final String key) {
            final int index = names.indexOf(key);
            try {
                return (Double) row.get(index);
            } catch (ClassCastException e) {
                throw new RuntimeException("Problem with column: " + key + " with value " + row.get(index), e);
            }
        }

        public String getMarker() {
            if (getStringValue(REFERENCE_NO).indexOf('-') == 4) {
                return getStringValue(REFERENCE_NO).substring(0, 8);
            } else {
                return getStringValue(REFERENCE_NO).substring(0, 7);
            }
        }

        private String buildImage1() {
            return getMarker() + ".jpg";
        }

        private String buildImage2() {
            return getMarker() + "_2.jpg";
        }

        private ItemDto buildItem() {
            final ItemDto itemDto = new ItemDto();
            itemDto.categoryNumber = getDoubleValue(ORDINAL_CATEGORY_NUMBER).intValue();
            itemDto.category = getStringValue(PRODUCT_CATEGORY);
            itemDto.reference = getStringValue(REFERENCE);
            itemDto.referenceNo = getStringValue(REFERENCE_NO);
            itemDto.color = getStringValue(COLOR);
            itemDto.gender = getStringValue(GENDER);
            itemDto.image1 = buildImage1();
            itemDto.image2 = buildImage2();
            itemDto.description = getStringValue(COMPOSITION);

            itemDto.articles = new ArrayList<>();
            itemDto.articles.add(buildArticle());
            return itemDto;
        }

        private ArticleDto buildArticle() {
            final ArticleDto articleDto = new ArticleDto();
            articleDto.name = getStringValue(REFERENCE_NO);
            articleDto.price = CURRENCY_FORMATER.format(getDoubleValue(PRICE));
            return articleDto;
        }
    }
}
