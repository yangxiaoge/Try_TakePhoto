package com.example.yjn.trytakephoto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.TResult;

import java.io.File;

/**
 * Created by yjn on 2016/11/4.
 */

public class SimpleFragment extends TakePhotoFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_layout, null);

        //拍照
        view.findViewById(R.id.btnPickByTake).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompressConfig compressConfig = new CompressConfig.Builder().
                        setMaxSize(50 * 1024).setMaxPixel(800).create();  //压缩图片配置

                File file = new File(Environment.getExternalStorageDirectory(), "/yangyangyang/" + /*System.currentTimeMillis()*/ "头像" + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);

                TakePhoto takePhoto = getTakePhoto();
                //takePhoto.onEnableCompress(compressConfig, true); //启用图片压缩
                takePhoto.onPickFromCapture(imageUri);
            }
        });
        return view;
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();

        Bitmap bt = BitmapFactory.decodeFile(result.getImage().getPath());// 从Sd中找头像，解码成Bitmap
        if (bt != null) Toast.makeText(getActivity(), "取到图片啦!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
    }
}
