package br.com.dijalmasilva.services;

import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.com.dijalmasilva.converts.ConvertObjectsJSON;
import br.com.dijalmasilva.enums.Constants;
import br.com.dijalmasilva.pdm.forms.RecordVO;
import br.com.dijalmasilva.pdm.models.RecordModel;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 21/05/17.
 */

public class RecordService {

    private File recordFolder;
    private File imagePreviewFolder;
    private File recordFile;
    private File imagePreviewFile;

    public List<RecordVO> findRecords(String nameWebCam) {
        List<RecordModel> recordModels = new ArrayList<>();
        List<RecordVO> recordVOs = new ArrayList<>();
        try {
            HttpRequest request = new HttpRequest(Constants.HOST_WITH_PROTOCOL.getValue() +
                    "/record/camera/" + nameWebCam);
            final JSONArray jsonArray = request.get().sendAndReadArrayJSON();
            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);
                final RecordModel recordModel = ConvertObjectsJSON.convertToRecordModel(jsonObject);
                recordModels.add(recordModel);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        for (RecordModel recordModel : recordModels) {
            try {
                final boolean exist = verifyRecordExistInMemory(recordModel.getNameCam(), recordModel.getId());
                if (exist) {
                    recordVOs.add(findRecordLocal(recordModel));
                } else {
                    findImagePreviewInWebServer(recordModel);
                    recordVOs.add(convertToRecordVoWithoutRecordFile(recordModel));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return recordVOs;
    }

    private void findImagePreviewInWebServer(RecordModel recordModel) {
        try {
            createImagePreviewFolder(recordModel.getNameCam());
            createImagePreviewFile(recordModel.getNameCam(), recordModel.getId());
            Socket socket = new Socket(Constants.HOST.getValue(), 1091);
            final OutputStream out = socket.getOutputStream();
            out.write(recordModel.getId().toString().getBytes());
            Log.d("DEBUG_DIJALMA", "Enviou ID");
            final InputStream in = socket.getInputStream();
//            findImagePreviewInServer(in);
            FileOutputStream fileOutput = new FileOutputStream(imagePreviewFile);
            Log.d("DEBUG_DIJALMA", "Fazendo leitura");
            byte[] buffer = new byte[1024];
            while (true) {
                int len = in.read(buffer);
                if (len < 0) {
                    break;
                }
                fileOutput.write(buffer, 0, len);
                fileOutput.flush();
            }
            fileOutput.close();
            out.close();
            in.close();
            Log.d("DEBUG_DIJALMA", "Imagem recebida");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File findRecordFileInServer(String nameCam, Long id) {
        try {
            createRecordFolder(nameCam);
            createRecordFile(nameCam, id);
            //
            Socket socket = new Socket(Constants.HOST.getValue(), 1092);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeLong(id);
            //
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//            findRecordInServer(in);
            FileOutputStream fileOutput = new FileOutputStream(recordFile);
            byte[] buffer = new byte[1024];
            while (true) {
                int len = in.read(buffer);
                if (len < 0) {
                    break;
                }
                fileOutput.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        return recordFile;
    }

    public RecordVO findRecordLocal(RecordModel recordModel) {
        createRecordFolder(recordModel.getNameCam());
        createImagePreviewFolder(recordModel.getNameCam());
        String recordName = recordModel.getNameCam() + "_" + recordModel.getId() + ".mp4";
        String imagePreviewName = recordModel.getNameCam() + "_" + recordModel.getId() + ".png";
        recordFile = new File(recordFolder, recordName);
        imagePreviewFolder = new File(imagePreviewFolder, imagePreviewName);
        return convertToRecordVo(recordModel);
    }

    public boolean verifyRecordExistInMemory(String nameCam, Long id) throws IOException {
        createImagePreviewFolder(nameCam);
        String file = nameCam + "_" + id + ".mp4";
        File fileVerify = new File(imagePreviewFolder, file);
        return fileVerify.exists();
    }

    private void findRecordInServer(ObjectInputStream in) throws IOException {

    }

    private void findImagePreviewInServer(ObjectInputStream in) throws IOException {

    }

    private RecordVO convertToRecordVo(RecordModel recordModel) {
        RecordVO recordVO = new RecordVO();
        recordVO.setImagePreview(imagePreviewFile);
        recordVO.setRecord(recordFile);
        recordVO.setNameWebCam(recordModel.getNameCam());
        recordVO.setId(recordModel.getId());
        recordVO.setHour(recordModel.getHour());
        recordVO.setData(recordModel.getData());
        return recordVO;
    }

    private RecordVO convertToRecordVoWithoutRecordFile(RecordModel recordModel) {
        RecordVO recordVO = new RecordVO();
        recordVO.setImagePreview(imagePreviewFile);
        recordVO.setRecord(null);
        recordVO.setNameWebCam(recordModel.getNameCam());
        recordVO.setId(recordModel.getId());
        recordVO.setHour(recordModel.getHour());
        recordVO.setData(recordModel.getData());
        return recordVO;
    }

    private void createRecordFolder(String nameCam) {
        final File externalMovies = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        recordFolder = new File(externalMovies, "records/" + nameCam);
        if (!recordFolder.exists()) {
            recordFolder.mkdirs();
        }
    }

    private void createImagePreviewFolder(String nameCam) {
        final File externalPictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        imagePreviewFolder = new File(externalPictures, "imagesPreview/" + nameCam);
        if (!imagePreviewFolder.exists()) {
            imagePreviewFolder.mkdirs();
        }
    }

    private void createRecordFile(String nameCam, Long idRecord) throws IOException {
        String prepend = nameCam + "_" + idRecord;
        recordFile = new File(recordFolder, prepend + ".mp4");
    }

    private void createImagePreviewFile(String nameCam, Long idRecord) throws IOException {
        String prepend = nameCam + "_" + idRecord;
        imagePreviewFile = new File(imagePreviewFolder, prepend + ".png");
    }
}
