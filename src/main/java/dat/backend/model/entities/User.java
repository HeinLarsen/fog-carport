package dat.backend.model.entities;

import java.util.Objects;

public class User
{
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String address;
    private int phone_number;
    private int role_id;
    private int membership_id;
    private int zip;





    public User(int id, String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip)
    {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
        this.role_id = role_id;
        this.membership_id = membership_id;
        this.zip = zip;

    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public int getRole_id() {
        return role_id;
    }

    public int getMembership_id() {
        return membership_id;
    }

    public String getPassword() {
        return password;
    }

    public int getZip() {
        return zip;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) && getRole_id() == user.getRole_id();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getEmail(), getPassword(), getRole_id());
    }

    @Override
    public String toString()
    {
        return "User{" +
                "brugerNavn='" + email + '\'' +
                ", kodeord='" + password + '\'' +
                ", rolle='" + role_id + '\'' +
                ", medlemskab='" + membership_id + '\'' +
                '}';
    }
}
