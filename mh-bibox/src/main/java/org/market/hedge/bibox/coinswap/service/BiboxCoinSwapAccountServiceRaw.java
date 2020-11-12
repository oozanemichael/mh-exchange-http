package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.bibox.service.BiboxBaseService;

public class BiboxCoinSwapAccountServiceRaw extends BiboxCoinSwapBaseService {
    /**
     * Constructor
     *
     * @param exchange
     */
    protected BiboxCoinSwapAccountServiceRaw(Exchange exchange) {
        super(exchange);
    }
}
