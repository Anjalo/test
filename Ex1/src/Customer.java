/**
 *
 * @author Rammuni Anjalo De Zoysa
 */
public class Customer{
    
    private String name;
    private String password;
    private int balance;
    private ChequingAcc cacc;
    private SavingsAcc sacc;
    
    public Customer(String name, String password, int chequingAccBalance, int savingsAccBalance){
        this.name = name;
        this.password = password;
        cacc = new ChequingAcc(chequingAccBalance);
        if(savingsAccBalance!=0)
            sacc = new SavingsAcc(savingsAccBalance);
    }
    
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public int getBalance(){
        return balance;
    }
    
    public ChequingAcc getCacc(){
        return cacc;
    }
    
    public SavingsAcc getSacc(){
        return sacc;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setBalance(int balance){
        this.balance = balance;
    }
    
    public void setCacc(ChequingAcc cacc){
        this.cacc = cacc;
    }
    
    public void setSacc(SavingsAcc sacc){
        this.sacc = sacc;
    }
}
