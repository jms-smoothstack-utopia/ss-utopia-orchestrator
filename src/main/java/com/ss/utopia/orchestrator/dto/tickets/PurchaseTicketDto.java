package com.ss.utopia.orchestrator.dto.tickets;

import com.ss.utopia.orchestrator.models.tickets.Ticket;
import com.ss.utopia.orchestrator.models.tickets.Ticket.TicketStatus;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseTicketDto {

  @NotNull
  private Long purchaserId;

  @NotNull
  private Long flightId;

  @NotEmpty
  private List<TicketItem> tickets;

}
