package com.solera.registers.service;

import com.solera.registers.entity.Register;

import java.util.List;

public interface RegisterService {
    Register rechargeRegister(String name, int amount);

    List<Register> transferBetweenRegisters(String source, String destination, int amount);

    List<Register> getRegisters();

}
