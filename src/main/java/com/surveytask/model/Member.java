package com.surveytask.model;

import java.util.Objects;

public class Member {
    private int memberId;
    private String fullName;
    private String email;
    private boolean isActive;

    public Member(int memberId, String fullName, String email, boolean isActive) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId == member.memberId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(memberId);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
