package br.com.dijalmasilva.pdm.forms;

import java.io.File;
import java.io.Serializable;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank"><a href="http://dijalmasilva.github.io" target="_blank">dijalma</a></a> on 18/05/17.
 */
public class RecordVO implements Serializable {

    private Long id;
    private String nameWebCam;
    private String data;
    private String hour;
    private File record;
    private File imagePreview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameWebCam() {
        return nameWebCam;
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

    public void setNameWebCam(String nameWebCam) {
        this.nameWebCam = nameWebCam;
    }

    public File getRecord() {
        return record;
    }

    public void setRecord(File record) {
        this.record = record;
    }

    public File getImagePreview() {
        return imagePreview;
    }

    public void setImagePreview(File imagePreview) {
        this.imagePreview = imagePreview;
    }
}
