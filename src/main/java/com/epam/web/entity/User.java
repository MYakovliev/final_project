package com.epam.web.entity;


import java.math.BigDecimal;

/**
 * Entity class User
 *
 * @author Nikita Yakovlev
 */
public class User {
    private long id;
    private String mail;
    private String name;
    private BigDecimal balance;
    private UserRole userRole;
    private String avatar;
    private boolean banned;

    public User(long id, String name, String mail, BigDecimal balance, UserRole userRole, String avatar, boolean banned) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.userRole = userRole;
        this.balance = balance;
        this.avatar = avatar;
        this.banned = banned;
    }

    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (banned != user.banned) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (balance != null ? !balance.equals(user.balance) : user.balance != null) return false;
        if (userRole != user.userRole) return false;
        return avatar != null ? avatar.equals(user.avatar) : user.avatar == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (banned ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", userRole=").append(userRole);
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append(", banned=").append(banned);
        sb.append('}');
        return sb.toString();
    }
}
