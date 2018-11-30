package com.blauband.ods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergeizenevich
 */
public class ArticleDto implements Serializable {
    
    public static List<ArticleDto> getDefaults() {
        final List<ArticleDto> dtos = new ArrayList<>();
        dtos.add(new ArticleDto("302-120-092", "59,95 €"));
        dtos.add(new ArticleDto("302-120-098", "59,95 €"));
        dtos.add(new ArticleDto("302-120-104", "59,95 €"));
        dtos.add(new ArticleDto("302-120-110", "59,95 €"));
        dtos.add(new ArticleDto("302-120-116", "59,95 €"));
        dtos.add(new ArticleDto("302-120-128", "59,95 €"));
        dtos.add(new ArticleDto("302-120-140", "59,95 €"));
        dtos.add(new ArticleDto("302-120-152", "59,95 €"));
        dtos.add(new ArticleDto("302-120-164", "71,95 €"));
        dtos.add(new ArticleDto("302-120-176", "71,95 €"));
        return dtos;
    }

    public String name;
    public String price;

    public ArticleDto() {
    }

    public ArticleDto(String name, String price) {
        this.name = name;
        this.price = price;
    }
}
