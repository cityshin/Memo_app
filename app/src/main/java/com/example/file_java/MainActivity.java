package com.example.file_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    String FILENAME="a.txt";

    String NAME;
    EditText editText_File_name;
    EditText editText_File_content;
    Button btn_read;
    Button btn_write;
    Button btn_delete;


//    EditText editText_File_name=findViewById(R.id.File_name);
//    EditText editText_File_content = findViewById(R.id.File_content);
//    String FILENAME= editText_File_name.toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_File_name = findViewById(R.id.File_name);
        editText_File_content=findViewById(R.id.File_content);
        btn_write = findViewById(R.id.btn_write);
        btn_read = findViewById(R.id.btn_read);
        btn_delete = findViewById(R.id.btn_delete);

        write();
        read();
        delete();
    }

    public void write() {
        btn_write.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {

                    FileOutputStream fos = openFileOutput(editText_File_name.getText().toString(),Context.MODE_PRIVATE);
                    fos.write(editText_File_content.getText().toString().getBytes(StandardCharsets.UTF_8));
                    fos.close();
                    Toast.makeText(MainActivity.this, "저장이 완료되었습니다", Toast.LENGTH_SHORT).show();

                }  catch (IOException e) {
                   // e.printStackTrace();
                }
            }
        });
    }

    public void read() {

        btn_read.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = openFileInput(editText_File_name.getText().toString());        //EditTEXT 불러올 때는 항상 getText().toString()
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    editText_File_name.setText(editText_File_name.getText().toString());
                    editText_File_content.setText(new String(buffer) );
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void delete() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              deleteFile(editText_File_name.getText().toString());
              editText_File_name.setText(null);
              editText_File_content.setText(null);
            }
        });
    }


}

