package com.example.administrator.zzudangdang.myinfo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
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


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.zzudangdang.R;
import com.example.administrator.zzudangdang.dao.entity.Image;
import com.example.administrator.zzudangdang.dao.entity.ShopCart;
import com.example.administrator.zzudangdang.dao.entity.StoreInfo;
import com.example.administrator.zzudangdang.dao.entity.UserInfo;
import com.example.administrator.zzudangdang.util.ConstantUtil;
import com.example.administrator.zzudangdang.util.JSONUtil;
import com.example.administrator.zzudangdang.util.TakePictureManager;
import com.example.administrator.zzudangdang.util.UploadImgFileUtil;
import com.google.gson.Gson;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//实现

//待实现
    //编辑框，箭头界面可以优化  (微信標題換了個顔色，如果覺的可疑的話，後期可以統一換）
    //邮箱那个位置出错，应该可以改正
    //主界面那個textview顯示字符的長度未設置（会出现字数变多就乱的情况

//待修改
        //SexAdapter未涉及到保存这个按钮，直接对radiobutton进行了监听，但是那个sexbutton.xml保存按钮未删除



public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {

    public static Activity instance;    //用于在其它活动中销毁此活动


    private static final String TAG = "MainActivity";

    private TextView textView_Self;
    private TextView textView_Name;
    private TextView textView_Sex;
    private TextView textView_We_No;
    private TextView textView_QQ_No;
    private TextView textView_Email;

    //TODO 注意手机号和密码后期要存在手机上获取
    private static String phone = "18838951998";   //用户手机号
    private static String pasword = "123456"; //用户密码

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

    //下面是各组件要填充的内容
    private String self_Text;       //自我介绍（节选）
    private String self_Name;       //昵称
    private String self_QQ;         //QQ号码
    private String self_email;      //电子邮箱
    private String self_Wechat;     //微信
    private String head_uri;        //头像地址
    private int self_sex;   //用户性别
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycentergjj);
        instance = this;
        //初始化各组件
        initView();
    }

    private void initView() {
        //昵称
        textView_Name =(TextView)findViewById(R.id.text_nickname);
        View viewname=(View) findViewById(R.id.view_nickname);
        viewname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.e(TAG, "onClick: now click nickname");
                Intent intent=new Intent(MyInfoActivity.this, NameActivity.class);
                startActivity(intent);
            }
        });

        head = (ImageView) findViewById(R.id.head);
        //性别
        textView_Sex=(TextView)findViewById(R.id.editsex);
        View viewsex=(View) findViewById(R.id.view_sex);
        viewsex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this, SexActivity.class);
                startActivity(intent);
                }
        });

        //自我描述
        textView_Self=(TextView)findViewById(R.id.self_intro);
        View viewSelf=(View) findViewById(R.id.view_self);
        viewSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this, SelfInActivity.class);
                startActivity(intent);
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
                startActivity(intent);
            }
        });

        //邮箱
        textView_Email=(TextView)findViewById(R.id.email);
        View viewemail=(View)findViewById(R.id.view_email);
        viewemail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MyInfoActivity.this, EmailActivity.class);
                startActivity(intent);
            }
        });

        //QQ
        textView_QQ_No=(TextView)findViewById(R.id.text_qqno);
        View viewqqno=(View)findViewById(R.id.view_qq_no);
        viewqqno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this, QQActivity.class);
                startActivity(intent);
            }
        });

        //初始化要填充的变量
        initData();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化要填充的变量 TODO
     */
    private void initData() {
        //网络请求来初始化变量
        sendRequestForInfo();
    }

    private void sendRequestForInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ConstantUtil.getServer() + "/User/getUserInfo?phone=" + phone + "&password=" + pasword)
                            .build();
                    System.out.println(ConstantUtil.getServer() + "/User/getUserInfo?phone=" + phone + "&password=" + pasword);
                    Response response = client.newCall(request).execute();
                    String jsonData = response.body().string();

                    //根据请求到的数据对活动数据进行更新
                    changeViewForResult(jsonData);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //根据请求到的JSON数据对活动数据进行更新
    private void changeViewForResult(final String jsonData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                UserInfo userInfo = JSONUtil.parseUserInfoWithGSON(jsonData);
                if (userInfo != null) {
                    self_Text = userInfo.getIntroduce();       //自我介绍（节选）
                    self_Name = userInfo.getNickname();       //昵称
                    self_QQ = userInfo.getQq();         //QQ号码
                    self_email = userInfo.getEmail();      //电子邮箱
                    self_Wechat = userInfo.getWechat();     //微信
                    head_uri = ConstantUtil.getServer() + "/" +  userInfo.getHead();        //头像地址
                    self_sex = userInfo.getSex();   //用户性别
                    //下面开始初始化各组件
                    changeView();
                }


            }
        });
    }

    private void changeView() {
        textView_Self.setText(self_Text);
        textView_Name.setText(self_Name);
        textView_Sex.setText(self_sex == 1?"男":"女");
        textView_We_No.setText(self_Wechat);
        textView_QQ_No.setText(self_QQ);
        textView_Email.setText(self_email);
        //下面加载头像
        displayImage(head_uri);
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
                            Image image = new Image(phone, base64);
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
                        Image image = new Image(phone, base64);
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

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView();
                    }
                });
            }
        }).start();
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        takePictureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        takePictureManager.attachToActivityForResult(requestCode, resultCode, data);
    }



    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Glide.with(this).load(head_uri).diskCacheStrategy( DiskCacheStrategy.NONE) .skipMemoryCache(true).placeholder(R.mipmap.ic_launcher).into(head);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

}

