package com.blauband.ods.converter;

import com.blauband.ods.CatalogueDto;
import com.blauband.ods.OdsTest;
import com.blauband.ods.Settings;
import com.blauband.ods.config.ConfigDaoBean;
import com.blauband.ods.config.ConfigDto;
import com.blauband.ods.downloader.ImageDownloder;
import com.blauband.ods.parse.XlsxParser;
import com.google.inject.Inject;
import com.softteco.toolset.dto.StringDto;
import com.softteco.toolset.restlet.AbstractResource;
import com.softteco.toolset.restlet.UserSession;
import java.util.concurrent.CompletableFuture;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 *
 * @author sergeizenevich
 */
public class PdfConverterResource extends AbstractResource<UserSession> {

    @Inject
    private ConfigDaoBean configDao;

    @Get("json")
    public StatusDto getProcessStatus() {
        return StatusKeeper.INSTANCE.status;
    }

    @Post("json")
    public StatusDto upload(final StringDto file) {
        StatusKeeper.INSTANCE.start(file.string);
        
        final ConfigDto configDto = configDao.find();
        StatusKeeper.INSTANCE.addLine(configDto.toString());

        CompletableFuture.runAsync(() -> {
            try {
                final CatalogueDto catalogueDto = new XlsxParser(Settings.FILES_FOLDER + file.string).parse();
                catalogueDto.config = configDto;
                StatusKeeper.INSTANCE.addCatalogueRecord(catalogueDto);

                new ImageDownloder(Settings.FILES_FOLDER).download(catalogueDto);

                StatusKeeper.INSTANCE.addLine("Creating pdf...");
                new OdsTest().createUserInvoicePDF(catalogueDto);
                StatusKeeper.INSTANCE.addLine("pdf was created.");
            } catch (Exception e) {
                StatusKeeper.INSTANCE.handleError(e);
            } finally {
                StatusKeeper.INSTANCE.finished();
            }
        });
        return getProcessStatus();
    }
}
