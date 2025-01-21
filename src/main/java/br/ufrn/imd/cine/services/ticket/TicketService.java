package br.ufrn.imd.cine.services.ticket;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.cine.model.ticket.BuyTicketRecord;
import br.ufrn.imd.cine.model.ticket.ChildrensTicket;
import br.ufrn.imd.cine.model.ticket.ChildrensTransferData;
import br.ufrn.imd.cine.repositories.ChildrensTicketRepository;
import br.ufrn.imd.cine.repositories.TicketTypeRepository;
import br.ufrn.imd.cineframework.models.session.Session;
import br.ufrn.imd.cineframework.models.user.User;
import br.ufrn.imd.cineframework.repositories.GenericRepository;
import br.ufrn.imd.cineframework.repositories.session.SessionRepository;
import br.ufrn.imd.cineframework.repositories.user.UserRepository;
import br.ufrn.imd.cineframework.services.GenericService;

@Service
public class TicketService extends GenericService<ChildrensTicket> {
	private static final long serialVersionUID = 1L;

	@Override
	public GenericRepository<ChildrensTicket> getRepository() {
		return ticketRepository;
	}

	@Autowired
	private ChildrensTicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private TicketTypeRepository ticketTypeRepository;

	@Autowired
	private ChildrensTransferStrategy simpleTransferStrategy;

	public List<ChildrensTicket> findByOwner(User userLogged) {
		if (userRepository.findById(userLogged.getId()).isEmpty()) {
			throw new IllegalArgumentException("User not found");
		} else {
			return ticketRepository.findByParent(userRepository.findById(userLogged.getId()).get());
		}
	}

	// buy
	public ChildrensTicket buyTicket(BuyTicketRecord buyTicket, User userLogged) {

		Session s = sessionRepository.findById(buyTicket.idSession())
				.orElseThrow(() -> new IllegalArgumentException("Session not found"));

		if (s.getAvailableSeats() == 0) {
			throw new IllegalArgumentException("Session full");
		}

		ChildrensTicket t = new ChildrensTicket();

		t.setSession(s);
		t.setTicketTypes(Set.of(ticketTypeRepository.findById(buyTicket.type()).get()));
		t.setSeat(buyTicket.seat());
		t.setPrice(buyTicket.price());
		t.setParent(userLogged);
		t.setChild(userRepository.findByUsername(buyTicket.child()).get());
		t.setAge(buyTicket.childAge());
		t.setToken(UUID.randomUUID().toString());

		s.setAvailableSeats(s.getAvailableSeats() - 1);

		sessionRepository.save(s);

		return insert(t);
	}

	public ChildrensTicket transferTicket(ChildrensTransferData transferTicket, User userLogged) {

		ChildrensTicket existingTicket = ticketRepository.findById(transferTicket.idTicket).orElseThrow(
				() -> new IllegalArgumentException("Ticket not found with id: " + transferTicket.idTicket));

		if (!existingTicket.getParent().equals(userLogged)) {
			throw new IllegalArgumentException(
					"Logged user are not owner from the ticket with id " + transferTicket.idTicket);
		}

		existingTicket.setTranferStrategy(simpleTransferStrategy);
		return update((ChildrensTicket) existingTicket.transfer(transferTicket));
	}

	public List<ChildrensTicket> findBySession(Long sessionId) {
		Session s = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session not found"));

		return ticketRepository.findBySession(s);
	}

}
