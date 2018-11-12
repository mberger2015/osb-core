package de.evoila.cf.broker.model;

/**
 * @author Christian Brinker, evoila.
 */
public class ServerAddress {

    private String name;

    private String ip;

    private int port;

    public ServerAddress() { }

    public ServerAddress(String name) {
        this(name, null);
    }

    public ServerAddress(String name, String ip) {
        this(name, ip, 0);
    }

    public ServerAddress(ServerAddress address) {
        this(address.name, address.ip, address.port);
    }

    public ServerAddress(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
