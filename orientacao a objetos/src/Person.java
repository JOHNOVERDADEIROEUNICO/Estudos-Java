import java.time.OffsetDateTime;

public class Person {
    private String name;

    private int age;

    private int lastYearAgeInc = OffsetDateTime.now().getYear();

    public Person(){

    }

    public Person(String nome, int idade){
        name = nome;
        age = idade;
    }

    public void fazerAniversario(){
        if(lastYearAgeInc >= OffsetDateTime.now().getYear()) return;

        age ++;
        lastYearAgeInc = OffsetDateTime.now().getYear();
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setName(String nome){
        name = nome;
    }

    public void setAge(int idade){
        age = idade;
    }
}
