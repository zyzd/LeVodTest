package com.mstring.andtest.activity.live;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseLeLiveNetActivity;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeLiveModifyActivty extends BaseLeLiveNetActivity {

    private EditText etActivityId;
    private EditText etActivityName;
    private EditText etActivityCategory;
    private EditText etStartTime;
    private EditText etEndTime;
    private EditText etCoverImgUrl;
    private EditText etDescription;
    private RadioGroup rgLiveNum;
    private CheckBox checkbox1;
    private CheckBox checkbox2;
    private CheckBox checkbox3;
    private CheckBox checkbox4;
    private CheckBox checkbox5;
    private RadioGroup rgPlayMode;
    private RadioGroup rgNeedRecord;
    private RadioGroup rgNeedTimeShift;
    private RadioGroup rgNeedFullView;
    private Button btnStartRequest;
    private TextView tvResult;
    private TextView tvDesc;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_le_live_modify_activty;
    }

    @Override
    protected void initView() {
        etActivityId = (EditText) findViewById(R.id.et_activityId);
        etActivityName = (EditText) findViewById(R.id.et_activityName);
        etActivityCategory = (EditText) findViewById(R.id.et_activityCategory);
        etStartTime = (EditText) findViewById(R.id.et_startTime);
        etEndTime = (EditText) findViewById(R.id.et_endTime);
        etCoverImgUrl = (EditText) findViewById(R.id.et_coverImgUrl);
        etDescription = (EditText) findViewById(R.id.et_description);
        checkbox1 = (CheckBox) findViewById(R.id.checkbox_1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox_2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox_3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox_4);
        checkbox5 = (CheckBox) findViewById(R.id.checkbox_5);
        rgLiveNum = (RadioGroup) findViewById(R.id.rg_liveNum);
        rgPlayMode = (RadioGroup)findViewById(R.id.rg_playMode);
        rgNeedRecord = (RadioGroup)findViewById(R.id.rg_need_record);
        rgNeedTimeShift = (RadioGroup)findViewById(R.id.rg_need_time_shift);
        rgNeedFullView = (RadioGroup)findViewById(R.id.rg_need_full_view);
        btnStartRequest = (Button) findViewById(R.id.btn_start_request);
        tvResult = (TextView) findViewById(R.id.tv_result);
        tvDesc = (TextView) findViewById(R.id.tv_desc);
    }


    @Override
    protected void addListener() {
        btnStartRequest.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tvDesc.setVisibility(View.GONE);
        tvResult.setText("注意事项：\n" +
                "（1）直播中的直播活动只能修改媒资信息：activityName、activityCategory 、startTime、endTime、coverImgUrl、description。\n" +
                "（2）活动开始时间应该晚于当前时间\n" +
                "（3）活动未开始，可以修改活动开始时间；反之，不可修改。\n" +
                "（4）活动结束1小时后，不能修改结束时间；反之，可以修改。");
    }

    @Override
    protected void onSuccess(String response) {
        TLog.d("zyzd", "LeLiveModifyActivty>>onSuccess--> over");
    }

    @Override
    protected void parseData(Object data) {

    }

    @Override
    protected Type getType() {
        return null;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_request:
                startRequest();
                break;
        }

    }

    private void startRequest() {
        String activityId = etActivityId.getText().toString().trim();
        if (TextUtils.isEmpty(activityId)) {
            Toast.makeText(getApplicationContext(), "活动ID不能为空", Toast.LENGTH_SHORT).show();
        }

        TreeMap<String, String> params = new TreeMap<>();

        String activityName = etActivityName.getText().toString().trim();
        if (!TextUtils.isEmpty(activityName)) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_ACTIVITY_NAME, activityName);
        }

        String activityCategory = etActivityCategory.getText().toString().trim();
        if (!TextUtils.isEmpty(activityCategory)) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_ACTIVITY_CATEGORY, activityCategory);
        }

        String startTime = etStartTime.getText().toString().trim();
        if (!TextUtils.isEmpty(startTime)) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_START_TIME, transformDate(startTime).getTime() + "");
        }

        String endTime = etEndTime.getText().toString().trim();
        if (!TextUtils.isEmpty(endTime)) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_END_TIME, transformDate(endTime).getTime() + "");
        }

        String coverImgUrl = etCoverImgUrl.getText().toString().trim();
        if (!TextUtils.isEmpty(coverImgUrl)) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_COVERIMG_URL, coverImgUrl);
        }

        String description = etDescription.getText().toString().trim();
        if (!TextUtils.isEmpty(description)) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_DESCRIPTION, description);
        }

        int liveNum = -1;
        switch (rgLiveNum.getCheckedRadioButtonId()) {
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
        if (liveNum != -1) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_LIVE_NUM, liveNum + "");
        }

        int playMode = -1;
        switch (rgPlayMode.getCheckedRadioButtonId()) {
            case R.id.rbtn_real_time:
                playMode = 0;
                break;
            case R.id.rbtn_fluent:
                playMode = 1;
                break;
        }
        if (playMode != -1) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_PLAY_MODE, playMode + "");
        }

        ArrayList<Integer> codeRateTypes = new ArrayList<>();
        if (checkbox5.isChecked()) {
            codeRateTypes.add(99);
        }
        if (checkbox4.isChecked()) {
            codeRateTypes.add(25);
        }
        if (checkbox3.isChecked()) {
            codeRateTypes.add(19);
        }
        if (checkbox2.isChecked()) {
            codeRateTypes.add(16);
        }
        if (checkbox1.isChecked()) {
            codeRateTypes.add(13);
        }

        if (codeRateTypes.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (Integer codeRateType : codeRateTypes) {
                sb.append(codeRateType).append(",");
            }
            params.put(LeUrlUtils.LE_LIVE_PARAMS_CODE_RATE_TYPES, sb.substring(0, sb.length() - 1).toString());
        }

        int needRecord = -1;
        switch (rgNeedRecord.getCheckedRadioButtonId()) {
            case R.id.rbtn_need_record_no:
                needRecord = 0;
                break;
            case R.id.rbtn_need_record_yes:
                needRecord = 1;
                break;
        }
        if (needRecord != -1) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_NEED_RECORD, needRecord + "");
        }

        int needTimeShift = -1;
        switch (rgNeedTimeShift.getCheckedRadioButtonId()) {
            case R.id.rbtn_need_time_shift_no:
                needTimeShift = 0;
                break;
            case R.id.rbtn_need_time_shift_yes:
                needTimeShift = 1;
                break;
        }
        if (needTimeShift != -1) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_NEED_TIMESHIFT, needTimeShift + "");
        }

        int needFullView = -1;
        switch (rgNeedFullView.getCheckedRadioButtonId()) {
            case R.id.rbtn_need_full_view_no:
                needFullView = 0;
                break;
            case R.id.rbtn_need_full_view_yes:
                needFullView = 1;
                break;
        }
        if (needFullView != -1) {
            params.put(LeUrlUtils.LE_LIVE_PARAMS_NEED_FULLVIEW, needFullView + "");
        }

        TreeMap<String, String> liveModifyMaps = LeUrlUtils.getLiveModifyMaps(activityId, params);
        Set<String> keySet = liveModifyMaps.keySet();
        for (String s : keySet) {
            TLog.d("zyzd", "LeLiveModifyActivty>>--> " + "   "+s+"   "+liveModifyMaps.get(s));
        }
        VolleyHelper.stringRequestByPost(LeUrlUtils.BaseLeLivePath, liveModifyMaps, this, this);
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
}
