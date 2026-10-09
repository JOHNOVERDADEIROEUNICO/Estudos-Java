public class AppObject {
    public static void main(String[] args) throws Exception {
        var male = new Person();

        male.setAge(21);
        male.setName("John");

        System.out.println("male name: " + male.getName() + "\nmale age: " + male.getAge());
    }
}
