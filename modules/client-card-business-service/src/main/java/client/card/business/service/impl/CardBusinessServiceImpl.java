package client.card.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import client.card.business.dto.CardDto;
import client.card.business.service.CardBusinessService;

@Component(
		service = CardBusinessService.class
	)
public class CardBusinessServiceImpl implements CardBusinessService{

	@Override
	public List<CardDto> getCardByClientId(long clientId) {
		List<CardDto> list = new ArrayList<CardDto>();
		
		//Consumir el API con credenciales oAuth2 diferentes. 
		//clientId
		// {{ServerLiferay}}/o/c/cards/?filter=r_clientCard_c_clientId eq '34228'&fields=id,numberCard,typeCard,yearExpiration,monthExpiration
		
		return list;
	}

}
