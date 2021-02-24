 
import java.io.Serializable;
public abstract class Person implements Serializable{ 
    
    public static final long serialVersionID = 97876;
    private String name,email,pass,checkin,checkout;
    private int id,mobile;
    private boolean active;  
    private Holiday holiday;
    private Holiday[] done_holidays;
    private int holiday_size=0;
    public Person(String name, String email, String pass, int id, int mobile,int status) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.checkin = "00:00";
        this.checkout ="00:00";
        this.id = id;
        this.mobile = mobile;     
        this.active = status==1;     
        done_holidays = new Holiday[10];
    }      
    public void setHolidayAccept(boolean accept){         
        if(holiday!=null ){
            holiday.setCheck(accept?1:2);                                      
            if(accept)
                done_holidays[holiday_size++]=holiday;                           
            holiday=null;
        }         
    }     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMobile() {
        return mobile;
    }
    public void setMobile(int mobile) {
        this.mobile = mobile;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    } 
    public String getCheckin() {
        return checkin;
    }    
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }
    public String getCheckout() {
        return checkout;
    }
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }             
    public boolean equals(Object obj) {          
        if (this.id != ((Person) obj).id) {
            return false;
        }
        return true;
    }      
    public Holiday getHoliday() {
        return holiday;
    }
    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }  
    public String getHolidayDetails(){         
        return "\nID: "+id+" || "+holiday.toString()+"\n";                     
    }
    public String getPersonHolidayReport(){     
        String msg="\n>>>>>>>>>>All accepted holiday<<<<<<<<<<\n";
        for(int i=0;i<holiday_size;i++)
            msg+=done_holidays[i].toString()+"\n";    
        if(holiday != null)
            msg+=holiday.toString()+"\n";    
        return msg;
    }
    public String toString() {
        return   "ID: " + id + " || Name: " + name + " || email: " + email + " || Phone: " + mobile 
                + " || Status: " + active+ " || Check in: " + checkin+ " || Check out: " + checkout  ;       
    }
}
