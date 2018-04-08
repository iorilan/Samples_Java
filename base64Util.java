package com.example.tdw.non_cablecarvalidator.Helpers;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by lanliang on 15/11/16.
 */
public final class ZipUtil
{
    public static String CompressToBase64(String string){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(string.length());
            GZIPOutputStream gos = new GZIPOutputStream(os);
            gos.write(string.getBytes());
            gos.close();
            byte[] compressed = os.toByteArray();
            os.close();

            String result = Base64.encodeToString(compressed, Base64.DEFAULT);
            return result;
        } catch (IOException e) {
            e.printStackTrace();


        }
        catch (Exception ex){

        }
        return "";
    }

    public static String DecompressToBase64(String textToDecode){
        //String textToDecode = "H4sIAAAAAAAAAPNIzcnJBwCCidH3BQAAAA==\n";
        try {
            byte[] compressed = Base64.decode(textToDecode, Base64.DEFAULT);
            final int BUFFER_SIZE = 32;
            ByteArrayInputStream inputStream = new ByteArrayInputStream(compressed);

            GZIPInputStream gis  = new GZIPInputStream(inputStream, BUFFER_SIZE);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] data = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = gis.read(data)) != -1) {
                baos.write(data, 0, bytesRead);
            }

            return baos.toString("UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception ex){

        }
        return "";
    }
}
