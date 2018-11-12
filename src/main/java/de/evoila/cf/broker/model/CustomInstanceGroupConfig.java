package de.evoila.cf.broker.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomInstanceGroupConfig extends InstanceGroupConfig {

    protected String name;

    public CustomInstanceGroupConfig() {}

    public CustomInstanceGroupConfig(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
