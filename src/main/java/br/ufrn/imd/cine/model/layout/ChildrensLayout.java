package br.ufrn.imd.cine.model.layout;

import java.util.List;

import br.ufrn.imd.cine.model.seat.ChildrensUnit;
import br.ufrn.imd.cineframework.models.layout.AbstractLayout;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "childrens_layout")
public class ChildrensLayout extends AbstractLayout {

	@Column(name = "capacity")
	private Integer capacity;

	@NotNull
	@NotEmpty
	@ManyToMany
	@JoinTable(name = "layout_has_seat", joinColumns = @JoinColumn(name = "layout_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
	private List<ChildrensUnit> seats;

	public ChildrensLayout() {
	}

	public ChildrensLayout(Double size, Integer capacity, @NotNull @NotEmpty List<ChildrensUnit> seats) {
		super(size);
		this.capacity = capacity;
		this.seats = seats;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<ChildrensUnit> getSeats() {
		return seats;
	}

	public void setSeats(List<ChildrensUnit> seats) {
		this.seats = seats;
	}

}
