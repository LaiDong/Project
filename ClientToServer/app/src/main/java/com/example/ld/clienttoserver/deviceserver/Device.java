package com.example.ld.clienttoserver.deviceserver;

/**
 * Created by LD on 7/17/2016.
 */
public class Device {
    public String status;
    public String detail;

    public Device(String status, String detail) {
        this.status = status;
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
