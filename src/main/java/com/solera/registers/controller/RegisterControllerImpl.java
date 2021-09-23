package com.solera.registers.controller;

import com.solera.registers.entity.Register;
import com.solera.registers.service.RegisterService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/registers")
public class RegisterControllerImpl implements RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterControllerImpl(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    @GetMapping(value = "/recharge/{name}")
    public ResponseEntity<Register> rechargeRegister(@PathVariable String name, @RequestParam("amount") int amount) {
        return new ResponseEntity<>(registerService.rechargeRegister(name,amount), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/transfer/{amount}")
    public ResponseEntity<List<Register>> transferBetweenRegisters(@RequestParam("source") String source, @RequestParam("destination") String destination, @PathVariable int amount) {
        return new ResponseEntity<>(registerService.transferBetweenRegisters(source,destination,amount), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Register>> getRegisters() {
        return new ResponseEntity<>(registerService.getRegisters(), HttpStatus.OK);
    }
}
