package org.market.hedge.deribit.option;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.marketdata.DeribitInstrument;
import org.market.hedge.deribit.dto.marketdata.DeribitOrderBook;
import org.market.hedge.deribit.option.service.DeribitOptionMarketDataService;
import org.market.hedge.deribit.service.DeribitMarketDataService;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class DeribitOptionMarketDataTest {

    static Logger logger= LoggerFactory.getLogger(DeribitOptionMarketDataTest.class);

    @Test
    public void getDeribitOrderBookTest() throws Exception {
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(DeribitExchange.class, TradingArea.Option);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        DeribitOptionMarketDataService marketDataService= (DeribitOptionMarketDataService) exchange.getMarketDataService();
        try {

            DeribitOrderBook orderBook=marketDataService.getDeribitOrderBook(CurrencyPair.BTC_USDT,"18DEC20-13750-C");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getContractInfos(){
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(DeribitExchange.class, TradingArea.Option);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        DeribitOptionMarketDataService marketDataService= (DeribitOptionMarketDataService) exchange.getMarketDataService();
        try {
            List<DeribitInstrument> result=marketDataService.getContractInfos("BTC",null,null);
            result.forEach(e->{
                logger.info("{}",e.getInstrumentName());
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
