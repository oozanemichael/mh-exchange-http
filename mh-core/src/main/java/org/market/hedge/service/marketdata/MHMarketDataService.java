package org.market.hedge.service.marketdata;

import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.market.hedge.core.ParsingCurrencyPair;

import java.io.IOException;

/**
 * 市场
 * */
public interface MHMarketDataService extends MarketDataService {

    /**
     * 获取盘口行情
     * */
    default OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }



}
