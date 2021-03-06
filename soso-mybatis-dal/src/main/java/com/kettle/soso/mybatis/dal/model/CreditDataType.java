package com.kettle.soso.mybatis.dal.model;

import java.io.Serializable;
import org.beans.GogoDo;

public class CreditDataType extends GogoDo implements Serializable {
    /**
     * 生成主键
     * 表字段 : credit_data_type.uuid
     */
    private String uuid;

    /**
     * 数据代号
     * 表字段 : credit_data_type.data_code
     */
    private String dataCode;

    /**
     * 数据名称
     * 表字段 : credit_data_type.data_name
     */
    private String dataName;

    /**
     * 对应流程
     * 表字段 : credit_data_type.data_process
     */
    private String dataProcess;

    /**
     * 默认的组织代号
     * 表字段 : credit_data_type.default_organization
     */
    private String defaultOrganization;

    /**
     * 默认转换
     * 表字段 : credit_data_type.default_transfor
     */
    private String defaultTransfor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table credit_data_type
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取 生成主键 字段:credit_data_type.uuid
     *
     * @return credit_data_type.uuid, 生成主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 生成主键 字段:credit_data_type.uuid
     *
     * @param uuid the value for credit_data_type.uuid, 生成主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取 数据代号 字段:credit_data_type.data_code
     *
     * @return credit_data_type.data_code, 数据代号
     */
    public String getDataCode() {
        return dataCode;
    }

    /**
     * 设置 数据代号 字段:credit_data_type.data_code
     *
     * @param dataCode the value for credit_data_type.data_code, 数据代号
     */
    public void setDataCode(String dataCode) {
        this.dataCode = dataCode == null ? null : dataCode.trim();
    }

    /**
     * 获取 数据名称 字段:credit_data_type.data_name
     *
     * @return credit_data_type.data_name, 数据名称
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * 设置 数据名称 字段:credit_data_type.data_name
     *
     * @param dataName the value for credit_data_type.data_name, 数据名称
     */
    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    /**
     * 获取 对应流程 字段:credit_data_type.data_process
     *
     * @return credit_data_type.data_process, 对应流程
     */
    public String getDataProcess() {
        return dataProcess;
    }

    /**
     * 设置 对应流程 字段:credit_data_type.data_process
     *
     * @param dataProcess the value for credit_data_type.data_process, 对应流程
     */
    public void setDataProcess(String dataProcess) {
        this.dataProcess = dataProcess == null ? null : dataProcess.trim();
    }

    /**
     * 获取 默认的组织代号 字段:credit_data_type.default_organization
     *
     * @return credit_data_type.default_organization, 默认的组织代号
     */
    public String getDefaultOrganization() {
        return defaultOrganization;
    }

    /**
     * 设置 默认的组织代号 字段:credit_data_type.default_organization
     *
     * @param defaultOrganization the value for credit_data_type.default_organization, 默认的组织代号
     */
    public void setDefaultOrganization(String defaultOrganization) {
        this.defaultOrganization = defaultOrganization == null ? null : defaultOrganization.trim();
    }

    /**
     * 获取 默认转换 字段:credit_data_type.default_transfor
     *
     * @return credit_data_type.default_transfor, 默认转换
     */
    public String getDefaultTransfor() {
        return defaultTransfor;
    }

    /**
     * 设置 默认转换 字段:credit_data_type.default_transfor
     *
     * @param defaultTransfor the value for credit_data_type.default_transfor, 默认转换
     */
    public void setDefaultTransfor(String defaultTransfor) {
        this.defaultTransfor = defaultTransfor == null ? null : defaultTransfor.trim();
    }

    @Override
    public String getBoQualifiedIntfName() {
        return "com.kettle.soso.mybatis.dal.bo.CreditDataTypeBo";
    }
}