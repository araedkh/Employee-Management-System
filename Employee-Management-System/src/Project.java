
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner; 
public class Project implements Serializable{
    

    public static Scanner input=new Scanner(System.in);
    public static int  adminID;
    public static String username,password;    
    public static void main(String[] args)  {                    
        int c,id;
        String pass;
        try{
            ObjectInputStream in = new ObjectInputStream (new FileInputStream("data.co"));                               
            Manager.man=  (Manager[])in.readObject(); 
            Manager.size = in.readInt();
            Employee.emp = (Employee[])in.readObject();
            Employee.size = in.readInt();
            adminID = in.readInt();             
            username = (String)in.readObject();                        
            password = (String)in.readObject();
        }catch(FileNotFoundException ex){
            System.out.println("No file avilable\nEnter the first data to save it\nEnter the ID for admin: ");
            adminID = input.nextInt();
            System.out.println("Enter the password for admin: ");
            password =input.next ();
            System.out.println("Enter the name for admin: ");
            username =input.next ();                        
            Project.saveUpdate();
        } catch (ClassNotFoundException | IOException ex) { 
            System.out.println("Error: "+ex);
            System.exit(0);
        }
        do{    
            System.out.println(">>>>>>> Login screen <<<<<<<<<\n1- Login as Admin\n2- Login as Manager\n3- Login as Employee\n4- Exit");        
            c = input.nextInt();       
            if(c == 1){
                System.out.println("Enter ID:");
                id = input.nextInt();
                System.out.println("Enter Password:");
                pass = input.next();  
                admin(id,pass);
            }else if(c==2){
                System.out.println("Enter ID:");
                id = input.nextInt();
                System.out.println("Enter Password:");
                pass = input.next();
                manager(id,pass);
            }else if(c==3){
                System.out.println("Enter ID:");
                id = input.nextInt();
                System.out.println("Enter Password:");
                pass = input.next();
                employee(id,pass);
            }else if(c==4){               
                System.exit(0);                 
            }else{
                System.out.println("Error Input!");
            }             
            input.nextLine();            
        }while(true);
    }     
    public static void saveUpdate(){
        try{
            ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("data.co"));               
            out.writeObject(Manager.man);
            out.writeInt(Manager.size);
            out.writeObject(Employee.emp);
            out.writeInt(Employee.size);
            out.writeInt(adminID); 
            out.writeObject(username);
            out.writeObject(password);
            out.close(); 
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
    public static void admin(int id,String pass){          
        if(id != adminID || !pass.equals(password)){
            System.out.println("Error ID or password");
            return;
        }
        int i,mobile,status,index,c;
        String name,email,password;
        while(true){            
            System.out.println("\n>>>>>>>>>>>> Admin <<<<<<<<<<\n1- Add Manager\n2- Update Manager"
                    + "\n3- Delete Manager\n4- Search about Manager \n5- Report about Manager"
                    + "\n6- Report about all Manager \n7- Holiday requests."
                    + "\n8- Deactive & Active Manager \n9- Exit\n");
            c = input.nextInt();
            if(c==1){
                System.out.println(">>>>>>>>>Add Manager<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                if(Manager.findManager(i)!=-1){
                    System.out.println("This number is used, can't be added twice !");                            
                    continue;
                }
                System.out.println("Enter name");
                name= input.next ();
                System.out.println("Enter password");
                password= input.next ();                        
                System.out.println("Enter email");
                email= input.next ();
                System.out.println("Enter mobile");
                mobile = input.nextInt();
                System.out.println("Enter status (1)<active> or (0)<disactive>");
                status = input.nextInt();                        
                new Manager(name,email,password,i,mobile,status);
                System.out.println("The Manager has been successfully added ^_^");
            }else if(c==2){
                System.out.println(">>>>>>>>>Udate Manager<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Manager.findManager(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                System.out.println("New password");
                password= input.next (); 
                Manager.man[index].setPass(password);
                System.out.println("The Manager has been successfully updated ^_^");
            }else if(c==3){
                System.out.println(">>>>>>>>>Delete Manager<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Manager.findManager(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                Manager.remove(index);
                System.out.println("The Manager has been successfully deleted ^_^");
            }else if(c==4){
                System.out.println(">>>>>>>>>Serch about Manager<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Manager.findManager(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                System.out.println("Name: "+Manager.man[index].getName()+" || Status "+Manager.man[index].isActive());
            }else if(c==5){
                System.out.println(">>>>>>>>>Report about Manager<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Manager.findManager(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                System.out.println(Manager.man[index].toString());
            }else if(c==6){
                System.out.println(Manager.getAllManagerReport());
            }else if(c==7){
                int choice,indexM,indexE;
                while(true){
                    
                    System.out.println(">>>>>>>>>Holidays Request<<<<<<<<<\n1- View Holiday requests"
                            + "\n2- Accept the Holiday\n3- Rejected the Holiday\n4- Exit");
                    choice = input.nextInt();                                                                       
                    if(choice==1){
                        System.out.println(">>>>>>>>>>> View Holiday requests<<<<<<<<<<");                         
                        System.out.print(Manager.getHolidayReport());                                               
                        System.out.print(Employee.getHolidayReport());                                                                        
                    }else if(choice==2 ){
                        Person user=null ;
                        System.out.println("Enter id");
                        id = input.nextInt();
                        indexM = Manager.findManager(id);
                        indexE = Employee.findEmployee(id);
                        if(indexM==-1 && indexE==-1){
                            System.out.println("This number is not used");                            
                            continue;
                        }else if(indexM!=-1){
                            user = Manager.man[indexM];                        
                        }else if(indexE!=-1){
                            user = Employee.emp[indexE];
                        }                
                        user.setHolidayAccept(true);
                        System.out.println( " Approved successfully" );
                    }
                    else if( choice==3){
                        Person user=null ;
                        System.out.println("Enter id");
                        id = input.nextInt();
                        indexM = Manager.findManager(id);
                        indexE = Employee.findEmployee(id);
                        if(indexM==-1 && indexE==-1){
                            System.out.println("This number is not used");                            
                            continue;
                        }else if(indexM!=-1){
                            user = Manager.man[indexM];                        
                        }else if(indexE!=-1){
                            user = Employee.emp[indexE];
                        }                                                         
                        user.setHolidayAccept(false);                                                                         
                        System.out.println(" the request is rejected");
                    }else if(choice==4){
                        break;
                    }else{
                        System.out.println("Error choice !");
                    }         
                    Project.saveUpdate();
                }
            }else if(c==8){
                System.out.println(">>>>>>>>>Deactive & Active Manager<<<<<<<<<");
                System.out.println("1- Activate.\n2- Deactivate");                    
                int choice = input.nextInt();
                System.out.println(">>>>>>>>>>>> "+(choice==1?"Activate":"Deactivate")+" Manager <<<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Manager.findManager(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                Manager.man[index].setActive(choice==1);
                System.out.println("The account has been "+(choice==1?"Activated":"Deactivated"));                
            }else if(c==9){
                return;
            }else{
                System.out.println("Error input !");     
            }   
            Project.saveUpdate();
        }                 
    }     
    public static void holiday(Person p){
        String name="",reson="",details="",date="";        
        do{
            System.out.println(">>>> Holiday <<<<\n1- My Holiday\n2- Create Holiday\n3- Exit");               
            int c = input.nextInt(); input.nextLine();
            if(c==1){
                System.out.println(p.getPersonHolidayReport());
            }else if(c==2){
                System.out.println("Enter name:");
                name = input.nextLine();
                System.out.println("Enter reson:");
                reson = input.nextLine();                
                System.out.println("Enter details:");
                details = input.nextLine();
                System.out.println("Enter date:");
                date = input.nextLine();
                p.setHoliday(new Holiday(name,reson,details,date));
                System.out.println("Added sussefully");
            }else if(c==3){
                return;
            }else{
                System.out.println("Error input!");                 
            }
            Project.saveUpdate();
        }while(true);
    }
    public static void attendence(Person p){
        while(true){
            System.out.println(">>>> Holiday <<<<\n1- Time of attendence\n2- Time to leave\n3- Exit");        
            int c = input.nextInt();
            String time="";
            if(c==1){
                System.out.println(">>>>>>>>>> Time of attendence <<<<<<<<<\nEnter time:");
                time = input.next();
                p.setCheckin(time);
            }else if(c==2){
                System.out.println(">>>>>>>>>> Time of leave <<<<<<<<<\nEnter time:");
                time = input.next();
                p.setCheckout(time);
            }
            else if(c==3)
                return;               
            else 
                System.out.println("Error input!");     
            Project.saveUpdate();
        }
    }
    public static void manager(int id ,String pass){          
        int index =Manager.login(id, pass) ;
        if(index==-1){
            System.out.println("Error ID or password");
            return;
        }                 
        Manager user = Manager.man[index];
        if(!user.isActive()){
            System.out.println("The account isn't active !");
            return;
        }
        while(true){
            int i,mobile,status;
            String name,email,password;
            System.out.println("\n>>>>>>>>>>>> Manager <<<<<<<<<<\n1- Add Employee\n2- Update Employee "
                    + "\n3- Delete Employee\n4- Search about Employee \n5- Report about Employee"
                    + "\n6- Report about all Employee\n7- Deactive & Active Employee "
                    + "\n8- Promote an employee to manager\n9- Attendance\n10- Holiday\n11- Exit\n");                
            int c = input.nextInt();
            if(c==1){
                System.out.println(">>>>>>>>>Add Employee<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                if(Manager.findManager(i)!=-1){
                    System.out.println("This number is used, can't be added twice !");                            
                    continue;
                }
                System.out.println("Enter name");
                name= input.next ();
                System.out.println("Enter password");
                password= input.next ();                        
                System.out.println("Enter email");
                email= input.next ();
                System.out.println("Enter mobile");
                mobile = input.nextInt();
                System.out.println("Enter type of employee (1)<full-timme> or (2)<part-timme>");
                int type = input.nextInt();  
                System.out.println("Enter status (1)<active> or (0)<disactive>");
                status = input.nextInt();  
                new Employee(name,email,password,i,mobile,status,type);
                System.out.println("The Manager has been successfully added ^_^");
            }else if(c==2){
                System.out.println(">>>>>>>>>Udate Employee<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Employee.findEmployee(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                System.out.println("New password");
                password= input.next (); 
                Employee.emp[index].setPass(password);
                System.out.println("The Manager has been successfully updated ^_^");
            }else if(c==3){
                System.out.println(">>>>>>>>>Delete Employee<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Employee.findEmployee(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                Employee.remove(index);
                System.out.println("The Manager has been successfully deleted ^_^");
            }else if(c==4){
                System.out.println(">>>>>>>>>Serch about Employee<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Employee.findEmployee(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                System.out.println("Name: "+Employee.emp[index].getName()+" || Status "+Employee.emp[index].isActive());
            }else if(c==5){
                System.out.println(">>>>>>>>>Report about Employee<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Employee.findEmployee(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                System.out.println(Employee.emp[index].toString());
            }else if(c==6){
                System.out.println(Employee.getAllEmployeeReport());
            }else if(c==7){
                System.out.println(">>>>>>>>>Deactive & Active Manager<<<<<<<<<\n"
                    + "1- Activate.\n2- Deactivate\n");                         
                int choice = input.nextInt();
                System.out.println(">>>>>>>>>>>> "+(choice==1?"Activate":"Deactivate")+" Manager <<<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Employee.findEmployee(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                Employee.emp[index].setActive(choice==1);
                System.out.println("The account has been "+(choice==1?"Activated":"Deactivated"));
            }else if(c==8){
                System.out.println(">>>>>>>>>Promote an Employee to Manager<<<<<<<<<");
                System.out.println("Enter id");
                i = input.nextInt();
                index = Employee.findEmployee(i);
                if(index==-1){
                    System.out.println("This number is not used");                            
                    continue;
                }
                Employee.emp[index].promoteToManager();
                System.out.println("The cuurent employee ("+i+") has the authority of a manager");
            }else if(c==9){
                attendence(user);
            }else if(c==10){
                holiday(user); 
            }else if(c==11){
                return;
            }else{
                System.out.println("Error input !");        
            }          
            Project.saveUpdate();
        }         
    }
    public static void employee(int id ,String pass){          
        int index =Employee.login(id, pass) ;
        if(index==-1){
            System.out.println("Error ID or password");
            return;
        }                 
        Employee user = Employee.emp[index];
        if(!user.isActive()){
            System.out.println("The account isn't active !");
            return;
        }        
        while(true){
            System.out.println("\n1- Change Password\n2- Attendance\n3- Holiday\n4- Exit\n");
            int c = input.nextInt();
            if(c==1){
                System.out.println(">>>>>>>> Change Passwprd <<<<<<<<\nEnter new Passorwd:");
                String newPass = input.next();
                user.setPass(newPass);        
            }else if(c==2){
                attendence(user);
            }else if(c==3){
                holiday(user);
            }else if(c==4){
                return;   
            }else{
                System.out.println("Error input !");                 
            } 
            Project.saveUpdate();
        }         
    }         
}
                   





