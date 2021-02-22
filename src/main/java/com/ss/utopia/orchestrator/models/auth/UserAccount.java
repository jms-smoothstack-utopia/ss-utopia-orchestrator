package com.ss.utopia.orchestrator.models.auth;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

  private UUID id;

  @NotBlank
  @Email
  private String email;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @NotBlank
  private String hashedPassword;

  @Builder.Default
  private boolean isConfirmed = false;

  @Builder.Default
  private UserRole userRole = UserRole.DEFAULT;

  private ZonedDateTime creationDateTime;

  private ZonedDateTime lastModifiedDateTime;

}
