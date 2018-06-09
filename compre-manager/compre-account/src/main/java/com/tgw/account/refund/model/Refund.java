package com.tgw.account.refund.model;

import com.tgw.basic.core.model.AbstractBaseBean;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.Date;

@Table(name = "account_refund")
public class Refund extends AbstractBaseBean {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "refund_sum")
    private Double refundSum;

    @Column(name = "refund_date")
    private Date refundDate;
    @Transient
    private Date refundDateStart;
    @Transient
    private Date refundDateEnd;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "fk_lend_id")
    private Integer fkLendId;

    @Transient
    private String userName;
    @Transient
    private String loginName;
    @Transient
    private Integer fkUserId;

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
     * @return refund_sum
     */
    public Double getRefundSum() {
        return refundSum;
    }

    /**
     * @param refundSum
     */
    public void setRefundSum(Double refundSum) {
        this.refundSum = refundSum;
    }

    /**
     * @return refund_date
     */
    public Date getRefundDate() {
        return refundDate;
    }

    /**
     * @param refundDate
     */
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
     * @return fk_lend_id
     */
    public Integer getFkLendId() {
        return fkLendId;
    }

    /**
     * @param fkLendId
     */
    public void setFkLendId(Integer fkLendId) {
        this.fkLendId = fkLendId;
    }

    public Integer getFkUserId() {
        return fkUserId;
    }

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