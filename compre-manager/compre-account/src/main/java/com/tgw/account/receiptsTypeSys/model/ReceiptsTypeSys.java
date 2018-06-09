package com.tgw.account.receiptsTypeSys.model;

import com.tgw.basic.core.model.AbstractBaseBean;

import javax.persistence.*;
import java.util.Date;

@Table(name = "account_receipts_type_sys")
public class ReceiptsTypeSys extends AbstractBaseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fk_parent_id")
    private Integer fkParentId;

    @Column(name = "receipts_type_name")
    private String receiptsTypeName;

    @Column(name = "order_num")
    private Integer orderNum;

    @Column(name = "is_sys_own")
    private Integer isSysOwn;

    private String remark;

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
     * @return receipts_type_name
     */
    public String getReceiptsTypeName() {
        return receiptsTypeName;
    }

    /**
     * @param receiptsTypeName
     */
    public void setReceiptsTypeName(String receiptsTypeName) {
        this.receiptsTypeName = receiptsTypeName;
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
}