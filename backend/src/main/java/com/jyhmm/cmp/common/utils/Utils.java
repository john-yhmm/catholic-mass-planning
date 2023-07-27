package com.jyhmm.cmp.common.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class Utils {

    public static URI buildUriWithId(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
