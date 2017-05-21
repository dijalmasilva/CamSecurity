package br.com.dijalmasilva.services;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.dijalmasilva.converts.ConvertObjectsJSON;
import br.com.dijalmasilva.enums.Constants;
import br.com.dijalmasilva.pdm.models.WebCam;
import br.com.dijalmasilva.repository.DaoRepository;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 19/05/17.
 */
public class WebCamService {

    private HttpRequest httpRequest;
    private final String rest = "/webcam";
    private final DaoRepository dao;

    public WebCamService(Context context) {
        this.dao = new DaoRepository(context);
    }

    public WebCam registerWebCam() {
        if (verifyExistRegister()) {
            return recoverWebCam();
        } else {
            return saveNewWebCam();
        }
    }

    private boolean verifyExistRegister() {
        //
        return findIDCamInTheDatabase() != null;
    }

    private WebCam saveNewWebCam() {
        //save in Server
        //save ID result from CAM server
        try {
            httpRequest = new HttpRequest(Constants.HOST.getValue() + rest);
            WebCam webCam = new WebCam("");
            webCam.setActivate(true);
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            final JSONObject jsonObject = httpRequest.withHeaders(headers).post()
                    .withDataJSON(ConvertObjectsJSON.convertWebCamToJSON(webCam))
                    .sendAndReadJSON();
            final WebCam cam = ConvertObjectsJSON.convertToWebCam(jsonObject);
            saveIDCamInDatabase(cam.getId());
            return cam;// return CAM

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private WebCam recoverWebCam() {
        try {
            Long id = findIDCamInTheDatabase();
            httpRequest = new HttpRequest(Constants.HOST.getValue() + rest + "/" + id);
            final JSONObject jsonObject = httpRequest.get().sendAndReadJSON();
            final WebCam webCam = ConvertObjectsJSON.convertToWebCam(jsonObject);
            webCam.setActivate(true);
            return webCam;
        } catch (IOException | JSONException e) {
            dao.delete();
            saveNewWebCam();
            e.printStackTrace();
        }
        //
        return null;
    }

    private void saveIDCamInDatabase(Long id) {
        //save ID the CAM in database
        dao.insert(id);
    }

    private Long findIDCamInTheDatabase() {
        //search in the database some ID CAM
        return dao.find(); //return ID found or null
    }
}
