package br.ufrn.imd.cine.services.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.cine.model.layout.ChildrensLayout;
import br.ufrn.imd.cineframework.services.layout.LayoutService;

@Service
public class ChildrensLayoutService {

	@Autowired
	private ChildrensLayoutCreator simpleLayoutCreator;

	public ChildrensLayout createLayout(Object data) {
		LayoutService layoutService = new LayoutService(simpleLayoutCreator);
		ChildrensLayout simpleLayout = (ChildrensLayout) layoutService.createLayout(data);
		return simpleLayout;
	}
}
