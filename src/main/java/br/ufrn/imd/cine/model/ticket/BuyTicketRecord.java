package br.ufrn.imd.cine.model.ticket;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BuyTicketRecord(
		@NotNull
		Long type,
		@NotNull
		String seat,
		@NotNull
		@Positive
		Double price,
		@NotNull
		String child,
		@NotNull
		Long idSession,
		@NotNull
		Integer childAge) {
}
