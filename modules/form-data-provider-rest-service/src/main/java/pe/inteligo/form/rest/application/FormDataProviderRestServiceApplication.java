package pe.inteligo.form.rest.application;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;


/**
 * @author Entelgy
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/form-data-provider",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Form.Data.Provider.Rest",
		"auth.verifier.guest.allowed=true",
		"liferay.access.control.disable=true"
	},
	service = Application.class
)
public class FormDataProviderRestServiceApplication extends Application {
	private Log LOG = LogFactoryUtil.getLog(FormDataProviderRestServiceApplication.class);
	
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/productive-sector-list/{clientId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response productiveSectorList(@PathParam("clientId") long clientId) {
		LOG.info("Cliente ID:" + clientId);
		
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		
		JSONObject responseJson = JSONFactoryUtil.createJSONObject();
		responseJson.put("status", true);
		
		jsonArray.put(JSONFactoryUtil.createJSONObject().put("name", "Energía"));
		jsonArray.put(JSONFactoryUtil.createJSONObject().put("name", "Agricultura"));
		jsonArray.put(JSONFactoryUtil.createJSONObject().put("name", "Minería"));
		jsonArray.put(JSONFactoryUtil.createJSONObject().put("name", "Pesca"));
		
		responseJson.put("data", jsonArray);
		
		
		return Response.ok().entity(responseJson.toJSONString()).build();
	}
}