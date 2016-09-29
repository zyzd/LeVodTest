package com.mstring.andtest.bean;

import java.io.Serializable;

/**
 * Created by 李宗源 on 2016/9/28.
 * 乐视video.get 和video.list的bean
 */

public class LeVideoGetBean {
    private int video_id;//视频ID
    private String video_unique;//视频唯一标识码
    private String video_name;//视频名称
    private int status;//视频状态：
    private String video_desc;//视频简介
    private String tag;//标签
    private int is_pay;//视频是否收费：0表示不收费；1表示收费
    private String img;//频截图URL地址
    private int video_duration;//播放时长，单位为秒
    private int initial_size;//上传文件原始大小，单位为字节
    private int error_code;//错误码，视频处理失败的错误码（0: 成功, 其他值: 失败）
    private String error_desc;//错误描述，视频处理失败的具体原因描述
    private String add_time;//视频添加时间，格式为：yyyy-mm-dd hh:ii:ss
    private String complete_time;//上传完毕时间，格式为：yyyy-mm-dd hh:ii:ss
    private String file_md5;//视频源文件的MD5值
    private String init_pic;//视频自定义截图URL地址，默认为空
    private int isdownload;//是否可以下载 0不可以 1可以
    private int isdrm;//视频是否DRM：0 否，1是
    private int mid;//媒资ID
    private int usercategory1;//视频一级分类ID
    private int usercategory2;//视频二级分类ID

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getVideo_unique() {
        return video_unique;
    }

    public void setVideo_unique(String video_unique) {
        this.video_unique = video_unique;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVideo_desc() {
        return video_desc;
    }

    public void setVideo_desc(String video_desc) {
        this.video_desc = video_desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(int video_duration) {
        this.video_duration = video_duration;
    }

    public int getInitial_size() {
        return initial_size;
    }

    public void setInitial_size(int initial_size) {
        this.initial_size = initial_size;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(String complete_time) {
        this.complete_time = complete_time;
    }

    public String getFile_md5() {
        return file_md5;
    }

    public void setFile_md5(String file_md5) {
        this.file_md5 = file_md5;
    }

    public String getInit_pic() {
        return init_pic;
    }

    public void setInit_pic(String init_pic) {
        this.init_pic = init_pic;
    }

    public int getIsdownload() {
        return isdownload;
    }

    public void setIsdownload(int isdownload) {
        this.isdownload = isdownload;
    }

    public int getIsdrm() {
        return isdrm;
    }

    public void setIsdrm(int isdrm) {
        this.isdrm = isdrm;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getUsercategory1() {
        return usercategory1;
    }

    public void setUsercategory1(int usercategory1) {
        this.usercategory1 = usercategory1;
    }

    public int getUsercategory2() {
        return usercategory2;
    }

    public void setUsercategory2(int usercategory2) {
        this.usercategory2 = usercategory2;
    }

    @Override
    public String toString() {
        return "LeVideoGetBean{" +
                "video_id=" + video_id +
                ", video_unique='" + video_unique + '\'' +
                ", video_name='" + video_name + '\'' +
                ", status=" + status +
                ", video_desc='" + video_desc + '\'' +
                ", tag='" + tag + '\'' +
                ", is_pay=" + is_pay +
                ", img='" + img + '\'' +
                ", video_duration=" + video_duration +
                ", initial_size=" + initial_size +
                ", error_code=" + error_code +
                ", error_desc='" + error_desc + '\'' +
                ", add_time='" + add_time + '\'' +
                ", complete_time='" + complete_time + '\'' +
                ", file_md5='" + file_md5 + '\'' +
                ", init_pic='" + init_pic + '\'' +
                ", isdownload=" + isdownload +
                ", isdrm=" + isdrm +
                ", mid=" + mid +
                ", usercategory1=" + usercategory1 +
                ", usercategory2=" + usercategory2 +
                '}';
    }
}
//10表示可以正常播放；
//20表示转码失败；
//21表示审核失败；
//22表示片源错误；
//23表示发布失败；
//24表示上传失败；
//30表示正在处理过程中；
//31表示正在审核过程中；
//32表示无视频源；
//33表示正在上传初始化；
//34表示正在上传过程中；
//40表示停