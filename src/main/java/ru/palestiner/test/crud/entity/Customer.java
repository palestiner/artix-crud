package ru.palestiner.test.crud.entity;

import ru.palestiner.test.crud.entity.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "customer")
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Max(value = 100, message = "Maximum length is 20 characters")
    @NotNull
    @NotEmpty
    @Column(name = "name_", nullable = false, length = 100)
    private String name = "";

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "dob", columnDefinition = "DATE", nullable = false)
    private LocalDate dayOfBirth;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                gender == customer.gender &&
                Objects.equals(dayOfBirth, customer.dayOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, dayOfBirth);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", dayOfBirth=" + dayOfBirth +
                '}';
    }
}
