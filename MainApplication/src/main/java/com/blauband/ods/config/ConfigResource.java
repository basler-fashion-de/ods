package com.blauband.ods.config;

import com.google.inject.Inject;
import com.softteco.toolset.restlet.AbstractResource;
import com.softteco.toolset.restlet.UserSession;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 *
 * @author sergeizenevich
 */
public class ConfigResource extends AbstractResource<UserSession> {

    @Inject
    private ConfigDaoBean configDao;

    @Get("json")
    public ConfigDto getConfig() {
        return configDao.find();
    }

    @Post("json")
    public ConfigDto updateConfig(final ConfigDto config) {
        return configDao.merge(config);
    }
}
