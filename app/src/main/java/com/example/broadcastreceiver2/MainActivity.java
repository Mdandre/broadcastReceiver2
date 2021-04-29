package com.example.broadcastreceiver2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tvmask;
    private TextView tvsize;
    private TextView tvsimilar;
    private TextView tvinfo;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btnfc;
    private Button onCamera;
    private  Button offCamera;
    public static MainActivity main = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        main = this;
        this.btn1 = (Button)this.findViewById(R.id.btn1);
        this.btn2 = (Button)this.findViewById(R.id.btn2);
        this.btn3 = (Button)this.findViewById(R.id.btn3);
        this.btnfc = (Button)this.findViewById(R.id.btnfc);
        this.onCamera = (Button)this.findViewById(R.id.onCamera);
        this.offCamera = (Button)this.findViewById(R.id.offCamera);
        this.tv1 = (TextView)this.findViewById(R.id.tv1);
        this.tv2 = (TextView)this.findViewById(R.id.tv2);
        this.tv3 = (TextView)this.findViewById(R.id.tv3);
        this.tvmask = (TextView)this.findViewById(R.id.tvmask);
        this.tvsize = (TextView)this.findViewById(R.id.tvsize);
        this.tvsimilar = (TextView)this.findViewById(R.id.tvsimilar);
        this.tvinfo = (TextView)this.findViewById(R.id.tvinfo);
        this.btn1.setOnClickListener(this);
        this.btn2.setOnClickListener(this);
        this.btn3.setOnClickListener(this);
        this.btnfc.setOnClickListener(this);
    }

    public void onClick(View arg0) {
        Intent mintent;
        if (arg0 == this.btn1) {
            mintent = new Intent();
            mintent.setAction("marvel.intent.action.external.omcexc");
            mintent.putExtra("names", "GetAbnormalTemp");
            this.sendBroadcast(mintent);
            this.setText1("null");
        }

        if (arg0 == this.btn2) {
            mintent = new Intent();
            mintent.setAction("marvel.intent.action.external.omcexc");
            mintent.putExtra("names", "GetBodyTemp");
            this.sendBroadcast(mintent);
            this.setText2("null");
        }

        if (arg0 == this.btn3) {
            mintent = new Intent();
            mintent.setAction("marvel.intent.action.external.omcexc");
            mintent.putExtra("names", "GetAllTemp");
            this.sendBroadcast(mintent);
            this.setText3("null");
        }

        if (arg0 == this.btnfc) {
            mintent = new Intent();
            mintent.setAction("marvel.intent.action.external.omcexc");
            mintent.putExtra("names", "GetFaceDetect");
            this.sendBroadcast(mintent);
            this.setText3("null");
        }
        if (arg0 == this.onCamera) {
            mintent = new Intent();
            mintent.setAction("marvel.intent.action.external.omcexc");
            mintent.putExtra("HideFaceWeb", "yes");
            this.sendBroadcast(mintent);
            this.setText3("null");
        }
        if (arg0 == this.offCamera) {
            mintent = new Intent();
            mintent.setAction("marvel.intent.action.external.omcexc");
            mintent.putExtra("HideFaceWeb", "null");
            this.sendBroadcast(mintent);
            this.setText3("null");
        }

    }

    public void setText1(String str) {
        this.tv1.setText(str);
    }

    public void setText2(String str) {
        this.tv2.setText(str);
    }

    public void setText3(String str) {
        this.tv3.setText(str);
    }

    public void setTextMask(String str) {
        this.tvmask.setText(str);
    }

    public void setTextLive(String str) {
    }

    public void setTextSize(String str) {
        this.tvsize.setText(str);
    }

    public void setTextScroe(String str) {
        this.tvsimilar.setText(str);
    }

    public void setTextInfo(String str) {
        this.tvinfo.setText(str);
    }

  /*  public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(2131165184, menu);
        return true;
    }*/
}