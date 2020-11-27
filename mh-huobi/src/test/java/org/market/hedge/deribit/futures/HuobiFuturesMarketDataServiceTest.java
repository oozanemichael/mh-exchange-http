package org.market.hedge.deribit.futures;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.market.hedge.deribit.HuobiExchange;
import org.market.hedge.deribit.swap.HuobiSwapMarketData;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

public class HuobiFuturesMarketDataServiceTest {

    static Logger logger= LoggerFactory.getLogger(HuobiSwapMarketData.class);

    @Test
    public void getOrderBoook(){
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.Futures);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=huobi.getMarketDataService();
        try {
            for (int i=0;i<100;i++){
                OrderBook orderBook=marketDataService.getOrderBook(parsing.parsing(CurrencyPair.BTC_USD,new Date(1603440000000L)));
                logger.info("{}","ask:"+orderBook.getAsks().get(0).getLimitPrice()+" bid:"+orderBook.getBids().get(0).getLimitPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        logger.info("ss");
    }

}
