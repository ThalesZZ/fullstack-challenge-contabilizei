package com.thaleszz.challenge_contabilizei.models.user;

public enum UserRole {
    ADMIN,
    USER;

    @Override
    public String toString() {
        return this.name();
    }
}
