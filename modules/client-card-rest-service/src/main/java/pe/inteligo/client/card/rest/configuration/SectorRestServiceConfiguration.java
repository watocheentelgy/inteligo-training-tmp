package pe.inteligo.client.card.rest.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
		generateUI = false,
		category = "clients-and-cards", 
		scope = ExtendedObjectClassDefinition.Scope.COMPANY
	)

	@Meta.OCD(
		id = "pe.inteligo.client.card.rest.configuration.SectorRestServiceConfiguration", 
		localization = "content/Language", 
		name = "Sector_Rest_Service_Configuration"
	)
public interface SectorRestServiceConfiguration {

	@Meta.AD(
			deflt = "descriptive", 
			name = "default-display-view", 
			optionLabels = { "Descriptiva", "Lista" }, 
			optionValues = { "descriptive", "list" }, 
			required = false
			)
	public String defaultDisplayView();
}
