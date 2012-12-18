package org.mspring.platform.security.support;

public class IPMapper {
    private String ipAddress = null;
    private PathMapper excludeUrls = null;

    public IPMapper() {
        this.excludeUrls = new PathMapper();
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void addExcludeUrl(String path) {
        this.excludeUrls.put("", path);
    }

    public boolean isPathExcluded(String path, String remoteAddress) {
        if (!this.ipAddress.equals(remoteAddress)) {
            return false;
        }

        if (this.excludeUrls.isEmpty()) {
            return true;
        }

        String key = this.excludeUrls.get(path);

        return "".equals(key);
    }

    public String toString() {
        return "IPMapper[ipAddress=" + this.ipAddress + ", excludeUrls=" + this.excludeUrls + "]";
    }
}