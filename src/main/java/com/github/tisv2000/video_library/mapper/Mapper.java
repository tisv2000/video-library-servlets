package com.github.tisv2000.video_library.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
