package com.example.vadim.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class KryptoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.krypto_layout);

    }

    public void onEncrypt (View v) {
        String key = ((EditText) findViewById(R.id.key)).getText().toString();
        String data = ((EditText) findViewById(R.id.data)).getText().toString();
        try {
            CipherModel getCipher = new CipherModel(key);
            String getEncryption = getCipher.encrypt(data);
            ((EditText)findViewById(R.id.data)).setText(getEncryption);
        }
        catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    public void onDecrypt (View v) {
        String key = ((EditText) findViewById(R.id.key)).getText().toString();
        String data = ((EditText) findViewById(R.id.data)).getText().toString();
        try
        {
            CipherModel getCipher = new CipherModel(key);
            String getDecryption = getCipher.decrypt(data);
            ((EditText)findViewById(R.id.data)).setText(getDecryption);
        }
        catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    public void onSave(View v)
    {
        try
        {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast label = Toast.makeText(this, "Note Saved.", Toast.LENGTH_LONG);
            label.show();
        }
        catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    public void onLoad(View v)
    {
        try
        {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            Toast label = Toast.makeText(this, "Note Loaded.", Toast.LENGTH_LONG);
            File dir = this.getFilesDir();
            File getFiles = new File(dir, name);
            FileReader fr = new FileReader(getFiles);

            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read())
            {
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.data)).setText(show);
            label.show();
        }
        catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }
}

