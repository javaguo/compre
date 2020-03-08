package com.tgw.account.receipts.model;

import com.tgw.basic.core.model.AbstractBaseBean;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "account_receipts")
public class Receipts extends AbstractBaseBean {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "rec_sum")
    private Double recSum;
    @Transient
    private Double recSumStart;
    @Transient
    private Double recSumEnd;

    @Column(name = "rec_date")
    private Date recDate;
    @Transient
    private Date recDateStart;
    @Transient
    private Date recDateEnd;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String remark;

    @Column(name = "fk_receipts_type_id")
    private Integer fkReceiptsTypeId;

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
     * @return rec_sum
     */
    public Double getRecSum() {
        return recSum;
    }

    /**
     * @param recSum
     */
    public void setRecSum(Double recSum) {
        this.recSum = recSum;
    }

    public Double getRecSumStart() {
        return recSumStart;
    }

    public void setRecSumStart(Double recSumStart) {
        this.recSumStart = recSumStart;
    }

    public Double getRecSumEnd() {
        return recSumEnd;
    }

    public void setRecSumEnd(Double recSumEnd) {
        this.recSumEnd = recSumEnd;
    }

    /**
     * @return rec_date
     */
    public Date getRecDate() {
        return recDate;
    }

    /**
     * @param recDate
     */
    public void setRecDate(Date recDate) {
        this.recDate = recDate;
    }

    public Date getRecDateStart() {
        return recDateStart;
    }

    public void setRecDateStart(Date recDateStart) {
        this.recDateStart = recDateStart;
    }

    public Date getRecDateEnd() {
        return recDateEnd;
    }

    public void setRecDateEnd(Date recDateEnd) {
        this.recDateEnd = recDateEnd;
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
     * @return fk_receipts_type_id
     */
    public Integer getFkReceiptsTypeId() {
        return fkReceiptsTypeId;
    }

    /**
     * @param fkReceiptsTypeId
     */
    public void setFkReceiptsTypeId(Integer fkReceiptsTypeId) {
        this.fkReceiptsTypeId = fkReceiptsTypeId;
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