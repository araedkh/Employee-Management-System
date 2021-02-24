
import java.io.Serializable; 
public class Holiday  implements Serializable{
    public static final long serialVersionID = 6576;
    private String name,reason,details,date;
    private int check;
    public Holiday(String name, String reason, String details, String date) {
        this.name = name;
        this.reason = reason;
        this.details = details;
        this.date = date;
        this.check = 0;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getCheck() {
        return check;
    }
    public void setCheck(int check) {
        this.check = check;
    }   
    public boolean equals(Object obj) {         
        final Holiday other = (Holiday) obj;
        if (! this.name.equals(other.name)) {
            return false;
        }
        if (!this.date.equals(other.date)) {
            return false;
        }
        return true;
    }    
    public String toString() {
        return   "Name: " + name + " || Reason: " + reason + " || Details: " + details + " || Date: " + date + " || Check: " + check   ;
    }
}
