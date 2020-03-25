package se.ec.johan.thymeleafdbdemo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateAppUserForm {

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 255)
    private String firstName;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 255)
    private String lastName;

    @NotBlank(message = "You need to define a password")
    @Pattern(regexp = "^(?=.*\\d).{4,8}$", message = "Password must be between 4 and 8 digits long and include at least one numeric digit.")
    private String password;

    @NotBlank(message = "Need to validate your password")
    private String passwordConfirm;

    @NotBlank(message = "You need to define an email")
    @Pattern(regexp = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$", message = "Entered email is not valid")
    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
