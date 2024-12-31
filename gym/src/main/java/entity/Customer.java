package entity;

public class Customer extends Person {
    int age;
    private String email;
    private Integer idClass;

    public Customer(int id, String name, int age, String phone, String email, Integer idClass) {
        super(id, name, phone);
        this.age = age;
        this.email = email;
        this.idClass = idClass;
    }

    public Customer(String name, int age, String phone, String email, Integer idClass) {
        super(name, phone);
        this.age = age;
        this.email = email;
        this.idClass = idClass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdClass() {
        return idClass;
    }
    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }

    @Override
    public String toString() {
        return super.toString() + "Customer [age=" + age + ", email=" + email + "]";
    }

}
