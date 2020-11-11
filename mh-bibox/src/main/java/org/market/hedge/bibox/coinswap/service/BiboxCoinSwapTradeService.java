package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.bibox.service.BiboxTradeServiceRaw;
import org.market.hedge.service.trade.MHTradeService;

public class BiboxCoinSwapTradeService extends BiboxCoinSwapTradeServiceRaw implements MHTradeService {
    /**
     * Constructor
     *
     * @param exchange
     */
    public BiboxCoinSwapTradeService(Exchange exchange) {
        super(exchange);
    }
}
