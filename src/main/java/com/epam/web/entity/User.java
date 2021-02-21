package com.epam.web.entity;


import java.math.BigDecimal;

public class User {
    private String mail;
    private String name;
    private BigDecimal balance = BigDecimal.valueOf(0.00);
    private UserRole userRole;
    private String avatar;

    public User(String name, String mail, UserRole userRole, BigDecimal balance) {
        this.name = name;
        this.mail = mail;
        this.userRole = userRole;
        this.balance = balance;
    }

    public User(String name, String mail){
        this.name = name;
        this.mail = mail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (balance != null ? !balance.equals(user.balance) : user.balance != null) return false;
        return userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("mail='").append(mail).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", userRole=").append(userRole);
        sb.append('}');
        return sb.toString();
    }
}
