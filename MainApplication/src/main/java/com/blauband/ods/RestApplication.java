package com.blauband.ods;

import com.blauband.ods.config.ConfigResource;
import com.blauband.ods.converter.PdfConverterResource;
import com.softteco.toolset.restlet.AbstractRestletApplication;
import com.softteco.toolset.restlet.AbstractStatusService;
import org.restlet.routing.Router;

/**
 *
 * @author sergeizenevich
 */
public class RestApplication extends AbstractRestletApplication {

    @Override
    protected AbstractStatusService createStatusService() {
        return new AbstractStatusService() {
        };
    }

    @Override
    protected void createInboundRoot(final Router router) {
        router.attach("/config", ConfigResource.class);
        router.attach("/pdf", PdfConverterResource.class);
    }

}
