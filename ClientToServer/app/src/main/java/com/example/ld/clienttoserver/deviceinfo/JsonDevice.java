package com.example.ld.clienttoserver.deviceinfo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.JsonWriter;
import android.util.Log;

import com.example.ld.clienttoserver.deviceserver.Device;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by LD on 7/17/2016.
 */
public class JsonDevice {
    //2, ghi doi tuong java thanh du lieu json vao Writer
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void writeJson(Writer output, DeviceInfo device) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(output);
        jsonWriter.beginObject();

        jsonWriter.name("osVersion").value(device.getOsVersion());
        jsonWriter.name("osApi").value(device.getOsApi());
        jsonWriter.name("device").value(device.getDevice());
        jsonWriter.name("model").value(device.getModel());
        jsonWriter.name("imei").value(device.getImei());
        jsonWriter.name("imsi").value(device.getImsi());
        jsonWriter.name("softwareVersion").value(device.getSoftwareVersion());
        jsonWriter.name("networkID").value(device.getNetworkID());
        jsonWriter.name("networkName").value(device.getNetworkName());
        jsonWriter.name("batteryLevel").value(device.getBatteryLevel());
        jsonWriter.endObject();
    }

    //1, get device icon_info
    public static DeviceInfo getDevice(Context mContext){
        DeviceInfo device = new DeviceInfo();
        String serviceName = mContext.TELEPHONY_SERVICE;
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(serviceName);
        device.setOsVersion(System.getProperty("os.version") + "(" + Build.VERSION.INCREMENTAL + ")");
        device.setOsApi(android.os.Build.VERSION.SDK_INT);
        device.setDevice(android.os.Build.DEVICE);
        device.setModel(android.os.Build.MODEL + " (" + android.os.Build.PRODUCT + ")");
        device.setImei(telephonyManager.getDeviceId());
        device.setImsi(telephonyManager.getSubscriberId());
        device.setSoftwareVersion(telephonyManager.getDeviceSoftwareVersion());
        device.setNetworkID(telephonyManager.getNetworkOperator());
        device.setNetworkName(telephonyManager.getNetworkOperatorName());
        device.setBatteryLevel(MyDevice.getBatteryLevel(mContext));
        String a = "OS Version: " + System.getProperty("os.version")
                +  "\nOS API Level: " + android.os.Build.VERSION.SDK_INT
                + "\nBattery Level: " + MyDevice.getBatteryLevel(mContext)
                + "\nDeviceInfo: " +android.os.Build.DEVICE
                + "\nModel: " + android.os.Build.MODEL + " (" + android.os.Build.PRODUCT + ")"
                + "\nIMEI: " + telephonyManager.getDeviceId()
                + "\nIMSI: " + telephonyManager.getSubscriberId()
                + "\nSoftware Version: "+ telephonyManager.getDeviceSoftwareVersion()
                + "\nNetwork ID: " + telephonyManager.getNetworkOperator()
                + "\nNetwork Name: " + telephonyManager.getNetworkOperatorName();

        return device;
    }

    // send json to server
    public static void sendJsontoServer(final String url, final Context mContext) throws JSONException, UnsupportedEncodingException {
                // post json
                AsyncHttpClient client = new AsyncHttpClient();
                JSONObject jsonParams = new JSONObject();
                String serviceName = mContext.TELEPHONY_SERVICE;
                TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(serviceName);
                    jsonParams.put("osVersion", System.getProperty("os.version"));
                    jsonParams.put("osApi", android.os.Build.VERSION.SDK_INT);
                    jsonParams.put("batteryLevel", MyDevice.getBatteryLevel(mContext));
                    jsonParams.put("device", android.os.Build.DEVICE);
                    jsonParams.put("model", android.os.Build.MODEL + " (" + android.os.Build.PRODUCT + ")");
                    jsonParams.put("imei", telephonyManager.getDeviceId());
                    jsonParams.put("imsi", telephonyManager.getSubscriberId());
                    jsonParams.put("softwareVersion", telephonyManager.getDeviceSoftwareVersion());
                    jsonParams.put("networkID", telephonyManager.getNetworkOperator());
                    jsonParams.put("networkName", telephonyManager.getNetworkOperatorName());
                    StringEntity entity = new StringEntity("#"+jsonParams.toString());
                    entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    client.patch(mContext, url, entity, "application/json", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Log.e("Success", "send json to Server");
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
    }
}
