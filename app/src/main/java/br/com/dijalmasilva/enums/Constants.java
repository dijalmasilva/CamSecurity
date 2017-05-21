package br.com.dijalmasilva.enums;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 20/05/17.
 */
public enum Constants {

    HOST("http://192.168.0.107:8080");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
