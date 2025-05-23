package com.ibm.currency_exchange_service;

import com.ibm.currency_exchange_service.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchange(@PathVariable String from, @PathVariable String to){
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from,to);

        if (currencyExchange == null)
            throw new RuntimeException("Enable to find data for" + from + "to" + to);

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
