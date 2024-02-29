package client.card.business.service.impl;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import client.card.business.dto.ClientDto;
import client.card.business.enumeration.FieldObjectEnum;
import client.card.business.service.ClientBusinessService;
import client.card.business.util.Constants;

@Component(
		service = ClientBusinessService.class
	)
public class ClientBusinessServiceImpl implements ClientBusinessService{

	private Log LOG = LogFactoryUtil.getLog(ClientBusinessService.class);
	
	@Reference
	private ObjectDefinitionLocalService objectDefinitionLocalService;
	@Reference
	private ObjectEntryLocalService objectEntryLocalService;
	
	
	public List<ClientDto> getClientList (ServiceContext serviceContext) throws PortalException {
		ObjectDefinition objectDefinition = objectDefinitionLocalService
				.fetchObjectDefinition(serviceContext.getCompanyId(),
				Constants.OBJECT_CLIENT_NAME);

		List<ObjectEntry> objectEntryList = objectEntryLocalService
				.getObjectEntries(0, 
				objectDefinition.getObjectDefinitionId(), 
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<ClientDto> clientList = new ArrayList<>();
		
		ClientDto clientDto;
		for (ObjectEntry objectEntry : objectEntryList) {
			Map<String, Serializable> valuesObject = objectEntryLocalService
					.getValues(objectEntry.getObjectEntryId());
			clientDto = new ClientDto();
			clientDto.setId(objectEntry.getObjectEntryId());
			clientDto.setName(valuesObject.get(FieldObjectEnum.NAME.getName()).toString());
			clientDto.setLastName(valuesObject.get(FieldObjectEnum.LASTNAME.getName()).toString());
			clientDto.setAddress(valuesObject.get(FieldObjectEnum.ADDRESS.getName()).toString());
			clientDto.setEmail(valuesObject.get(FieldObjectEnum.EMAIL.getName()).toString());
			clientDto.setMobile(valuesObject.get(FieldObjectEnum.MOBILE.getName()).toString());
			
			clientList.add(clientDto);
		}
		
		return clientList;
	}
	
	
	public void addClient(ServiceContext serviceContext, ClientDto clientDto) 
			throws PortalException{
		ObjectDefinition objectDefinition = objectDefinitionLocalService
			.fetchObjectDefinition(serviceContext.getCompanyId(),
			Constants.OBJECT_CLIENT_NAME);
		
		Map<String, Serializable> values = new LinkedHashMap<>();
        values.put(FieldObjectEnum.NAME.getName(), clientDto.getName());
        values.put(FieldObjectEnum.LASTNAME.getName(), clientDto.getLastName());
        values.put(FieldObjectEnum.ADDRESS.getName(), clientDto.getAddress());
        values.put(FieldObjectEnum.EMAIL.getName(), clientDto.getEmail());
        values.put(FieldObjectEnum.MOBILE.getName(), clientDto.getMobile());

        ObjectEntry objectEntry = objectEntryLocalService
        		.addObjectEntry(serviceContext.getUserId(), 0L, 
        		objectDefinition.getObjectDefinitionId(), 
        		values, serviceContext);
        
        LOG.info("ObjectEntryId: " + objectEntry.getObjectEntryId());
	}
}
