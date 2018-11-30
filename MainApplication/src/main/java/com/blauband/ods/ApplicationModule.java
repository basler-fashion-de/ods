package com.blauband.ods;

import com.blauband.ods.config.UploadPictureServlet;
import com.blauband.ods.converter.UploadXlsxServlet;
import com.softteco.toolset.AbstractApplicationModule;
import com.softteco.toolset.restlet.AbstractRestletApplication;
import com.softteco.toolset.restlet.UserSession;

/**
 *
 * @author sergeizenevich
 */
public class ApplicationModule extends AbstractApplicationModule {
    @Override
    protected void configureApplication() {
        serve("/upload/picture").with(UploadPictureServlet.class);
        serve("/upload/xlsx").with(UploadXlsxServlet.class);
    }

    @Override
    protected Class<? extends UserSession> getUserSessionClass() {
        return UserSessionImpl.class;
    }

    @Override
    protected String getJpaUnitName() {
        return "persistence";
    }

    @Override
    protected Class<? extends AbstractRestletApplication> getRestletApplication() {
        return RestApplication.class;
    }

}
