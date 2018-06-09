package com.tgw.account.lend.model;

import com.tgw.basic.core.model.AbstractBaseBean;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.Date;

@Table(name = "account_lend")
public class Lend  extends AbstractBaseBean {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "lend_or_borrow")
    private Integer lendOrBorrow;

    @Column(name = "lend_sum")
    private Double lendSum;
    @Transient
    private Double lendSumStart;
    @Transient
    private Double lendSumEnd;

    @Column(name = "has_refunded_data")
    private Double hasRefundedData;

    @Column(name = "lend_person")
    private String lendPerson;

    @Column(name = "lend_purpose")
    private String lendPurpose;

    @Column(name = "lend_date")
    private Date lendDate;
    @Transient
    private Date lendDateStart;
    @Transient
    private Date lendDateEnd;

    @Column(name = "plan_refund_date")
    private Date planRefundDate;

    @Column(name = "refund_date")
    private Date refundDate;

    @Transient
    private Date refundDateStart;
    @Transient
    private Date refundDateEnd;

    @Column(name = "has_refunded")
    private Integer hasRefunded;

    private String remark;

    @Column(name = "fk_user_id")
    private Integer fkUserId;

    @Transient
    private String userName;
    @Transient
    private String loginName;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * @return lend_or_borrow
     */
    public Integer getLendOrBorrow() {
        return lendOrBorrow;
    }

    /**
     * @param lendOrBorrow
     */
    public void setLendOrBorrow(Integer lendOrBorrow) {
        this.lendOrBorrow = lendOrBorrow;
    }

    /**
     * @return lend_sum
     */
    public Double getLendSum() {
        return lendSum;
    }

    /**
     * @param lendSum
     */
    public void setLendSum(Double lendSum) {
        this.lendSum = lendSum;
    }

    public Double getLendSumStart() {
        return lendSumStart;
    }

    public void setLendSumStart(Double lendSumStart) {
        this.lendSumStart = lendSumStart;
    }

    public Double getLendSumEnd() {
        return lendSumEnd;
    }

    public void setLendSumEnd(Double lendSumEnd) {
        this.lendSumEnd = lendSumEnd;
    }

    /**
     * @return has_refunded_data
     */
    public Double getHasRefundedData() {
        return hasRefundedData;
    }

    /**
     * @param hasRefundedData
     */
    public void setHasRefundedData(Double hasRefundedData) {
        this.hasRefundedData = hasRefundedData;
    }

    /**
     * @return lend_person
     */
    public String getLendPerson() {
        return lendPerson;
    }

    /**
     * @param lendPerson
     */
    public void setLendPerson(String lendPerson) {
        this.lendPerson = lendPerson;
    }

    /**
     * @return lend_purpose
     */
    public String getLendPurpose() {
        return lendPurpose;
    }

    /**
     * @param lendPurpose
     */
    public void setLendPurpose(String lendPurpose) {
        this.lendPurpose = lendPurpose;
    }

    /**
     * @return lend_date
     */
    public Date getLendDate() {
        return lendDate;
    }

    /**
     * @param lendDate
     */
    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getLendDateStart() {
        return lendDateStart;
    }

    public void setLendDateStart(Date lendDateStart) {
        this.lendDateStart = lendDateStart;
    }

    public Date getLendDateEnd() {
        return lendDateEnd;
    }

    public void setLendDateEnd(Date lendDateEnd) {
        this.lendDateEnd = lendDateEnd;
    }

    /**
     * @return plan_refund_date
     */
    public Date getPlanRefundDate() {
        return planRefundDate;
    }

    /**
     * @param planRefundDate
     */
    public void setPlanRefundDate(Date planRefundDate) {
        this.planRefundDate = planRefundDate;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public Date getRefundDateStart() {
        return refundDateStart;
    }

    public void setRefundDateStart(Date refundDateStart) {
        this.refundDateStart = refundDateStart;
    }

    public Date getRefundDateEnd() {
        return refundDateEnd;
    }

    public void setRefundDateEnd(Date refundDateEnd) {
        this.refundDateEnd = refundDateEnd;
    }

    /**
     * @return has_refunded
     */
    public Integer getHasRefunded() {
        return hasRefunded;
    }

    /**
     * @param hasRefunded
     */
    public void setHasRefunded(Integer hasRefunded) {
        this.hasRefunded = hasRefunded;
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
}