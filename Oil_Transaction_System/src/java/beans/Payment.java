
package beans;
 
import java.util.Date;
 
public class Payment {
    private String paymentid;
    private String clientid;
    private double amount;
    private Date pdate;
    private String tid;
    /**
     * @return the amount
     */
    public double getamount() {
        return amount;
    }
    /**
     * @param amount the amount to set
     */
    public void setamount(double amount) {
        this.amount = amount;
    }
    /**
     * @return the pdate
     */
    public Date getpdate() {
        return pdate;
    }
    /**
     * @param pdate the pdate to set
     */
    public void setpdate(Date pdate) {
        this.pdate = pdate;
    }
    /**
     * @return the paymentid
     */
    public String getpaymentid() {
        return paymentid;
    }
    /**
     * @param paymentid the paymentid to set
     */
    public void setpaymentid(String paymentid) {
        this.paymentid = paymentid;
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
     * @return the transactionid
     */
    public String gettransactionid() {
        return tid;
    }
    /**
     * @param transactionid the transactionid to set
     */
    public void settransactionid(String tid) {
        this.tid = tid;
    }
}