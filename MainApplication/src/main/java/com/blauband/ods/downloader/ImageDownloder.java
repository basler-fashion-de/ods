package com.blauband.ods.downloader;

import com.blauband.ods.CatalogueDto;
import com.blauband.ods.ItemDto;
import com.blauband.ods.Settings;
import com.blauband.ods.converter.StatusKeeper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author sergeizenevich
 */
public class ImageDownloder {

    private static final Logger LOGGER = Logger.getLogger(ImageDownloder.class.getName());

    public static void main(final String[] args) {
        new ImageDownloder(Settings.FILES_FOLDER).download("105-114.jpg");
    }

    private final String imageFolder;

    public ImageDownloder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

    public void download(final String image) {
        final File folder = new File(imageFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }

        final File imageFile = new File(imageFolder + image);
        if (imageFile.exists()) {
            StatusKeeper.INSTANCE.addLine("Image exists: " + image);
            return;
        }

        try {
            InputStream in = null;
            FileOutputStream out = null;
            try {
                final URL url = new URL(Settings.IMAGE_URL_PREFIX + image);
                in = new BufferedInputStream(url.openStream());

                out = new FileOutputStream(imageFile);
                IOUtils.copy(in, out);
            } finally {
                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }
            }
            StatusKeeper.INSTANCE.addLine("Image was downloaded: " + image);
            
            ImageResizer.scaleImage(imageFile, imageFile);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Image was not found.", e);
            StatusKeeper.INSTANCE.addLine("Image was not found: " + image);
        }
    }

    public void download(final CatalogueDto catalogueDto) {
        final List<String> images = new ArrayList<>();
        for (ItemDto each : catalogueDto.items) {
            images.add(each.image1);
            each.image1 = imageFolder + each.image1;
            images.add(each.image2);
            each.image2 = imageFolder + each.image2;
        }

        download(images);
    }

    public void download(final List<String> images) {
        int i = 0;
        int totalCount = images.size();
        for (String each : images) {
            System.out.println(i + " of " + totalCount);
            download(each);
            i++;
        }
    }

}
