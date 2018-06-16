package com.summer.tech.mybatis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Doctor implements Serializable {
    private Long id;

    private String hospital;

    private String department;

    private String jobtitle;

    private String headpic;

    private Integer yipenghao;

    private String tdcodeurl;

    private String authPhoto;

    private Byte authStatus;

    private Byte gender;

    private Date birthday;

    private String phone;

    private String password;

    private String email;

    private Byte isshowdetail;

    private Byte iscybersquatting;

    private Byte busyStatus;

    private Date registertime;

    private String answerphone;

    private String name;

    private String inviteCode;

    private Byte enabled;

    private String bankCardNo;

    private String bankUserName;

    private String bankName;

    private Date lastLoginTime;

    private Date authTime;

    private BigDecimal monthlycost;

    private BigDecimal weeklycost;

    private BigDecimal phonecost;

    private Byte serveTxtconsult;

    private Byte servePlossign;

    private Byte serveRegvarvisit;

    private Byte servePhoneconsult;

    private Date authPassTime;

    private Byte isfree;

    private BigDecimal onetimecost;

    private String platform;

    private Integer dailynum;

    private Integer zixunnum;

    private String interest;

    private Byte isfamilydoctor;

    private Byte servePrivatedoctors;

    private Long onedepartmentId;

    private Long twodepartmentId;

    private Long doctorweixingroupId;

    private Byte isnoshowclient;

    private String alipayname;

    private String alipaycardnumber;

    private Long areaId;

    private String areaname;

    private Long hospitalId;

    private Byte trialtype;

    private String trialphone;

    private String openid;

    private String bankAddress;

    private Byte iscooperation;

    private String homeaddress;

    private String communicationskills;

    private String personality;

    private String professionaldegree;

    private String serviceattitude;

    private String satisfaction;

    private String wechatid;

    private Byte isdocregiter;

    private Byte type;

    private Byte showplatform;

    private String practitioners;

    private String impressiontag;

    private String grade;

    private Date updateTime;

    private String hospitalizeProductCode;

    private BigDecimal hospitalizePrice;

    private String phoneConsultProductCode;

    private BigDecimal phoneConsultPrice;

    private String txtConsultProductCode;

    private BigDecimal txtConsultPrice;

    private String famousDoctorLevel;

    private String qrurlStr;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle == null ? null : jobtitle.trim();
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic == null ? null : headpic.trim();
    }

    public Integer getYipenghao() {
        return yipenghao;
    }

    public void setYipenghao(Integer yipenghao) {
        this.yipenghao = yipenghao;
    }

    public String getTdcodeurl() {
        return tdcodeurl;
    }

    public void setTdcodeurl(String tdcodeurl) {
        this.tdcodeurl = tdcodeurl == null ? null : tdcodeurl.trim();
    }

    public String getAuthPhoto() {
        return authPhoto;
    }

    public void setAuthPhoto(String authPhoto) {
        this.authPhoto = authPhoto == null ? null : authPhoto.trim();
    }

    public Byte getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Byte authStatus) {
        this.authStatus = authStatus;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getIsshowdetail() {
        return isshowdetail;
    }

    public void setIsshowdetail(Byte isshowdetail) {
        this.isshowdetail = isshowdetail;
    }

    public Byte getIscybersquatting() {
        return iscybersquatting;
    }

    public void setIscybersquatting(Byte iscybersquatting) {
        this.iscybersquatting = iscybersquatting;
    }

    public Byte getBusyStatus() {
        return busyStatus;
    }

    public void setBusyStatus(Byte busyStatus) {
        this.busyStatus = busyStatus;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getAnswerphone() {
        return answerphone;
    }

    public void setAnswerphone(String answerphone) {
        this.answerphone = answerphone == null ? null : answerphone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName == null ? null : bankUserName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public BigDecimal getMonthlycost() {
        return monthlycost;
    }

    public void setMonthlycost(BigDecimal monthlycost) {
        this.monthlycost = monthlycost;
    }

    public BigDecimal getWeeklycost() {
        return weeklycost;
    }

    public void setWeeklycost(BigDecimal weeklycost) {
        this.weeklycost = weeklycost;
    }

    public BigDecimal getPhonecost() {
        return phonecost;
    }

    public void setPhonecost(BigDecimal phonecost) {
        this.phonecost = phonecost;
    }

    public Byte getServeTxtconsult() {
        return serveTxtconsult;
    }

    public void setServeTxtconsult(Byte serveTxtconsult) {
        this.serveTxtconsult = serveTxtconsult;
    }

    public Byte getServePlossign() {
        return servePlossign;
    }

    public void setServePlossign(Byte servePlossign) {
        this.servePlossign = servePlossign;
    }

    public Byte getServeRegvarvisit() {
        return serveRegvarvisit;
    }

    public void setServeRegvarvisit(Byte serveRegvarvisit) {
        this.serveRegvarvisit = serveRegvarvisit;
    }

    public Byte getServePhoneconsult() {
        return servePhoneconsult;
    }

    public void setServePhoneconsult(Byte servePhoneconsult) {
        this.servePhoneconsult = servePhoneconsult;
    }

    public Date getAuthPassTime() {
        return authPassTime;
    }

    public void setAuthPassTime(Date authPassTime) {
        this.authPassTime = authPassTime;
    }

    public Byte getIsfree() {
        return isfree;
    }

    public void setIsfree(Byte isfree) {
        this.isfree = isfree;
    }

    public BigDecimal getOnetimecost() {
        return onetimecost;
    }

    public void setOnetimecost(BigDecimal onetimecost) {
        this.onetimecost = onetimecost;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public Integer getDailynum() {
        return dailynum;
    }

    public void setDailynum(Integer dailynum) {
        this.dailynum = dailynum;
    }

    public Integer getZixunnum() {
        return zixunnum;
    }

    public void setZixunnum(Integer zixunnum) {
        this.zixunnum = zixunnum;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }

    public Byte getIsfamilydoctor() {
        return isfamilydoctor;
    }

    public void setIsfamilydoctor(Byte isfamilydoctor) {
        this.isfamilydoctor = isfamilydoctor;
    }

    public Byte getServePrivatedoctors() {
        return servePrivatedoctors;
    }

    public void setServePrivatedoctors(Byte servePrivatedoctors) {
        this.servePrivatedoctors = servePrivatedoctors;
    }

    public Long getOnedepartmentId() {
        return onedepartmentId;
    }

    public void setOnedepartmentId(Long onedepartmentId) {
        this.onedepartmentId = onedepartmentId;
    }

    public Long getTwodepartmentId() {
        return twodepartmentId;
    }

    public void setTwodepartmentId(Long twodepartmentId) {
        this.twodepartmentId = twodepartmentId;
    }

    public Long getDoctorweixingroupId() {
        return doctorweixingroupId;
    }

    public void setDoctorweixingroupId(Long doctorweixingroupId) {
        this.doctorweixingroupId = doctorweixingroupId;
    }

    public Byte getIsnoshowclient() {
        return isnoshowclient;
    }

    public void setIsnoshowclient(Byte isnoshowclient) {
        this.isnoshowclient = isnoshowclient;
    }

    public String getAlipayname() {
        return alipayname;
    }

    public void setAlipayname(String alipayname) {
        this.alipayname = alipayname == null ? null : alipayname.trim();
    }

    public String getAlipaycardnumber() {
        return alipaycardnumber;
    }

    public void setAlipaycardnumber(String alipaycardnumber) {
        this.alipaycardnumber = alipaycardnumber == null ? null : alipaycardnumber.trim();
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Byte getTrialtype() {
        return trialtype;
    }

    public void setTrialtype(Byte trialtype) {
        this.trialtype = trialtype;
    }

    public String getTrialphone() {
        return trialphone;
    }

    public void setTrialphone(String trialphone) {
        this.trialphone = trialphone == null ? null : trialphone.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress == null ? null : bankAddress.trim();
    }

    public Byte getIscooperation() {
        return iscooperation;
    }

    public void setIscooperation(Byte iscooperation) {
        this.iscooperation = iscooperation;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress == null ? null : homeaddress.trim();
    }

    public String getCommunicationskills() {
        return communicationskills;
    }

    public void setCommunicationskills(String communicationskills) {
        this.communicationskills = communicationskills == null ? null : communicationskills.trim();
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality == null ? null : personality.trim();
    }

    public String getProfessionaldegree() {
        return professionaldegree;
    }

    public void setProfessionaldegree(String professionaldegree) {
        this.professionaldegree = professionaldegree == null ? null : professionaldegree.trim();
    }

    public String getServiceattitude() {
        return serviceattitude;
    }

    public void setServiceattitude(String serviceattitude) {
        this.serviceattitude = serviceattitude == null ? null : serviceattitude.trim();
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction == null ? null : satisfaction.trim();
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid == null ? null : wechatid.trim();
    }

    public Byte getIsdocregiter() {
        return isdocregiter;
    }

    public void setIsdocregiter(Byte isdocregiter) {
        this.isdocregiter = isdocregiter;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getShowplatform() {
        return showplatform;
    }

    public void setShowplatform(Byte showplatform) {
        this.showplatform = showplatform;
    }

    public String getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(String practitioners) {
        this.practitioners = practitioners == null ? null : practitioners.trim();
    }

    public String getImpressiontag() {
        return impressiontag;
    }

    public void setImpressiontag(String impressiontag) {
        this.impressiontag = impressiontag == null ? null : impressiontag.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHospitalizeProductCode() {
        return hospitalizeProductCode;
    }

    public void setHospitalizeProductCode(String hospitalizeProductCode) {
        this.hospitalizeProductCode = hospitalizeProductCode == null ? null : hospitalizeProductCode.trim();
    }

    public BigDecimal getHospitalizePrice() {
        return hospitalizePrice;
    }

    public void setHospitalizePrice(BigDecimal hospitalizePrice) {
        this.hospitalizePrice = hospitalizePrice;
    }

    public String getPhoneConsultProductCode() {
        return phoneConsultProductCode;
    }

    public void setPhoneConsultProductCode(String phoneConsultProductCode) {
        this.phoneConsultProductCode = phoneConsultProductCode == null ? null : phoneConsultProductCode.trim();
    }

    public BigDecimal getPhoneConsultPrice() {
        return phoneConsultPrice;
    }

    public void setPhoneConsultPrice(BigDecimal phoneConsultPrice) {
        this.phoneConsultPrice = phoneConsultPrice;
    }

    public String getTxtConsultProductCode() {
        return txtConsultProductCode;
    }

    public void setTxtConsultProductCode(String txtConsultProductCode) {
        this.txtConsultProductCode = txtConsultProductCode == null ? null : txtConsultProductCode.trim();
    }

    public BigDecimal getTxtConsultPrice() {
        return txtConsultPrice;
    }

    public void setTxtConsultPrice(BigDecimal txtConsultPrice) {
        this.txtConsultPrice = txtConsultPrice;
    }

    public String getFamousDoctorLevel() {
        return famousDoctorLevel;
    }

    public void setFamousDoctorLevel(String famousDoctorLevel) {
        this.famousDoctorLevel = famousDoctorLevel == null ? null : famousDoctorLevel.trim();
    }

    public String getQrurlStr() {
        return qrurlStr;
    }

    public void setQrurlStr(String qrurlStr) {
        this.qrurlStr = qrurlStr == null ? null : qrurlStr.trim();
    }
}