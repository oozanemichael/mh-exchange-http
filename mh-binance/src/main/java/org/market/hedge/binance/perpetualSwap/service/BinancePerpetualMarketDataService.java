package org.market.hedge.binance.perpetualSwap.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.binance.BinanceAuthenticated;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.dto.marketdata.BinanceOrderbook;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.core.Kline;
import org.market.hedge.core.KlineInterval;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;

import java.io.IOException;
import java.util.List;


/**
 * @author chuxianbo
 */
public class BinancePerpetualMarketDataService  extends BinancePerpetualMarketDataServiceRaw  implements MHMarketDataService{
    public BinancePerpetualMarketDataService(BinanceExchange exchange, BinancePerpetualAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }

    /**
     *      @param args args[0] limit  false  Integer
     *
     * */
    @Override
    public OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {
        Integer limit = 10;
        if (args != null && args.length > 0) {
            if (args[0] instanceof Integer && (Integer) args[0] > 0) {
                limit = (Integer) args[0];
            }
        }
        BinanceOrderbook binanceOrderbook = getBinanceOrderbook(currencyPair,limit);
        return convertOrderBook(binanceOrderbook, currencyPair.getCurrencyPair());
    }

    @Override
    public List<Kline> getKlines(ParsingCurrencyPair pair, KlineInterval interval, Integer limit) throws IOException {
        return klines(pair,  interval,  limit, null, null);
    }
}
