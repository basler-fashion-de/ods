package com.blauband.ods;

import com.blauband.ods.config.ConfigDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "catalogue")
public class CatalogueDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int LINES_COUNT_MAX = 4;

    public ConfigDto config = new ConfigDto();
    @XmlElement
    public List<ItemDto> items;
    @XmlElement
    public List<String> categories;

    public void init() {
        config.picture = Settings.FILES_FOLDER + config.picture;
        
        items.stream().forEach((each) -> {
            each.init();
        });
        
        categories = new ArrayList<>();
        
        int index = 0;
        String currentCategory = null;
        String currentCategoryForId = null;
        for (ItemDto each : items) {
            if (currentCategory == null || !currentCategory.equals(each.category)) {
                currentCategory = each.category;
                currentCategoryForId = "" + categories.size();
                index = 0;
                categories.add(currentCategory);
            }
            
            each.id = currentCategoryForId + "_" + index;
            index++;
        }
    }
}
