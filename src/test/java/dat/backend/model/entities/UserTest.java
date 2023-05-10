package dat.backend.model.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{

    User user1 = null;
    User user2 = null;
    @BeforeEach
    void setup()
    {
       user1 = new User(1, "John", "Doe", "test@email.com", "1234", "testvej 50", 12345678, 2, 2, 1111);
       user2 = new User(2, "martin", "smart", "test2@email.com", "12345678", "testvej 51", 87654321, 3, 3, 2222);
    }


    @Test
    void getId() throws SQLException
    {
        int expectedid = 1;
        int actualid = user1.getId();
        assertEquals(expectedid,actualid);
    }


    @Test
    void getFirst_name() throws SQLException
    {
        String expectedname = "John";
        String actualname = user1.getFirst_name();
        assertEquals(expectedname,actualname);
    }


    @Test
    void getLast_name() throws SQLException
    {
        String expectedname = "Doe";
        String actualname = user1.getLast_name();
        assertEquals(expectedname,actualname);
    }


    @Test
    void getEmail() throws SQLException
    {
        String expectedemail = "test@gmail.com";
        String actualemail = user1.getEmail();
        assertEquals(expectedemail,actualemail);
    }


    @Test
    void getAddress() throws SQLException
    {
        String expectedadress = "testvej 50";
        String actualadress = user1.getAddress();
        assertEquals(expectedadress,actualadress);
    }

    @Test
    void getPhone_number() throws SQLException
    {
     String expectednumber = "12345678";
     String actualnumber = String.valueOf(user1.getPhone_number());
     assertEquals(expectednumber,actualnumber);
    }

    @Test
    void getRole_id() throws SQLException
    {
        int expectedid = 2;
        int actualid = user1.getRole_id();
        assertEquals(expectedid,actualid);
    }

    @Test
    void getMembership_id() throws SQLException
    {
        int expectedid = 2;
        int actualid = user1.getMembership_id();
        assertEquals(expectedid,actualid);
    }


    @Test
    void getPassword() throws SQLException
    {
        String expectedpassword = "1234";
        String actualpassword = user1.getPassword();
        assertEquals(expectedpassword,actualpassword);
    }


    @Test
    void getZip() throws SQLException
    {
        int expectedzip = 1111;
        int actualzip = user1.getZip();
        assertEquals(expectedzip,actualzip);
    }


    @Test
    void testEquals()
    {
      User expecteduser = user1;
      User actualuser = user1;
      assertEquals(expecteduser,actualuser);
    }

    @Test
    void testHashCode()
    {
    }
}