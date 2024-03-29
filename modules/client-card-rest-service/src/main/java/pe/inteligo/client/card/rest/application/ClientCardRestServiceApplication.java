package pe.inteligo.client.card.rest.application;

import com.liferay.portal.configuration.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import client.card.business.dto.ClientDto;
import client.card.business.service.ClientBusinessService;
import pe.inteligo.client.card.rest.configuration.ClientCardRestServiceConfiguration;
import pe.inteligo.client.card.rest.configuration.SectorRestServiceConfiguration;

/**
 * @author Entelgy
 */
@Component(property = { 
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/catalog-client",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Client.Card.Rest.Service.Rest" }, service = Application.class)
public class ClientCardRestServiceApplication extends Application {

	private Log LOG = LogFactoryUtil.getLog(ClientCardRestServiceApplication.class);

	@Reference
	private ClientBusinessService clientBusinessService;
	
	@Reference
	private CompanyLocalService companyLocalService;
	
	private ClientCardRestServiceConfiguration clientCardRestServiceConfiguration;
	private SectorRestServiceConfiguration sectorRestServiceConfiguration;
	
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@Context HttpServletRequest request) {

		ServiceContext serviceContext;
		try {
			serviceContext = ServiceContextFactory.getInstance(request);
		} catch (Exception ex) {
			LOG.error("Error al obtener contexto. Detalle: " + ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSONFactoryUtil.createJSONObject()
					.put("success", false).put("error", ex.getMessage()).toJSONString()).build();
		}


		List<ClientDto> clientList;
		try {
			clientList = clientBusinessService
					.getClientList(serviceContext);
		} catch (PortalException ex) {
			LOG.error("Error al obtener contexto. Detalle: " + ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSONFactoryUtil.createJSONObject()
					.put("success", false).put("error", ex.getMessage()).toJSONString()).build();
		}
		
		
		JSONObject responseJson = JSONFactoryUtil.createJSONObject();
		responseJson.put("status", true);
		responseJson.put("data", clientList);

		return Response.accepted().entity(responseJson.toJSONString()).build();
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(@Context HttpServletRequest request, ClientDto clientDto) {
		ServiceContext serviceContext;
		try {
			serviceContext = ServiceContextFactory.getInstance(request);
		} catch (Exception ex) {
			LOG.error("Error al obtener contexto. Detalle: " + ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSONFactoryUtil.createJSONObject()
					.put("success", false).put("error", ex.getMessage()).toJSONString()).build();
		}
		
		try {
			clientBusinessService.addClient(serviceContext, clientDto);
		} catch (PortalException ex) {
			LOG.error("Error al obtener contexto. Detalle: " + ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSONFactoryUtil.createJSONObject()
					.put("success", false).put("error", ex.getMessage()).toJSONString()).build();
		}
		
		JSONObject responseJson = JSONFactoryUtil.createJSONObject();
		responseJson.put("status", true);
		
		return Response.ok().entity(responseJson.toJSONString()).build();
	}
	
	@GET
	@Path("/configuration/{configCompany}/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response configuration(@PathParam("configCompany") boolean configCompany,
			@PathParam("companyId") long companyId) throws ConfigurationException {
		LOG.info("company: " + companyId);
		try {
			companyLocalService.getCompany(companyId);
		} catch (PortalException ex) {
			LOG.error("Error al obtener contexto. Detalle: " + ex.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSONFactoryUtil.createJSONObject()
					.put("success", false).put("error", ex.getMessage()).toJSONString()).build();
		}
		
		if(configCompany) {
			clientCardRestServiceConfiguration = 
				ConfigurationProviderUtil.getCompanyConfiguration(
						ClientCardRestServiceConfiguration.class, companyId);
			
			sectorRestServiceConfiguration = 
					ConfigurationProviderUtil.getCompanyConfiguration(
							SectorRestServiceConfiguration.class, companyId);
		}else {
			clientCardRestServiceConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(
							ClientCardRestServiceConfiguration.class);
			
			sectorRestServiceConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(
							SectorRestServiceConfiguration.class);
		}
		
		JSONObject responseJson = JSONFactoryUtil.createJSONObject();
		responseJson.put("status", true);
		
		JSONObject clientCardJson = JSONFactoryUtil.createJSONObject();
		
		clientCardJson.put("maximumNotifications", clientCardRestServiceConfiguration.maximumNotifications());
		clientCardJson.put("defaultRole", clientCardRestServiceConfiguration.defaultRole());
		clientCardJson.put("avatarList", clientCardRestServiceConfiguration.avatarList());
		clientCardJson.put("supportedMimeTypes", clientCardRestServiceConfiguration.supportedMimeTypes());
		clientCardJson.put("sandboxEnabled", clientCardRestServiceConfiguration.sandboxEnabled());
		clientCardJson.put("sandboxBetaEnabled", clientCardRestServiceConfiguration.sandboxBetaEnabled());
		
		responseJson.put("clientCardJson", clientCardJson);
		
		JSONObject sectorJson = JSONFactoryUtil.createJSONObject();
		sectorJson.put("defaultDisplayView", sectorRestServiceConfiguration.defaultDisplayView());
		
		responseJson.put("sectorJson", sectorJson);
		
		clientBusinessService.extensions(clientCardRestServiceConfiguration.supportedMimeTypes());
		
		return Response.ok().entity(responseJson.toJSONString()).build();
	}
	
	
}