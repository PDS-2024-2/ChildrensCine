package br.ufrn.imd.cine.model.ticket;

import br.ufrn.imd.cineframework.models.ticket.TransferData;

public class ChildrensTransferData implements TransferData {
	public Long idTicket;
	public String parentToTransfer;
	public String childToTransfer;
	public Integer childAge;
}
