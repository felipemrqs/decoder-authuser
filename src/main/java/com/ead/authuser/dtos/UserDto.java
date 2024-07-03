package com.ead.authuser.dtos;

import com.ead.authuser.constraints.UsernameContraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(UUID userId,
                      @UsernameContraint(groups = {UserView.RegistrationPost.class}) @Size(groups = {UserView.RegistrationPost.class}, min = 4, max = 50) @NotBlank(groups = {UserView.RegistrationPost.class}) @JsonView(UserView.RegistrationPost.class) String userName,
                      @NotBlank(groups = {UserView.RegistrationPost.class}) @Email(groups = {UserView.RegistrationPost.class}) @JsonView(UserView.RegistrationPost.class) String email,
                      @NotBlank(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class}) @Size(groups = {UserView.PasswordPut.class, UserView.RegistrationPost.class}, min = 6, max = 50) @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class}) String password,
                      @NotBlank(groups = {UserView.PasswordPut.class}) @Size(groups = {UserView.PasswordPut.class}, min = 6, max = 50) @JsonView(UserView.PasswordPut.class) String oldPassword,
                      @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class}) String fullName,
                      @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class}) String phoneNumber,
                      @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class}) String cpf,
                      @NotBlank(groups = {UserView.ImagePut.class}) @JsonView(UserView.ImagePut.class) String imageUrl) {
    public interface UserView {
        interface RegistrationPost {
        }

        interface UserPut {
        }

        interface PasswordPut {
        }

        interface ImagePut {
        }
    }
}
