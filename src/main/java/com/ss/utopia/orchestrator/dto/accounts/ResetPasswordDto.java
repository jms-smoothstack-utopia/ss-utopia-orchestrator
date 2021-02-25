package com.ss.utopia.orchestrator.dto.accounts;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordDto {
  @NotNull
  @NotBlank(message = "Email cannot be blank.")
  @Email(message = "Email must be valid.")
  private String email;
}