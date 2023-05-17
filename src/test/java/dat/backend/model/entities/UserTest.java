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
        int expectedId = 1;
        int actualId = user1.getId();
        assertEquals(expectedId,actualId);
    }


    @Test
    void getFirstName() throws SQLException
    {
        String expectedName = "John";
        String actualName = user1.getFirstName();
        assertEquals(expectedName,actualName);
    }


    @Test
    void getLastName() throws SQLException
    {
        String expectedName = "Doe";
        String actualName = user1.getLastName();
        assertEquals(expectedName,actualName);
    }


    @Test
    void getEmail() throws SQLException
    {
        String expectedEmail = "test@email.com";
        String actualEmail = user1.getEmail();
        assertEquals(expectedEmail,actualEmail);
    }


    @Test
    void getAddress() throws SQLException
    {
        String expectedAdress = "testvej 50";
        String actualAdress = user1.getAddress();
        assertEquals(expectedAdress,actualAdress);
    }

    @Test
    void getPhoneNumber() throws SQLException
    {
     String expectedNumber = "12345678";
     String actualNumber = String.valueOf(user1.getPhoneNumber());
     assertEquals(expectedNumber,actualNumber);
    }

    @Test
    void getRoleId() throws SQLException
    {
        int expectedId = 2;
        int actualId = user1.getRoleId();
        assertEquals(expectedId,actualId);
    }

    @Test
    void getMembershipId() throws SQLException
    {
        int expectedId = 2;
        int actualId = user1.getMembershipId();
        assertEquals(expectedId,actualId);
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
        int expectedZip = 1111;
        int actualZip = user1.getZip();
        assertEquals(expectedZip,actualZip);
    }


    @Test
    void testEquals()
    {
      User expectedUser = user1;
      User actualUser = user1;
      assertEquals(expectedUser,actualUser);
    }


}