package com.chen.bean;

import java.io.Serializable;

/**
 * Created by ChenNing on 16/5/24.
 */
public class ImgParam implements Serializable{

    private boolean Status;

    private Integer Error;

    private String FileKey;

    private String ExtParam;

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public Integer getError() {
        return Error;
    }

    public void setError(Integer error) {
        Error = error;
    }

    public String getFileKey() {
        return FileKey;
    }

    public void setFileKey(String fileKey) {
        FileKey = fileKey;
    }

    public String getExtParam() {
        return ExtParam;
    }

    public void setExtParam(String extParam) {
        ExtParam = extParam;
    }

    @Override
    public String toString() {
        return "ImgParam{" +
                "Status=" + Status +
                ", Error=" + Error +
                ", FileKey='" + FileKey + '\'' +
                ", ExtParam='" + ExtParam + '\'' +
                '}';
    }
}
