package one.model.entity;


public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private RoleType role;

    private User() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public static class Builder{
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder id(int id){
            user.id = id;
            return this;
        }
        public Builder name(String name){
            user.name = name;
            return this;
        }
        public Builder surname(String surname){
            user.surname = surname;
            return this;
        }
        public Builder email(String email){
            user.email = email;
            return this;
        }
        public Builder password(String password){
            user.password = password;
            return this;
        }
        public Builder role(RoleType role){
            user.role = role;
            return this;
        }
        public User build(){
            return user;
        }

    }
}
