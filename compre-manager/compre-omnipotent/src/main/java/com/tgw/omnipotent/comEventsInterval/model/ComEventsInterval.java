package com.tgw.omnipotent.comEventsInterval.model;

import com.tgw.basic.core.model.AbstractBaseBean;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.Date;

@Table(name = "com_events_interval")
public class ComEventsInterval extends AbstractBaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fk_constant_id")
    private Integer fkConstantId;

    @Column(name = "start_time")
    private Date startTime;

    @Transient
    private Date startTimeStart;
    @Transient
    private Date startTimeEnd;

    @Transient
    private Date endTimeStart;
    @Transient
    private Date endTimeEnd;

    @Column(name = "end_time")
    private Date endTime;

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
     * @return fk_constant_id
     */
    public Integer getFkConstantId() {
        return fkConstantId;
    }

    /**
     * @param fkConstantId
     */
    public void setFkConstantId(Integer fkConstantId) {
        this.fkConstantId = fkConstantId;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTimeStart() {
        return startTimeStart;
    }

    public void setStartTimeStart(Date startTimeStart) {
        this.startTimeStart = startTimeStart;
    }

    public Date getStartTimeEnd() {
        return startTimeEnd;
    }

    public void setStartTimeEnd(Date startTimeEnd) {
        this.startTimeEnd = startTimeEnd;
    }

    public Date getEndTimeStart() {
        return endTimeStart;
    }

    public void setEndTimeStart(Date endTimeStart) {
        this.endTimeStart = endTimeStart;
    }

    public Date getEndTimeEnd() {
        return endTimeEnd;
    }

    public void setEndTimeEnd(Date endTimeEnd) {
        this.endTimeEnd = endTimeEnd;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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