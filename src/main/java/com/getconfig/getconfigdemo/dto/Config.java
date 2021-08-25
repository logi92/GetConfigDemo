package com.getconfig.getconfigdemo.dto;

/**
 * DTO Класс для Конфига.
 */
public class Config {
    private int config_id;
    private String nameConfig;
    private String specificationConfig;


    public Config() {
    }

    public Config(String nameConfig, String specificationConfig) {
        this.nameConfig = nameConfig;
        this.specificationConfig = specificationConfig;
    }

    public int getConfig_id() {
        return config_id;
    }

    public String getNameConfig() {
        return nameConfig;
    }

    public void setNameConfig(String nameConfig) {
        this.nameConfig = nameConfig;
    }

    public String getSpecificationConfig() {
        return specificationConfig;
    }

    public void setSpecificationConfig(String specificationConfig) {
        this.specificationConfig = specificationConfig;
    }

    public void setConfig_id(int config_id) {
        this.config_id = config_id;
    }


    @Override
    public String toString() {
        return "Config : " + nameConfig + " " + specificationConfig;
    }
}
