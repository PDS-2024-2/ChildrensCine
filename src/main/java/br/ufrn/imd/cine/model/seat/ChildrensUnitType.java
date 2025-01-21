package br.ufrn.imd.cine.model.seat;

import br.ufrn.imd.cineframework.models.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "childrens_unit_type")
public class ChildrensUnitType extends AbstractEntity {
	@Column(name = "price_increase")
	private Double priceIncrease;

	@Column(name = "code")
	private String code;

	@Column(name = "collective")
	private String collective;

	public ChildrensUnitType() {
	}

	public ChildrensUnitType(Double priceIncrease, String code, String collective) {
		super();
		this.priceIncrease = priceIncrease;
		this.code = code;
		this.collective = collective;
	}

	public Double getPriceIncrease() {
		return priceIncrease;
	}

	public void setPriceIncrease(Double priceIncrease) {
		this.priceIncrease = priceIncrease;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCollective() {
		return collective;
	}

	public void setCollective(String collective) {
		this.collective = collective;
	}

}
