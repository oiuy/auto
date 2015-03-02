package ru.eldorado.web;

public class RunParameters {
    public static final String BASE_URL = "selenide.baseUrl";

    public static String getBaseUrl() {
        return System.getProperty(BASE_URL);
    }
}
