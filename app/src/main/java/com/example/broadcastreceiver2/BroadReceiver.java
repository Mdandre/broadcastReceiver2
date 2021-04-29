package com.example.broadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class BroadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        String action = intent.getAction();
        if (action.equals("marvel.intent.action.external.execute")) {
            System.out.println("Marvel.onReceive:" + action);
            Bundle bundle = intent.getExtras();
            Object objn1 = bundle.get("names");
            Object objd1 = bundle.get("datas");
            String names1;
            if (objn1 != null && objd1 != null) {
                System.out.println("Marvel.onReceive:names.toString:" + objn1);
                names1 = objn1.toString();
                String datas1 = objd1.toString();
                if (names1.equals("AbnormalTemp")) {
                    MainActivity.main.setText1(names1 + ":" + datas1);
                    SendMessage("{\"" +names1 +"\""+ ":" + datas1 +"}", context);
                }

                if (names1.equals("BodyTemp")) {
                    MainActivity.main.setText2(names1 + ":" + datas1);
                    SendMessage("{\"" +names1 +"\""+ ":" + datas1 +"}", context);
                }

                if (names1.equals("AllTemp")) {
                    MainActivity.main.setText3(names1 + ":" + datas1);
                    SendMessage("{\"" +names1 +"\""+ ":" + datas1 +"}", context);
                }
                if (names1.equals("GetFaceDetect")) {
                    MainActivity.main.setText3(names1 + ":" + datas1);
                    SendMessage("{\"" +names1 +"\""+ ":" + datas1 +"}", context);
                }
            }

            if (objn1 != null) {
                names1 = objn1.toString();
                if (names1.equals("FaceInfo")) {
                    Object objmask = bundle.get("FaceMask");
                    Object objsize = bundle.get("FaceSize");
                    Object objscroe = bundle.get("FaceScore");
                    Object objinfo = bundle.get("FaceInfo");
                    MainActivity.main.setTextMask("Mask:" + objmask.toString());
                    SendMessage("{\"" +"Mask"+"\""+ ": \"" +  objmask.toString()+"\"}", context);
                    MainActivity.main.setTextSize("Size:" + objsize.toString());
                    SendMessage("{\"" +"Size" +"\""+ ":" +objsize.toString()+"}", context);
                    MainActivity.main.setTextScroe("Score:" + objscroe.toString());
                    SendMessage("{\"" +"Score" +"\""+ ":" + objscroe.toString()+"}", context);
                    MainActivity.main.setTextInfo("FaceInfo:" + objinfo.toString());
                    SendMessage("{\"" +"FaceInfo"+"\""+ ":" + objinfo.toString()+"}", context);
                }
            }
        }

    }
    public void SendMessage( String message, Context context )
    {
        try{
        byte[]  sendBuffer = message.getBytes();
        InetAddress address = InetAddress.getByName("192.168.3.179");
       DatagramPacket packet = new DatagramPacket( sendBuffer, sendBuffer.length, getBroadcastAddress(context), 4444 );
            System.out.println("-----------------------------------------------------------"+ getBroadcastAddress(context));
        //packet = new DatagramPacket( sendBuffer, sendBuffer.length, address, port );
            DatagramSocket s = new DatagramSocket();
            s.send( packet );
        try
        {

            s.send( packet );
        }
        catch (IOException ioe)
        {
           Log.d( "NETWORK", "Failed to send UDP packet due to IOException: " + ioe.getMessage() );
            ioe.printStackTrace();
        }
        catch( Exception e )
        {
            Log.d( "NETWORK", "Failed to send UDP packet due to Exeption: " + e.getMessage() );
            e.printStackTrace();
        }}catch (Exception e){ System.out.println(e);}
    }
    InetAddress getBroadcastAddress(Context context) throws IOException {
        WifiManager wifi = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        DhcpInfo dhcp = wifi.getDhcpInfo();
        // handle null somehow

        int broadcast = (dhcp.ipAddress & dhcp.netmask) | ~dhcp.netmask;
        byte[] quads = new byte[4];
        for (int k = 0; k < 4; k++)
            quads[k] = (byte) ((broadcast >> k * 8) & 0xFF);
        return InetAddress.getByAddress(quads);
    }
    }
