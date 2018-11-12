package de.evoila.cf.broker.model.volume;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Jannik Heyl, Johannes Hiemer.
 */
public class VolumeMount {

    @JsonSerialize
    @JsonProperty("driver")
    private String driver;

    @JsonSerialize
    @JsonProperty("container_dir")
    private String containerDir;

    @JsonSerialize
    @JsonProperty("mode")
    private VolumeMode mode;

    @JsonProperty("device_type")
    private DeviceType deviceType;

    @JsonSerialize
    @JsonProperty("device")
    private Device device;

    public VolumeMount() {}

    public VolumeMount(String driver, String containerDir, VolumeMode mode, DeviceType deviceType, Device device) {
        this.driver = driver;
        this.containerDir = containerDir;
        this.mode = mode;
        this.deviceType = deviceType;
        this.device = device;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getContainerDir() {
        return containerDir;
    }

    public void setContainerDir(String containerDir) {
        this.containerDir = containerDir;
    }

    public VolumeMode getMode() {
        return mode;
    }

    public void setMode(VolumeMode mode) {
        this.mode = mode;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}