package org.market.hedge.service.marketdata;

import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.market.hedge.core.ParsingCurrencyPair;

import java.io.IOException;

public interface MHMarketDataService extends MarketDataService {

    default OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }



}
