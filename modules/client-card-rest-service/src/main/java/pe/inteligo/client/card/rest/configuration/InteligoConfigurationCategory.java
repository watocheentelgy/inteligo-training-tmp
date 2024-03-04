package pe.inteligo.client.card.rest.configuration;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationCategory.class)
public class InteligoConfigurationCategory implements ConfigurationCategory {

	@Override
	public String getCategoryKey() {
		return "clients-and-cards";
	}

	@Override
	public String getCategorySection() {
		return "inteligo";
	}
	
	@Override
	public String getCategoryIcon() {
		return "plus-squares";
	}

}
