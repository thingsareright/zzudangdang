package com.example.administrator.zzudangdang.myinfo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.Image;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.TakePictureManager;
import com.example.administrator.zzudangdang.util.UploadImgFileUtil;
import com.google.gson.Gson;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//实现
    // 从下一个活动传给上一个活动的信息，如昵称，性别，个人介绍，qq，微信，主页显示联系方式
    //每点击一个按钮，按钮的颜色会变化，较为美观
    //相机实现了可以从本地选择照片，但由于照片过大或者其他原因，导致点击照片时会闪退；
    // 同时也实现了调用手机上自带的照相机，但是点击保存之后会闪退

//未实现：
    //上传照片相机功能未实现完全

//待实现
    //编辑框，箭头界面可以优化  (微信標題換了個顔色，如果覺的可疑的話，後期可以統一換）
    //邮箱那个位置出错，应该可以改正
    //主界面那個textview顯示字符的長度未設置（会出现字数变多就乱的情况

//待修改
        //SexAdapter未涉及到保存这个按钮，直接对radiobutton进行了监听，但是那个sexbutton.xml保存按钮未删除

//疑问
        //这么多活动的返回按钮可以用同一个来代替吗

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TextView textView_Self;
    private TextView textView_Name;
    private TextView textView_Sex;
    private TextView textView_We_No;
    private TextView textView_QQ_No;
    private TextView textView_Email;

    private View inflate;
    private Button choosePhoto;  //相机功能未实现完全
    private Button takePhoto;
    private Button cancel;
    private Dialog dialog;
    private static final int CHANGE_EMAIL=8;
    private static final int CHANGE_SELF=7;
    private static final int CHANGE_SEX = 6;
    private static final int CHANGE_QQ_NO = 5;
    private static final int CHANGE_WECHAT_NO = 4;
    private static final int CHANGE_NICK_NAME = 3;
    private static final int TAKE_PHOTO = 1;  //拍照
    private static final int CHOOSE_PHOTO = 2;  //选择照片
    private TakePictureManager takePictureManager;  //第三方类

    private ImageView head;
    private Uri imageUri;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycentergjj);

        //昵称
        textView_Name =(TextView)findViewById(R.id.text_nickname);
        View viewname=(View) findViewById(R.id.view_nickname);
        viewname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.e(TAG, "onClick: now click nickname");
                Intent intent=new Intent(MyInfoActivity.this, NameActivity.class);
                startActivityForResult(intent, CHANGE_NICK_NAME);
            }
        });

        //性别
        textView_Sex=(TextView)findViewById(R.id.editsex);
        View viewsex=(View) findViewById(R.id.view_sex);
        viewsex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this, SexActivity.class);
                startActivityForResult(intent,CHANGE_SEX);
            }
        });

        //自我描述
        textView_Self=(TextView)findViewById(R.id.self_intro);
        View viewSelf=(View) findViewById(R.id.view_self);
        viewSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this, SelfInActivity.class);
                startActivityForResult(intent,CHANGE_SELF);
            }
        });

        //微信
        textView_We_No=(TextView)findViewById(R.id.wechat_no);
        View viewwechatno=(View)findViewById(R.id.wechat);
        viewwechatno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.e(TAG, "onClick: now click wechat");
                Intent intent=new Intent(MyInfoActivity.this, WechatActivity.class);
                startActivityForResult(intent, CHANGE_WECHAT_NO);
            }
        });

        //邮箱
        textView_Email=(TextView)findViewById(R.id.email);
        View viewemail=(View)findViewById(R.id.view_email);
        viewemail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MyInfoActivity.this, EmailActivity.class);
                startActivityForResult(intent, CHANGE_EMAIL);
            }
        });

        //QQ
        textView_QQ_No=(TextView)findViewById(R.id.text_qqno);
        View viewqqno=(View)findViewById(R.id.view_qq_no);
        viewqqno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this, QQActivity.class);
                startActivityForResult(intent,CHANGE_QQ_NO);
            }
        });
    }


    public void show(View view){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        inflate = LayoutInflater.from(this).inflate(R.layout.buttoncameragjj, null);
        choosePhoto = (Button) inflate.findViewById(R.id.choosePhoto);
        takePhoto = (Button) inflate.findViewById(R.id.takePhoto);
        cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        choosePhoto.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        cancel.setOnClickListener(this);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity( Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width=WindowManager.LayoutParams.MATCH_PARENT; //设置弹出的拍照窗口为match_parent
        lp.y = 20;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.takePhoto:
                takePictureManager = new TakePictureManager(this);
                //开启裁剪 比例 1:3 宽高 350 350  (默认不裁剪)
                takePictureManager.setTailor(1, 3, 350, 350);
                //拍照方式
                takePictureManager.startTakeWayByCarema();
                //回调
                takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
                    //成功拿到图片,isTailor 是否裁剪？ ,outFile 拿到的文件 ,filePath拿到的URl
                    @Override
                    public void successful(boolean isTailor, File outFile, Uri filePath) {
                        try {
                            InputStream inputStream = new FileInputStream(outFile);
                            byte[] data = null;
                            data = new byte[inputStream.available()];
                            inputStream.read(data);
                            inputStream.close();
                            //加密
                            String base64 = new String(Base64.encodeBase64(data));
                            //TODO 这里的userid暂且为1
                            Image image = new Image("18838951998", base64);
                            String imageJson = new Gson().toJson(image);
                            sendRequestPostImage(imageJson);    //向服务器发送更改头像请求
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MyInfoActivity.this, "成功拍照", Toast.LENGTH_SHORT).show();
                    }

                    //失败回调
                    @Override
                    public void failed(int errorCode, List<String> deniedPermissions) {
                        Toast.makeText(MyInfoActivity.this, "拍照失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.choosePhoto:
                takePictureManager = new TakePictureManager(this);
                takePictureManager.setTailor(1, 1, 350, 350);
                takePictureManager.startTakeWayByAlbum();
                takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
                    @Override
                    public void successful(boolean isTailor, File outFile, Uri filePath) {
                        byte[] data = new byte[0];
                        try {
                            InputStream inputStream = new FileInputStream(outFile);
                            data = null;
                            data = new byte[inputStream.available()];
                            inputStream.read(data);
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //加密
                        String base64 = new String(Base64.encodeBase64(data));
                        //TODO 这里的userid暂且为1
                        Image image = new Image("18838951998", base64);
                        String imageJson = new Gson().toJson(image);
                        sendRequestPostImage(imageJson);    //向服务器发送更改头像请求
                        Toast.makeText(MyInfoActivity.this, "选择照片成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(int errorCode, List<String> deniedPermissions) {
                        Toast.makeText(MyInfoActivity.this, "选择照片失败", Toast.LENGTH_SHORT).show();
                    }

                });
                break;


            case R.id.btn_cancel:
                dialog.dismiss();
                break;

        }
    }

    private void sendRequestPostImage(final String imageJson) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),imageJson);
                Request request = new Request.Builder()
                        .url(ConstantUtil.getServer() + "/image/")
                        .post(requestBody)
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("upload", response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } ;
            }
        }).start();
    }


    private void openAlbum() { //打开相册
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        takePictureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        takePictureManager.attachToActivityForResult(requestCode, resultCode, data);
        switch (requestCode) {  //启动活动
            case CHANGE_NICK_NAME:
                if(resultCode == RESULT_OK) {
                    String name = data.getStringExtra("nickname");
                    textView_Name.setText(name);
                }
                break;
            case CHANGE_WECHAT_NO:
                if(resultCode == RESULT_OK) {

                    String wechatno = data.getStringExtra("wechatNo");
                    textView_We_No.setText(wechatno);
                }
                break;
            case CHANGE_QQ_NO:
                if(resultCode == RESULT_OK) {

                    String qqno = data.getStringExtra("qqNo");
                    textView_QQ_No.setText(qqno);
                }
                break;
            case CHANGE_SEX:
                if(resultCode == RESULT_OK) {

                    String sexmale = data.getStringExtra("sex");
                    textView_Sex.setText(sexmale);
                }
                break;

            case CHANGE_SELF:
                if(resultCode == RESULT_OK) {

                    String selfdescribe = data.getStringExtra("describe");
                    textView_Self.setText(selfdescribe);
                }
                break;
            case CHANGE_EMAIL:

                if(requestCode == RESULT_OK){
                    String emailText = data.getStringExtra("e_mail");
                    textView_Email.setText(emailText);
                }

            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) { //为了兼容老版本
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath(); //获取图片的真是uri路径
        }
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            head.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }



}

