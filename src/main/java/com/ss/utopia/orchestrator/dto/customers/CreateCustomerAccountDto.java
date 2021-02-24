package com.ss.utopia.orchestrator.dto.customers;

import com.ss.utopia.orchestrator.dto.accounts.CreateUserAccountDto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * For creating an account and customer record.
 * <p>
 * Can be broken down into the individual DTOs for account creation with the auth service and
 * customer profile/record creation with the customers service.
 * @see #getAccountDto()
 * @see #getRecordDto()
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerAccountDto {

  public static final String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*-_=+,.?])[A-Za-z\\d!@#$%^&*-_=+,.?]{10,128}$";
  public static final String REGEX_MSG = "Password must be between 10 and 128 characters,"
      + " contain at least one lowercase letter,"
      + " at least one uppercase letter,"
      + " at least one number,"
      + " and at least one special character from the following: !@#$%^&*-_=+,.?";

  @NotNull
  @NotBlank(message = "Email cannot be blank.")
  @Email(message = "Email must be valid.")
  private String email;

  @ToString.Exclude
  @NotNull
  @NotBlank(message = "Password cannot be blank.")
  @Size(min = 10, max = 128, message = "Length must be between 10 and 128 characters.")
  @Pattern(regexp = REGEX, message = REGEX_MSG)
  private String password;

  @NotBlank(message = "First name is mandatory")
  private String firstName;

  @NotBlank(message = "Last name is mandatory")
  private String lastName;

  @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Phone number must be in the form ###-###-####.")
  private String phoneNumber;

  @NotBlank(message = "Address line1 is mandatory")
  private String addrLine1;

  private String addrLine2;

  @NotBlank(message = "City is mandatory")
  private String city;

  @NotBlank(message = "State is mandatory")
  @Size(min = 2, max = 2, message = "State must consist of only 2 characters.")
  private String state;

  @NotBlank(message = "Zipcode is mandatory")
  @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$",
      message = "Zipcode does not meet expected format: '#####-####' or '#####'")
  private String zipcode;

  public CreateUserAccountDto getAccountDto() {
    return CreateUserAccountDto.builder()
        .email(email)
        .password(password)
        .build();
  }

  public CreateCustomerRecordDto getRecordDto() {
    return CreateCustomerRecordDto.builder()
        .email(email)
        .firstName(firstName)
        .lastName(lastName)
        .phoneNumber(phoneNumber)
        .addrLine1(addrLine1)
        .addrLine2(addrLine2)
        .city(city)
        .state(state)
        .zipcode(zipcode)
        .build();
  }
}
