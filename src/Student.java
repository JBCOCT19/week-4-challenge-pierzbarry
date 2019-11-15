public class Student extends Person {
    public Student() {
    }

    public Student(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString(){
        return ("\tStudent Id: " + this.getId() + "\tStudent Name: " + this.getName());
    }
}
