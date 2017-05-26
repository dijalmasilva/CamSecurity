package br.com.dijalmasilva.enums;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 20/05/17.
 */
public enum Constants {

    HOST("192.168.0.108"), HOST_WITH_PROTOCOL("http://192.168.0.108:8080");
//    HOST("10.3.27.148"), HOST_WITH_PROTOCOL("http://10.3.27.148:8080");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
