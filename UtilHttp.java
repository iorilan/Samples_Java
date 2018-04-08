package com.example.tdw.non_cablecarvalidator.Helpers;

/**
 * Created by T.DW on 4/7/16.
 */
import android.content.Context;
import android.util.Log;

import com.example.tdw.non_cablecarvalidator.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;

public class UtilHttp {
    private static final String TAG = "UtilHttp";
    public static String err;

    public static String doHttpGet(Context ctx, String serverUrl) {
        Log.v(TAG, "HTTPGET:" + serverUrl);
        String result = null;
        err = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        try {
            int TIMEOUT_MILLISEC = 15000;

            conn = (HttpURLConnection) new URL(serverUrl).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIMEOUT_MILLISEC);
            conn.setReadTimeout(TIMEOUT_MILLISEC);
            conn.setRequestProperty("Accept-Encoding", "");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoInput(true);

            conn.connect();
            int responseCode = conn.getResponseCode();
            Log.v(TAG, "Return code = " + responseCode);
            if (responseCode == 200 || responseCode == 400) {
                in = new BufferedInputStream(conn.getInputStream());
                String encoding = conn.getContentEncoding() == null ? "UTF-8"
                        : conn.getContentEncoding();
                result = IOUtils.toString(in, encoding);
            } else {
                String responseMsg = conn.getResponseMessage();
                if (responseMsg != null) {
                    Log.v(TAG, "Response message = " + responseMsg);
                    err = responseCode + ":" + responseMsg;
                } else {
                    Log.v(TAG, "Response message is null");
                    err = responseCode + ":"
                            + ctx.getString(R.string.responseisnull);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            err = e.getMessage();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        if (result != null) {
            Log.v(TAG, result);
        } else {
            Log.v(TAG, "Result is null");
        }
        return result;
    }

    public static String doHttpPostWithResult(Context ctx, String serverUrl,
                                              String params) {
        Log.v(TAG, "HTTPPOST:" + serverUrl);
        String result = null;
        err = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        OutputStreamWriter out = null;
        try {
            int TIMEOUT_MILLISEC = 15000;

            conn = (HttpURLConnection) new URL(serverUrl).openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(TIMEOUT_MILLISEC);
            conn.setReadTimeout(TIMEOUT_MILLISEC);
            conn.setRequestProperty("Accept-Encoding", "");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setChunkedStreamingMode(0);
            if (params != null) {
                out = new OutputStreamWriter(conn.getOutputStream());
                out.write(params);
                out.flush();
            }

            conn.connect();
            int responseCode = conn.getResponseCode();
            Log.v(TAG, "Return code = " + responseCode);
            if (responseCode == 200 || responseCode == 400) {
                in = new BufferedInputStream(conn.getInputStream());
                String encoding = conn.getContentEncoding() == null ? "UTF-8"
                        : conn.getContentEncoding();
                result = IOUtils.toString(in, encoding);
            } else {
                String responseMsg = conn.getResponseMessage();
                if (responseMsg != null) {
                    Log.v(TAG, "Response message = " + responseMsg);
                    err = responseCode + ":" + responseMsg;
                } else {
                    Log.v(TAG, "Response message is null");
                    err = responseCode + ":"
                            + ctx.getString(R.string.responseisnull);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            err = e.getMessage();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        if (result != null) {
            Log.v(TAG, result);
        } else {
            Log.v(TAG, "Result is null");
        }
        return result;
    }
}
