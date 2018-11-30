package com.blauband.ods.converter;

import com.blauband.ods.CatalogueDto;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author sergeizenevich
 */
public class StatusKeeper {

    public static final StatusKeeper INSTANCE = new StatusKeeper();

    public StatusDto status = new StatusDto();

    synchronized void start(final String file) {
        status = new StatusDto();
        status.file = file;
        addLine(file);
    }

    public void finished() {
        addLine("done");
        status.finished = true;
    }

    public void handleError(final Exception e) {
        addLine(ExceptionUtils.getFullStackTrace(e));
        finished();
    }

    public void addLine(final String s) {
        status.trace += "\n" + s;
    }

    public void addCatalogueRecord(final CatalogueDto catalogueDto) {
        addLine("Parsed catelogues with " + catalogueDto.items.size() + " items.");
    }
}
