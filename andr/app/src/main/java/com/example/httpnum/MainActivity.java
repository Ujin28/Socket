package com.example.httpnum;

import androidx.appcompat.app.AppCompatActivity;

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
    //private JSONObject number;
    //private String number;

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

        //mSocket.on("result", onNewMessage);
        mSocket.connect();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String num = binding.num.getText().toString();
                mSocket.emit("number", num);

                String result;
                //mSocket.on("result", result);

                /**mSocket.on(Socket.EVENT_CONNECT, (Object... objects) -> {
                    JSONObject preJsonObject = new JSONObject();
                    preJsonObject.put("roomName", myroom);
                    JSONObject jsonObject = new JSONObject(preJsonObject.toString());
                    mSocket.emit("joinRoom",jsonObject);
                }).on("recMsg", (Object... objects) -> {
                    System.out.println(objects[0]);
                    JsonParser jsonParsers = new JsonParser();
                    JSONObject jsonObject = (JSONObject) jsonParsers.parse(objects[0] + "");
                    jTextArea.append(jsonObject.get("comment").getAsString() + "");
                });**/

            }

        });


    }

    //import io.socket.emitter.Emitter;

    /*private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }

                    // add the message to view
                    addMessage(username, message);
                }
            });
        }
    };*/
}