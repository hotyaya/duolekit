package cn.rs.bbsadmin.dao;
// default package

import java.util.Date;


/**
 * CDBMember entity. @author MyEclipse Persistence Tools
 */

public class CDBMember  implements java.io.Serializable {


    // Fields    

     private Integer uid;
     private String username;
     private String password;
     private String secques;
     private Boolean gender;
     private Boolean adminid;
     private Integer groupid;
     private Integer groupexpiry;
     private String extgroupids;
     private String regip;
     private Integer regdate;
     private String lastip;
     private Integer lastvisit;
     private Integer lastactivity;
     private Integer lastpost;
     private Integer posts;
     private Short digestposts;
     private Short oltime;
     private Integer pageviews;
     private Integer credits;
     private Integer extcredits1;
     private Integer extcredits2;
     private Integer extcredits3;
     private Integer extcredits4;
     private Integer extcredits5;
     private Integer extcredits6;
     private Integer extcredits7;
     private Integer extcredits8;
     private String email;
     private Date bday;
     private Boolean sigstatus;
     private Short tpp;
     private Short ppp;
     private Short styleid;
     private Boolean dateformat;
     private Boolean timeformat;
     private Boolean pmsound;
     private Boolean showemail;
     private Boolean newsletter;
     private Boolean invisible;
     private String timeoffset;
     private Boolean newpm;
     private Boolean accessmasks;
     private Boolean editormode;
     private Boolean customshow;
     private Boolean xspacestatus;


    // Constructors

    /** default constructor */
    public CDBMember() {
    }

    
    /** full constructor */
    public CDBMember(String username, String password, String secques, Boolean gender, Boolean adminid, Integer groupid, Integer groupexpiry, String extgroupids, String regip, Integer regdate, String lastip, Integer lastvisit, Integer lastactivity, Integer lastpost, Integer posts, Short digestposts, Short oltime, Integer pageviews, Integer credits, Integer extcredits1, Integer extcredits2, Integer extcredits3, Integer extcredits4, Integer extcredits5, Integer extcredits6, Integer extcredits7, Integer extcredits8, String email, Date bday, Boolean sigstatus, Short tpp, Short ppp, Short styleid, Boolean dateformat, Boolean timeformat, Boolean pmsound, Boolean showemail, Boolean newsletter, Boolean invisible, String timeoffset, Boolean newpm, Boolean accessmasks, Boolean editormode, Boolean customshow, Boolean xspacestatus) {
        this.username = username;
        this.password = password;
        this.secques = secques;
        this.gender = gender;
        this.adminid = adminid;
        this.groupid = groupid;
        this.groupexpiry = groupexpiry;
        this.extgroupids = extgroupids;
        this.regip = regip;
        this.regdate = regdate;
        this.lastip = lastip;
        this.lastvisit = lastvisit;
        this.lastactivity = lastactivity;
        this.lastpost = lastpost;
        this.posts = posts;
        this.digestposts = digestposts;
        this.oltime = oltime;
        this.pageviews = pageviews;
        this.credits = credits;
        this.extcredits1 = extcredits1;
        this.extcredits2 = extcredits2;
        this.extcredits3 = extcredits3;
        this.extcredits4 = extcredits4;
        this.extcredits5 = extcredits5;
        this.extcredits6 = extcredits6;
        this.extcredits7 = extcredits7;
        this.extcredits8 = extcredits8;
        this.email = email;
        this.bday = bday;
        this.sigstatus = sigstatus;
        this.tpp = tpp;
        this.ppp = ppp;
        this.styleid = styleid;
        this.dateformat = dateformat;
        this.timeformat = timeformat;
        this.pmsound = pmsound;
        this.showemail = showemail;
        this.newsletter = newsletter;
        this.invisible = invisible;
        this.timeoffset = timeoffset;
        this.newpm = newpm;
        this.accessmasks = accessmasks;
        this.editormode = editormode;
        this.customshow = customshow;
        this.xspacestatus = xspacestatus;
    }

   
    // Property accessors

    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecques() {
        return this.secques;
    }
    
    public void setSecques(String secques) {
        this.secques = secques;
    }

    public Boolean getGender() {
        return this.gender;
    }
    
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getAdminid() {
        return this.adminid;
    }
    
    public void setAdminid(Boolean adminid) {
        this.adminid = adminid;
    }

    public Integer getGroupid() {
        return this.groupid;
    }
    
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getGroupexpiry() {
        return this.groupexpiry;
    }
    
    public void setGroupexpiry(Integer groupexpiry) {
        this.groupexpiry = groupexpiry;
    }

    public String getExtgroupids() {
        return this.extgroupids;
    }
    
    public void setExtgroupids(String extgroupids) {
        this.extgroupids = extgroupids;
    }

    public String getRegip() {
        return this.regip;
    }
    
    public void setRegip(String regip) {
        this.regip = regip;
    }

    public Integer getRegdate() {
        return this.regdate;
    }
    
    public void setRegdate(Integer regdate) {
        this.regdate = regdate;
    }

    public String getLastip() {
        return this.lastip;
    }
    
    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public Integer getLastvisit() {
        return this.lastvisit;
    }
    
