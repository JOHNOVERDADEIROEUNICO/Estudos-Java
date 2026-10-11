public class AppObject {
    public static void main(String[] args) throws Exception {
        //Perceba que o java aceita sobrecarga de construtores.
        var female = new Person();
        var male = new Person("John", 21);

        female.setAge(21);
        female.setName("Ana");

        male.fazerAniversario();
        female.fazerAniversario();

        System.out.println("male name: " + male.getName() + "\nmale age: " + male.getAge());
        System.out.println("female name: " + female.getName() + "\nfemale age: " + female.getAge());
    }
}
