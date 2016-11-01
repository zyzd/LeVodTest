package com.mstring.andtest.activity.live;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseLeLiveNetActivity;
import com.mstring.andtest.bean.LeLiveCreateRecTaskBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;
import java.util.TreeMap;

import static com.mstring.andtest.utils.DateUtil.transformDate;

/**
 * Created by 李宗源 on 2016/10/31.
 * <p>
 * 备注：虽然乐视接口文档中是说明返回参数为字段名为taskId，但实际返回的接口是只有任务ID的值而不是一个合格的json对象。
 * 所以取消了LeLiveCreateRecTaskBean的使用。
 * <p>
 * 作用：对一个直播进行部分时间段的内容进行录制，没有在直播时获取的数据是0.
 */

public class LeLiveCreateRecTaskActivity extends BaseLeLiveNetActivity {

    public Button mBtnStartRequest;
    public TextView mTvResult;
    public EditText mEtLiveId;
    public EditText mEtStartTime;
    public EditText mEtEndTime;

    @Override
    protected void initView() {
        super.initView();
        mEtLiveId = (EditText) findViewById(R.id.et_liveId);
        mEtStartTime = (EditText) findViewById(R.id.et_startTime);
        mEtEndTime = (EditText) findViewById(R.id.et_endTime);
        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
        mTvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void addListener() {
        super.addListener();
        mBtnStartRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_request:
                startRequest();
                break;
        }
    }

    private void startRequest() {
        mTvResult.setText("");

        String liveId = mEtLiveId.getText().toString().trim();
        String startTime = mEtStartTime.getText().toString().trim();
        String endTime = mEtEndTime.getText().toString().trim();

        if (TextUtils.isEmpty(liveId)) {
            Toast.makeText(getApplicationContext(), "直播ID不可以为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(startTime)) {
            Toast.makeText(getApplicationContext(), "开始时间不可以为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(endTime)) {
            Toast.makeText(getApplicationContext(), "结束不可以为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        TreeMap<String, String> createRecTaskMap = LeUrlUtils.getCreateRecTaskMap(liveId, transformDate(startTime).getTime() + "", transformDate(endTime).getTime() + "");

        VolleyHelper.stringRequestByPost(LeUrlUtils.BaseLeLivePath, createRecTaskMap, this, this);
    }



    /**
     * 不解析，所以进行重写
     *
     * @param response
     */
    @Override
    protected void onSuccess(String response) {
        if (TextUtils.isEmpty(response)) {
            TLog.d("zyzd", "BaseLeLiveNetActivity>>onSuccess--> " + "response is empty!");
            return;
        }
        TLog.d("zyzd", "BaseLeLiveNetActivity>>onSuccess--> " + response);

        parseData(response);
    }

    @Override
    protected void parseData(Object data) {
        String taskId = (String) data;
        mTvResult.setText("taskId:" + taskId);
        try {
            if (Integer.parseInt(taskId) > 0) {
                Toast.makeText(getApplicationContext(), "创建录制任务成功！", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Type getType() {
        return new TypeToken<LeLiveCreateRecTaskBean>() {
        }.getType();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_le_live_create_rec_task;
    }
}
