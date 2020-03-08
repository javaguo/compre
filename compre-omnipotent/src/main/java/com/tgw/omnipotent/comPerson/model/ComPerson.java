package com.tgw.omnipotent.comPerson.model;

import com.tgw.basic.core.model.AbstractBaseBean;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "com_person")
public class ComPerson extends AbstractBaseBean{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String sex;

    private Date birthday;

    @Transient
    private Date birthdayStart;
    @Transient
    private Date birthdayEnd;

    @Transient
    private Date birthdayLunarStart;
    @Transient
    private Date birthdayLunarEnd;

    @Column(name = "birthday_lunar")
    private Date birthdayLunar;

    @Column(name = "identity_card")
    private String identityCard;

    private String profession;

    /**
     * 专长
     */
    private String speciality;

    @Column(name = "mobile_phone1")
    private String mobilePhone1;

    @Column(name = "mobile_phone2")
    private String mobilePhone2;

    @Column(name = "mobile_phone3")
    private String mobilePhone3;

    /**
     * 户籍所在地
     */
    @Column(name = "place_of_domicile")
    private String placeOfDomicile;

    private String address;

    @Column(name = "address_work")
    private String addressWork;

    @Column(name = "last_graduate_school")
    private String lastGraduateSchool;

    @Column(name = "last_graduate_date")
    private Date lastGraduateDate;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthdayStart() {
        return birthdayStart;
    }

    public void setBirthdayStart(Date birthdayStart) {
        this.birthdayStart = birthdayStart;
    }

    public Date getBirthdayEnd() {
        return birthdayEnd;
    }

    public void setBirthdayEnd(Date birthdayEnd) {
        this.birthdayEnd = birthdayEnd;
    }

    public Date getBirthdayLunarStart() {
        return birthdayLunarStart;
    }

    public void setBirthdayLunarStart(Date birthdayLunarStart) {
        this.birthdayLunarStart = birthdayLunarStart;
    }

    public Date getBirthdayLunarEnd() {
        return birthdayLunarEnd;
    }

    public void setBirthdayLunarEnd(Date birthdayLunarEnd) {
        this.birthdayLunarEnd = birthdayLunarEnd;
    }

    /**
     * @return birthday_lunar
     */
    public Date getBirthdayLunar() {
        return birthdayLunar;
    }

    /**
     * @param birthdayLunar
     */
    public void setBirthdayLunar(Date birthdayLunar) {
        this.birthdayLunar = birthdayLunar;
    }

    /**
     * @return identity_card
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * @param identityCard
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    /**
     * @return profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * 获取专长
     *
     * @return speciality - 专长
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * 设置专长
     *
     * @param speciality 专长
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * @return mobile_phone1
     */
    public String getMobilePhone1() {
        return mobilePhone1;
    }

    /**
     * @param mobilePhone1
     */
    public void setMobilePhone1(String mobilePhone1) {
        this.mobilePhone1 = mobilePhone1;
    }

    /**
     * @return mobile_phone2
     */
    public String getMobilePhone2() {
        return mobilePhone2;
    }

    /**
     * @param mobilePhone2
     */
    public void setMobilePhone2(String mobilePhone2) {
        this.mobilePhone2 = mobilePhone2;
    }

    /**
     * @return mobile_phone3
     */
    public String getMobilePhone3() {
        return mobilePhone3;
    }

    /**
     * @param mobilePhone3
     */
    public void setMobilePhone3(String mobilePhone3) {
        this.mobilePhone3 = mobilePhone3;
    }

    /**
     * 获取户籍所在地
     *
     * @return place_of_domicile - 户籍所在地
     */
    public String getPlaceOfDomicile() {
        return placeOfDomicile;
    }

    /**
     * 设置户籍所在地
     *
     * @param placeOfDomicile 户籍所在地
     */
    public void setPlaceOfDomicile(String placeOfDomicile) {
        this.placeOfDomicile = placeOfDomicile;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return address_work
     */
    public String getAddressWork() {
        return addressWork;
    }

    /**
     * @param addressWork
     */
    public void setAddressWork(String addressWork) {
        this.addressWork = addressWork;
    }

    /**
     * @return last_graduate_school
     */
    public String getLastGraduateSchool() {
        return lastGraduateSchool;
    }

    /**
     * @param lastGraduateSchool
     */
    public void setLastGraduateSchool(String lastGraduateSchool) {
        this.lastGraduateSchool = lastGraduateSchool;
    }

    /**
     * @return last_graduate_date
     */
    public Date getLastGraduateDate() {
        return lastGraduateDate;
    }

    /**
     * @param lastGraduateDate
     */
    public void setLastGraduateDate(Date lastGraduateDate) {
        this.lastGraduateDate = lastGraduateDate;
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