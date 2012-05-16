package cn.rs.bbsadmin.dao;
// default package



/**
 * CDBThread entity. @author MyEclipse Persistence Tools
 */

public class CDBThread  implements java.io.Serializable {


    // Fields    

     private Integer tid;
     private Short fid;
     private Short iconid;
     private Short typeid;
     private Short readperm;
     private Short price;
     private String author;
     private Integer authorid;
     private String subject;
     private Integer dateline;
     private Integer lastpost;
     private String lastposter;
     private Integer views;
     private Integer replies;
     private Integer displayorder;
     private Boolean highlight;
     private Boolean digest;
     private Boolean rate;
     private Boolean blog;
     private Boolean special;
     private Boolean attachment;
     private Boolean subscribed;
     private Boolean moderated;
     private Integer closed;
     private Integer itemid;
     private Boolean supePushstatus;
     private Integer sgid;


    // Constructors

    /** default constructor */
    public CDBThread() {
    }

    
    /** full constructor */
    public CDBThread(Short fid, Short iconid, Short typeid, Short readperm, Short price, String author, Integer authorid, String subject, Integer dateline, Integer lastpost, String lastposter, Integer views, Integer replies, Integer displayorder, Boolean highlight, Boolean digest, Boolean rate, Boolean blog, Boolean special, Boolean attachment, Boolean subscribed, Boolean moderated, Integer closed, Integer itemid, Boolean supePushstatus, Integer sgid) {
        this.fid = fid;
        this.iconid = iconid;
        this.typeid = typeid;
        this.readperm = readperm;
        this.price = price;
        this.author = author;
        this.authorid = authorid;
        this.subject = subject;
        this.dateline = dateline;
        this.lastpost = lastpost;
        this.lastposter = lastposter;
        this.views = views;
        this.replies = replies;
        this.displayorder = displayorder;
        this.highlight = highlight;
        this.digest = digest;
        this.rate = rate;
        this.blog = blog;
        this.special = special;
        this.attachment = attachment;
        this.subscribed = subscribed;
        this.moderated = moderated;
        this.closed = closed;
        this.itemid = itemid;
        this.supePushstatus = supePushstatus;
        this.sgid = sgid;
    }

   
    // Property accessors

    public Integer getTid() {
        return this.tid;
    }
    
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Short getFid() {
        return this.fid;
    }
    
    public void setFid(Short fid) {
        this.fid = fid;
    }

    public Short getIconid() {
        return this.iconid;
    }
    
    public void setIconid(Short iconid) {
        this.iconid = iconid;
    }

    public Short getTypeid() {
        return this.typeid;
    }
    
    public void setTypeid(Short typeid) {
        this.typeid = typeid;
    }

    public Short getReadperm() {
        return this.readperm;
    }
    
    public void setReadperm(Short readperm) {
        this.readperm = readperm;
    }

    public Short getPrice() {
        return this.price;
    }
    
    public void setPrice(Short price) {
        this.price = price;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAuthorid() {
        return this.authorid;
    }
    
    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getDateline() {
        return this.dateline;
    }
    
    public void setDateline(Integer dateline) {
        this.dateline = dateline;
    }

    public Integer getLastpost() {
        return this.lastpost;
    }
    
    public void setLastpost(Integer lastpost) {
        this.lastpost = lastpost;
    }

    public String getLastposter() {
        return this.lastposter;
    }
    
    public void setLastposter(String lastposter) {
        this.lastposter = lastposter;
    }

    public Integer getViews() {
        return this.views;
    }
    
    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getReplies() {
        return this.replies;
    }
    
    public void setReplies(Integer replies) {
        this.replies = replies;
    }

    public Integer getDisplayorder() {
        return this.displayorder;
    }
    
    public void setDisplayorder(Integer displayorder) {
        this.displayorder = displayorder;
    }

    public Boolean getHighlight() {
        return this.highlight;
    }
    
    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public Boolean getDigest() {
        return this.digest;
    }
    
    public void setDigest(Boolean digest) {
        this.digest = digest;
    }

    public Boolean getRate() {
        return this.rate;
    }
    
    public void setRate(Boolean rate) {
        this.rate = rate;
    }

    public Boolean getBlog() {
        return this.blog;
    }
    
    public void setBlog(Boolean blog) {
        this.blog = blog;
    }

    public Boolean getSpecial() {
        return this.special;
    }
    
    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public Boolean getAttachment() {
        return this.attachment;
    }
    
    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public Boolean getSubscribed() {
        return this.subscribed;
    }
    
    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Boolean getModerated() {
        return this.moderated;
    }
    
    public void setModerated(Boolean moderated) {
        this.moderated = moderated;
    }

    public Integer getClosed() {
        return this.closed;
    }
    
    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    public Integer getItemid() {
        return this.itemid;
    }
    
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Boolean getSupePushstatus() {
        return this.supePushstatus;
    }
    
    public void setSupePushstatus(Boolean supePushstatus) {
        this.supePushstatus = supePushstatus;
    }

    public Integer getSgid() {
        return this.sgid;
    }
    
    public void setSgid(Integer sgid) {
        this.sgid = sgid;
    }
   








}