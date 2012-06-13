package cn.rs.cr.dao;
// default package



/**
 * Softitem entity. @author MyEclipse Persistence Tools
 */

public class Softitem  implements java.io.Serializable {


    // Fields    

     private String registerid;
     private String typecode;
     private String softname;
     private String softbrief;
     private String version;
     private String author;
     private String publishdate;
     private String registerdate;


    // Constructors

    /** default constructor */
    public Softitem() {
    }

	/** minimal constructor */
    public Softitem(String registerid) {
        this.registerid = registerid;
    }
    
    /** full constructor */
    public Softitem(String registerid, String typecode, String softname, String softbrief, String version, String author, String publishdate, String registerdate) {
        this.registerid = registerid;
        this.typecode = typecode;
        this.softname = softname;
        this.softbrief = softbrief;
        this.version = version;
        this.author = author;
        this.publishdate = publishdate;
        this.registerdate = registerdate;
    }

   
    // Property accessors

    public String getRegisterid() {
        return this.registerid;
    }
    
    public void setRegisterid(String registerid) {
        this.registerid = registerid;
    }

    public String getTypecode() {
        return this.typecode;
    }
    
    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getSoftname() {
        return this.softname;
    }
    
    public void setSoftname(String softname) {
        this.softname = softname;
    }

    public String getSoftbrief() {
        return this.softbrief;
    }
    
    public void setSoftbrief(String softbrief) {
        this.softbrief = softbrief;
    }

    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishdate() {
        return this.publishdate;
    }
    
    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getRegisterdate() {
        return this.registerdate;
    }
    
    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }
   








}