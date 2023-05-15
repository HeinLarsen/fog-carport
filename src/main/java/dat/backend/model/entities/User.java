package dat.backend.model.entities;

import java.util.Objects;

public class User
{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private int phoneNumber;
    private int roleId;
    private int membershipId;
    private int zip;





    public User(int id, String firstName, String lastName, String email, String password, String address, int phoneNumber, int zip)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.roleId = roleId;
        this.membershipId = membershipId;
        this.zip = zip;

    }

    public User(int id, String firstName, String lastName, String email, String password, String address, int phoneNumber, int roleId, int membershipId, int zip)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.roleId = roleId;
        this.membershipId = membershipId;
        this.zip = zip;

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getMembershipId() {
        return membershipId;
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
        return getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) && getRoleId() == user.getRoleId();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getEmail(), getPassword(), getRoleId());
    }

    @Override
    public String toString()
    {
        return "User{" +
                "brugerNavn='" + email + '\'' +
                ", kodeord='" + password + '\'' +
                ", rolle='" + roleId + '\'' +
                ", medlemskab='" + membershipId + '\'' +
                '}';
    }
}
