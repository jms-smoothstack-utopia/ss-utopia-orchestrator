package com.ss.utopia.orchestrator.models.tickets;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

  private Long id;

  @NotNull
  private Long flightId;

  @NotNull
  private Long purchaserId;

  @NotBlank
  private String passengerName;

  @NotBlank
  private String seatClass;

  @NotBlank
  private String seatNumber;

  @NotNull
  private TicketStatus status;

  public String getPassenger() {
    return null;
  }

  public Long getCustomerId() {
    return null;
  }

  public enum TicketStatus {
    PURCHASED,
    CHECKED_IN,
    CANCELLED,
    REFUNDED;

    TicketStatus fromString(String ticketStatus) {
      return TicketStatus.valueOf(ticketStatus.toUpperCase());
    }
  }
}
