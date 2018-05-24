package com.tgw.account.statistics.model;

import com.tgw.basic.core.model.AbstractBaseBean;

import java.util.Date;

public class Statistics extends AbstractBaseBean {

    private Integer id;

    private Integer fkUserId;

    private Date statiDate1Start;
    private Date statiDate1End;

    private Date statiDate2Start;
    private Date statiDate2End;

    private Date statiDate3Start;
    private Date statiDate3End;

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

    public Integer getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Integer fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Date getStatiDate1Start() {
        return statiDate1Start;
    }

    public void setStatiDate1Start(Date statiDate1Start) {
        this.statiDate1Start = statiDate1Start;
    }

    public Date getStatiDate1End() {
        return statiDate1End;
    }

    public void setStatiDate1End(Date statiDate1End) {
        this.statiDate1End = statiDate1End;
    }

    public Date getStatiDate2Start() {
        return statiDate2Start;
    }

    public void setStatiDate2Start(Date statiDate2Start) {
        this.statiDate2Start = statiDate2Start;
    }

    public Date getStatiDate2End() {
        return statiDate2End;
    }

    public void setStatiDate2End(Date statiDate2End) {
        this.statiDate2End = statiDate2End;
    }

    public Date getStatiDate3Start() {
        return statiDate3Start;
    }

    public void setStatiDate3Start(Date statiDate3Start) {
        this.statiDate3Start = statiDate3Start;
    }

    public Date getStatiDate3End() {
        return statiDate3End;
    }

    public void setStatiDate3End(Date statiDate3End) {
        this.statiDate3End = statiDate3End;
    }
}