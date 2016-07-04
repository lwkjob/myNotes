//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package zkdemo.zk.core.config;

import java.io.Serializable;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ZookeeperConfig implements Serializable {
    private String clusterAddress;
    private String namespace;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
    private String authority;

    public ZookeeperConfig() {
    }

    public ZookeeperConfig(ZookeeperConfig.Builder builder) {
        this(builder.getClusterAddress(), builder.getNamespace(), builder.getSessionTimeoutMs(), builder.getConnectionTimeoutMs(), builder.getAuthority());
    }

    public ZookeeperConfig(String clusterAddress, String namespace, int sessionTimeoutMs, int connectionTimeoutMs, String authority) {
        this.clusterAddress = clusterAddress;
        this.namespace = namespace;
        this.sessionTimeoutMs = sessionTimeoutMs;
        this.connectionTimeoutMs = connectionTimeoutMs;
        this.authority = authority;
    }

    public String getClusterAddress() {
        return this.clusterAddress;
    }

    public void setClusterAddress(String clusterAddress) {
        this.clusterAddress = clusterAddress;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public int getSessionTimeoutMs() {
        return this.sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public int getConnectionTimeoutMs() {
        return this.connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static ZookeeperConfig.Builder builder() {
        return new ZookeeperConfig.Builder();
    }

    public static class Builder {
        private String clusterAddress;
        private transient String namespace;
        private transient int sessionTimeoutMs;
        private transient int connectionTimeoutMs;
        private transient String authority;

        public Builder() {
        }

        public ZookeeperConfig build() {
            return new ZookeeperConfig(this);
        }

        public ZookeeperConfig.Builder cluster(String clusterAddress) {
            this.clusterAddress = clusterAddress;
            return this;
        }

        public ZookeeperConfig.Builder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public ZookeeperConfig.Builder sessionTimeoutMs(int sessionTimeoutMs) {
            this.sessionTimeoutMs = sessionTimeoutMs;
            return this;
        }

        public ZookeeperConfig.Builder connectionTimeoutMs(int connectionTimeoutMs) {
            this.connectionTimeoutMs = connectionTimeoutMs;
            return this;
        }

        public ZookeeperConfig.Builder authority(String authority) {
            this.authority = authority;
            return this;
        }

        public String getClusterAddress() {
            return this.clusterAddress;
        }

        public String getNamespace() {
            return this.namespace;
        }

        public int getSessionTimeoutMs() {
            return this.sessionTimeoutMs;
        }

        public int getConnectionTimeoutMs() {
            return this.connectionTimeoutMs;
        }

        public String getAuthority() {
            return this.authority;
        }
    }
}
