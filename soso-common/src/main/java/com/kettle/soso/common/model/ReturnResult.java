package com.kettle.soso.common.model;

/**
 * 结果响应对象
 * @Author: csz
 * @Date: 2018/12/19 10:47
 */
public class ReturnResult <T> {

    /**
     * result data
     */
    private T result;

    /**
     * success or fail
     */
    private String status = "success";

    /**
     * status code   200,404,302,500...
     */
    private String code = "200";

    /**
     * message
     */
    private String message;

    /**
     *remarks
     */
    private String remarks;


    public ReturnResult() {
    }

    public ReturnResult(T result) {
        this.result = result;
    }

    public ReturnResult(T result, String status) {
        this.result = result;
        this.status = status;
    }

    public ReturnResult(T result, String status, String code) {
        this.result = result;
        this.status = status;
        this.code = code;
    }

    public ReturnResult(T result, String status, String code, String message) {
        this.result = result;
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ReturnResult(T result, String status, String code, String message, String remarks) {
        this.result = result;
        this.status = status;
        this.code = code;
        this.message = message;
        this.remarks = remarks;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
