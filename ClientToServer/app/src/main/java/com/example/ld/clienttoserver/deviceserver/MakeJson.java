package com.example.ld.clienttoserver.deviceserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LD on 7/22/2016.
 */
public class MakeJson {

public static void devicejson(final String respone){
    Thread th = new Thread(){
        @Override
        public void run() {
            super.run();
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(respone);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String json = jsonObject.toString();
            // dung gson de tach json
            Gson gson = new GsonBuilder().create();
            DeviceJson deviceJson = gson.fromJson(json, DeviceJson.class);
            OnFinishDeviceJSonListener.finishDeviceJSon(deviceJson);
        }
    };th.start();
}

    public interface OnFinishDeviceJSonListener {
        void finishDeviceJSon(DeviceJson json);
    }

    public static OnFinishDeviceJSonListener OnFinishDeviceJSonListener;

    public void setOnFinishLoadJSonListener(OnFinishDeviceJSonListener OnFinishDeviceJSonListener) {
        this.OnFinishDeviceJSonListener = OnFinishDeviceJSonListener;
    }

}
