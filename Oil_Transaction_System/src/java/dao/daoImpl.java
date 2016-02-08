package dao;
 
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.UUID;
 import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
 
import beans.Client;
import beans.Trader;
import beans.Transaction;
import beans.Payment;
import beans.User;
import beans.Log;
import beans.graph;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;
 
@Component
public class daoImpl {
    public enum CommissionMode {
        MONEY,
        OIL
    }
     
     public enum QueryDurationType {
        WEEKLY,
        MONTHLY,
        HALFYEARLY
    }
     
    public final char UserTypeClient = 'c';
    public final char UserTypeTrader = 't';
    /**
     * QueryString 
     */
    private final String searchuser = "SELECT uname,pword,fname,lname,phone,type,uid,cellphone,email FROM newuser WHERE uname = ? AND pword = ?";
    private final String searchuserforid = "SELECT uname,pword,fname,lname,phone,type,uid,cellphone,email FROM newuser WHERE uid=?";
    private final String searchClient = "SELECT clientid,level,due,street,city,zipcode,state,stock FROM client WHERE clientid = ?";
    private final String searchClientforUnameOrEmail = "SELECT clientid,level,due,street,city,zipcode,state,stock FROM client,newuser WHERE  client.clientid=newuser.uid AND (uname=? OR email=?)";
    private final String updateclientlevel = "UPDATE client SET level=? WHERE clientid=?";
    private final String insertuser = "INSERT INTO newuser (uname,pword,fname,lname,phone,cellphone,email,type,uid) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String inserttrader = "INSERT INTO trader (traderid) VALUES (?)";
    private final String insertclient = "INSERT INTO client (clientid,level,due,street,city,zipcode,state,stock) VALUES (?,?,?,?,?,?,?,?)";
    private final String getstock = "SELECT reserve FROM transaction1 ORDER BY tdate DESC LIMIT 1";
    private final String getClientLevel = "SELECT level FROM client WHERE clientid = ?";
    private final String getClientStock = "SELECT stock FROM client WHERE clientid = ?";
    private final String getEligibleTraders = "SELECT traderid FROM trader WHERE transactioncount = "
            + "(SELECT MIN(transactioncount) FROM trader)";
    private final String getAllTradersFromTransactions = "SELECT DISTINCT traderid FROM transaction1";
    private final String getAllTraders = "SELECT traderId FROM trader";
    private final String insertTransaction = "INSERT INTO transaction1 (clientid,traderid,tid,quantity,tdate,rate,commission,cmode,reserve,active,buyorsell) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private final String getCurrDue = "SELECT due FROM client WHERE clientid=?";
    private final String getCurrStock = "SELECT stock FROM client WHERE clientid=?";
    private final String updateDueAndStockOnClient = "UPDATE client SET due=?, stock=? WHERE clientid=?";
    private final String updateDueOnClient = "UPDATE client SET due=? WHERE clientid=?";
    private final String addlog = "INSERT INTO log (traderid,tid,pdate) VALUES (?,?,?)";
    private final String getallLog = "SELECT traderid,tid,pdate FROM log";
    private final String getallLogfortrader = "SELECT traderid,tid,pdate FROM log WHERE traderid=?";
    private final String getallLogfortransaction = "SELECT traderid,tid,pdate FROM log WHERE tid=?";
    private final String getallLogfordate = "SELECT traderid,tid,pdate FROM log WHERE pdate=?";
    private final String getTransactionForId = "SELECT clientid,traderid,tid,quantity,tdate,rate,commission,cmode,reserve,active,buyorsell FROM transaction1 WHERE tid=?";
    private final String getBuyTranForClientAndId = "SELECT quantity*rate+commission FROM transaction1 WHERE tid=? AND clientid=? AND buyorsell=? AND active=?";
    private final String getTranDue = "Select sum(T.quantity*T.rate + T.commission)- sum(p.amount) From transaction1 as T, payment as P Where T.tid=P.tid && T.tid=?";
    private final String insertPayment = "INSERT INTO payment (paymentid,clientid,amount,pdate,tid) VALUES (?,?,?,?,?)";
    private final String updateTrantoInActive = "UPDATE transaction1 SET active=? WHERE tid=?";
    private final String getPaymentForId = "SELECT paymentid,clientid,amount,pdate,tid FROM payment WHERE paymentid=?";
    private final String getPaymentForIdAndClientId = "SELECT paymentid, clientid, amount,pdate,tid FROM payment WHERE paymentid=? AND clientid=?";
    private final String deletePayment = "DELETE FROM payment WHERE paymentid=?";
    private final String deleteTransaction = "DELETE FROM transaction1 WHERE tid=?";
    private final String getTraderForTran = "SELECT traderid FROM transaction1 WHERE tid=?";
    
    
    private final String getWeeklyTransaction = "SELECT DATEPART(day, datediff(day, 0, tdate)/7 * 7)/7 + 1 as label FROM transaction1 WHERE (DATEPART(mm,tdate)=DATEPART(mm,GETDATE())) GROUP BY DATEPART(day, datediff(day, 0, tdate)/7 * 7)/7 + 1 ORDER BY label";
    private final String get12MonthlyTransaction= "SELECT DATEPART(mm,tdate) as label FROM transaction1 WHERE (DATEPART(yyyy,tdate)=DATEPART(yyyy,GETDATE())) GROUP BY DATEPART(mm,tdate) ORDER BY label";
    private final String getYearlyTransaction= "SELECT DATEPART(yyyy,tdate) as label FROM transaction1 WHERE DATEPART(yyyy,tdate)-DATEPART(yyyy,GETDATE())<5 GROUP BY DATEPART(yyyy,tdate) ORDER BY label";
    private final String get10DailyTransaction= "SELECT tdate as label FROM transaction1 WHERE (DATEPART(mm,tdate)=DATEPART(mm,GETDATE())) AND DATEPART(day,GETDATE())-DATEPART(day,tdate)<=10 GROUP BY tdate  ORDER BY label";
    
    
    private final String getWeeklyTransaction1 = "SELECT T.val from (SELECT DATEPART(day, datediff(day, 0, tdate)/7 * 7)/7 + 1 as label,count(*) as val FROM transaction1 WHERE (DATEPART(mm,tdate)=DATEPART(mm,GETDATE())) GROUP BY DATEPART(day, datediff(day, 0, tdate)/7 * 7)/7 + 1)as T ORDER BY T.val";
    private final String get12MonthlyTransaction1= "SELECT T.val from (SELECT DATEPART(mm,tdate) as label,count(*) as val FROM transaction1 WHERE (DATEPART(yyyy,tdate)=DATEPART(yyyy,GETDATE())) GROUP BY DATEPART(mm,tdate) )as T ORDER BY T.val";
    private final String getYearlyTransaction1= "SELECT T.val from (SELECT DATEPART(yyyy,tdate) as label,count(*) as val FROM transaction1 WHERE DATEPART(yyyy,tdate)-DATEPART(yyyy,GETDATE())<5 GROUP BY DATEPART(yyyy,tdate) )as T ORDER BY T.val";
    private final String get10DailyTransaction1= "SELECT T.val from (SELECT tdate as label,count(*) as val FROM transaction1 WHERE (DATEPART(mm,tdate)=DATEPART(mm,GETDATE())) AND DATEPART(day,GETDATE())-DATEPART(day,tdate)<=10 GROUP BY tdate )as T ORDER BY T.val";
    
