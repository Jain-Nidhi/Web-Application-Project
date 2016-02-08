
/**
 * 
 */
package beans;
 
/**
 * @author Apoorva
 *
 */
public class User{
     private String uname = "";
    private String pword = "";
    private String fname = "";
    private String lname = "";
    private long phone = 0;
    private char type = ' ';
   private String uid = "";
    private long cellphone = 0;
    private String email = "";
     
    /**
     * @return  uid
     */
    public String getuid() {
        return uid;
    }
     
    /**
     * sets uid
     */
    public void setuid(String uid){
        this.uid = uid;
    }
     
     /**
     * @return  uname
     */
    public String getuname() {
        return uname;
    }
     
    /**
     * sets uname
     */
    public void setuname(String uname){
        this.uname = uname;
    }
    /**
     * @return the FirstName
     */
    public String getfname() {
        return fname;
    }
    
     /**
     * sets uname
     */
    public void setfname(String fname){
        this.fname = fname;
    }
    /**
     * @return the passwd
     */
    public String getpword() {
        return pword;
    }
 
    /**
     * @param passwd
     */
    public void setpword(String pword) {
        this.pword = pword;
    }
 
    /**
     * @return the LastName
     */
    public String getlname() {
        return lname;
    }
 
    /**
     * @param lastName
     */
    public void setlname(String lname) {
        this.lname = lname;
    }
 
    /**
     * @return the PhoneNumber
     */
    public long getphone() {
        return phone;
    }
 
    /**
     * @param phoneNumber
     */
    public void setphone(long phoneNumber) {
        this.phone = phoneNumber;
    }
    
      /**
     * @return the mobile
     */
    public long getcellphone() {
        return cellphone;
    }
    
    public void setcellphone(long cellphone) {
        this.cellphone = cellphone;
    }
 
    /**
     * @param mobile
     */
     
     /**
     * @return  type
     */
    public char gettype() {
        return type;
    }
     
    /**
     * sets type
     */
    public void settype(char type){
        this.type = type;
    }
     
    public String getemail() {
        return email;
    }
 
    /**
     * @param email to set
     */
    public void setemail(String email) {
        this.email = email;
    }  

    /*@Override
    public String toString() {
        return "DataObject   [firstname="   + firstname 
                            +"lastname="    + lastname
                            +"phonenumber=" + phonenumber
                            +"street="      + street
                            +"city="        + city
                            +"state="       + state
                            +"zipcode="     + zipcode+"]";
    }*/
}