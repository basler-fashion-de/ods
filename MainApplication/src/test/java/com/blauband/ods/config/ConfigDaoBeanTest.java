package com.blauband.ods.config;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author sergeizenevich
 */
public class ConfigDaoBeanTest {
    
    /**
     * Test of find method, of class ConfigDaoBean.
     */
    @Test
    public void testFind() {
        final ConfigDaoBean configDaoBean = new ConfigDaoBean("/tmp/");
        
        final ConfigDto configDto = new ConfigDto();
        final ConfigDto merged = configDaoBean.merge(configDto);
        
        Assert.assertEquals(configDto.category, merged.category);
    }
    
}
