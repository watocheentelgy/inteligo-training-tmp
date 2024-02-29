package client.card.business.service;

import java.util.List;

import client.card.business.dto.CardDto;

public interface CardBusinessService {
	public List<CardDto> getCardByClientId(long clientId);
}
