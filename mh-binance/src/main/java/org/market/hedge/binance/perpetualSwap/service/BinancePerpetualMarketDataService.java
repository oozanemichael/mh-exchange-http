package org.market.hedge.binance.perpetualSwap.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.binance.BinanceAuthenticated;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.core.Kline;
import org.market.hedge.core.KlineInterval;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;

import java.io.IOException;
import java.util.List;


public class BinancePerpetualMarketDataService  extends BinancePerpetualMarketDataServiceRaw  implements MHMarketDataService{
    public BinancePerpetualMarketDataService(BinanceExchange exchange, BinanceAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }

    @Override
    public List<Kline> getKlines(ParsingCurrencyPair pair, KlineInterval interval, Integer limit) throws IOException {
        return klines(pair.getCurrencyPair(),  interval,  limit, null, null);
    }
}
