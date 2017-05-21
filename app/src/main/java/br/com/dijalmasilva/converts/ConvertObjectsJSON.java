package br.com.dijalmasilva.converts;

import org.json.JSONException;
import org.json.JSONObject;

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
}
