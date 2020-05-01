package com.example.chess191019_0001;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    //TextView mTvBluetoothStatus;
    //TextView mTvReceiveData;
    TextView mTvSendData2;
    TextView mTvSendData1;
    TextView Chess00;
    TextView ChessA1, ChessA2, ChessA3, ChessA4, ChessA5, ChessA6, ChessA7, ChessA8, ChessB1, ChessB2, ChessB3, ChessB4, ChessB5, ChessB6, ChessB7, ChessB8, ChessC1, ChessC2, ChessC3, ChessC4, ChessC5, ChessC6, ChessC7, ChessC8, ChessD1, ChessD2, ChessD3, ChessD4, ChessD5, ChessD6, ChessD7, ChessD8, ChessE1, ChessE2, ChessE3, ChessE4, ChessE5, ChessE6, ChessE7, ChessE8, ChessF1, ChessF2, ChessF3, ChessF4, ChessF5, ChessF6, ChessF7, ChessF8, ChessG1, ChessG2, ChessG3, ChessG4, ChessG5, ChessG6, ChessG7, ChessG8, ChessH1, ChessH2, ChessH3, ChessH4, ChessH5, ChessH6, ChessH7, ChessH8;
    Button mBtnBluetoothOn;
    Button mBtnBluetoothOff;
    Button mBtnConnect;
    Button mBtnSendData;
    Button mBtnManual;

    BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> mPairedDevices;
    List<String> mListPairedDevices;

    Handler mBluetoothHandler;
    ConnectedBluetoothThread mThreadConnectedBluetooth;
    BluetoothDevice mBluetoothDevice;
    BluetoothSocket mBluetoothSocket;

    final static int BT_REQUEST_ENABLE = 1;
    final static int BT_MESSAGE_READ = 2;
    final static int BT_CONNECTING_STATUS = 3;
    final static UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTvBluetoothStatus = (TextView)findViewById(R.id.tvBluetoothStatus);
        //mTvReceiveData = (TextView)findViewById(R.id.tvReceiveData);
        Chess00 = (TextView)findViewById(R.id.Chess_00);
        //Chess99 = (TextView)findViewById(R.id.Chess_99);
        ChessA1 = (TextView)findViewById(R.id.Chess_A1);
        ChessA2 = (TextView)findViewById(R.id.Chess_A2);
        ChessA3 = (TextView)findViewById(R.id.Chess_A3);
        ChessA4 = (TextView)findViewById(R.id.Chess_A4);
        ChessA5 = (TextView)findViewById(R.id.Chess_A5);
        ChessA6 = (TextView)findViewById(R.id.Chess_A6);
        ChessA7 = (TextView)findViewById(R.id.Chess_A7);
        ChessA8 = (TextView)findViewById(R.id.Chess_A8);
        ChessB1 = (TextView)findViewById(R.id.Chess_B1);
        ChessB2 = (TextView)findViewById(R.id.Chess_B2);
        ChessB3 = (TextView)findViewById(R.id.Chess_B3);
        ChessB4 = (TextView)findViewById(R.id.Chess_B4);
        ChessB5 = (TextView)findViewById(R.id.Chess_B5);
        ChessB6 = (TextView)findViewById(R.id.Chess_B6);
        ChessB7 = (TextView)findViewById(R.id.Chess_B7);
        ChessB8 = (TextView)findViewById(R.id.Chess_B8);
        ChessC1 = (TextView)findViewById(R.id.Chess_C1);
        ChessC2 = (TextView)findViewById(R.id.Chess_C2);
        ChessC3 = (TextView)findViewById(R.id.Chess_C3);
        ChessC4 = (TextView)findViewById(R.id.Chess_C4);
        ChessC5 = (TextView)findViewById(R.id.Chess_C5);
        ChessC6 = (TextView)findViewById(R.id.Chess_C6);
        ChessC7 = (TextView)findViewById(R.id.Chess_C7);
        ChessC8 = (TextView)findViewById(R.id.Chess_C8);
        ChessD1 = (TextView)findViewById(R.id.Chess_D1);
        ChessD2 = (TextView)findViewById(R.id.Chess_D2);
        ChessD3 = (TextView)findViewById(R.id.Chess_D3);
        ChessD4 = (TextView)findViewById(R.id.Chess_D4);
        ChessD5 = (TextView)findViewById(R.id.Chess_D5);
        ChessD6 = (TextView)findViewById(R.id.Chess_D6);
        ChessD7 = (TextView)findViewById(R.id.Chess_D7);
        ChessD8 = (TextView)findViewById(R.id.Chess_D8);
        ChessE1 = (TextView)findViewById(R.id.Chess_E1);
        ChessE2 = (TextView)findViewById(R.id.Chess_E2);
        ChessE3 = (TextView)findViewById(R.id.Chess_E3);
        ChessE4 = (TextView)findViewById(R.id.Chess_E4);
        ChessE5 = (TextView)findViewById(R.id.Chess_E5);
        ChessE6 = (TextView)findViewById(R.id.Chess_E6);
        ChessE7 = (TextView)findViewById(R.id.Chess_E7);
        ChessE8 = (TextView)findViewById(R.id.Chess_E8);
        ChessF1 = (TextView)findViewById(R.id.Chess_F1);
        ChessF2 = (TextView)findViewById(R.id.Chess_F2);
        ChessF3 = (TextView)findViewById(R.id.Chess_F3);
        ChessF4 = (TextView)findViewById(R.id.Chess_F4);
        ChessF5 = (TextView)findViewById(R.id.Chess_F5);
        ChessF6 = (TextView)findViewById(R.id.Chess_F6);
        ChessF7 = (TextView)findViewById(R.id.Chess_F7);
        ChessF8 = (TextView)findViewById(R.id.Chess_F8);
        ChessG1 = (TextView)findViewById(R.id.Chess_G1);
        ChessG2 = (TextView)findViewById(R.id.Chess_G2);
        ChessG3 = (TextView)findViewById(R.id.Chess_G3);
        ChessG4 = (TextView)findViewById(R.id.Chess_G4);
        ChessG5 = (TextView)findViewById(R.id.Chess_G5);
        ChessG6 = (TextView)findViewById(R.id.Chess_G6);
        ChessG7 = (TextView)findViewById(R.id.Chess_G7);
        ChessG8 = (TextView)findViewById(R.id.Chess_G8);
        ChessH1 = (TextView)findViewById(R.id.Chess_H1);
        ChessH2 = (TextView)findViewById(R.id.Chess_H2);
        ChessH3 = (TextView)findViewById(R.id.Chess_H3);
        ChessH4 = (TextView)findViewById(R.id.Chess_H4);
        ChessH5 = (TextView)findViewById(R.id.Chess_H5);
        ChessH6 = (TextView)findViewById(R.id.Chess_H6);
        ChessH7 = (TextView)findViewById(R.id.Chess_H7);
        ChessH8 = (TextView)findViewById(R.id.Chess_H8);
        mTvSendData1 =  (TextView) findViewById(R.id.tvSendData1);
        mTvSendData2 =  (TextView) findViewById(R.id.tvSendData2);
        mBtnBluetoothOn = (Button)findViewById(R.id.btnBluetoothOn);
        mBtnBluetoothOff = (Button)findViewById(R.id.btnBluetoothOff);
        mBtnConnect = (Button)findViewById(R.id.btnConnect);
        mBtnSendData = (Button)findViewById(R.id.btnSendData);
        mBtnManual = (Button)findViewById(R.id.btnManual);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mBtnBluetoothOn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOn();
            }
        });
        mBtnBluetoothOff.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOff();
            }
        });
        mBtnConnect.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPairedDevices();

                Chess00.setText(null);

                ChessA1.setText(null);
                ChessA2.setText(null);
                ChessA3.setText(null);
                ChessA4.setText(null);
                ChessA5.setText(null);
                ChessA6.setText(null);
                ChessA7.setText(null);
                ChessA8.setText(null);
                ChessB1.setText(null);
                ChessB2.setText(null);
                ChessB3.setText(null);
                ChessB4.setText(null);
                ChessB5.setText(null);
                ChessB6.setText(null);
                ChessB7.setText(null);
                ChessB8.setText(null);
                ChessC1.setText(null);
                ChessC2.setText(null);
                ChessC3.setText(null);
                ChessC4.setText(null);
                ChessC5.setText(null);
                ChessC6.setText(null);
                ChessC7.setText(null);
                ChessC8.setText(null);
                ChessD1.setText(null);
                ChessD2.setText(null);
                ChessD3.setText(null);
                ChessD4.setText(null);
                ChessD5.setText(null);
                ChessD6.setText(null);
                ChessD7.setText(null);
                ChessD8.setText(null);
                ChessE1.setText(null);
                ChessE2.setText(null);
                ChessE3.setText(null);
                ChessE4.setText(null);
                ChessE5.setText(null);
                ChessE6.setText(null);
                ChessE7.setText(null);
                ChessE8.setText(null);
                ChessF1.setText(null);
                ChessF2.setText(null);
                ChessF3.setText(null);
                ChessF4.setText(null);
                ChessF5.setText(null);
                ChessF6.setText(null);
                ChessF7.setText(null);
                ChessF8.setText(null);
                ChessG1.setText(null);
                ChessG2.setText(null);
                ChessG3.setText(null);
                ChessG4.setText(null);
                ChessG5.setText(null);
                ChessG6.setText(null);
                ChessG7.setText(null);
                ChessG8.setText(null);
                ChessH1.setText(null);
                ChessH2.setText(null);
                ChessH3.setText(null);
                ChessH4.setText(null);
                ChessH5.setText(null);
                ChessH6.setText(null);
                ChessH7.setText(null);
                ChessH8.setText(null);
            }
        });

        mBtnSendData.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mThreadConnectedBluetooth != null) {
                    String SendData1 = mTvSendData1.getText().toString();
                    String SendData2 = mTvSendData2.getText().toString();
                    String SendData = SendData1 + SendData2;

                    mThreadConnectedBluetooth.write(SendData);

                    /*Chess00.setText(null);
                    Chess99.setText(null);*/

                    Chess00.setText(null);

                    ChessA1.setText(null);
                    ChessA2.setText(null);
                    ChessA3.setText(null);
                    ChessA4.setText(null);
                    ChessA5.setText(null);
                    ChessA6.setText(null);
                    ChessA7.setText(null);
                    ChessA8.setText(null);
                    ChessB1.setText(null);
                    ChessB2.setText(null);
                    ChessB3.setText(null);
                    ChessB4.setText(null);
                    ChessB5.setText(null);
                    ChessB6.setText(null);
                    ChessB7.setText(null);
                    ChessB8.setText(null);
                    ChessC1.setText(null);
                    ChessC2.setText(null);
                    ChessC3.setText(null);
                    ChessC4.setText(null);
                    ChessC5.setText(null);
                    ChessC6.setText(null);
                    ChessC7.setText(null);
                    ChessC8.setText(null);
                    ChessD1.setText(null);
                    ChessD2.setText(null);
                    ChessD3.setText(null);
                    ChessD4.setText(null);
                    ChessD5.setText(null);
                    ChessD6.setText(null);
                    ChessD7.setText(null);
                    ChessD8.setText(null);
                    ChessE1.setText(null);
                    ChessE2.setText(null);
                    ChessE3.setText(null);
                    ChessE4.setText(null);
                    ChessE5.setText(null);
                    ChessE6.setText(null);
                    ChessE7.setText(null);
                    ChessE8.setText(null);
                    ChessF1.setText(null);
                    ChessF2.setText(null);
                    ChessF3.setText(null);
                    ChessF4.setText(null);
                    ChessF5.setText(null);
                    ChessF6.setText(null);
                    ChessF7.setText(null);
                    ChessF8.setText(null);
                    ChessG1.setText(null);
                    ChessG2.setText(null);
                    ChessG3.setText(null);
                    ChessG4.setText(null);
                    ChessG5.setText(null);
                    ChessG6.setText(null);
                    ChessG7.setText(null);
                    ChessG8.setText(null);
                    ChessH1.setText(null);
                    ChessH2.setText(null);
                    ChessH3.setText(null);
                    ChessH4.setText(null);
                    ChessH5.setText(null);
                    ChessH6.setText(null);
                    ChessH7.setText(null);
                    ChessH8.setText(null);
                }
            }
        });

        mBluetoothHandler = new Handler(){
            public void handleMessage(android.os.Message msg){
                Timer buttonTimer = new Timer();

                if(msg.what == BT_MESSAGE_READ) {
                    String readMessage = null;
                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String[] array_word;
                    array_word = readMessage.split("");

                    for(int k=0;k<array_word.length;k++){
                        if(array_word[k].equals("1")){
                            array_word[k] = "";
                        }
                        else if(array_word[k].equals("0")){
                            array_word[k] = "♙";
                        }
                    }

                    Chess00.setText(readMessage);

                    if(array_word[5].equals("7")){
                        ChessA1.setText(array_word[6]);
                        ChessA2.setText(array_word[7]);
                        ChessA3.setText(array_word[8]);
                        ChessA4.setText(array_word[9]);
                        ChessA5.setText(array_word[10]);
                        ChessA6.setText(array_word[11]);
                        ChessA7.setText(array_word[12]);
                        ChessA8.setText(array_word[13]);
                        ChessB1.setText(array_word[14]);
                        ChessB2.setText(array_word[15]);
                        ChessB3.setText(array_word[16]);
                        ChessB4.setText(array_word[17]);
                        ChessB5.setText(array_word[18]);
                        ChessB6.setText(array_word[19]);
                        ChessB7.setText(array_word[20]);
                        ChessB8.setText(array_word[21]);
                        ChessC1.setText(array_word[22]);
                        ChessC2.setText(array_word[23]);
                        ChessC3.setText(array_word[24]);
                        ChessC4.setText(array_word[25]);
                        ChessC5.setText(array_word[26]);
                        ChessC6.setText(array_word[27]);
                        ChessC7.setText(array_word[28]);
                        ChessC8.setText(array_word[29]);
                        ChessD1.setText(array_word[30]);
                        ChessD2.setText(array_word[31]);
                        ChessD3.setText(array_word[32]);
                        ChessD4.setText(array_word[33]);
                        ChessD5.setText(array_word[34]);
                        ChessD6.setText(array_word[35]);
                        ChessD7.setText(array_word[36]);
                        ChessD8.setText(array_word[37]);
                        ChessE1.setText(array_word[38]);
                        ChessE2.setText(array_word[39]);
                        ChessE3.setText(array_word[40]);
                        ChessE4.setText(array_word[41]);
                        ChessE5.setText(array_word[42]);
                        ChessE6.setText(array_word[43]);
                        ChessE7.setText(array_word[44]);
                        ChessE8.setText(array_word[45]);
                        ChessF1.setText(array_word[46]);
                        ChessF2.setText(array_word[47]);
                        ChessF3.setText(array_word[48]);
                        ChessF4.setText(array_word[49]);
                        ChessF5.setText(array_word[50]);
                        ChessF6.setText(array_word[51]);
                        ChessF7.setText(array_word[52]);
                        ChessF8.setText(array_word[53]);
                        ChessG1.setText(array_word[54]);
                        ChessG2.setText(array_word[55]);
                        ChessG3.setText(array_word[56]);
                        ChessG4.setText(array_word[57]);
                        ChessG5.setText(array_word[58]);
                        ChessG6.setText(array_word[59]);
                        ChessG7.setText(array_word[60]);
                        ChessG8.setText(array_word[61]);
                        ChessH1.setText(array_word[62]);
                        ChessH2.setText(array_word[63]);
                        ChessH3.setText(array_word[64]);
                        ChessH4.setText(array_word[65]);
                        ChessH5.setText(array_word[66]);
                        ChessH6.setText(array_word[67]);
                        ChessH7.setText(array_word[68]);
                        ChessH8.setText(array_word[69]);
                    }

                    if(array_word[5].equals("")){
                        ChessA1.setText(array_word[5]);
                        ChessA2.setText(array_word[6]);
                        ChessA3.setText(array_word[7]);
                        ChessA4.setText(array_word[8]);
                        ChessA5.setText(array_word[9]);
                        ChessA6.setText(array_word[10]);
                        ChessA7.setText(array_word[11]);
                        ChessA8.setText(array_word[12]);
                        ChessB1.setText(array_word[13]);
                        ChessB2.setText(array_word[14]);
                        ChessB3.setText(array_word[15]);
                        ChessB4.setText(array_word[16]);
                        ChessB5.setText(array_word[17]);
                        ChessB6.setText(array_word[18]);
                        ChessB7.setText(array_word[19]);
                        ChessB8.setText(array_word[20]);
                        ChessC1.setText(array_word[21]);
                        ChessC2.setText(array_word[22]);
                        ChessC3.setText(array_word[23]);
                        ChessC4.setText(array_word[24]);
                        ChessC5.setText(array_word[25]);
                        ChessC6.setText(array_word[26]);
                        ChessC7.setText(array_word[27]);
                        ChessC8.setText(array_word[28]);
                        ChessD1.setText(array_word[29]);
                        ChessD2.setText(array_word[30]);
                        ChessD3.setText(array_word[31]);
                        ChessD4.setText(array_word[32]);
                        ChessD5.setText(array_word[33]);
                        ChessD6.setText(array_word[34]);
                        ChessD7.setText(array_word[35]);
                        ChessD8.setText(array_word[36]);
                        ChessE1.setText(array_word[37]);
                        ChessE2.setText(array_word[38]);
                        ChessE3.setText(array_word[39]);
                        ChessE4.setText(array_word[40]);
                        ChessE5.setText(array_word[41]);
                        ChessE6.setText(array_word[42]);
                        ChessE7.setText(array_word[43]);
                        ChessE8.setText(array_word[44]);
                        ChessF1.setText(array_word[45]);
                        ChessF2.setText(array_word[46]);
                        ChessF3.setText(array_word[47]);
                        ChessF4.setText(array_word[48]);
                        ChessF5.setText(array_word[49]);
                        ChessF6.setText(array_word[50]);
                        ChessF7.setText(array_word[51]);
                        ChessF8.setText(array_word[52]);
                        ChessG1.setText(array_word[53]);
                        ChessG2.setText(array_word[54]);
                        ChessG3.setText(array_word[55]);
                        ChessG4.setText(array_word[56]);
                        ChessG5.setText(array_word[57]);
                        ChessG6.setText(array_word[58]);
                        ChessG7.setText(array_word[59]);
                        ChessG8.setText(array_word[60]);
                        ChessH1.setText(array_word[61]);
                        ChessH2.setText(array_word[62]);
                        ChessH3.setText(array_word[63]);
                        ChessH4.setText(array_word[64]);
                        ChessH5.setText(array_word[65]);
                        ChessH6.setText(array_word[66]);
                        ChessH7.setText(array_word[67]);
                        ChessH8.setText(array_word[68]);
                    }
                    readMessage = null;

                    buttonTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ChessA1.setText(null);
                                    ChessA2.setText(null);
                                    ChessA3.setText(null);
                                    ChessA4.setText(null);
                                    ChessA5.setText(null);
                                    ChessA6.setText(null);
                                    ChessA7.setText(null);
                                    ChessA8.setText(null);
                                    ChessB1.setText(null);
                                    ChessB2.setText(null);
                                    ChessB3.setText(null);
                                    ChessB4.setText(null);
                                    ChessB5.setText(null);
                                    ChessB6.setText(null);
                                    ChessB7.setText(null);
                                    ChessB8.setText(null);
                                    ChessC1.setText(null);
                                    ChessC2.setText(null);
                                    ChessC3.setText(null);
                                    ChessC4.setText(null);
                                    ChessC5.setText(null);
                                    ChessC6.setText(null);
                                    ChessC7.setText(null);
                                    ChessC8.setText(null);
                                    ChessD1.setText(null);
                                    ChessD2.setText(null);
                                    ChessD3.setText(null);
                                    ChessD4.setText(null);
                                    ChessD5.setText(null);
                                    ChessD6.setText(null);
                                    ChessD7.setText(null);
                                    ChessD8.setText(null);
                                    ChessE1.setText(null);
                                    ChessE2.setText(null);
                                    ChessE3.setText(null);
                                    ChessE4.setText(null);
                                    ChessE5.setText(null);
                                    ChessE6.setText(null);
                                    ChessE7.setText(null);
                                    ChessE8.setText(null);
                                    ChessF1.setText(null);
                                    ChessF2.setText(null);
                                    ChessF3.setText(null);
                                    ChessF4.setText(null);
                                    ChessF5.setText(null);
                                    ChessF6.setText(null);
                                    ChessF7.setText(null);
                                    ChessF8.setText(null);
                                    ChessG1.setText(null);
                                    ChessG2.setText(null);
                                    ChessG3.setText(null);
                                    ChessG4.setText(null);
                                    ChessG5.setText(null);
                                    ChessG6.setText(null);
                                    ChessG7.setText(null);
                                    ChessG8.setText(null);
                                    ChessH1.setText(null);
                                    ChessH2.setText(null);
                                    ChessH3.setText(null);
                                    ChessH4.setText(null);
                                    ChessH5.setText(null);
                                    ChessH6.setText(null);
                                    ChessH7.setText(null);
                                    ChessH8.setText(null);
                                }
                            });
                        }
                    }, 30000);
                }
            }
        };

        mBtnManual.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popupdata();
            }
        });
    }

    void Popupdata() {
        Intent intent = new Intent(this, PopupActivity.class);
        startActivityForResult(intent, 1);
    }

    void bluetoothOn() {
        if(mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_LONG).show();
        }
        else {
            if (mBluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "블루투스가 이미 활성화 되어 있습니다.", Toast.LENGTH_LONG).show();
                //mTvBluetoothStatus.setText("활성화");
            }
            else {
                Toast.makeText(getApplicationContext(), "블루투스가 활성화 되어 있지 않습니다.", Toast.LENGTH_LONG).show();
                Intent intentBluetoothEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);
            }
        }
    }
    void bluetoothOff() {
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되었습니다.", Toast.LENGTH_SHORT).show();
            //mTvBluetoothStatus.setText("비활성화");
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 이미 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BT_REQUEST_ENABLE:
                if (resultCode == RESULT_OK) { // 블루투스 활성화를 확인을 클릭하였다면
                    Toast.makeText(getApplicationContext(), "블루투스 활성화", Toast.LENGTH_LONG).show();
                    //mTvBluetoothStatus.setText("활성화");
                } else if (resultCode == RESULT_CANCELED) { // 블루투스 활성화를 취소를 클릭하였다면
                    Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                    //mTvBluetoothStatus.setText("비활성화");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    void listPairedDevices() {
        if (mBluetoothAdapter.isEnabled()) {
            mPairedDevices = mBluetoothAdapter.getBondedDevices();

            if (mPairedDevices.size() > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("장치 선택");

                mListPairedDevices = new ArrayList<String>();
                for (BluetoothDevice device : mPairedDevices) {
                    mListPairedDevices.add(device.getName());
                    //mListPairedDevices.add(device.getName() + "\n" + device.getAddress());
                }
                final CharSequence[] items = mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);
                mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        connectSelectedDevice(items[item].toString());
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    void connectSelectedDevice(String selectedDeviceName) {
        for(BluetoothDevice tempDevice : mPairedDevices) {
            if (selectedDeviceName.equals(tempDevice.getName())) {
                mBluetoothDevice = tempDevice;
                break;
            }
        }
        try {
            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(BT_UUID);
            mBluetoothSocket.connect();
            mThreadConnectedBluetooth = new ConnectedBluetoothThread(mBluetoothSocket);
            mThreadConnectedBluetooth.start();
            mBluetoothHandler.obtainMessage(BT_CONNECTING_STATUS, 1, -1).sendToTarget();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
        }
    }

    private class ConnectedBluetoothThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedBluetoothThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();

            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
                finish();
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {
                    bytes = mmInStream.available();
                    if (bytes != 0) {
                        SystemClock.sleep(100);
                        bytes = mmInStream.available();
                        bytes = mmInStream.read(buffer, 0, bytes);
                        mBluetoothHandler.obtainMessage(BT_MESSAGE_READ, bytes, -1, buffer).sendToTarget();
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }


        public void write(String str) {
            Timer buttonTimer = new Timer();

            byte[] bytes = str.getBytes();
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
            mBtnSendData.setEnabled(false);
            //mTvReceiveData.setText(null);

            buttonTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mBtnSendData.setEnabled(true);ChessA1.setText(null);
                        }
                    });
                }
            }, 30000);
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 해제 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
