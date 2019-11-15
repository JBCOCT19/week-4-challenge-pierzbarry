public class Faculty extends Person {
    public Faculty() {
    }

    public Faculty(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString(){
        return ("\tFaculty Id: " + this.getId() + "\tFaculty Name: " + this.getName());
    }
}
