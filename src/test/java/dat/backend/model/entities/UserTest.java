package dat.backend.model.entities;

import dat.backend.model.exceptions.DatabaseException;
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
    void getId()
    {
        int expectedId = 1;
        int actualId = user1.getId();
        assertEquals(expectedId,actualId);
    }


    @Test
    void getFirstName()
    {
        String expectedName = "John";
        String actualName = user1.getFirstName();
        assertEquals(expectedName,actualName);
    }


    @Test
    void getLastName()
    {
        String expectedName = "Doe";
        String actualName = user1.getLastName();
        assertEquals(expectedName,actualName);
    }


    @Test
    void getEmail()
    {
        String expectedEmail = "test@email.com";
        String actualEmail = user1.getEmail();
        assertEquals(expectedEmail,actualEmail);
    }


    @Test
    void getAddress()
    {
        String expectedAdress = "testvej 50";
        String actualAdress = user1.getAddress();
        assertEquals(expectedAdress,actualAdress);
    }

    @Test
    void getPhoneNumber()
    {
     String expectedNumber = "12345678";
     String actualNumber = String.valueOf(user1.getPhoneNumber());
     assertEquals(expectedNumber,actualNumber);
    }

    @Test
    void getRoleId()
    {
        int expectedId = 2;
        int actualId = user1.getRoleId();
        assertEquals(expectedId,actualId);
    }

    @Test
    void getMembershipId()
    {
        int expectedId = 2;
        int actualId = user1.getMembershipId();
        assertEquals(expectedId,actualId);
    }


    @Test
    void getPassword()
    {
        String expectedpassword = "1234";
        String actualpassword = user1.getPassword();
        assertEquals(expectedpassword,actualpassword);
    }


    @Test
    void getZip()
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