    private final String getMonthlyOilTransaction= "SELECT tid FROM transaction1 WHERE (DATEPART(mm,tdate)=DATEPART(mm,GETDATE())) AND clientid=?";
    private final String getAllLastMonthTransaction= "SELECT tid FROM transaction1 WHERE (DATEPART(mm,tdate)=DATEPART(mm,GETDATE())) AND clientid=?";
    
    
    private final String getAllActiveTransaction= "SELECT tid FROM transaction1 WHERE clientid=? AND active=? ORDER BY tdate";
    private final String getAllInactiveTransaction= "SELECT tid FROM transaction1 WHERE clientid=? AND active=? ORDER BY tdate";
    private final String getAllTraderActiveTransaction= "SELECT tid FROM transaction1 WHERE traderid=? AND active=? ORDER BY tdate";
    private final String getAllTraderInactiveTransaction= "SELECT tid FROM transaction1 WHERE traderid=? AND active=? ORDER BY tdate";
    
    private final String updateUser = "UPDATE newuser SET fname=?, lname=?, phone=?, cellphone=? WHERE uid=?";
    private final String updateClient = "UPDATE client SET street=?, city=?, zipcode=?, state=? WHERE clientid=?";
    private final String getmonthpurchase = "SELECT SUM(quantity) FROM transaction1 where clientid=? AND buyorsell=? AND DATEPART(mm,tdate) IN ( SELECT MONTH(GETDATE()) from payment )";
    private final String getmonthsale = "SELECT SUM(quantity) FROM transaction1 where clientid=? AND buyorsell=? AND DATEPART(mm,tdate) IN ( SELECT MONTH(GETDATE()) from payment )";
    private final String getTransactionPayments = "SELECT paymentid FROM payment WHERE tid=?";
    private final String getClientPayments = "SELECT paymentid FROM payment WHERE clientid=?";
    
    private final String gettid  = "SELECT tid FROM payment WHERE clientid=?";
    
    private final String getTraderPayments = "SELECT paymentid FROM payment,transaction1 WHERE payment.tid=transaction1.tid AND traderid=?";
    
    private final String gettradertid  = "SELECT payment.tid FROM payment,transaction1 WHERE payment.tid=transaction1.tid AND traderid=?";
    
