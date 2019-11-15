public class Course {
    private int id;
    private int teacherId;
    private String name;
    private String description;

    public Course() {
    }

    public Course(int id, int teacherId, String name, String description) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString(){
        return ("\tCourse Id: " + this.getId() + "\tCourse Name: " + this.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
