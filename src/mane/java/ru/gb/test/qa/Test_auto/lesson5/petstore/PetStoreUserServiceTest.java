package ru.gb.test.qa.Test_auto.lesson5.petstore;

import jdk.jfr.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PetStoreUserServiceTest {
    @Autowired
    private RequestSpecification requestSpecification;

    public PetStoreUserServiceTest(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    @Test
    void saveUserAndGetByUserNameTest() {

        //pre-condition - create user
        PetStoreUserDto userDto = PetStoreUserDto.builder()
                .firstName("firstName")
                .id(100)
                .lastName("lastName")
                .username("userName")
                .email("email@email.com")
                .password("qwerty")
                .phone("phone")
                .userStatus(5)
                .build();

        // step 1 - post user
        requestSpecification
                .body(userDto)
                .post(EndPoints.USER.getUrl())
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.OK.value());

        // step - 2 get user by username
        PetStoreUserDto userFromService = requestSpecification
                .get(EndPoints.USER.getUrl() + "/" + userDto.getUsername())
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(PetStoreUserDto.class);

        //assert response
        Assertions.assertThat(userFromService).isEqualTo(userDto);
    }

    @Test
    void deleteUserTest() {

        //pre-condition - create user
        PetStoreUserDto userDto = PetStoreUserDto.builder()
                .firstName("firstName")
                .id(200)
                .lastName("lastName")
                .username("userDelete")
                .email("email@email.com")
                .password("qwerty")
                .phone("phone")
                .userStatus(5)
                .build();

        //post user
        requestSpecification
                .body(userDto)
                .post(EndPoints.USER.getUrl())
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.OK.value());

        //delete user by username
        requestSpecification
                .delete(EndPoints.USER.getUrl() + "/" + userDto.getUsername())
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.OK.value());

        //get user again and assert that response code is 404
        requestSpecification
                .get(EndPoints.USER.getUrl() + "/" + userDto.getUsername())
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.NOT_FOUND.value());

    }

    @Test

    void updateUserTest() {  
        
        //pre-condition - create user
        PetStoreUserDto userDto = PetStoreUserDto.builder()
                .firstName("firstName")
                .id(100)
                .lastName("lastName")
                .username("userName")
                .email("email@email.com")
                .password("qwerty")
                .phone("phone")
                .userStatus(5)
                .build();
        
        //update user
       requestSpecification
           id=
           .body(new UserDTO)
                .post(/api/users)
                .then()
                .statusCode()
                .assertThat()
                .body()
                .exctract()
                .response()
                .body()
                .path();
        
        
    }




}