    public void setLastvisit(Integer lastvisit) {
        this.lastvisit = lastvisit;
    }

    public Integer getLastactivity() {
        return this.lastactivity;
    }
    
    public void setLastactivity(Integer lastactivity) {
        this.lastactivity = lastactivity;
    }

    public Integer getLastpost() {
        return this.lastpost;
    }
    
    public void setLastpost(Integer lastpost) {
        this.lastpost = lastpost;
    }

    public Integer getPosts() {
        return this.posts;
    }
    
    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    public Short getDigestposts() {
        return this.digestposts;
    }
    
    public void setDigestposts(Short digestposts) {
        this.digestposts = digestposts;
    }

    public Short getOltime() {
        return this.oltime;
    }
    
    public void setOltime(Short oltime) {
        this.oltime = oltime;
    }

    public Integer getPageviews() {
        return this.pageviews;
    }
    
    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    public Integer getCredits() {
        return this.credits;
    }
    
    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getExtcredits1() {
        return this.extcredits1;
    }
    
    public void setExtcredits1(Integer extcredits1) {
        this.extcredits1 = extcredits1;
    }

    public Integer getExtcredits2() {
        return this.extcredits2;
    }
    
    public void setExtcredits2(Integer extcredits2) {
        this.extcredits2 = extcredits2;
    }

    public Integer getExtcredits3() {
        return this.extcredits3;
    }
    
    public void setExtcredits3(Integer extcredits3) {
        this.extcredits3 = extcredits3;
    }

    public Integer getExtcredits4() {
        return this.extcredits4;
    }
    
    public void setExtcredits4(Integer extcredits4) {
        this.extcredits4 = extcredits4;
    }

    public Integer getExtcredits5() {
        return this.extcredits5;
    }
    
    public void setExtcredits5(Integer extcredits5) {
        this.extcredits5 = extcredits5;
    }

    public Integer getExtcredits6() {
        return this.extcredits6;
    }
    
    public void setExtcredits6(Integer extcredits6) {
        this.extcredits6 = extcredits6;
    }

    public Integer getExtcredits7() {
        return this.extcredits7;
    }
    
    public void setExtcredits7(Integer extcredits7) {
        this.extcredits7 = extcredits7;
    }

    public Integer getExtcredits8() {
        return this.extcredits8;
    }
    
    public void setExtcredits8(Integer extcredits8) {
        this.extcredits8 = extcredits8;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBday() {
        return this.bday;
    }
    
    public void setBday(Date bday) {
        this.bday = bday;
    }

    public Boolean getSigstatus() {
        return this.sigstatus;
    }
    
    public void setSigstatus(Boolean sigstatus) {
        this.sigstatus = sigstatus;
    }

    public Short getTpp() {
        return this.tpp;
    }
    
    public void setTpp(Short tpp) {
        this.tpp = tpp;
    }

    public Short getPpp() {
        return this.ppp;
    }
    
    public void setPpp(Short ppp) {
        this.ppp = ppp;
    }

    public Short getStyleid() {
        return this.styleid;
    }
    
    public void setStyleid(Short styleid) {
        this.styleid = styleid;
    }

    public Boolean getDateformat() {
        return this.dateformat;
    }
    
    public void setDateformat(Boolean dateformat) {
        this.dateformat = dateformat;
    }

    public Boolean getTimeformat() {
        return this.timeformat;
    }
    
    public void setTimeformat(Boolean timeformat) {
        this.timeformat = timeformat;
    }

    public Boolean getPmsound() {
        return this.pmsound;
    }
    
    public void setPmsound(Boolean pmsound) {
        this.pmsound = pmsound;
    }

    public Boolean getShowemail() {
        return this.showemail;
    }
    
    public void setShowemail(Boolean showemail) {
        this.showemail = showemail;
    }

    public Boolean getNewsletter() {
        return this.newsletter;
    }
    
    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    public Boolean getInvisible() {
        return this.invisible;
    }
    
    public void setInvisible(Boolean invisible) {
        this.invisible = invisible;
    }

    public String getTimeoffset() {
        return this.timeoffset;
    }
    
    public void setTimeoffset(String timeoffset) {
        this.timeoffset = timeoffset;
    }

    public Boolean getNewpm() {
        return this.newpm;
    }
    
    public void setNewpm(Boolean newpm) {
        this.newpm = newpm;
    }

    public Boolean getAccessmasks() {
        return this.accessmasks;
    }
    
    public void setAccessmasks(Boolean accessmasks) {
        this.accessmasks = accessmasks;
    }

    public Boolean getEditormode() {
        return this.editormode;
    }
    
    public void setEditormode(Boolean editormode) {
        this.editormode = editormode;
    }

    public Boolean getCustomshow() {
        return this.customshow;
    }
    
    public void setCustomshow(Boolean customshow) {
        this.customshow = customshow;
    }

    public Boolean getXspacestatus() {
        return this.xspacestatus;
    }
    
    public void setXspacestatus(Boolean xspacestatus) {
        this.xspacestatus = xspacestatus;
    }
   








}