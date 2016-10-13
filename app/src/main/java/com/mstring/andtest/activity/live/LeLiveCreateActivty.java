package com.mstring.andtest.activity.live;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseLeLiveNetActivity;
import com.mstring.andtest.bean.LeLiveCreateBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class LeLiveCreateActivty extends BaseLeLiveNetActivity<LeLiveCreateBean> {

    EditText etActivityName;
    EditText etActivityCategory;
    EditText etStartTime;
    EditText etEndTime;
    RadioGroup mRgPlayMode;
    public RadioGroup mRgLiveNum;
    CheckBox checkbox1;
    CheckBox checkbox2;
    CheckBox checkbox3;
    CheckBox checkbox4;
    CheckBox checkbox5;
    Button btnStartRequest;
    TextView tvResult;

    @Override
    protected void initView() {
        etActivityName = (EditText) findViewById(R.id.et_activityName);
        etActivityCategory = (EditText) findViewById(R.id.et_activityCategory);
        etStartTime = (EditText) findViewById(R.id.et_startTime);
        etEndTime = (EditText) findViewById(R.id.et_endTime);
        mRgPlayMode = (RadioGroup) findViewById(R.id.rg_playMode);
        mRgLiveNum = (RadioGroup) findViewById(R.id.rg_liveNum);
        checkbox1 = (CheckBox) findViewById(R.id.checkbox_1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox_2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox_3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox_4);
        checkbox5 = (CheckBox) findViewById(R.id.checkbox_5);
        btnStartRequest = (Button) findViewById(R.id.btn_start_request);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void addListener() {
        btnStartRequest.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_le_live_create_activty;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        startRequest();
    }

    private void startRequest() {
        String activityName = etActivityName.getText().toString().trim();
        if (TextUtils.isEmpty(activityName)) {
            Toast.makeText(getApplicationContext(), "活动名称不能为空", Toast.LENGTH_SHORT).show();
        }
        String activityCategory = etActivityCategory.getText().toString().trim();
        if (TextUtils.isEmpty(activityCategory)) {
            Toast.makeText(getApplicationContext(), "活动类型不能为空", Toast.LENGTH_SHORT).show();
        }
        String startTime = etStartTime.getText().toString().trim();
        if (TextUtils.isEmpty(startTime)) {
            Toast.makeText(getApplicationContext(), "开始时间不能为空", Toast.LENGTH_SHORT).show();
        }
        String endTime = etEndTime.getText().toString().trim();
        if (TextUtils.isEmpty(endTime)) {
            Toast.makeText(getApplicationContext(), "结束时间不能为空", Toast.LENGTH_SHORT).show();
        }

        int playMode = 0;

        switch (mRgPlayMode.getCheckedRadioButtonId()) {
            case R.id.rbtn_real_time:
                playMode = 0;
                break;
            case R.id.rbtn_fluent:
                playMode = 1;
                break;
        }
        int liveNum = 0;
        switch (mRgLiveNum.getCheckedRadioButtonId()) {
            case R.id.rbtn_one:
                liveNum = 1;
                break;
            case R.id.rbtn_two:
                liveNum = 2;
                break;
            case R.id.rbtn_three:
                liveNum = 3;
                break;
            case R.id.rbtn_four:
                liveNum = 4;
                break;
        }

        ArrayList<Integer> integers = new ArrayList<>();
        if (checkbox5.isChecked()) {
            integers.add(99);
        }
        if (checkbox4.isChecked()) {
            integers.add(25);
        }
        if (checkbox3.isChecked()) {
            integers.add(19);
        }
        if (checkbox2.isChecked()) {
            integers.add(16);
        }
        if (checkbox1.isChecked()) {
            integers.add(13);
        }

        if (integers.size() < 1) {
            Toast.makeText(getApplicationContext(), "码率必须至少选择一个...", Toast.LENGTH_SHORT).show();
            return;
        }

        int[] codeRateTypes = new int[integers.size()];
        for (int i = 0; i < codeRateTypes.length; i++) {
            codeRateTypes[i] = integers.get(i);
        }
        Map<String, String> liveCreateMaps = LeUrlUtils.getLiveCreateMaps(activityName, activityCategory, transformDate(startTime).getTime(), transformDate(endTime).getTime(), playMode, liveNum, codeRateTypes, null);
        VolleyHelper.stringRequestByPost(LeUrlUtils.BaseLeLivePath, liveCreateMaps, this, this);
    }

    public static final String format1 = "yyyy-MM-dd HH:mm:ss";

    public Date transformDate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format1);
        Date date = null;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
//            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "时间格式错误", Toast.LENGTH_SHORT).show();
        }
        return date;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        TLog.d("zyzd", "LeLiveCreateActivty>>onErrorResponse--> " + error.getMessage());
    }

//    @Override
//    protected void onSuccess(String response) {
//        TLog.d("zyzd", "LeLiveCreateActivty>>onSuccess--> " + response);
//        LeLiveCreateBean resultBean = (LeLiveCreateBean) new Gson().fromJson(response, getType());
//        if (resultBean == null)
//            return;
//        parseData(resultBean);
//    }

    @Override
    protected void parseData(LeLiveCreateBean data) {
        tvResult.setText(data.toString());
    }

    @Override
    protected Type getType() {
        return new TypeToken<LeLiveCreateBean>() {
        }.getType();
    }
}
