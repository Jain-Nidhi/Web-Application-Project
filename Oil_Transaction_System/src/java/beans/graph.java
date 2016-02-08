
/**
 * 
 */
package beans;
 
/**
 * @author Apoorva
 *
 */
public class graph{
    
     private String label = "";
    private int val = 0;
    
    public String getlabel() {
        return label;
    }
     
    /**
     * sets uid
     */
    public void setlabel(String label){
        this.label = label;
    }
     
     /**
     * @return  uname
     */
    public int getval() {
        return val;
    }
     
    /**
     * sets uname
     */
    public void setval(int val){
        this.val = val;
    }
    
}