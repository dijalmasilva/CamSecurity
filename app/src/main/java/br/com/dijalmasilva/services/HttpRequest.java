package br.com.dijalmasilva.services;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import br.com.dijalmasilva.enums.Method;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 20/05/17.
 */
public class HttpRequest {

    private static final int REQUEST_INTERNET_RESULT = 2;
    private static final int REQUEST_ACCESS_NETWORK_STATE_RESULT = 3;

    private URL url;
    private HttpURLConnection conn;
    private OutputStream os;

    public HttpRequest(String url) throws IOException {
        this.url = new URL(url);
        this.conn = (HttpURLConnection) this.url.openConnection();
    }

    private void prepareAll(Method method) throws IOException {
        if (method != Method.POST) {
            conn.setRequestMethod(method.getName());
        }
        //
        if (method == Method.POST || method == Method.PUT) {
            conn.setDoInput(true);
            conn.setDoOutput(true);
            os = conn.getOutputStream();
        }
    }

    public HttpRequest get() throws IOException {
        prepareAll(Method.GET);
        return this;
    }

    public HttpRequest post() throws IOException {
        prepareAll(Method.POST);
        return this;
    }

    public HttpRequest put() throws IOException {
        prepareAll(Method.PUT);
        return this;
    }

    public HttpRequest delete() throws IOException {
        prepareAll(Method.DELETE);
        return this;
    }

    public HttpRequest withHeaders(Map<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            conn.setRequestProperty(header.getKey(), header.getValue());
        }
        return this;
    }

    public HttpRequest withData(String query) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.close();
        return this;
    }

    public HttpRequest withData(Map<String, String> data) throws IOException {
        StringBuilder result = new StringBuilder();
        //
        for (Map.Entry<String, String> entry : data.entrySet()) {
            result.append(result.length() > 0 ? "?" : "" + entry.getKey() + "=" + entry.getValue());
        }
        //
        withData(result.toString());
        return this;
    }

    public HttpRequest withDataJSON(JSONObject dataJSON) throws IOException {
        withData(dataJSON.toString());
        return this;
    }

    public int send() throws IOException {
        return conn.getResponseCode();
    }

    public String sendAndReadString() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String result = "";
        String line = br.readLine();
        while (line != null) {
            Log.d("DIJALMA_DEBUG", "RESPOSTA EM ANDAMENTO: " + result);
            result += line;
            line = br.readLine();
        }
        //LOG
        Log.d("DIJALMA_DEBUG", "Code Response: " + conn.getResponseCode());
        Log.d("DIJALMA_DEBUG", "Message Response: " + conn.getResponseMessage());
        Log.d("DIJALMA_DEBUG", result);
        return result;
    }

    public JSONObject sendAndReadJSON() throws JSONException, IOException {
        return new JSONObject(sendAndReadString());
    }
}
