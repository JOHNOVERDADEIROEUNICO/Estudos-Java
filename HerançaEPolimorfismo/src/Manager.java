public class Manager extends Employee {
    private String login;

    private String password;

    private double commission;

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public double getCommission(){
        return commission;
    }

    public void setLogin(String Login){
        login = Login;
    }

    public void setPassword(String Password){
        password = Password;
    }

    public void setCommission(double Commission ){
        commission = Commission;
    }
}
