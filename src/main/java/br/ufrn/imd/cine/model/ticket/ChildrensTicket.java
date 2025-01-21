package br.ufrn.imd.cine.model.ticket;

import java.time.LocalDateTime;
import java.util.Set;

import br.ufrn.imd.cineframework.models.combo.Combo;
import br.ufrn.imd.cineframework.models.session.Session;
import br.ufrn.imd.cineframework.models.ticket.AbstractTicket;
import br.ufrn.imd.cineframework.models.ticket.TicketType;
import br.ufrn.imd.cineframework.models.ticket.TransferStrategy;
import br.ufrn.imd.cineframework.models.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "childrens_ticket")
public class ChildrensTicket extends AbstractTicket {
	@Column(name = "seat")
	private String seat;

	@Column(name = "start_date")
	private LocalDateTime start;

	@Column(name = "end_date")
	private LocalDateTime end;

	@Column(name = "child_age")
	private Integer age;

	@Column(name = "id_child")
	private User child;

	@Column(name = "is_session")
	private Session session;

	@Column(name = "id_parent")
	private User parent;

	public ChildrensTicket() {
	}

	public ChildrensTicket(Double price, String token, Set<TicketType> ticketTypes, Set<Combo> combos,
			TransferStrategy tranferStrategy, String seat, LocalDateTime start, LocalDateTime end, Integer age,
			User child, Session session, User parent) {
		super(price, token, ticketTypes, combos, tranferStrategy);
		this.seat = seat;
		this.start = start;
		this.end = end;
		this.age = age;
		this.child = child;
		this.session = session;
		this.parent = parent;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User getChild() {
		return child;
	}

	public void setChild(User child) {
		this.child = child;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

}
