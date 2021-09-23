package com.solera.registers.service;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.solera.registers.entity.Register;
import com.solera.registers.exception.AmountExceededBalanceException;
import com.solera.registers.exception.RegisterNotFoundException;
import com.solera.registers.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService{
    RegisterRepository repository;

    @Autowired
    public RegisterServiceImpl(RegisterRepository repository){
        this.repository = repository;
    }

    @Override
    public synchronized Register rechargeRegister(String name, int amount) {

        Register theRegister = repository.findByName(name);
        if(theRegister != null){
            theRegister.setBalance(theRegister.getBalance()+amount);
            return repository.save(theRegister);
        }else{
            throw new RegisterNotFoundException("Register with name "+ name +" not found");
        }
    }

    @Override
    public synchronized List<Register> transferBetweenRegisters(String source, String destination, int amount) {
        Register sourceRegister = repository.findByName(source);
        Register targetRegister = repository.findByName(destination);

        if (sourceRegister.getBalance() >= amount) {
            sourceRegister.setBalance(sourceRegister.getBalance() - amount);
            targetRegister.setBalance(targetRegister.getBalance() + amount);
        } else {
            throw new AmountExceededBalanceException("The deducted amount can't be more than source register balance");
        }

        repository.save(sourceRegister);
        repository.save(targetRegister);

        return new ArrayList<>(Arrays.asList(sourceRegister, targetRegister));
    }


    @Override
    public synchronized List<Register> getRegisters() {
        Iterable<Register> registerList = repository.findAll();
        List<Register> theRegisters= new ArrayList<>();
        for(Register register: registerList){
            theRegisters.add(register);
        }
        return theRegisters;
    }
}
