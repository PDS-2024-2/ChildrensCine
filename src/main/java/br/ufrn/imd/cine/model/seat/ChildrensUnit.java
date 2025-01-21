package br.ufrn.imd.cine.model.seat;

import br.ufrn.imd.cineframework.models.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "childrens_unit")
public class ChildrensUnit extends AbstractEntity {
	@NotNull
	@NotBlank
	@Column(name = "seat_code")
	private String code;

	@NotNull
	@NotBlank
	@Column(name = "accessible")
	private String accessible;

	@NotNull
	@Column(name = "childrens_unit_type_id")
	private ChildrensUnitType childrensUnitType;

	public ChildrensUnit() {

	}

	public ChildrensUnit(@NotNull @NotBlank String code, @NotNull @NotBlank String accessible,
			@NotNull ChildrensUnitType childrensUnitType) {
		super();
		this.code = code;
		this.accessible = accessible;
		this.childrensUnitType = childrensUnitType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccessible() {
		return accessible;
	}

	public void setAccessible(String accessible) {
		this.accessible = accessible;
	}

	public ChildrensUnitType getChildrensUnitType() {
		return childrensUnitType;
	}

	public void setChildrensUnitType(ChildrensUnitType childrensUnitType) {
		this.childrensUnitType = childrensUnitType;
	}

}
