package vn.sunasterisk.buoi13_internalstorage;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;

import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKeys;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class StorageManager {
    private Context mContext;

    public StorageManager(Context context) {
        mContext = context;
    }

    public void writeFileToInternalStorage(String fileName) {
        String contentFile = "Hello PA1908!";
        FileOutputStream outputStream;

        try {
            outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(contentFile.getBytes());
            outputStream.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFileToInternalStorage2(String fileName)
            throws GeneralSecurityException,
            IOException {
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;

        String masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);

        String parentPath = mContext.getFilesDir().getAbsolutePath();
        File file = new File(parentPath, fileName);

        EncryptedFile encryptedFile = new EncryptedFile.Builder(
                file,
                mContext,
                masterKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build();

        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(encryptedFile)));

        writer.write("Hello PA1908 ^^!");
        writer.close();
    }

    public boolean existFile(String fileName) {
        String filePath = mContext.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(filePath);

        return file.exists();
    }
}
