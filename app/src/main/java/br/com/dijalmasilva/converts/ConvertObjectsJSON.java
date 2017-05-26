package br.com.dijalmasilva.converts;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import br.com.dijalmasilva.pdm.forms.RecordVO;
import br.com.dijalmasilva.pdm.models.RecordModel;
import br.com.dijalmasilva.pdm.models.WebCam;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 20/05/17.
 */
public class ConvertObjectsJSON {

    public static WebCam convertToWebCam(JSONObject jsonObject) throws JSONException {
        WebCam webCam = new WebCam(jsonObject.getString("nameCam"));
        webCam.setId(jsonObject.getLong("id"));
        webCam.setActivate(jsonObject.getBoolean("activate"));
        webCam.setRecording(jsonObject.getBoolean("recording"));
        return webCam;
    }

    public static RecordVO convertToRecordVO(JSONObject jsonObject) throws JSONException {
        RecordVO recordVO = new RecordVO();
        recordVO.setId(jsonObject.getLong("id"));
        recordVO.setNameWebCam(jsonObject.getString("nameWebCam"));
        recordVO.setRecord((File) jsonObject.get("record"));
        recordVO.setImagePreview((File) jsonObject.get("imagePreview"));
        return recordVO;
    }

    public static RecordModel convertToRecordModel(JSONObject jsonObject) throws JSONException {
        RecordModel recordModel = new RecordModel();
        recordModel.setId(jsonObject.getLong("id"));
        recordModel.setImgPreview(jsonObject.getString("imgPreview"));
        recordModel.setPathRecord(jsonObject.getString("pathRecord"));
        recordModel.setNameCam(extractNameWebCam(recordModel.getPathRecord()));
        recordModel.setData(extractDate(jsonObject.getJSONArray("dateTime")));
        recordModel.setHour(extractHour(jsonObject.getJSONArray("dateTime")));
        Log.d("DEBUG_DIJALMA", "RecordModel: " + recordModel.toString());
        return recordModel;
    }

    public static JSONObject convertWebCamToJSON(WebCam webCam) throws JSONException {
        String webcamResult = "{" +
                "id:" +
                webCam.getId() + "," +
                "nameCam:" +
                (webCam.getNameCam().equals("") ? "nothing" : webCam.getNameCam()) + "," +
                "activity:" +
                webCam.isActivate() + "," +
                "recording:" +
                webCam.isRecording() +
                "}";
        return new JSONObject(webcamResult);
    }

    private static String extractNameWebCam(String text) {
        final String[] split = text.split("/");
        return split[split.length - 2];
    }

    private static String extractDate(JSONArray jsonArray) throws JSONException {
        StringBuilder dateResult = new StringBuilder();
        dateResult.append(jsonArray.getInt(2) < 9 ? "0" : "").append(jsonArray.getInt(2)).append("/");
        dateResult.append(jsonArray.getInt(1) < 9 ? "0" : "").append(jsonArray.getInt(1)).append("/");
        dateResult.append(jsonArray.getInt(0));
        return dateResult.toString();
    }

    private static String extractHour(JSONArray jsonArray) throws JSONException {
        StringBuilder hourResult = new StringBuilder();
        hourResult.append(jsonArray.getInt(3) < 9 ? "0" : "").append(jsonArray.getInt(3)).append(":");
        hourResult.append(jsonArray.getInt(4) < 9 ? "0" : "").append(jsonArray.getInt(4));
        return hourResult.toString();
    }
}
