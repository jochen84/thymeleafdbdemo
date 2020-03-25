package se.ec.johan.thymeleafdbdemo.dto;

import org.hibernate.annotations.SortComparator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UppdateAppUserForm {

    @Positive
    private int userId;
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 255)
    private String firstName;
    private String lastName;
    private String email;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
