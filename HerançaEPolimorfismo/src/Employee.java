public class Employee {

    private String code;

    private String name;

    private String address;

    private int age;

    private double salary;


    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }
    
    public int getAge(){
        return age;
    }

    public double getSalary(){
        return salary;
    }

    public void setCode(String Code){
        code = Code;
    }

    public void setName(String Name){
        name = Name;
    }

    public void setAddress(String Address){
        address = Address;
    }

    public void setAge(int Age){
        age = Age;
    }

    public void setSalary(double Salary){
        salary = Salary;
    }
}
