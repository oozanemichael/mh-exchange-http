package org.market.hedge.bibox.coinswap.trade;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.bibox.BiboxExchange;
import org.market.hedge.bibox.coinswap.marketdata.BiboxCoinSwapMarketDataTest;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.TradingArea;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.trade.MHTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BiboxCoinSwapTradeTest {

    private static Logger logger= LoggerFactory.getLogger(BiboxCoinSwapMarketDataTest.class);


    @Test
    public void placeLimitOrder() throws IOException {
        MHExchange bibox= MHExchangeFactory.INSTANCE.createExchange(BiboxExchange.class,"00064ff6ec177438e289d5559b0693efc505af65","b53766db065a9413f572ad75b13f5464acbb75a0", TradingArea.CoinSwap);
        StreamingParsingCurrencyPair parsing=bibox.getStreamingParsing().parsingCurrencyPair;
        logger.warn("sss----{}",parsing.parsing(CurrencyPair.BTC_USD).getParsing());
        MHTradeService tradeService=  bibox.getTradeService();
        List<MHLimitOrder> orders=new ArrayList<>();

        MHLimitOrder order1=
                new MHLimitOrder(
                        Order.OrderType.EXIT_BID,
                        new BigDecimal("1") ,
                        CurrencyPair.BTC_USD ,
                        "11223311",
                        new Date(),
                        new BigDecimal("31349.0"),
                        parsing.parsing(CurrencyPair.BTC_USD));
        order1.setLeverage("5");
        try {
            tradeService.placeLimitOrder(order1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void cancelAllByInstrument() throws IOException {
        MHExchange bibox= MHExchangeFactory.INSTANCE.createExchange(BiboxExchange.class,"00064ff6ec177438e289d5559b0693efc505af65","b53766db065a9413f572ad75b13f5464acbb75a0", TradingArea.CoinSwap);
        StreamingParsingCurrencyPair parsing=bibox.getStreamingParsing().parsingCurrencyPair;
        logger.warn("sss----{}",parsing.parsing(CurrencyPair.BTC_USD).getParsing());
        MHTradeService tradeService=  bibox.getTradeService();
        tradeService.cancelAllByInstrument(parsing.parsing(CurrencyPair.BTC_USD));
    }

}
