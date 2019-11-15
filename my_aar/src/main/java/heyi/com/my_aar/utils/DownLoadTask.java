package com.heyi.pda.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import retrofit2.http.Url;

public class DownLoadTask extends AsyncTask<String, Integer, Boolean> {
    private String fileType = "";
    private String savePath = "/sdcard/heyi/";// 保存apk的文件夹
    private String fileName = "";
    private boolean isLoading = false;
    private int progress = 0;
    private OnDownloadingListener onDownloadingListener;

    public void setOnDownloadingListener(OnDownloadingListener onDownloadingListener) {
        this.onDownloadingListener = onDownloadingListener;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }


    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdir();
            }
            FileOutputStream outputStream = new FileOutputStream(savePath+ fileName + fileType);
            byte[] bytes = new byte[1024];
            int len = 0;
            int part = 0;
            int total = connection.getContentLength();
            Log.e("---", "doInBackground: "+isLoading );
            while ((part = inputStream.read(bytes)) != -1 && isLoading) {
                outputStream.write(bytes);
                len = part + len;
                progress = (int) (((float) len / total) * 100);

                publishProgress(progress);
            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        setLoading(true);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (onDownloadingListener != null) {
            onDownloadingListener.onComplete();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (onDownloadingListener != null) {
            onDownloadingListener.onLoading(values[0]);
        }
        if (values[0] == 100) {
            setLoading(false);
        }
    }

    public interface OnDownloadingListener {
        void onLoading(int progress);

        void onComplete();
    }
}
