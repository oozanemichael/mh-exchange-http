package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.bibox.dto.BiboxAdapters;
import org.market.hedge.bibox.dto.trade.BiboxOrderBook;
import org.market.hedge.bibox.service.BiboxMarketDataServiceRaw;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;

import java.io.IOException;

public class BiboxCoinSwapMarketDataService extends BiboxCoinSwapMarketDataServiceRaw implements MHMarketDataService {
    /**
     * Constructor
     *
     * @param exchange
     */
    public BiboxCoinSwapMarketDataService(Exchange exchange) {
        super(exchange);
    }

    /**
     * eg: BTC-USDT-200508-C-8800
     *
     *      @param args args[0] limit  false  Integer
     *
     * */
    @Override
    public OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {
        Integer depth = 10; // default on website

        if (args != null && args.length > 0) {
            if (args[0] instanceof Integer && (Integer) args[0] > 0) {
                depth = (Integer) args[0];
            }
        }
        BiboxOrderBook biboxOrderBook = getBiboxOrderBook(currencyPair, depth);
        return BiboxAdapters.adaptOrderBook(biboxOrderBook, currencyPair.getCurrencyPair());

    }
}
