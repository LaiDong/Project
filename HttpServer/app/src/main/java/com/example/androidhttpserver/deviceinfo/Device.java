package com.example.androidhttpserver.deviceinfo;

/**
 * Created by LD on 7/17/2016.
 */
public class Device {
    private String osVersion;
    private int osApi;
    private String device;
    private String model;
    private String imei;
    private String imsi;
    private String softwareVersion;
    private String networkID;
    private String networkName;
    private String batteryLevel;

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void setOsApi(int osApi) {
        this.osApi = osApi;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public void setNetworkID(String networkID) {
        this.networkID = networkID;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public void setBatteryLevel(String batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public int getOsApi() {
        return osApi;
    }

    public String getDevice() {
        return device;
    }

    public String getModel() {
        return model;
    }

    public String getImei() {
        return imei;
    }

    public String getImsi() {
        return imsi;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public String getNetworkID() {
        return networkID;
    }

    public String getNetworkName() {
        return networkName;
    }

    public String getBatteryLevel() {
        return batteryLevel;
    }
}