    private final String gettidforpay = "SELECT tid FROM payment WHERE paymentid=?";
    private final String getallclientsbyname = "SELECT uid FROM newuser WHERE fname LIKE ? OR lname LIKE ?";
    private final String getallclientsbyemail = "SELECT uid FROM newuser WHERE email=?"; 
    private final String getallclientsbyaddress = "SELECT clientid FROM client WHERE street LIKE ? OR city LIKE ? OR state LIKE ?";
            
            private DataSource mDataSource;
    private JdbcTemplate mJdbctemplate;
 
   
    /**
     * @param dataSource data source to jdbc template
     */
    
    public DataSource getDataSource(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("newSpringXMLConfig.xml"); 
        mDataSource = (DataSource)context.getBean("mDataSource");
        return mDataSource;
    }
    
    public void setDataSource(DataSource dataSource) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("newSpringXMLConfig.xml"); 
        mJdbctemplate = (JdbcTemplate)context.getBean("JdbcTemplate");
    }
 
    /**
     * @return mJdbctemplate
     */
    public JdbcTemplate getJdbctemplate() {
        return mJdbctemplate;
    }
 
    /**
     * @param mJdbctemplate the mJdbctemplate to set
     */
    
    public void setJdbctemplate(JdbcTemplate mJdbctemplate) {
        this.mJdbctemplate = mJdbctemplate;
    }
     
    /**
     * Takes username and password and returns the User object
     * @param uName
     * @param pWord
     * @return
     */
    public User Login(final String uName, final String pWord) {
       setDataSource(getDataSource());
        User us = (User)mJdbctemplate.queryForObject(searchuser, new Object[] {uName,pWord}, new BeanPropertyRowMapper(User.class));
        return us;
    }
     
    /**
     * 
     * @param uName
     * @param pWord
     * @param fName
     * @param lName
     * @param phone
     * @return
     */
    public Trader RegisterTrader(final String uName, final String pWord, final String fName, final String lName, final int phone){
        setDataSource(getDataSource());
        UUID currUId = UUID.randomUUID();
        mJdbctemplate.update(insertuser, new Object[]{uName,pWord,fName,lName,phone,"t",currUId},
                new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.CHAR,Types.VARCHAR});
        mJdbctemplate.update(inserttrader, new Object[]{currUId},new int[]{Types.VARCHAR});
        Trader td= (Trader)mJdbctemplate.queryForObject(searchuser, new Object[] {uName,pWord}, new BeanPropertyRowMapper(Trader.class));
        return td;
    }
     
   
    /**
     * 
     * @param uName
     * @param pWord
     * @param fName
     * @param lName
     * @param phone
     * @param level
     * @param due
     * @param street
     * @param city
     * @param zipcode
     * @param state
     * @return
     */
    public Client RegisterClient(final String uName, final String pWord, final String fName, final String lName, final long phone, final long cellphone,
            final String email, final String level, final double due, final String street, final String city, final int zipcode, final String state , final int stock ){
        setDataSource(getDataSource());
        UUID currUId = UUID.randomUUID();
        mJdbctemplate.update(insertuser, new Object[]{uName,pWord,fName,lName,phone,cellphone,email,"c",currUId},
                new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.BIGINT,Types.VARCHAR,Types.CHAR,Types.VARCHAR});
        mJdbctemplate.update(insertclient, new Object[]{currUId,level,due,street,city,zipcode,state,stock},
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.DOUBLE, Types.VARCHAR, Types.VARCHAR,Types.INTEGER,Types.CHAR,Types.INTEGER});
        
        User user = Login(uName,pWord);
        Client retVal = (Client)mJdbctemplate.queryForObject(searchClient, new Object[] {user.getuid()}, new BeanPropertyRowMapper(Client.class));
        retVal.setfname(user.getfname());
        retVal.setlname(user.getlname());
        retVal.setuname(user.getuname());
        retVal.setpword(user.getpword());
        retVal.settype(user.gettype());
        retVal.setuid(user.getuid());
        return retVal;
    }
     
      /* Given UserName and password get the client Object
     */
    public Client GetClientForCredentials(String uName, String pWord) {
        setDataSource(getDataSource());
        User user = Login(uName,pWord);
        Client retVal = (Client)mJdbctemplate.queryForObject(searchClient, new Object[] {user.getuid()}, new BeanPropertyRowMapper<Client>(Client.class));
        retVal.setfname(user.getfname());
        retVal.setfname(user.getuname());
        retVal.setlname(user.getlname());
        retVal.setphone(user.getphone());
        retVal.setuid(user.getuid());
        retVal.setpword(user.getpword());
        retVal.settype(user.gettype());
        return retVal;
    }
     
    public Client GetClientForUnameOrEmail(String str) {
        setDataSource(getDataSource());
        Client retVal = (Client)mJdbctemplate.queryForObject(searchClientforUnameOrEmail, new Object[] {str,str}, new BeanPropertyRowMapper<Client>(Client.class));
        return retVal;
    }
    /**
     * Given UserName and password get the client Object
     */
    public Trader GetTraderForCredentials(String uName, String pWord) {
        setDataSource(getDataSource());
        return (Trader)Login(uName,pWord);
    }
     
    /**
     * Given id get the trader
     */
    public Trader GetTraderForID(String traderid) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(searchuserforid, new Object[] {traderid}, new BeanPropertyRowMapper<Trader>(Trader.class));
    }
     
    public User GetUserForID(String uid) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(searchuserforid, new Object[] {uid}, new BeanPropertyRowMapper<User>(User.class));
    }
    /**
     * Given id get the trader
     */
    public Client GetClientForID(String clientid) {
        setDataSource(getDataSource());
        User user = mJdbctemplate.queryForObject(searchuserforid, new Object[] {clientid}, new BeanPropertyRowMapper<User>(User.class));
        Client retVal = (Client)mJdbctemplate.queryForObject(searchClient, new Object[] {user.getuid()}, new BeanPropertyRowMapper<Client>(Client.class));
        retVal.setfname(user.getfname());
        retVal.setlname(user.getlname());
        retVal.setphone(user.getphone());
        retVal.setuid(user.getuid());
        return retVal;
    }
 
     
    /**
     * Get the current stock;
     * @return
     */
    public double GetStock() {
        setDataSource(getDataSource());
        double retVal = 0.0;
        try{
            retVal = mJdbctemplate.queryForObject(getstock, float.class);
        } catch (Exception e) {
            //will get an exception if there is no entry in the transaction table.
            retVal = 1000;
        }
         
        return retVal;
    }
 
    /**
     * Updates trader
     */
    public Trader UpdateTrader(String traderid, String fname, String lname, long phone, long cellphone, String email) {
        setDataSource(getDataSource());
        mJdbctemplate.update(updateUser,new Object[]{fname,lname,phone,cellphone,email,traderid},new int[] {Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.BIGINT,Types.VARCHAR,Types.VARCHAR});
        return GetTraderForID(traderid);
    }
 
     
    /**
     * Updates Client
     */
    public void UpdateClient(String clientid, String fname, String lname, long phone, long cellphone,String street,String city, int zipcode,String state) {
        setDataSource(getDataSource());
        mJdbctemplate.update(updateUser,new Object[]{fname,lname,phone,cellphone,clientid},new int[] {Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.BIGINT,Types.VARCHAR});
        mJdbctemplate.update(updateClient,new Object[]{street,city,zipcode,state,clientid},new int[] {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR});
         
        return;
    }
     
    /**
     * Gets the Client level
     */
    public String GetClientLevel(String clientid) {
        setDataSource(getDataSource());
        return (String)mJdbctemplate.queryForObject(getClientLevel, new Object[] {clientid}, String.class);
    }
     
    /**
     * Gets the Client Stock
     */
    public String GetClientStock(String clientid) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(getClientStock, new Object[] {clientid}, String.class);
    }
     
    /**
     * Get Transaction Due for given transactionid
     */
    public double GetTransactionDue(String tranid) {
        setDataSource(getDataSource());
        Transaction trans = GetTransactionForId(tranid);
          double price = (trans.getquantity()* trans.getrate())+ trans.getcommission();
        double due=0.0;
        List<Payment> lp = getTransactionPayments(tranid);
           for (Payment Paym : lp) {
             due += Paym.getamount();
            }
           due = price-due;
        return due;
    }
     
      public List<Payment> getClientPayments(String clientid)
    {
        setDataSource(getDataSource());
        List<String> listOfPay= mJdbctemplate.queryForList(getClientPayments,new Object[] {clientid}, String.class);
        List<Payment> Pay=new ArrayList<Payment>();
        for (String Paym : listOfPay) {
            Pay.add(GetPayment(Paym));
        }
        return Pay;
    }
      
       public List<Payment> getTraderPayments(String traderid)
    {
        setDataSource(getDataSource());
        List<String> listOfPay= mJdbctemplate.queryForList(getTraderPayments,new Object[] {traderid}, String.class);
        List<Payment> Pay=new ArrayList<Payment>();
        for (String Paym : listOfPay) {
            Pay.add(GetPayment(Paym));
        }
        return Pay;
    }
    /**
     * Private Get trader ID with lowest number of transaction allocated
     * @return
     */
    private String GetTradersWithLowestAllocations() {
        String retVal = "";
        setDataSource(getDataSource());
        List<String> tradersWithLowestTran = new ArrayList();
         
        tradersWithLowestTran = mJdbctemplate.queryForList(getEligibleTraders,String.class);
        retVal = tradersWithLowestTran.get(0);
        
        return retVal;
    }
     
    /**
     * Private Get list of trader ID which have transaction
     * @return
     */
    private List<String> GetTradersWithTransaction() {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForList(getAllTradersFromTransactions, String.class);
    }
     
    /**
     * Private Get list of all traders 
     * @return
     */
    private List<String> GetAllTraders() {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForList(getAllTraders, String.class);
    }
     
     public List<String> GetAllClients_ByName(String name) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForList(getallclientsbyname,new Object[] {"%"+name+"%","%"+name+"%"}, String.class);
    }
     public List<String> GetAllClients_ByEmail(String email) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForList(getallclientsbyemail,new Object[] {email}, String.class);
    }
     public List<String> GetAllClients_ByAddress(String address) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForList(getallclientsbyaddress,new Object[] {"%"+address+"%","%"+address+"%","%"+address+"%"}, String.class);
    }
    /**
     * Get the current due for the client with give id
     * @return
     */
    private double GetCurrentDueForClient(String clientid) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(getCurrDue,new Object[] {clientid}, double.class);
    }
     
    /**
     * Get the current stock for the client with give id
     * @return
     */
    private double GetCurrentStockForClient(String clientid) {
        setDataSource(getDataSource());
        return (double)mJdbctemplate.queryForObject(getCurrStock,new Object[] {clientid}, double.class);
    }
     
    /**
     * Updates the due and stock for the client with give client id.
     * @param clientid
     * @param currentDue
     */
    private void UpdateDueAndStockForClient(String clientid, double currentDue, double stock) {
        setDataSource(getDataSource());
        mJdbctemplate.update(updateDueAndStockOnClient, new Object[] {currentDue,stock,clientid}, new int[] {Types.DOUBLE,Types.DOUBLE,Types.VARCHAR});
    }
     
    /**
     * Updates the due for the client with give client id.
     * @param clientid
     * @param currentDue
     */
    private void UpdateDueForClient(String clientid, double currentDue) {
        setDataSource(getDataSource());
        mJdbctemplate.update(updateDueOnClient, new Object[] {currentDue,clientid}, new int[] {Types.DOUBLE,Types.VARCHAR});
    }
     
    /**
     * Updates the due for the client with give client id.
     * @param clientid
     * @param currentDue
     */
    private void UpdateTransactionInActive(String transactionId) {
        setDataSource(getDataSource());
        mJdbctemplate.update(updateTrantoInActive, new Object[] {false,transactionId}, new int[] {Types.BOOLEAN,Types.VARCHAR});
    }
     
     public List<String> Gettid(String clientid) {
        setDataSource(getDataSource());
    List<String> s = mJdbctemplate.queryForList(gettid, new Object[] {clientid}, String.class);
        return s;
     }
     
     public List<String> Gettradertid(String traderid) {
        setDataSource(getDataSource());
    List<String> s = mJdbctemplate.queryForList(gettradertid, new Object[] {traderid}, String.class);
        return s;
     }
     
     
     public String gettidforpay(String paymentid){
        setDataSource(getDataSource());
        String s = mJdbctemplate.queryForObject(gettidforpay, new Object[] {paymentid}, String.class);
        return s;
     }
    /**
     * Gets Payment for given payment id
     */
    private Payment GetPayment(String paymentId) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(getPaymentForId, new Object[] {paymentId}, new BeanPropertyRowMapper<Payment>(Payment.class));
   
    }
     
    /**
     * Gets Payment for given payment id and given clientid
     */
    private Payment GetPayment(String paymentId, String clientid) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(getPaymentForIdAndClientId, new Object[] {paymentId,clientid}, new BeanPropertyRowMapper<Payment>(Payment.class));
    }
     
    /**
     * Gets the transaction for given ID.
     * @param transactionID
     * @return
     */
    public Transaction GetTransactionForId(String transactionID){
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(getTransactionForId, new Object[] {transactionID}, new BeanPropertyRowMapper<Transaction>(Transaction.class));
    }
     
    /**
     * Gets the trader id for given transaction id.
     * @param transactionID
     * @return
     */
    public String GetTraderIdForTransaction(String transactionID){
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(getTraderForTran, new Object[] {transactionID}, String.class);
    }
     
      public String getCurrentMonthPurchase(String clientid){
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(getmonthpurchase, new Object[] {clientid,true}, String.class);
    }
      
        public String getCurrentMonthSale(String clientid){
        setDataSource(getDataSource());
        return mJdbctemplate.queryForObject(getmonthsale, new Object[] {clientid,false}, String.class);
    }
    /**
     * Gets the commission rate depending up on the level of the client for given client id.
     */
    public double GetCommisionRate(String clientid) {
        double commissionRate;
        String level = GetClientLevel(clientid);
        if(level.equalsIgnoreCase("gold")) {
            commissionRate = 0.05;
        } else if(level.equalsIgnoreCase("silver")) {
            commissionRate = 0.1;
        } else {
            throw new RuntimeException("Incorrect level");
        }
         
        return commissionRate;
    }
     
    /**
     * 
     * @param clientid
     * @param quantity
     * @param tradeDate
     * @param rate
     * @param cmode
     * @param CommissionRate
     * @return
     */
    public Transaction Buy(String clientid, double quantity, Date tradeDate, double rate, boolean cmode) {
        double reserve = GetStock();
         
        //returning null if reserve is lesser than requested amount.
        if(quantity>reserve) {
            return null;
        }
         
        String level = GetClientLevel(clientid);
       double CommissionRate = GetCommisionRate(clientid);
         
        double commissionAmt = quantity*rate*CommissionRate;
        boolean bMode;
        if(cmode == false) {
            bMode = false;
        } else  {
            bMode = true;
        }
        String traderIds = GetTradersWithLowestAllocations();
         
         
        reserve = reserve-quantity;
        UUID transactionid = UUID.randomUUID();
        //By Default the transaction is active and in this case since we are buying hence last argument is also true.
        Object[] args = {clientid,traderIds,transactionid,quantity,tradeDate,rate,commissionAmt,bMode,reserve,true,true}; 
        int[] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DOUBLE,Types.DATE,Types.DOUBLE,Types.DOUBLE,Types.BOOLEAN,Types.DOUBLE,Types.BOOLEAN,Types.BOOLEAN};
        mJdbctemplate.update(insertTransaction, args, argTypes);
         
        double currentDue;
        double currentStock;
        Client cli = GetClientForID(clientid);
        //if bmode is oil meaning true then we should deduct amount from stock and give rest to the stock of client
        if(bMode) {
            currentDue = cli.getdue() + quantity*rate;
            currentStock = cli.getstock() + quantity - (commissionAmt/(rate*CommissionRate));
        } else {
            currentDue =  cli.getdue() + quantity*rate +commissionAmt;
            currentStock =  cli.getstock() + quantity;
        }
         
         
        UpdateDueAndStockForClient(clientid, currentDue,currentStock);
        
        float monthly_oil_traded=GetMonthlyOilTransaction(clientid);
        if(monthly_oil_traded>=30 && cli.getlevel().equals("Silver")) UpdateClientLevel(clientid);
         
        return GetTransactionForId(transactionid.toString());
    }
     
    public Transaction Sell(String clientid, double quantity, Date tradeDate, double rate, boolean cmode) {
         
        Client cli = GetClientForID(clientid);
        double currentDue;
        double stock = cli.getstock();
        double reserve = GetStock();
        //returning null if reserve is lesser than requested amount.
        if(quantity>stock) {
            return null;
        }
         
      double CommissionRate = GetCommisionRate(clientid);
        double commissionAmt = quantity*rate*CommissionRate;
        String traderIds = GetTradersWithLowestAllocations();
         
        boolean bMode;
        if(cmode == false) {
            bMode = false;
        } else  {
            bMode = true;
        }
         
        reserve = reserve + quantity;
        UUID transactionid = UUID.randomUUID();
        //By Default the transaction is active and in this case since we are selling hence last argument is false.
        
        Object[] args = {clientid,traderIds,transactionid,quantity,tradeDate,rate,commissionAmt,bMode,reserve,true,false};
        
        Object[] args1 = {clientid,traderIds,transactionid,quantity,tradeDate,rate,commissionAmt,bMode,reserve,false,false};
        
        int[] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DOUBLE,Types.DATE,Types.DOUBLE,Types.DOUBLE,Types.BOOLEAN,Types.DOUBLE,Types.BOOLEAN,Types.BOOLEAN};
        if(bMode==false)
        mJdbctemplate.update(insertTransaction, args, argTypes);
         else
        mJdbctemplate.update(insertTransaction, args1, argTypes);
        cli = GetClientForID(clientid);
        if(bMode) {
            currentDue = cli.getdue() - quantity*rate ;
            stock = stock - quantity - (commissionAmt/(rate*CommissionRate));
            reserve = reserve + quantity + (commissionAmt/(rate*CommissionRate));
        } else {
            currentDue = cli.getdue() - quantity*rate + commissionAmt;
            stock = stock - quantity;
            reserve = reserve + quantity;
        }
           UpdateDueAndStockForClient(clientid, currentDue, stock);
        float monthly_oil_traded=GetMonthlyOilTransaction(clientid);
        if(monthly_oil_traded>=30 && cli.getlevel().equals("Silver")) UpdateClientLevel(clientid);
        
        return GetTransactionForId(""+transactionid);
    }
     
    /**
     * Make payment
     */
    public Payment MakePaymentForTransaction(String clientId, String transactionid, double amount, Date pDate){
        setDataSource(getDataSource());
        Transaction tn = GetTransactionForId(transactionid);
        double tranDue = (tn.getquantity()* tn.getrate())+tn.getcommission();
        if(amount>tranDue) {
            amount=tranDue; //amount to be paid should never be more than due
        }
        Client cli = GetClientForID(clientId);
      //  double clientdue = mJdbctemplate.queryForObject(getCurrDue, new Object[] {clientId}, double.class);
        
        cli.setdue(cli.getdue()-amount);
        UpdateDueForClient(clientId,cli.getdue());
         
        UUID paymentid = UUID.randomUUID();
        mJdbctemplate.update(insertPayment, new Object[] {paymentid,clientId,amount,pDate,transactionid}, new int[] {Types.VARCHAR,Types.VARCHAR,Types.DOUBLE,Types.DATE,Types.VARCHAR});
        tn = GetTransactionForId(transactionid);
        double price = (tn.getquantity()* tn.getrate())+tn.getcommission();
        double pendingpayment=0.0;
        List<Payment> lp = getTransactionPayments(transactionid);
           for (Payment Paym : lp) {
             pendingpayment += Paym.getamount();
            }
           pendingpayment = price - pendingpayment;
        if(pendingpayment == 0.0) {
            UpdateTransactionInActive(transactionid);
        }
         
        return GetPayment(paymentid.toString());
    }
     
    /**
     * Delete Payment
     */
    public void DeletePayment(String paymentid) {
        setDataSource(getDataSource());
        mJdbctemplate.update(deletePayment,new Object[] {paymentid});
    }
     
    /**
     * Delete Transaction
     */
    public void DeleteTransaction(String tid) {
        setDataSource(getDataSource());
        mJdbctemplate.update(deleteTransaction,new Object[] {tid});
    }
     
    /**
     * Cancels Payment
     */
    public Payment CancelPayment(String paymentid, String traderid) {
        Payment retVal = GetPayment(paymentid);
        if(retVal == null) {
            return null;
        }
        Client cli = GetClientForID(retVal.getclientid());
        double currentDue = cli.getdue();
        currentDue = currentDue + retVal.getamount();
        DeletePayment(paymentid);
        UpdateDueForClient(retVal.getclientid(), currentDue);
        AddLog(paymentid, traderid, GetCurrentDate());
        return retVal;
    }
     
    /**
     * Cancels Transactions
     */
    public Transaction CancelTransaction(String transactionid, String traderid){
        Transaction retVal = GetTransactionForId(transactionid);
        if(retVal == null) {
            return null;
        }
         
        Client cli = GetClientForID(retVal.getclientid());
        double currentDue = cli.getdue();
        currentDue = currentDue - (   (retVal.getquantity()*retVal.getrate())  + retVal.getcommission());
        DeleteTransaction(transactionid);
        UpdateDueForClient(retVal.getclientid(), currentDue);
        AddLog(transactionid, traderid, GetCurrentDate());
        return retVal;
    }
     
    /**
     * Query APIS
     */
      public List<Payment> getTransactionPayments(String tid)
    {
        setDataSource(getDataSource());
        List<String> listOfPay= mJdbctemplate.queryForList(getTransactionPayments,new Object[] {tid}, String.class);
        List<Payment> Pay=new ArrayList<Payment>();
        for (String Paym : listOfPay) {
            Pay.add(GetPayment(Paym));
        }
        return Pay;
    }
    
      public List<Transaction> getAllActiveTransaction(String clientid)
    {
        setDataSource(getDataSource());
        List<String> listOfTransactionId= mJdbctemplate.queryForList(getAllActiveTransaction,new Object[] {clientid,true}, String.class);
        List<Transaction> ActiveTransaction=new ArrayList<Transaction>();
        for (String transaction : listOfTransactionId) {
            ActiveTransaction.add(GetTransactionForId(transaction));
        }
        return ActiveTransaction;
    }
      
     public List<Transaction> getAllLastMonthTransaction(String clientid)
    {
        setDataSource(getDataSource());
        List<String> listOfTransactionId= mJdbctemplate.queryForList(getAllLastMonthTransaction,new Object[] {clientid}, String.class);
        List<Transaction> ActiveTransaction=new ArrayList<Transaction>();
        for (String transaction : listOfTransactionId) {
            ActiveTransaction.add(GetTransactionForId(transaction));
        }
        return ActiveTransaction;
    }
      
         public List<Transaction> getAllInactiveTransaction(String clientid)
    {
        setDataSource(getDataSource());
        List<String> listOfTransactionId= mJdbctemplate.queryForList(getAllInactiveTransaction,new Object[] {clientid,false}, String.class);
        List<Transaction> InactiveTransaction=new ArrayList<Transaction>();
        for (String transaction : listOfTransactionId) {
            InactiveTransaction.add(GetTransactionForId(transaction));
        }
        return InactiveTransaction;
    }
      
         
         public List<Transaction> getAllTraderActiveTransaction(String traderid)
    {
        setDataSource(getDataSource());
        List<String> listOfTransactionId= mJdbctemplate.queryForList(getAllTraderActiveTransaction,new Object[] {traderid,true}, String.class);
        List<Transaction> ActiveTransaction=new ArrayList<Transaction>();
        for (String transaction : listOfTransactionId) {
            ActiveTransaction.add(GetTransactionForId(transaction));
        }
        return ActiveTransaction;
    }
      
         public List<Transaction> getAllTraderInactiveTransaction(String traderid)
    {
        setDataSource(getDataSource());
        List<String> listOfTransactionId= mJdbctemplate.queryForList(getAllTraderInactiveTransaction,new Object[] {traderid,false}, String.class);
        List<Transaction> InactiveTransaction=new ArrayList<Transaction>();
        for (String transaction : listOfTransactionId) {
            InactiveTransaction.add(GetTransactionForId(transaction));
        }
        return InactiveTransaction;
    }
      
         
    public List<graph> getWeeklyTransaction()
    {
        setDataSource(getDataSource());
         List<String> lb= mJdbctemplate.queryForList(getWeeklyTransaction, String.class);
         List<String> vl= mJdbctemplate.queryForList(getWeeklyTransaction1, String.class);
        List<graph> list=new ArrayList();
        int i=0;
        for(String s:lb){
            graph g= new graph();
            g.setlabel(s);
            g.setval(Integer.parseInt(vl.get(i)));
            list.add(g);
            i++;
        }
        return list;
    }
     
     
    public List<graph> get10DailyTransaction()
    {
        setDataSource(getDataSource());
         List<String> lb= mJdbctemplate.queryForList(get10DailyTransaction, String.class);
          List<String> vl= mJdbctemplate.queryForList(get10DailyTransaction1, String.class);
         List<graph> list=new ArrayList();
        int i=0;
        for(String s:lb){
            graph g= new graph();
            g.setlabel(s);
            g.setval(Integer.parseInt(vl.get(i)));
            list.add(g);
            i++;
        }
        return list;
    }
     
    public List<graph> get12MonthlyTransaction()
    {
        setDataSource(getDataSource());
         List<String> lb= mJdbctemplate.queryForList(get12MonthlyTransaction, String.class);
        List<String> vl= mJdbctemplate.queryForList(get12MonthlyTransaction1, String.class);
        List<graph> list=new ArrayList();
        int i=0;
        for(String s:lb){
            graph g= new graph();
            g.setlabel(s);
            g.setval(Integer.parseInt(vl.get(i)));
            list.add(g);
            i++;
        }
        return list;
    }
     
    public List<graph> getYearlyTransaction()
    {
        setDataSource(getDataSource());
       List<String> lb= mJdbctemplate.queryForList(getYearlyTransaction, String.class);
       List<String> vl= mJdbctemplate.queryForList(getYearlyTransaction1, String.class);
       List<graph> list=new ArrayList();
        int i=0;
        for(String s:lb){
            graph g= new graph();
            g.setlabel(s);
            g.setval(Integer.parseInt(vl.get(i)));
            list.add(g);
            i++;
        }
       return list;
    }
     
    /**
     * Adds a new Log entry
     */
    private void AddLog(String transactionid, String traderid, Date date) {
        setDataSource(getDataSource());
        mJdbctemplate.update(addlog,new Object[] {traderid,transactionid,date}, new int[] {Types.VARCHAR,Types.VARCHAR,Types.DATE});
    }
     
    /**
     * Gets all logs
     */
    public List<Log> GetAllLog() {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForList(getallLog, Log.class);
    }
     
    /**
     * Gets all logs for a date
     */
    public List<Log> GetAllLogforTrader(String traderid) {
        setDataSource(getDataSource());
        return mJdbctemplate.queryForList(getallLogfortrader,new Object[] {traderid},new int[] {Types.VARCHAR}, Log.class);
    }
     
    public float GetMonthlyOilTransaction(String clientid) {
        List<String> listOfTransactionId= mJdbctemplate.queryForList(getMonthlyOilTransaction,new Object[] {clientid}, String.class);
        Transaction ActiveTransaction=new Transaction();
        float res=0f;
        for (String transaction : listOfTransactionId) {
            ActiveTransaction=GetTransactionForId(transaction);
            res+=ActiveTransaction.getquantity();
        }
        return res;
    }
    
      public void UpdateClientLevel(String clientid) {
        setDataSource(getDataSource());
        mJdbctemplate.update(updateclientlevel,new Object[] {"Gold",clientid},new int[] {Types.VARCHAR,Types.VARCHAR});
    
    }
    /**
     * A util method to get Date
     * @return
     */
    private static Date GetCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(
                "MM-dd-yyyy");
            int year = 2014;
            int month = 10;
            int day = 31;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month - 1); // <-- months start
                                                // at 0.
            cal.set(Calendar.DAY_OF_MONTH, day);
 
            Date date = new Date(cal.getTimeInMillis());
            System.out.println(sdf.format(date));
            return date;
    }  
}