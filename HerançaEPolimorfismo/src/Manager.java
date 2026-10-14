public class Manager extends Employee {
    private String login;

    private String password;


    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public void setLogin(String Login){
        login = Login;
    }

    public void setPassword(String Password){
        password = Password;
    }

    public void setSalary(double Commission, double Salary ){
        var de = Commission / 100; 
        salary = (Salary * de) + Salary;
    }
}
