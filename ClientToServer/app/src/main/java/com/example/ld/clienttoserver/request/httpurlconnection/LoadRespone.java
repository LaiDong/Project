package com.example.ld.clienttoserver.request.httpurlconnection;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by LD on 7/21/2016.
 */

// da duoc thay the bang StringRequestActivity
public class LoadRespone extends Activity{
    public static String responsestring;

    // truyen va lay du lieu tu server
    public void sendStringPOST(final String url, final String data){
        Thread th = new Thread(){
            @Override
            public void run() {
                super.run();
                URL obj = null;
                try {
                    obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");

                    // For POST only - START
                    con.setDoOutput(true);
                    OutputStream os = con.getOutputStream();
                    os.write(("#" + data).getBytes());
                    os.flush();
                    os.close();

                    // For POST only - END
                    int responseCode = con.getResponseCode();
                    Log.e("POST Response Code :: ", responseCode + "");

                    if (responseCode == HttpURLConnection.HTTP_OK) { //success
                        BufferedReader in = new BufferedReader(new InputStreamReader(
                                con.getInputStream()));
                        String inputLine;
                        final StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        // print result
                        Log.e("response", response.toString());
                        responsestring = response.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onFinishLoadJSonListener.finishLoadJSon(responsestring);
                            }
                        });

                        con.disconnect();
                    } else {
                        System.out.println("POST request not worked");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };th.start();
    }

    public interface OnFinishLoadJSonListener {
        void finishLoadJSon(String json);
    }

    public OnFinishLoadJSonListener onFinishLoadJSonListener;

    public void setOnFinishLoadJSonListener(OnFinishLoadJSonListener onFinishLoadJSonListener) {
        this.onFinishLoadJSonListener = onFinishLoadJSonListener;
    }

}
