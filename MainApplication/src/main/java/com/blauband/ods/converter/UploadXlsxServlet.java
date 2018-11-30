package com.blauband.ods.converter;

import com.blauband.ods.Settings;
import com.google.inject.Singleton;
import com.softteco.toolset.files.FileUploadServlet;

/**
 *
 * @author sergeizenevich
 */
@Singleton
public class UploadXlsxServlet extends FileUploadServlet {

    @Override
    protected String getFolder() {
        return Settings.FILES_FOLDER;
    }

    @Override
    protected String getRedirect() {
        return null;
    }

}
