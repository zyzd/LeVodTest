package com.mstring.andtest.activity.live;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lecloud.sdk.http.engine.HttpUploadEngine;
import com.lecloud.sdk.http.entity.HttpUploadFile;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseLeLiveNetActivity;
import com.mstring.andtest.bean.LeLiveModifyCoverImgBean;
import com.mstring.andtest.dialog.ImagePickerDialog;
import com.mstring.andtest.utils.FileUtil;
import com.mstring.andtest.utils.ImageUtils;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.StringUtils;
import com.mstring.andtest.utils.TLog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class LeLiveModifyCoverImgActivty extends BaseLeLiveNetActivity<LeLiveModifyCoverImgBean>
        implements ImagePickerDialog.ImagePickerListener, EasyPermissions.PermissionCallbacks {

    private static final int CAMERA_PERM = 1;

    private EditText etActivityId;
    private ImageView imageCoverimg;
    private Button btnStartRequest;
    private TextView tvResult;
    private ImagePickerDialog mImagePickerDialog;

    private Uri origUri;
    private final static int CROP = 400;//用来控制裁剪后的图片质量
    private String protraitPath;
    private File protraitFile;
    private Bitmap protraitBitmap;

    private final static String FILE_SAVEPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/juxing/coverimg/";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_le_live_modify_cover_img_activty;
    }

    @Override
    protected void initView() {
        etActivityId = (EditText) findViewById(R.id.et_activityId);
        imageCoverimg = (ImageView) findViewById(R.id.image_coverimg);
        btnStartRequest = (Button) findViewById(R.id.btn_start_request);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void addListener() {
        btnStartRequest.setOnClickListener(this);
        imageCoverimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_coverimg:
                addImage();
                break;
            case R.id.btn_start_request:
                startRequest();
                break;
        }
    }

    private void startRequest() {
        String activityId = etActivityId.getText().toString().trim();
        if (TextUtils.isEmpty(activityId)) {
            Toast.makeText(getApplicationContext(), "直播活动ID不可以为空", Toast.LENGTH_SHORT).show();
            return;
        }

        HttpUploadEngine httpUploadEngine = new HttpUploadEngine();

        httpUploadEngine.setUrl(LeUrlUtils.BaseLeLivePath);

        //设置上传的文件
        HttpUploadFile uploadFile = new HttpUploadFile(protraitPath);
        TLog.d("zyzd", "LeLiveModifyCoverImgActivty>>uploadFile.length--> " + uploadFile.length());
        ArrayList<HttpUploadFile> httpUploadFiles = new ArrayList<>();
        httpUploadFiles.add(uploadFile);
        httpUploadEngine.setFileParams(httpUploadFiles);
        //设置请求头
        TreeMap<String, String> headerMap = new TreeMap<>();
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap.put("charset", "utf-8");
        httpUploadEngine.setHeadParams(headerMap);
        //设置请求体
        TreeMap<String, String> coverImgMap = LeUrlUtils.getLiveModifyCoverImgMap(activityId);
        httpUploadEngine.setUrlParams(coverImgMap);
        //设置监听
        httpUploadEngine.setUploadProgressListener(new HttpUploadEngine.UploadProgressListener() {
            @Override
            public void uploadedProgress(int i, int i1) {
                TLog.d("zyzd", "LeLiveModifyCoverImgActivty>>uploadedProgress--> i:" + i + "i1:  " + i1);
            }
        });
        //开始上传
        httpUploadEngine.doUpload();

//        TLog.d("zyzd", "LeLiveModifyCoverImgActivty>>startRequest--> " + protraitPath);
//        Bitmap bitmap = ImageUtils.getBitmapByPath(protraitPath);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//
//        TreeMap<String, String> coverImgMap = LeUrlUtils.getLiveModifyCoverImgMap(activityId);
//
////        byte[] bytes = bos.toByteArray();
////        TLog.d("zyzd", "LeLiveModifyCoverImgActivty>>startRequest--> " + bytes.length);
//        VolleyHelper.modifyCoverByPost(LeUrlUtils.BaseLeLivePath, coverImgMap, this, this, protraitFile);
    }

    private void addImage() {
        mImagePickerDialog = new ImagePickerDialog(this);
        mImagePickerDialog.setCancelable(true);
        mImagePickerDialog.setCanceledOnTouchOutside(true);
        mImagePickerDialog.setImagePickerListener(this);
        mImagePickerDialog.show();
    }

    @Override
    protected void parseData(LeLiveModifyCoverImgBean data) {
        TLog.d("zyzd", "LeLiveModifyCoverImgActivty>>parseData--> " + data.toString());
    }

    @Override
    protected Type getType() {
        return new TypeToken<LeLiveModifyCoverImgBean>() {
        }.getType();
    }

    /**
     * 选择图片裁剪
     */
    @Override
    public void startImagePick() {
        Intent intent;

        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        }
        mImagePickerDialog.dismiss();
    }

    @Override
    @AfterPermissionGranted(CAMERA_PERM)
    public void startTakePhotoByPermissions() {//打开相机
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            try {
                startTakePhoto();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), R.string.permissions_camera_error, Toast.LENGTH_LONG).show();
            }
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this,
                    getResources().getString(R.string.str_request_camera_message),
                    CAMERA_PERM, perms);
        }
        mImagePickerDialog.dismiss();
    }

    /**
     * 开始拍照
     */
    private void startTakePhoto() {
        Intent intent;
        // 判断是否挂载了SD卡
        String savePath = "";
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/xingju/Camera/";
            File savedir = new File(savePath);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        }

        // 没有挂载SD卡，无法保存文件
        if (StringUtils.isEmpty(savePath)) {
            Toast.makeText(getApplicationContext(), "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
            return;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String fileName = "xingju_" + timeStamp + ".jpg";// 照片命名
        File out = new File(savePath, fileName);
        Uri uri = Uri.fromFile(out);
        origUri = uri;

        String theLarge = savePath + fileName;

        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
    }

    /**
     * 上传新照片
     */
    private void uploadNewPhoto() {
        //showWaitDialog("正在上传头像...");
        Toast.makeText(getApplicationContext(), "正在上传头像...", Toast.LENGTH_SHORT).show();
        // 获取头像缩略图
        if (!StringUtils.isEmpty(protraitPath) && protraitFile.exists()) {
            protraitBitmap = ImageUtils
                    .loadImgThumbnail(protraitPath, 400, 400);
            TLog.d("zyzd", "LeLiveModifyCoverImgActivty>>protraitPath--> " + protraitPath);
        } else {
            //AppContext.showToast("图像不存在，上传失败");
            Toast.makeText(getApplicationContext(), "图像不存在，上传失败", Toast.LENGTH_SHORT).show();
        }

        if (protraitBitmap != null) {
            try {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(), "加载成功", Toast.LENGTH_SHORT).show();
                        // 显示新头像
                        imageCoverimg.setImageBitmap(protraitBitmap);
                    }
                }, 500);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "图像不存在，加载失败", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TLog.d("zyzd", "UserInfoActivity>>onActivityResult--> " + "have" + requestCode + (data == null));
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA://1
                TLog.d("zyzd", "UserInfoActivity>>onActivityResult--> " + ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
                startActionCrop(origUri);// 拍照后裁剪
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                startActionCrop(data.getData());// 选图后裁剪
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:
                uploadNewPhoto();//上传照片
                break;
            default:
                break;
        }
    }

    /**
     * 拍照后裁剪
     *
     * @param data 原始图片
     */
    private void startActionCrop(Uri data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", this.getUploadTempFile(data));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", CROP);// 输出图片大小
        intent.putExtra("outputY", CROP);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent,
                ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
    }

    // 裁剪头像的绝对路径
    private Uri getUploadTempFile(Uri uri) {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(FILE_SAVEPATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            Toast.makeText(getApplicationContext(), "无法保存上传的头像，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String thePath = ImageUtils.getAbsolutePathFromNoStandardUri(uri);

        // 如果是标准Uri
        if (StringUtils.isEmpty(thePath)) {
            thePath = ImageUtils.getAbsoluteImagePath(this, uri);
        }
        String ext = FileUtil.getFileFormat(thePath);
        ext = StringUtils.isEmpty(ext) ? "jpg" : ext;
        // 照片命名
        String cropFileName = "juxing_crop_" + timeStamp + "." + ext;
        // 裁剪头像的绝对路径
        protraitPath = FILE_SAVEPATH + cropFileName;
        protraitFile = new File(protraitPath);

        return Uri.fromFile(protraitFile);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        try {
            startTakePhoto();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), R.string.permissions_camera_error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(getApplicationContext(), R.string.permissions_camera_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    public byte[] getData(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        return bos.toByteArray();
    }
}
