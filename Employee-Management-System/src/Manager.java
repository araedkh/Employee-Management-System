
import java.io.Serializable;
public class Manager extends Person implements Serializable{
    public static final long serialVersionID = 5867;
    public static  Manager[]  man = new Manager[100];
    public static int size;
    public Manager(String name, String email, String pass, int id, int mobile,int status) {
        super(name, email, pass , id, mobile,status);
        man[size++]=this;        
    }
    public static void add(Manager m){
        man[size++] = m;
    }
    public static void remove(int i){
        for( ; i<size;i++){
            man[i] = man[i+1];
        }
        size--;                       
    }
    public static String getHolidayReport(){
        String msg="";        
        for(int i=0;i<size;i++){
            if(man[i].getHoliday()!=null)
                msg+= man[i].getHolidayDetails();
        }
        return msg;
    }
    public static int findManager(int id){
        for(int i=0;i< size ;i++)
            if(man[i].getId()==id)
                return i;                            
        return -1;
    }
    public static int login(int id, String pass){
        int i=findManager(id);
        if(i==-1)
            return -1;
        if(man[i].getPass().equals(pass))
            return i;
        return -1;
    }
    public static String getAllManagerReport(){
        String msg=">>>>>>>>>>>>>>>> Report about all Manager <<<<<<<<<<< ";
        for(int i=0;i<size;i++)
            msg+=  man[i].toString()+"\n";        
        return msg;
    }
    
}
