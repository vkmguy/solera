package com.solera.registers.controller;

import com.solera.registers.entity.Register;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegisterController {

    ResponseEntity<Register> rechargeRegister(String name, int amount);

    ResponseEntity<List<Register>> transferBetweenRegisters(String source, String destination, int amount);

    ResponseEntity<List<Register>> getRegisters();
}
