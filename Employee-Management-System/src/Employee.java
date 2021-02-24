 
import java.io.Serializable;
public class Employee extends Person  implements Serializable{
    public static final long serialVersionID = 4568;
    public static Employee[] emp = new Employee[100];
    public static int size=0;
    private int type;
    public Employee(String name, String email, String pass, int id, int mobile , int type,int status) {
        super( name,  email,  pass,   id,  mobile, status);
        this.type = type;
        emp[size++]=this;
    } 
    public void promoteToManager(){                
        Manager temp = new Manager(this.getName(),this.getEmail(),this.getPass(),this.getId(),this.getMobile(),this.isActive()?1:0);        
        temp.setCheckin(this.getCheckin());
        temp.setCheckout(this.getCheckout());
        remove(findEmployee(this.getId()));
        Manager.add(temp);        
    }    
    public static void remove(int i){         
        for( ; i<size;i++){
            emp[i] = emp[i+1];
        }
        size--;              
    }    
    public static int findEmployee(int id){
        for(int i=0;i< size ;i++)
            if(emp[i].getId()==id)
                return i;                            
        return -1;
    }    
    public static String getHolidayReport(){
        String msg="";        
        for(int i=0;i<size;i++){
            if(emp[i].getHoliday()!=null)
                msg+= emp[i].getHolidayDetails();
        }
        return msg;
    }
    public static int login(int id, String pass){
        int i=findEmployee(id);
        if(i==-1)
            return -1;
        if(emp[i].getPass().equals(pass))
            return i;
        return -1;
    }
    public String toString() {
        return super.toString() + "Emp Type:" + type ;
    }
    public static String getAllEmployeeReport(){
        String msg=">>>>>>>>>>>>>>>> Report about all Employee <<<<<<<<<<< ";
        for(int i=0;i< size ;i++)
            msg+= emp[i].toString()+"\n";        
        return msg;
    }
}
