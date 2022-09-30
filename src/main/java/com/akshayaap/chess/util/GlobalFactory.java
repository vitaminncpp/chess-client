package com.akshayaap.chess.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GlobalFactory {
    private static final GlobalFactory factory = new GlobalFactory();

    private final ObjectMapper mapper = new ObjectMapper();

    private GlobalFactory() {

    }

    public static GlobalFactory getFactory() {
        return factory;
    }

    public ObjectMapper getObjectMapper() {
        return this.mapper;
    }
}
