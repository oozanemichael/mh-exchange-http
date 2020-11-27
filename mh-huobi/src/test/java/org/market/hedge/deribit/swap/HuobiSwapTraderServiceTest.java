package org.market.hedge.deribit.swap;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.deribit.HuobiExchange;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HuobiSwapTraderServiceTest {

    public static void main(String[] args) {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"914069ad-uymylwhfeg-c8084d47-d25e0"
                ,"fc791193-3ab4649c-e052fc6a-7016f"
                , TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  huobi.getTradeService();
        List<MHLimitOrder> orders=new ArrayList<>();

        new MHLimitOrder(Order.OrderType.BID,
                BigDecimal.ONE ,
                CurrencyPair.BTC_USD ,
                "112233",
                new Date(),
                new BigDecimal(""),
                parsing.parsing(CurrencyPair.BTC_USD)
                );

        try {

            tradeService.placeLimitOrders(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
