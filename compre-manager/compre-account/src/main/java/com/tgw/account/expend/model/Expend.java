package com.tgw.account.expend.model;

import com.tgw.basic.core.model.AbstractBaseBean;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "expend")
public class Expend extends AbstractBaseBean {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "exp_sum")
    private Double expSum;
    @Transient
    private Double expSumStart;
    @Transient
    private Double expSumEnd;

    @Column(name = "exp_date")
    private Date expDate;
    @Transient
    private Date expDateStart;
    @Transient
    private Date expDateEnd;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String remark;

    @Column(name = "fk_expend_type_id")
    private Integer fkExpendTypeId;

    @Column(name = "fk_user_id")
    private Integer fkUserId;

    @Transient
    private String userName;
    @Transient
    private String loginName;

    @Column(name = "fk_com_event_id")
    private Integer fkComEventId;

    @Column(name = "fk_com_person_id")
    private Integer fkComPersonId;

    @Transient
    private String typeIds;
    @Transient
    private List<String> idList;


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
     * @return exp_sum
     */
    public Double getExpSum() {
        return expSum;
    }

    /**
     * @param expSum
     */
    public void setExpSum(Double expSum) {
        this.expSum = expSum;
    }

    public Double getExpSumStart() {
        return expSumStart;
    }

    public void setExpSumStart(Double expSumStart) {
        this.expSumStart = expSumStart;
    }

    public Double getExpSumEnd() {
        return expSumEnd;
    }

    public void setExpSumEnd(Double expSumEnd) {
        this.expSumEnd = expSumEnd;
    }

    /**
     * @return exp_date
     */
    public Date getExpDate() {
        return expDate;
    }

    /**
     * @param expDate
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getExpDateStart() {
        return expDateStart;
    }

    public void setExpDateStart(Date expDateStart) {
        this.expDateStart = expDateStart;
    }

    public Date getExpDateEnd() {
        return expDateEnd;
    }

    public void setExpDateEnd(Date expDateEnd) {
        this.expDateEnd = expDateEnd;
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
     * @return fk_expend_type_id
     */
    public Integer getFkExpendTypeId() {
        return fkExpendTypeId;
    }

    /**
     * @param fkExpendTypeId
     */
    public void setFkExpendTypeId(Integer fkExpendTypeId) {
        this.fkExpendTypeId = fkExpendTypeId;
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

    public Integer getFkComEventId() {
        return fkComEventId;
    }

    public void setFkComEventId(Integer fkComEventId) {
        this.fkComEventId = fkComEventId;
    }

    public Integer getFkComPersonId() {
        return fkComPersonId;
    }

    public void setFkComPersonId(Integer fkComPersonId) {
        this.fkComPersonId = fkComPersonId;
    }

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }
}