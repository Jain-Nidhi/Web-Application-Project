
package beans;
 
import java.util.Date;
 
public class Transaction {
    private String clientid;
    private String traderid;
    private String tid;
    private float quantity;
    private Date tdate;
    private float rate;
    private double commission;
    private boolean cmode;
    private boolean active;
    private double reserve;
    private boolean buyorsell;
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
     * @return the traderid
     */
    public String gettraderid() {
        return traderid;
    }
    /**
     * @param traderid the traderid to set
     */
    public void settraderid(String traderid) {
        this.traderid = traderid;
    }
    /**
     * @return the tid
     */
    public String gettid() {
        return tid;
    }
    /**
     * @param tid the tid to set
     */
    public void settid(String tid) {
        this.tid = tid;
    }
    /**
     * @return the quantity
     */
    public float getquantity() {
        return quantity;
    }
    /**
     * @param quantity the quantity to set
     */
    public void setquantity(float quantity) {
        this.quantity = quantity;
    }
    /**
     * @return the tdate
     */
    public Date gettdate() {
        return tdate;
    }
    /**
     * @param tdate the tdate to set
     */
    public void settdate(Date tdate) {
        this.tdate = tdate;
    }
    /**
     * @return the rate
     */
    public float getrate() {
        return rate;
    }
    /**
     * @param rate the rate to set
     */
    public void setrate(float rate) {
        this.rate = rate;
    }
    /**
     * @return the commission
     */
    public double getcommission() {
        return commission;
    }
    /**
     * @param commission the commission to set
     */
    public void setcommission(double commission) {
        this.commission = commission;
    }
    /**
     * @return the cmode
     */
    public boolean iscmode() {
        return cmode;
    }
    /**
     * @param cmode the cmode to set
     */
    public void setcmode(boolean cmode) {
        this.cmode = cmode;
    }
    /**
     * @return the active
     */
    public boolean isactive() {
        return active;
    }
    /**
     * @param active the active to set
     */
    public void setactive(boolean active) {
        this.active = active;
    }
    /**
     * @return the reserve
     */
    public double getreserve() {
        return reserve;
    }
    /**
     * @param reserve the reserve to set
     */
    public void setreserve(double reserve) {
        this.reserve = reserve;
    }
    /**
     * @return the buyorsell
     */
    public boolean isbuyorsell() {
        return buyorsell;
    }
    /**
     * @param buyorsell the buyorsell to set
     */
    public void setbuyorsell(boolean buyorsell) {
        this.buyorsell = buyorsell;
    }
     
    public double getamount(){
        return this.quantity*this.rate+this.commission;
    }
}