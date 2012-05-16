package cn.rs.bbsadmin.dao;
// default package



/**
 * Post entity. @author MyEclipse Persistence Tools
 */

public class Post  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -7040460161360409043L;
	private Integer pid;
     private Short fid;
     private Integer tid;
     private Boolean first;
     private String author;
     private Integer authorid;
     private String subject;
     private Integer dateline;
     private String message;
     private String useip;
     private Integer invisible;
     private Boolean anonymous;
     private Boolean usesig;
     private Boolean htmlon;
     private Boolean bbcodeoff;
     private Boolean smileyoff;
     private Boolean parseurloff;
     private Boolean attachment;
     private Short rate;
     private Short ratetimes;
     private Boolean status;


    // Constructors

    /** default constructor */
    public Post() {
    }

    
    /** full constructor */
    public Post(Short fid, Integer tid, Boolean first, String author, Integer authorid, String subject, Integer dateline, String message, String useip, Integer invisible, Boolean anonymous, Boolean usesig, Boolean htmlon, Boolean bbcodeoff, Boolean smileyoff, Boolean parseurloff, Boolean attachment, Short rate, Short ratetimes, Boolean status) {
        this.fid = fid;
        this.tid = tid;
        this.first = first;
        this.author = author;
        this.authorid = authorid;
        this.subject = subject;
        this.dateline = dateline;
        this.message = message;
        this.useip = useip;
        this.invisible = invisible;
        this.anonymous = anonymous;
        this.usesig = usesig;
        this.htmlon = htmlon;
        this.bbcodeoff = bbcodeoff;
        this.smileyoff = smileyoff;
        this.parseurloff = parseurloff;
        this.attachment = attachment;
        this.rate = rate;
        this.ratetimes = ratetimes;
        this.status = status;
    }

   
    // Property accessors

    public Integer getPid() {
        return this.pid;
    }
    
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Short getFid() {
        return this.fid;
    }
    
    public void setFid(Short fid) {
        this.fid = fid;
    }

    public Integer getTid() {
        return this.tid;
    }
    
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Boolean getFirst() {
        return this.first;
    }
    
    public void setFirst(Boolean first) {
        this.first = first;
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

    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public String getUseip() {
        return this.useip;
    }
    
    public void setUseip(String useip) {
        this.useip = useip;
    }

    public Integer getInvisible() {
        return this.invisible;
    }
    
    public void setInvisible(Integer invisible) {
        this.invisible = invisible;
    }

    public Boolean getAnonymous() {
        return this.anonymous;
    }
    
    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Boolean getUsesig() {
        return this.usesig;
    }
    
    public void setUsesig(Boolean usesig) {
        this.usesig = usesig;
    }

    public Boolean getHtmlon() {
        return this.htmlon;
    }
    
    public void setHtmlon(Boolean htmlon) {
        this.htmlon = htmlon;
    }

    public Boolean getBbcodeoff() {
        return this.bbcodeoff;
    }
    
    public void setBbcodeoff(Boolean bbcodeoff) {
        this.bbcodeoff = bbcodeoff;
    }

    public Boolean getSmileyoff() {
        return this.smileyoff;
    }
    
    public void setSmileyoff(Boolean smileyoff) {
        this.smileyoff = smileyoff;
    }

    public Boolean getParseurloff() {
        return this.parseurloff;
    }
    
    public void setParseurloff(Boolean parseurloff) {
        this.parseurloff = parseurloff;
    }

    public Boolean getAttachment() {
        return this.attachment;
    }
    
    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public Short getRate() {
        return this.rate;
    }
    
    public void setRate(Short rate) {
        this.rate = rate;
    }

    public Short getRatetimes() {
        return this.ratetimes;
    }
    
    public void setRatetimes(Short ratetimes) {
        this.ratetimes = ratetimes;
    }

    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
   








}