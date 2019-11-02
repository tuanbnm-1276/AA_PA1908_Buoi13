package vn.sunasterisk.buoi13_internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    private StorageManager mStorageManager;

    /**
     * Khi luu giu lieu o Internal Storage, sau khi ban xoa ung dung
     * di thi du lieu do cung mat luon.
     * Han che luu giu lieu o day, chi dung luu nhung giu lieu bi mat
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorageManager = new StorageManager(getApplicationContext());

        findViewById(R.id.button_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mStorageManager.writeFileToInternalStorage2("PA1909.txt");
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.button_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean existFile = mStorageManager.existFile("PA1909.txt");
                Toast.makeText(MainActivity.this, String.valueOf(existFile), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
