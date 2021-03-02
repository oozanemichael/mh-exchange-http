package org.market.hedge.binance.perpetualSwap;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;

import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.core.Kline;
import org.market.hedge.core.KlineInterval;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class BinancePerpetualMarketDataServiceTest {

    Logger logger= LoggerFactory.getLogger(BinancePerpetualMarketDataServiceTest.class);

    @Test
    public void klines() throws IOException {
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class, TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=exchange.getMarketDataService();
        try {
            List<Kline> getKlines=marketDataService.getKlines(parsing.parsing(CurrencyPair.BTC_USDT), KlineInterval.m15,20);
            getKlines.forEach(e->{
                logger.info("{}",e.getClose());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
