package dto;

public class TrainerDTO {
    private int id;
    private String name;
    private String specialization;
    private String phone;
    private String className;
    public TrainerDTO() {

    }
    public TrainerDTO(int id, String name, String specialization, String phone, String className) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.className = className;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

}
