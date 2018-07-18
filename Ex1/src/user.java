
import java.io.*;
import java.util.*;

/**
 *
 * @author Rammuni Anjalo De Zoysa
 */
public class user {
    
    ArrayList<Customer> customers = new ArrayList<Customer>();
    
    public user(){}
    
    public void write(String name, String password, String role) throws IOException{
        BufferedWriter writer = new BufferedWriter( new FileWriter("profile.txt", true));
        writer.write(name+" "+password+" "+role);
        writer.newLine();
        writer.close();
    }
    
    public String read(String name, String password) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(new File("profile.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] lines = line.split(" ");
            if(lines[0].equals(name) && lines[1].equals(password))
                return lines[2];
        }
        return "none";
    }
 
    public void accountOptions(String name, String role) throws IOException{
        Scanner reader = new Scanner(System.in);
        Customer cust = null; 
        System.out.println("\nWelcome!");
        if(role.equals("Manager")){   
            while(true){
                System.out.println("To remove an user, please enter: 0");
                System.out.println("To add an user, please enter: 1");
                System.out.println("To logout, please enter: 2");
                int a = reader.nextInt();
                if(a == 0){
                    System.out.println("Enter the name of customer to be removed:");
                    String n = reader.nextLine();
                    for(int i = 0; i < customers.size(); i++)
                        if(customers.get(i).getName().equals(n))
                            customers.remove(i);
                        
                }
                else if(a==1){
                    System.out.println("Enter the username of the new customer:");
                    String n = reader.next();
                    System.out.println("Enter the password of the new customer:");
                    String p = reader.next();
                    System.out.println("Minimum account balance = 20");
                    System.out.println("Enter the Chequing Account balance of the new customer:");
                    int c = reader.nextInt();
                    System.out.println("Enter the Chequing Account balance of the new customer(Enter 0 if the user has"
                            + "no Savings Account):");
                    int s = reader.nextInt();
                    customers.add(new Customer(n,p,c,s));
                    write(n,p,"Customer");
                }
                else if(a==2){
                    System.out.println("Logout Complete!\n");
                    break;
                }
            }
        }
        else if(role.equals("Customer")){
            for(int i = 0; i < customers.size(); i++)
                        if(customers.get(i).getName().equals(name))
                           cust = customers.get(i);
            while(true){
                System.out.println("To check chequing account balance, please enter: 0");
                System.out.println("To check savings account balance, please enter: 1");
                System.out.println("For transfer options, please enter: 2");
                System.out.println("To logout, please enter: 3");
                int a = reader.nextInt();
                if(a == 0){
                    System.out.println("$"+cust.getCacc().getAmt());    
                }
                else if(a==1){
                    if(cust.getSacc() != null)
                        System.out.println("$"+cust.getSacc().getAmt());   
                    System.out.println("You have no savings account");
                }
                else if(a==2){
                    System.out.println("To transfer between chequing account to savings account, please enter: 0");
                    System.out.println("To transfer between savings account to chequing account, please enter: 1");
                    int b = reader.nextInt();
                    if(b==0){
                        System.out.println("How much money do you want to transfer from chequing account to "
                                + "savings account:");
                        int amt = reader.nextInt();
                        cust.getSacc().setAmt(cust.getSacc().getAmt() + amt);
                        cust.getCacc().setAmt(cust.getCacc().getAmt() - amt);
                    }
                    else if(b==1){
                        System.out.println("How much money do you want to transfer from savings account "
                                + "to chequing account:");
                        int amt = reader.nextInt();
                        cust.getSacc().setAmt(cust.getCacc().getAmt() - amt);
                        cust.getCacc().setAmt(cust.getCacc().getAmt() + amt);
                    }  
                }
                else if(a==3){
                    System.out.println("Logout Complete!\n");
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        user test = new user();
        test.write("admin","admin","Manager");
        int rslt=1;
        
        Scanner reader = new Scanner(System.in);
        
        while(rslt==1){
            System.out.println("Enter username:");
            String name = reader.next();
            System.out.println("Enter password:");
            String password = reader.next();
            String role = test.read(name,password);
            if(!role.equals("none")){
                test.accountOptions(name,role);
            }
            else{
                System.out.println("\nInvalid Account");
                System.out.println("To retry login, please press: 1");
                System.out.println("To exit, please press: 0");
                rslt = reader.nextInt();
                
            }
        }
    }
    
}
