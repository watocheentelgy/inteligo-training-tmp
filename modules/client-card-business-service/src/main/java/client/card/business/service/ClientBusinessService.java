package client.card.business.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import client.card.business.dto.ClientDto;

/**
 * @author Entelgy
 */

public interface ClientBusinessService {

	public List<ClientDto> getClientList (ServiceContext serviceContext) 
			throws PortalException;
	
	public void addClient(ServiceContext serviceContext, ClientDto clientDto) 
			throws PortalException;
}