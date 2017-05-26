package br.com.dijalmasilva.pdm.models;

import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
public class RecordModel implements Serializable {

    private Long id;
    private String nameCam;
    private String data;
    private String hour;
    private String pathRecord;
    private String imgPreview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCam() {
        return nameCam;
    }

    public void setNameCam(String nameCam) {
        this.nameCam = nameCam;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPathRecord() {
        return pathRecord;
    }

    public void setPathRecord(String pathRecord) {
        this.pathRecord = pathRecord;
    }

    public String getImgPreview() {
        return imgPreview;
    }

    public void setImgPreview(String imgPreview) {
        this.imgPreview = imgPreview;
    }

    @Override
    public String toString() {
        return "RecordModel{" +
                "id=" + id +
                ", nameCam='" + nameCam + '\'' +
                ", data='" + data + '\'' +
                ", hour='" + hour + '\'' +
                ", pathRecord='" + pathRecord + '\'' +
                ", imgPreview='" + imgPreview + '\'' +
                '}';
    }
}
