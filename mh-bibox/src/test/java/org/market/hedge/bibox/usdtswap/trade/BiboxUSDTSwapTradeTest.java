package org.market.hedge.bibox.usdtswap.trade;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.bibox.BiboxExchange;
import org.market.hedge.bibox.usdtswap.BiboxUSDTSwapAuthenticated;
import org.market.hedge.core.TradingArea;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.trade.MHTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BiboxUSDTSwapTradeTest {

    private static Logger logger= LoggerFactory.getLogger(BiboxUSDTSwapTradeTest.class);


    @Test
    public void placeLimitOrder() throws IOException {
        MHExchange bibox= MHExchangeFactory.INSTANCE.createExchange(BiboxExchange.class,
                "2d62e7bdfcf37d32d2d75770572962f467f52619",
                "e93e9539ec1c9faa707280e3723fccfbbeb8e35f",
                TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=bibox.getStreamingParsing().parsingCurrencyPair;
        logger.warn("sss----{}",parsing.parsing(CurrencyPair.BTC_USDT).getParsing());
        MHTradeService tradeService=  bibox.getTradeService();
        List<MHLimitOrder> orders=new ArrayList<>();

        MHLimitOrder order1=
                new MHLimitOrder(
                        Order.OrderType.ASK,
                        new BigDecimal("1") ,
                        CurrencyPair.BTC_USDT ,
                        "11223311",
                        new Date(),
                        new BigDecimal("35000"),
                        parsing.parsing(CurrencyPair.BTC_USDT));
        order1.setLeverage("5");
       MHLimitOrder order2=new MHLimitOrder(
               Order.OrderType.BID,
                new BigDecimal("1") ,
                CurrencyPair.BTC_USDT ,
                null,
                new Date(),
                new BigDecimal("19000"),
                parsing.parsing(CurrencyPair.BTC_USDT));
       /*
        order1.setLeverage("10");
        order2.setLeverage("10");
        orders.add(order1);
        orders.add(order2);*/
        try {
            tradeService.placeLimitOrder(order1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
