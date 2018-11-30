package com.blauband.ods.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Properties;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author sergeizenevich
 */
public class ConfigDto implements Serializable {

    private static final String TITLE = "title";
    private static final String SUBTITLE = "subtitle";
    private static final String COMPANY = "company";
    private static final String CATEGORY = "category";
    private static final String SITE = "site";
    private static final String PICTURE = "picture";

    @XmlElement
    public String title = "Price List FW2017";
    @XmlElement
    public String subtitle = "Outdoormode für Kinder";
    @XmlElement
    public String company = "TROLLKIDS";
    @XmlElement
    public String category = "Apparel";
    @XmlElement
    public String site = "www.trollkids.com";
    @XmlElement
    public String picture = "http://www.trollkids.com/wp-content/uploads/2015/03/megateaser-vipergreen.jpg";

    public ConfigDto() {
    }

    public ConfigDto(final Properties properties) {
        title = properties.getProperty(TITLE);
        subtitle = properties.getProperty(SUBTITLE);
        company = properties.getProperty(COMPANY);
        category = properties.getProperty(CATEGORY);
        site = properties.getProperty(SITE);
        picture = properties.getProperty(PICTURE);
    }

    @JsonIgnore
    public Properties getProperties() {
        final Properties properties = new Properties();
        properties.setProperty(TITLE, title);
        properties.setProperty(SUBTITLE, subtitle);
        properties.setProperty(COMPANY, company);
        properties.setProperty(CATEGORY, category);
        properties.setProperty(SITE, site);
        properties.setProperty(PICTURE, picture);
        return properties;
    }

    @Override
    public String toString() {
        return "ConfigDto{" + "title=" + title + ", subtitle=" + subtitle + ", company=" + company + ", category=" + category + ", site=" + site + ", picture=" + picture + '}';
    }

}
