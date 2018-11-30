package com.blauband.ods.downloader;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author sergeizenevich
 */
public class ImageResizer {

    public static void main(String[] args) throws IOException {
        ImageResizer.scaleImage("/tmp/3662.jpeg", "/tmp/3662.jpeg");
    }
    
    public static void scaleImage(final String inFileName, final String outFileName) {
        scaleImage(new File(inFileName), new File(outFileName));
    }
    
    public static void scaleImage(final File inFile, final File outFile) {
        final String format = inFile.getName().substring(inFile.getName().lastIndexOf(".") + 1);
        
        BufferedImage out = null;
        try {
            final BufferedImage in = ImageIO.read(inFile);

            out = new BufferedImage(in.getWidth() / 2, in.getHeight() / 2, in.getType());
            final Graphics2D g = out.createGraphics();
            final AffineTransform at = AffineTransform.getScaleInstance(0.5, 0.5);
            g.drawRenderedImage(in, at);
        } catch (IOException e) {
            throw new RuntimeException("Problem with reading file", e);
        }

        try {
            ImageIO.write(out, format, outFile);
        } catch (IOException e) {
            throw new RuntimeException("Problem with writing file", e);
        }
    }
}
