package com.blauband.ods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sergeizenevich
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "item")
public class ItemDto implements Serializable, Comparable<ItemDto> {

    public static List<ItemDto> getDefault() {
        final List<ItemDto> items = new ArrayList<>();
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("quick-dry UPF30+ T-shirt", "Kids Pointillism T", "navy/viper green", "boy", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125_2-300x300.jpg", "Extremely light down jacket (90/10 end tone). Ideal for spring and autumn activities or for mountain hikes, where pack size and every gram weight count.", ArticleDto.getDefaults()));
        items.add(new ItemDto("quick-dry UPF30+ T-shirt", "Kids Pointillism T", "navy/viper green", "boy", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125_2-300x300.jpg", "Extremely light down jacket (90/10 end tone). Ideal for spring and autumn activities or for mountain hikes, where pack size and every gram weight count.", ArticleDto.getDefaults()));
        items.add(new ItemDto("quick-dry UPF30+ T-shirt", "Kids Pointillism T", "navy/viper green", "boy", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125_2-300x300.jpg", "Extremely light down jacket (90/10 end tone). Ideal for spring and autumn activities or for mountain hikes, where pack size and every gram weight count.", ArticleDto.getDefaults()));
        items.add(new ItemDto("quick-dry UPF30+ T-shirt", "Kids Pointillism T", "navy/viper green", "boy", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125_2-300x300.jpg", "Extremely light down jacket (90/10 end tone). Ideal for spring and autumn activities or for mountain hikes, where pack size and every gram weight count.", ArticleDto.getDefaults()));
        items.add(new ItemDto("quick-dry UPF30+ T-shirt", "Kids Pointillism T", "navy/viper green", "boy", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125_2-300x300.jpg", "Extremely light down jacket (90/10 end tone). Ideal for spring and autumn activities or for mountain hikes, where pack size and every gram weight count.", ArticleDto.getDefaults()));
        items.add(new ItemDto("quick-dry UPF30+ T-shirt", "Kids Pointillism T", "navy/viper green", "boy", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-125_2-300x300.jpg", "Extremely light down jacket (90/10 end tone). Ideal for spring and autumn activities or for mountain hikes, where pack size and every gram weight count.", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));
        items.add(new ItemDto("hooded fleece jacket", "Kids Oslofjord Jacket", "navy/magenta", "girl", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100-300x300.jpg", "http://www.trollkids.com/wp-content/uploads/2017/08/118-100_2-300x300.jpg", "Bla bla\nbla-bla", ArticleDto.getDefaults()));

        return items;
    }

    private static final int ARTICLES_COUNT = 8;

    public int categoryNumber;
    public String referenceNo;
    @XmlElement
    public String id;
    @XmlElement
    public String category;
    @XmlElement
    public String reference;
    @XmlElement
    public String color;
    @XmlElement
    public String gender;
    @XmlElement
    public String image1;
    @XmlElement
    public String image2;
    @XmlElement
    public String description;
    public List<ArticleDto> articles;
    @XmlElement
    public String articlesAsString;
    @XmlElement
    public String articles2AsString;
    @XmlElement
    public String price1;
    @XmlElement
    public String price2;

    public ItemDto() {
    }

    public ItemDto(String category, String reference, String color, String gender, String image1, String image2, String description, List<ArticleDto> articles) {
        this.category = category;
        this.reference = reference;
        this.gender = gender;
        this.color = color;
        this.image1 = image1;
        this.image2 = image2;
        this.description = description;
        this.articles = articles;
    }

    void init() {
        articlesAsString = articles2AsString = "";

        boolean firstArticles = true;
        String currentPrice = null;
        for (int i = 0; i < articles.size(); i++) {
            final ArticleDto each = articles.get(i);
            if (i >= ARTICLES_COUNT) {
                firstArticles = false;
            }

            if (currentPrice == null) {
                price1 = currentPrice = each.price;
            }
            if (!currentPrice.equals(each.price)) {
                price2 = currentPrice = each.price;
            }

            if (firstArticles) {
                if (articlesAsString.length() > 0) {
                    articlesAsString += "\n";
                }
            } else if (articles2AsString.length() > 0) {
                articles2AsString += "\n";
            }
            if (firstArticles) {
                articlesAsString += each.name;
            } else {
                articles2AsString += each.name;
            }
        }
    }

    @Override
    public String toString() {
        return "ItemDto{categoryNumber=" + categoryNumber + ", reference=" + reference + ", reference no.= " + referenceNo + ", id=" + id + ", category=" + category + ", color=" + color + ", gender=" + gender + ", image1=" + image1 + ", image2=" + image2 + ", description=" + description + ", articlesAsString=" + articlesAsString + ", articles2AsString=" + articles2AsString + ", price1=" + price1 + ", price2=" + price2 + '}';
    }

    @Override
    public int compareTo(final ItemDto o) {
        int compareValue = new Integer(categoryNumber).compareTo(o.categoryNumber);
        if (compareValue != 0) {
            return compareValue;
        }

        compareValue = reference.compareTo(o.reference);
        if (compareValue != 0) {
            return compareValue;
        }

        return referenceNo.compareTo(o.referenceNo);
    }

}
