package com.tgw.account.expendType.model;

import com.tgw.basic.core.model.AbstractBaseBean;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "account_expend_type")
public class ExpendType extends AbstractBaseBean {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "fk_parent_id")
    private Integer fkParentId;

    @Column(name = "expend_type_name")
    private String expendTypeName;

    @Column(name = "order_num")
    private Integer orderNum;

    @Column(name = "is_sys_own")
    private Integer isSysOwn;

    private String remark;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "fk_user_id")
    private Integer fkUserId;

    @Transient
    private String userName;
    @Transient
    private String loginName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return fk_parent_id
     */
    public Integer getFkParentId() {
        return fkParentId;
    }

    /**
     * @param fkParentId
     */
    public void setFkParentId(Integer fkParentId) {
        this.fkParentId = fkParentId;
    }


    /**
     * @return expend_type_name
     */
    public String getExpendTypeName() {
        return expendTypeName;
    }

    /**
     * @param expendTypeName
     */
    public void setExpendTypeName(String expendTypeName) {
        this.expendTypeName = expendTypeName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return is_sys_own
     */
    public Integer getIsSysOwn() {
        return isSysOwn;
    }

    /**
     * @param isSysOwn
     */
    public void setIsSysOwn(Integer isSysOwn) {
        this.isSysOwn = isSysOwn;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return add_time
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return fk_user_id
     */
    public Integer getFkUserId() {
        return fkUserId;
    }

    /**
     * @param fkUserId
     */
    public void setFkUserId(Integer fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getUserName() {
        if(StringUtils.isNotBlank( userName )){
            return userName;
        }else{
            return "";
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}