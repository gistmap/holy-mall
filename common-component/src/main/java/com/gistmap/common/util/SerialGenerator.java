package com.gistmap.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class SerialGenerator {

    public static String randomUUID() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }
}
