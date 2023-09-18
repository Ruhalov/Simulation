package com.ruhalov;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public final class Configurations {
    private static Configurations instance;
    private final Properties properties;

    private Configurations() {
        this.properties = new Properties();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("simulation.properties")) {
            properties.load(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
    }

    public static Configurations getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    public String getProperty(String key) {
        String result = null;
        if (key != null && !key.trim().isEmpty()) {
            result = this.properties.getProperty(key);
        }
        return result;
    }

    /**
     * Override the clone method to ensure the "unique instance" requirement of this class
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}