package de.evoila.cf.broker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A service offered by this broker.
 * 
 * @author sgreenberg@gopivotal.com
 * @author Johannes Hiemer.
 * @author Marco Di Martino.
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE)
public class ServiceDefinition {

	@JsonSerialize
	@JsonProperty("id")
	private String id;

	@JsonSerialize
	@JsonProperty("name")
	private String name;

	@JsonSerialize
	@JsonProperty("description")
	private String description;

	@JsonSerialize
	@JsonProperty("bindable")
	private boolean bindable;

	@JsonSerialize
	@JsonProperty("plans")
	private List<Plan> plans = new ArrayList<Plan>();

	@JsonSerialize
	@JsonProperty("tags")
	private List<String> tags = new ArrayList<String>();

	@JsonSerialize
	@JsonProperty("metadata")
	private Map<String, Object> metadata = new HashMap<String, Object>();

	@JsonSerialize
	@JsonProperty("requires")
	private List<String> requires = new ArrayList<String>();
	
	@JsonSerialize
	@JsonProperty("dashboard")
	private Dashboard dashboard;
	
	@JsonSerialize
	@JsonProperty("dashboard_client")
	private DashboardClient dashboardClient;

	@JsonSerialize
	@JsonProperty("plan_updateable") // misspelling of attribute kept, do not change it
	private boolean updateable;


	public ServiceDefinition() {
		super();
	}

	public ServiceDefinition(String id, String name, String description, boolean bindable, List<Plan> plans, boolean updatable) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.bindable = bindable;
		this.setPlans(plans);
		this.updateable = updatable;
	}

	public ServiceDefinition(String id, String name, String description, boolean bindable, List<Plan> plans, boolean updatable, List<String> requires) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.bindable = bindable;
		this.updateable = updatable;
		this.setPlans(plans);
		this.setRequires(requires);
	}

	public ServiceDefinition(String id, String name, String description, boolean bindable, List<Plan> plans, boolean updatable,
			List<String> tags, Map<String, Object> metadata, List<String> requires) {
		this(id, name, description, bindable, plans, updatable);
		setTags(tags);
		setMetadata(metadata);
		setRequires(requires);
	}

	public ServiceDefinition(String id, String name, String description, boolean bindable, List<Plan> plans) {
		this(id, name, description, bindable, plans, false);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isBindable() {
		return bindable;
	}

	public void setBindable(boolean bindable) {
		this.bindable = bindable;
	}

	public boolean isUpdateable() {
		return updateable;
	}

	public void setPlanUpdateable(boolean updatable) {
		this.updateable = updatable;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		if (plans == null) {
			// ensure serialization as an empty array and not null
			this.plans = new ArrayList<>();
		} else {
			this.plans = plans;
		}
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		if (tags == null) {
			this.tags = new ArrayList<>();
		} else {
			this.tags = tags;
		}
	}

	public List<String> getRequires() {
		return requires;
	}

	public void setRequires(List<String> requires) {
		if (requires == null) {
			this.requires = new ArrayList<>();
		} else {
			this.requires = requires;
		}
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		if (metadata == null) {
			this.metadata = new HashMap<>();
		} else {
			this.metadata = metadata;
		}
	}
	
	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public DashboardClient getDashboardClient() {
		return dashboardClient;
	}

	public void setDashboardClient(DashboardClient dashboardClient) {
		this.dashboardClient = dashboardClient;
	}

}
