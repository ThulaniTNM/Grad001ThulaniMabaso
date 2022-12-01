package com.eviro.assessment.grad001.thulaniMabaso.Grad001ThulaniMabaso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class AccountProfile {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private String httpImageLink;

    // replicate record creation

    public AccountProfile(int id, String name, String surname, String httpImageLink) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.httpImageLink = httpImageLink;
    }

    public AccountProfile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHttpImageLink() {
        return httpImageLink;
    }

    public void setHttpImageLink(String httpImageLink) {
        this.httpImageLink = httpImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountProfile that = (AccountProfile) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(httpImageLink, that.httpImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, httpImageLink);
    }

    @Override
    public String toString() {
        return "AccountProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", httpImageLink='" + httpImageLink + '\'' +
                '}';
    }
}
