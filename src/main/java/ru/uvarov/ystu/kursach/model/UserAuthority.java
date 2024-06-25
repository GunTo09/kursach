package ru.uvarov.ystu.kursach.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {

    EMPLOYEE, // Роль продавца
    BUYER,  // Роль покупателя
    FULL;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
