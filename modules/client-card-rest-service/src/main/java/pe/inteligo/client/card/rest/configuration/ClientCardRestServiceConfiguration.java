package pe.inteligo.client.card.rest.configuration;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "clients-and-cards", 
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)

@Meta.OCD(
	id = "pe.inteligo.client.card.rest.configuration.ClientCardRestServiceConfiguration", 
	localization = "content/Language", 
	name = "Client_Card_Rest_Service_Configuration"
)
public interface ClientCardRestServiceConfiguration {
	
	@Meta.AD(
			deflt = "1", 
			description = "maximum-number-of-notifications", 
			name = "maximum-notifications", 
			required = false
			)
	public int maximumNotifications();

	@Meta.AD(
			deflt = "descriptive", 
			name = "default-display-view", 
			optionLabels = { "Descriptiva", "Lista" }, 
			optionValues = { "descriptive", "list" }, 
			required = false
			)
	public String defaultDisplayView();

	@Meta.AD(
			deflt = StringPool.BLANK, 
			name = "default-role", 
			required = false
			)
	public String defaultRole();
	
	@Meta.AD(
			name = "avatar-list", 
			required = false
			)
	public String[] avatarList();
	
	@Meta.AD(
			deflt = "image/bmp|image/gif|image/jpeg|image/pjpeg|image/png", 
			name = "supported-mime-type", 
			required = false)
			public String[] supportedMimeTypes();

	@Meta.AD(
			deflt = "false", 
			name = "sandbox-enabled", 
			required = false)
	public boolean sandboxEnabled();
	
	@Meta.AD(
			deflt = "false", 
			name = "sandbox-beta-enabled", 
			required = false)
	public boolean sandboxBetaEnabled();
}
