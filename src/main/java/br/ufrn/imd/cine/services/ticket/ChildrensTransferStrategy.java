package br.ufrn.imd.cine.services.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufrn.imd.cine.model.ticket.ChildrensTicket;
import br.ufrn.imd.cine.model.ticket.ChildrensTransferData;
import br.ufrn.imd.cine.repositories.ChildrensTicketRepository;
import br.ufrn.imd.cineframework.models.ticket.AbstractTicket;
import br.ufrn.imd.cineframework.models.ticket.TransferData;
import br.ufrn.imd.cineframework.models.ticket.TransferStrategy;
import br.ufrn.imd.cineframework.models.user.User;
import br.ufrn.imd.cineframework.repositories.user.UserRepository;

@Component
public class ChildrensTransferStrategy implements TransferStrategy {

	@Autowired
	private ChildrensTicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public AbstractTicket transfer(TransferData data) {
		ChildrensTransferData simpleTransferData = (ChildrensTransferData) data;

		ChildrensTicket existingTicket = ticketRepository.findById(simpleTransferData.idTicket).orElseThrow(
				() -> new IllegalArgumentException("Ticket not found with id: " + simpleTransferData.idTicket));

		if (simpleTransferData.parentToTransfer != null && simpleTransferData.parentToTransfer.isEmpty()) {
			User parent = userRepository.findByUsername(simpleTransferData.parentToTransfer).orElseThrow(
					() -> new IllegalArgumentException("User not found with id: " + simpleTransferData.idTicket));

			existingTicket.setParent(parent);
		}

		if (simpleTransferData.childToTransfer != null && simpleTransferData.childToTransfer.isEmpty()) {
			User child = userRepository.findByUsername(simpleTransferData.childToTransfer).orElseThrow(
					() -> new IllegalArgumentException("User not found with id: " + simpleTransferData.idTicket));

			existingTicket.setChild(child);
			existingTicket.setAge(simpleTransferData.childAge);
		}

		return existingTicket;
	}

}
