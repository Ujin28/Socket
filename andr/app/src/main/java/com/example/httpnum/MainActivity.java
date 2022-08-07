package com.example.httpnum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.httpnum.databinding.ActivityMainBinding;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.56.1:3000/test");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSocket.on("result", result);
        mSocket.connect();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String num = binding.num.getText().toString();
                mSocket.emit("number", num);

            }

        });


    }

    private Emitter.Listener result = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binding.result.setText(args[0].toString());
                }
            });
        }
    };
}