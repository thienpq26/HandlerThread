package com.example.handlerthreaddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private HandlerThread handlerThread;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("D/on", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHandlerThread();

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("D/on", "on run thread1");
                for (int i = 1; i <= 10; i++) {
                    Message message = new Message();
                    message.what = 1;
                    message.arg1 = i;
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("D/on", "on run thread2");
                for (int i = 1; i <= 10; i++) {
                    Message message = new Message();
                    message.what = 2;
                    message.arg1 = i;
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("D/on", "on run thread3");
                for (int i = 1; i <= 10; i++) {
                    Message message = new Message();
                    message.what = 3;
                    message.arg1 = i;
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private void initHandlerThread() {
        Log.d("D/on", "initHandlerThread");
        handlerThread = new HandlerThread("Handler Thread");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d("D/on", "handleMessage");
                switch (msg.what) {
                    case 1: {
                        Toast.makeText(MainActivity.this, "Thread 1: " + msg.arg1, Toast.LENGTH_SHORT).show();
                        Log.d("D/ex", "Thread 1: " + msg.arg1);
                        break;
                    }
                    case 2: {
                        Toast.makeText(MainActivity.this, "Thread 2: " + msg.arg1 + " ", Toast.LENGTH_SHORT).show();
                        Log.d("D/ex", "Thread 2: " + msg.arg1);
                        break;
                    }
                    case 3: {
                        Toast.makeText(MainActivity.this, "Thread 3: " + msg.arg1 + "  ", Toast.LENGTH_SHORT).show();
                        Log.d("D/ex", "Thread 3: " + msg.arg1);
                        break;
                    }
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        Log.d("D/on", "handleMessage");
        super.onDestroy();
        handlerThread.quit();
    }
}
