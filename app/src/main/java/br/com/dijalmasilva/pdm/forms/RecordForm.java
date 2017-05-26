package br.com.dijalmasilva.pdm.forms;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 21/05/17.
 */
public class RecordForm implements Serializable {

    private Long id;
    private String nameWebCam;
    private Date date;
    private Timestamp hour;
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

    public void setNameWebCam(String nameWebCam) {
        this.nameWebCam = nameWebCam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getHour() {
        return hour;
    }

    public void setHour(Timestamp hour) {
        this.hour = hour;
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
