package com.github.tisv2000.video_library.util;

import lombok.experimental.UtilityClass;

// TODO Что делает эта аннотация? делает final class, делает static каждый метод, создает приватный конструктор, бросает в нем exception если попробуешь создать экземпляр
@UtilityClass
public class UrlPath {

    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String REGISTRATION = "/registration";

    public static final String MOVIES = "/movies";
    public static final String PERSONS = "/persons";
    public static final String IMAGES = "/images";


}
