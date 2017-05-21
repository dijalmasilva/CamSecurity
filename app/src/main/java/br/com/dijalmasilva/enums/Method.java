package br.com.dijalmasilva.enums;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 20/05/17.
 */
public enum Method {

    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");
    //
    private String name;

    Method(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
