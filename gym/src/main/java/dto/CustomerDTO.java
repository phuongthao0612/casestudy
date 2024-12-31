package dto;

public class CustomerDTO {
    private int id;
    private String name;
    private int age;
    private String phone;
    private String email;
    private String className;
    public CustomerDTO() {

    }



    public CustomerDTO(int id, String name, int age, String phone, String email, String className) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

}
