package com.openwjk.central.web.utils;

import com.openwjk.central.web.handle.MsgHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SSEClient {
    // timeout
    private static Integer DEFAULT_TIME_OUT = 2 * 60 * 1000;

    /**
     * 获取SSE输入流
     */
    public static InputStream getSseInputStream(String urlPath) throws IOException {
        return getSseInputStream(urlPath, null);
    }

    /**
     * 获取SSE输入流
     */
    public static InputStream getSseInputStream(String urlPath, Integer timeoutMill) throws IOException {
        return getSseInputStreamByParam(urlPath, null, timeoutMill);
    }

    /**
     * 获取SSE输入流
     */
    public static InputStream getSseInputStreamByParam(String urlPath, String param, Integer timeoutMill) throws IOException {
        HttpURLConnection urlConnection = getHttpURLConnection(urlPath, param, timeoutMill);
        InputStream inputStream = urlConnection.getInputStream();
        return new BufferedInputStream(inputStream);
    }

    /**
     * 读流数据
     */
    public static void readStream(InputStream is, MsgHandler msgHandler) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line = "";
            while ((line = reader.readLine()) != null) {
                msgHandler.handleMsg(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 服务器端主动关闭时，客户端手动关闭
            reader.close();
            is.close();
        }
    }

    private static HttpURLConnection getHttpURLConnection(String urlPath, String param, Integer timeoutMill) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("accept", "application/json");
        connection.setConnectTimeout(timeoutMill == null || timeoutMill <= 0 ? DEFAULT_TIME_OUT : timeoutMill);
        if (StringUtils.isNotBlank(param)) {
            connection.setRequestProperty("Content-Length", String.valueOf(param.length()));
            OutputStream out = connection.getOutputStream();
            out.write(param.getBytes());
            out.flush();
            out.close();
        }
        return connection;
    }
}
