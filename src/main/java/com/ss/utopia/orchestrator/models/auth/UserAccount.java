package com.ss.utopia.orchestrator.models.auth;

import java.time.ZonedDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount {

  private Long id;

  @NotBlank
  @Email
  private String email;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @NotBlank
  private String hashedPassword;

  @NotNull
  @EqualsAndHashCode.Exclude
  private ZonedDateTime creationDateTime = ZonedDateTime.now();

  private boolean isConfirmed = false;

  private UserRole userRole = UserRole.DEFAULT;
}
