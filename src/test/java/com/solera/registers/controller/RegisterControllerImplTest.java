package com.solera.registers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solera.registers.entity.Register;
import com.solera.registers.repository.RegisterRepository;
import com.solera.registers.service.RegisterService;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import java.util.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
class RegisterControllerImplTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private RegisterRepository registerRepository;

    @MockBean
    private RegisterService registerService;

    Register walletRegister = new Register(1,"Wallet", 1000);
    Register savingsRegister = new Register(2,"Savings", 5000);
    Register inRegister = new Register(3,"Insurance policy", 0);
    Register foodRegister = new Register(4,"Food expenses", 0);

    @Test
    void rechargeRegister() throws Exception {
        Register record = (Register) walletRegister.clone();
        record.setBalance(3500);

        Mockito.when(registerService.rechargeRegister(record.getName(),2500)).thenReturn(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/registers/recharge/Wallet?amount=2500")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(record.getBalance())));
    }

    @Test
    void transferBetweenRegisters() throws Exception {

        Register recordS = (Register) walletRegister.clone();
        recordS.setBalance(500);

        Register recordT = (Register) foodRegister.clone();
        recordT.setBalance(500);

        List<Register> records = new ArrayList<>(Arrays.asList(recordS,recordT));

        Mockito.when(registerService.transferBetweenRegisters(walletRegister.getName(), foodRegister.getName(),500)).thenReturn(records);


        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/registers/transfer/500?source=Wallet&destination=Food expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].balance", is(recordS.getBalance())))
                .andExpect(jsonPath("$[1].balance", is(recordS.getBalance())));
    }

    @Test
    void getRegisters() throws Exception {
        List<Register> records = new ArrayList<>(Arrays.asList(walletRegister, savingsRegister, inRegister,foodRegister));
        Mockito.when(registerService.getRegisters()).thenReturn(records);
        mvc.perform(MockMvcRequestBuilders
                .get("/registers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", is("Wallet")));

    }
}