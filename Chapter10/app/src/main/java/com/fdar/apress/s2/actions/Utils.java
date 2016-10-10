package com.fdar.apress.s2.actions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class Utils {

    public static byte[] getBytesFromFile( File file ) {

           long length = file.length();
           byte[] bytes = new byte[(int)length];

           try {
               InputStream is = new FileInputStream(file);

               // Read in the bytes
               int offset = 0;
               int numRead = 0;
               while (offset < bytes.length
                       && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                   offset += numRead;
               }

               // Ensure all the bytes have been read in
               if (offset < bytes.length) {
                   throw new IOException("Could not completely read file "+file.getName());
               }

               // Close the input stream and return bytes
               is.close();
           } catch(Exception e ) {
               throw new RuntimeException(e);
           }
           return bytes;
       }

       public static InputStream getNoImageInputStream( String text ) {
           try {
               BufferedImage bi = new BufferedImage(120,40,BufferedImage.TYPE_INT_RGB);
               Graphics g = bi.getGraphics();

               g.setColor(Color.WHITE);
               g.fillRect(0,0,120,40);
               g.setColor(Color.BLACK);
               g.setFont( new Font("Serif",Font.BOLD,22));
               g.drawString( text,5,35);

               File tempImg = File.createTempFile("tempImg","jpg");
               tempImg.deleteOnExit();
               ImageIO.write(bi,"jpeg",tempImg);

               return new FileInputStream(tempImg);

           } catch (Exception e) {
               throw new RuntimeException(e);
           }

       }

}
