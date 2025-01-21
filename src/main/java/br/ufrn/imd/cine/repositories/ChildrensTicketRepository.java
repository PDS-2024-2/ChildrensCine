package br.ufrn.imd.cine.repositories;

import java.util.List;

import br.ufrn.imd.cine.model.ticket.ChildrensTicket;
import br.ufrn.imd.cineframework.models.session.Session;
import br.ufrn.imd.cineframework.models.user.User;
import br.ufrn.imd.cineframework.repositories.GenericRepository;

public interface ChildrensTicketRepository extends GenericRepository<ChildrensTicket> {
	List<ChildrensTicket> findByParent(User parent);

	List<ChildrensTicket> findByChild(User child);

	List<ChildrensTicket> findBySession(Session session);
}
