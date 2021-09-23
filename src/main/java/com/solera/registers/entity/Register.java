package com.solera.registers.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Register implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "BALANCE", nullable = false)
    private int balance;

    public Register(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }

    public Register() {

    }

    public Register(int id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public Object clone() {

        try{
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Register(this.getId(), this.getName(), this.getBalance());
        }
    }
}
