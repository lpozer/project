package com.spring.project.pojo;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    COMMON("Common"),
    ADMINISTRATOR("Administrator"),
    AUDITOR("Auditor");

    public String value;

    Authority(String value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return this.value;
    }
}
