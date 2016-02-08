
/**
 * 
 */
package beans;
 
/**
 * @author Apoorva
 *
 */
public class Client extends User {
    private String street = "";
    private String city = "";
    private String state = "";
    private int zipcode = 0;
    private int stock = 0;
    private String clientid="";
    private String level= "";
    private double due = 0.0;
  //  private String email= "";
     
     
    /**
     * @return the streetName
     */
    public String getstreet() {
        return street;
    }
 
    /**
     * @param streetName
     */
    public void setstreet(String streetName) {
        this.street = streetName;
    }
 
    /**
     * @return the city Name
     */
    public String getcity() {
        return city;
    }
 
    /**
     * @param cityName
     */
    public void setcity(String cityName) {
        this.city = cityName;
    }
 
    /**
     * @return state Name
     */
    public String getstate() {
        return state;
    }
 
    /**
     * @param stateName
     */
    public void setstate(String stateName) {
        this.state = stateName;
    }
 
    /**
     * @return the Zip code
     */
    public int getzipcode() {
        return zipcode;
    }
 
    /**
     * @param zipCode
     */
    public void setzipcode(int zipCode) {
        this.zipcode = zipCode;
    }
 
    public int getstock() {
        return stock;
    }
 
    public void setstock(int stock) {
        this.stock = stock;
    }
 
    /**
     * @return the clientid
     */
    public String getclientid() {
        return clientid;
    }
 
    /**
     * @param clientid the clientid to set
     */
    public void setclientid(String clientid) {
        this.clientid = clientid;
    }
 
    /**
     * @return the level
     */
    public String getlevel() {
        return level;
    }
 
    /**
     * @param level the level to set
     */
    public void setlevel(String level) {
        this.level = level;
    }
 
    /**
     * @return the due
     */
    public double getdue() {
        return due;
    }
 
    /**
     * @param due the due to set
     */
    public void setdue(double due) {
        this.due = due;
    }  
    
    /**
     * @return the email
     */
   
}