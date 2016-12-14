package com.example.houshuai.mvvmdemo;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by houshuai on 2016/12/14.
 */

public class ViewModle {
    public final ObservableField<String> mUrl = new ObservableField<>();
    public final ObservableField<String> mText = new ObservableField<>();
    public static final String VIDEO_NAME = "welcome_video.mp4";
    private Resources resources;
    private Context mContext;

    public ViewModle(Resources resources, Context mContext) {
        this.resources = resources;
        this.mContext = mContext;

        mUrl.set(filePath());
        mText.set("你好");
    }

    public String filePath() {
        String path = copyVideoFile().getPath();
        Log.e("AA", "地址："+path);
        return path;
    }

    private File copyVideoFile() {
        Log.e("AA", "copyVideoFile");

        File videoFile;
        try {
            FileOutputStream fos = mContext.openFileOutput(VIDEO_NAME, MODE_PRIVATE);
            InputStream in = resources.openRawResource(R.raw.welcome_video);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoFile = mContext.getFileStreamPath(VIDEO_NAME);
        if (!videoFile.exists())
            throw new RuntimeException("video file has problem, are you sure you have welcome_video.mp4 in res/raw folder?");
        return videoFile;
    }


    @BindingAdapter({"videourl"})
    public static void setVideoUrl(VideoView video, String url) {
        Log.e("AA", "setVideoUrl--"+url);
            video.setVideoPath(url);
            video.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
            });
    }

}
