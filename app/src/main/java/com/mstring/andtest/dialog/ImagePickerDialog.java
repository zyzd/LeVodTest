package com.mstring.andtest.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.mstring.andtest.R;
import com.mstring.andtest.utils.TDevice;


/**
 * Created by 李宗源 on 2016/8/13.
 */
public class ImagePickerDialog extends Dialog implements View.OnClickListener {
    private LinearLayout mPhotographUpload;
    private LinearLayout mPhotoUpload;


    public ImagePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
        //获取界面布局
        View contentView = getLayoutInflater().inflate(R.layout.dialog_image_picker, null);
        mPhotographUpload = (LinearLayout) contentView.findViewById(R.id.ll_select_photograph_upload);
        mPhotoUpload = (LinearLayout) contentView.findViewById(R.id.ll_select_photo_upload);

        mPhotographUpload.setOnClickListener(this);
        mPhotoUpload.setOnClickListener(this);

        // 去掉标题，必须在setContentView之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(contentView);
        // 设置Dialog的宽度等于屏幕的宽度，必须在setContentView后调用
        WindowManager.LayoutParams p = getWindow().getAttributes();
        int screenWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        p.width = (int) (screenWidth - 2 * 20 * TDevice.getDisplayMetrics().density);
        getWindow().setAttributes(p);
    }

    public ImagePickerDialog(Context context) {
        this(context, R.style.progress_dialog);
        getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_select_photograph_upload://本地上传
                if (imagePickerListener != null) {
                    imagePickerListener.startImagePick();
                }
                break;
            case R.id.ll_select_photo_upload://拍照上传
                if (imagePickerListener != null) {
                    imagePickerListener.startTakePhotoByPermissions();
                }
                break;
        }
    }

    private ImagePickerListener imagePickerListener;

    public void setImagePickerListener(ImagePickerListener imagePickerListener) {
        this.imagePickerListener = imagePickerListener;
    }

    public interface ImagePickerListener {
        void startImagePick();

        void startTakePhotoByPermissions();
    }

}
