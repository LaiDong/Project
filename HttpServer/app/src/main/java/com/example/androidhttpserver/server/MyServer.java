package com.example.androidhttpserver.server;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.androidhttpserver.deviceinfo.Device;
import com.example.androidhttpserver.deviceinfo.JsonDevice;
import com.example.androidhttpserver.deviceinfo.MyDevice;
import com.example.androidhttpserver.main.Var;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.FileNameMap;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * Created by LD on 7/23/2016.
 */
public class MyServer extends NanoHTTPD {
    public final static int PORT = 8080;
    public static String jsonText = null;
    public static String ip_client = null;
    private static List<String> listClient = new ArrayList<>();
    public static String change_pass = "admin";
    public static String ssidName = null;
    public static String shareKey = null;
    private static Response res = null;
    private static String nanohttp_querystring = "NanoHttpd.QUERY_STRING";
    private static final String request_login = "user=admin&pass=";
    public static final String
            MIME_PLAINTEXT = "text/plain",
            MIME_HTML = "text/html",
            MIME_JS = "application/javascript",
            MIME_CSS = "text/css",
            MIME_PNG = "image/png",
            MIME_DEFAULT_BINARY = "application/octet-stream",
            MIME_XML = "text/xml";
    Context context;

    public MyServer(Context mContext) throws IOException{
        super(PORT, null);
        this.context = mContext;
        System.out.println("\nRunning! Point your browers to http://localhost:8080/ \n");
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        Log.e("TAG","SERVE ::  URI "+uri);
        final StringBuilder buf = new StringBuilder();
        for (Map.Entry<Object, Object> kv : header.entrySet())
            buf.append(kv.getKey() + " : " + kv.getValue() + "\n");
        InputStream mbuffer = null;
        String msg = "";
        Log.e("LD: ",parms.toString());
        String uriText = uri.toString().trim();
        try {
            if(!parms.toString().trim().contains("{}")){
                switch (getRequest(parms)){
                    case 1:
                        msg ="login#" + MyDevice.getBatteryLevel(context);
                        break;
                    case 2:
                        msg = Var.PIN + MyDevice.getBatteryLevel(context);
                        break;
                    case 3:
                        try {
                            msg = jsonText();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        Intent mIntent = new Intent(Var.ACTION_REBOOT);
                        context.sendBroadcast(mIntent);
                        msg = Var.REBOOT_REQUEST;
                        break;
                    case 5:
                        String newpass = parms.get(Var.CHANGEPASS).toString();
                        Var.save(context, Var.KEY_PASS, newpass);
                        msg = Var.SUCCESS;
                        break;
                    case 6:
                        jsonText = parms.get(Var.JSON_CLIENT).toString().trim().toString();
                        break;
                    case 7:
                        msg = Var.SUCCESS;
                        Intent intent = new Intent(Var.CONFIG_WIFI_AP_ACCOUNT);
                        intent.putExtra("SSID", ssidName);
                        intent.putExtra("KEY", shareKey);
                        context.sendBroadcast(intent);
                        break;
                    case 8:
                        msg = Var.SUCCESS;
                        Intent i = new Intent(Var.CONFIG_WIFI_AP_SSID);
                        i.putExtra("SSID", parms.get(Var.CHANGESSID).toString().trim());
                        context.sendBroadcast(i);
                        break;
                    case 9:
                        String deviceinfo = parms.keySet().toString();
                        if(!listClient.contains(deviceinfo)){
                            listClient.add(deviceinfo);
                        }

                        msg = "user" + listClient.toString();
                        break;
                    case 10:
                        msg = Var.SUCCESS;
                        Intent i1 = new Intent(Var.ENABLEMOBILEDATA);
                        context.sendBroadcast(i1);
                        break;
                    case 11:
                        msg = Var.SUCCESS;
                        Intent i2 = new Intent(Var.DISABLEMOBILEDATA);
                        context.sendBroadcast(i2);
                        break;
                    case 12:
                        msg = Var.SUCCESS;
                        Intent i3 = new Intent(Var.ENABLEDATAROAMING);
                        context.sendBroadcast(i3);
                        break;
                    case 13:
                        msg = Var.SUCCESS;
                        Intent i4 = new Intent(Var.DISABLEDATAROAMING);
                        context.sendBroadcast(i4);
                        break;
                    default:
                        msg = "fail!";
                        break;
                }

            }
            else if(uri != null){

                if(uri.contains(".js")){
                    mbuffer = context.getAssets().open(uri.substring(1));
                    return new NanoHTTPD.Response(HTTP_OK, MIME_JS, mbuffer);
                }else if(uri.contains(".css")){
                    mbuffer = context.getAssets().open(uri.substring(1));
                    return new NanoHTTPD.Response(HTTP_OK, MIME_CSS, mbuffer);

                }else if(uri.contains(".cmd")){
                    mbuffer = context.getAssets().open(uri.substring(1));
                    return new NanoHTTPD.Response(HTTP_OK, MIME_CSS, mbuffer);
                }else if(uri.contains(".png")){
                    mbuffer = context.getAssets().open(uri.substring(1));
                    // HTTP_OK = "200 OK" or HTTP_OK = Status.OK;(check comments)
                    return new NanoHTTPD.Response(HTTP_OK, MIME_PNG, mbuffer);
                }else if(uri.contains(".html")){
                    mbuffer = context.getAssets().open(uri.substring(1));
                    // HTTP_OK = "200 OK" or HTTP_OK = Status.OK;(check comments)
                    return new NanoHTTPD.Response(HTTP_OK, MIME_HTML, mbuffer);
                }else if (uri.contains("/mnt/sdcard")){
                    Log.e("TAG","request for media on sdCard "+uri);
                    File request = new File(uri);
                    mbuffer = new FileInputStream(request);
                    FileNameMap fileNameMap = URLConnection.getFileNameMap();
                    String mimeType = fileNameMap.getContentTypeFor(uri);

                    Response streamResponse = new Response(HTTP_OK, mimeType, mbuffer);
                    Random rnd = new Random();
                    String etag = Integer.toHexString( rnd.nextInt() );
                    streamResponse.addHeader( "ETag", etag);
                    streamResponse.addHeader( "Connection", "Keep-alive");
                    return streamResponse;
                }else{
                    mbuffer = context.getAssets().open("index.html");
                    return new NanoHTTPD.Response(HTTP_OK, MIME_HTML, mbuffer);
                }
            }

        } catch (IOException e) {
            Log.e("TAG","Error opening file"+uri.substring(1));
            e.printStackTrace();
        }

        return new Response(HTTP_OK, MIME_PLAINTEXT, msg);

    }

    public static String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += "LocalAddress: "
                                + inetAddress.getHostAddress() + "\n";
                    }

                }

            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }

        return ip;
    }

   /* @Override
    protected ClientHandler createClientHandler(Socket finalAccept, InputStream inputStream) {
        ip_client = finalAccept.getInetAddress().toString();
        if(ip_client==null){
            try {
                inputStream.close();
                finalAccept.close();
                Log.e("Warning ", "no client connect...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.e("Client: ", ip_client);
        return super.createClientHandler(finalAccept, inputStream);
    }*/

    private String jsonText() throws IOException {
        String jsonTextDevice = "";
        StringWriter output = new StringWriter();
        Device device = JsonDevice.getDevice(context);
        // Chuyển đối tượng Java 'device' thành dữ liệu JSON và ghi vào Writer output.
        JsonDevice.writeJson(output, device);
        jsonTextDevice = output.toString();
        String response = jsonTextDevice;
        return response;
    }

    private int getRequest(Properties properties){
        int k=0;
        Map<String, String> parms= new HashMap<>();
        for (final String name: properties.stringPropertyNames())
            parms.put(name, properties.getProperty(name));
        for(String key: parms.keySet()) {
            if (parms.get(key).trim().equals(Var.get(context, Var.KEY_PASS))) {
               /* String s[] = key.split("#");
                jsonText = s[1];*/
                k = 1;
            } else if (parms.get(key).trim().equals(Var.PIN)) {
                k = 2;
            }else if(parms.get(key).trim().equals(Var.JSON_DEVICE)){
                k=3;
            }else if(parms.get(key).trim().equals(Var.REBOOT_REQUEST)){
                k=4;
            }else if(key.contains(Var.CHANGEPASS)){
                Log.e("newpass", parms.get(key));
                k=5;
            }else if(key.contains(Var.JSON_CLIENT)){
                k=6;
            }else if(key.equals(Var.CONFIGWIFI)){
                String s[] = parms.get(key).trim().split("#");
                ssidName = s[0];
                shareKey = s[1];
                k=7;
            }else if(key.equals(Var.CHANGESSID)){
                k=8;
            }else if(parms.get(key).trim().equals(Var.USERMANAGEMENT)){
                k=9;
            }
        }
        return k;
    }

  /*  private void getRequestWeb(String msg){
        InputStream stream = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            stream = new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8));
        }
        res = new Response(Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, stream, msg.length());
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.addHeader("Access-Control-Max-Age", "86400");
    }*/

    private String getKey(Map<String, String> parms){
        String key = "";
        for (Map.Entry<String, String> e : parms.entrySet()) {
             key = e.getKey();
        }
        return key;
    }

}


