package com.bank.dto.provider;

import java.util.Objects;

public class ProviderDetails {
    private String name;
    private String url;

    public ProviderDetails(String name) {
        this.name = name;
    }

    public ProviderDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProviderDetails that = (ProviderDetails) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
