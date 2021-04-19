package org.market.hedge.binance.trade;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualMarketDataServiceTest;
import org.market.hedge.binance.perpetualSwap.service.BinancePerpetualTradeService;
import org.market.hedge.binance.perpetualSwap.service.BinancePerpetualTradeServiceRaw;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.PositionInfo;
import org.market.hedge.core.TradingArea;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.dto.trade.Result;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.account.MHAccountService;
import org.market.hedge.service.trade.MHTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BinancePerpetualTradeServiceTest {

    Logger log= LoggerFactory.getLogger(BinancePerpetualMarketDataServiceTest.class);

    @Test
    public void placeMarketOrders() throws IOException {
        MHExchange exchange= MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class
                ,"914069ad-uymylwhfeg-c8084d47-d25e0"
                ,"fc791193-3ab4649c-e052fc6a-7016f"
                , TradingArea.Option);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  exchange.getTradeService();
        try {
            tradeService.placeMarketOrders(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void placeLimitOrder() {
        MHExchange exchange= MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class
                ,"NEq7SZ4xcE8jcRkeOvhJHJlgzV3X243g02KiF2TQqEIIPoOFI1nmaHhu8j1iSAMH"
                ,"J9GlHxSgCK759BBEEFLzbjYglsqiZWLQMEjR8kuUwHv1FID0HhdYmm6VDrLNc8IQ"
                , TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  exchange.getTradeService();
        ParsingCurrencyPair pair=parsing.parsing(CurrencyPair.BTC_USDT);
        try {
            MHLimitOrder  order1=
                    new MHLimitOrder(
                            Order.OrderType.ASK,
                            new BigDecimal("10") ,
                            CurrencyPair.BTC_USDT ,
                            "11223311",
                            new Date(),
                            new BigDecimal("63000"),
                            pair);
            log.info(pair.getParsing());
            List<MHLimitOrder> limitOrders=new ArrayList<>();
            limitOrders.add(order1);
            tradeService.placeLimitOrders(limitOrders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void positionRisk() throws IOException {
        MHExchange exchange= MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class
                ,"ajmNWdDNiHENQ51N7btBGU8ijdMJRuZstBKBbNnn9iplHR5U2GUjGClulVpozU9U"
                ,"PX1t8o3K3HYGmR5FPYg6EN3XaRaSsNC8pStEshZWN44O2L9ryUHOggjAU0TtNtq1"
                , TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHAccountService accountService=  exchange.getAccountService();
        try {
            PositionInfo positionInfo =  accountService.getPosition(parsing.parsing(CurrencyPair.BTC_USDT),null);
            log.info("positionInfo:{}",positionInfo.getSymbol());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setLeverage() throws IOException {
        MHExchange exchange= MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class
                ,"NEq7SZ4xcE8jcRkeOvhJHJlgzV3X243g02KiF2TQqEIIPoOFI1nmaHhu8j1iSAMH"
                ,"J9GlHxSgCK759BBEEFLzbjYglsqiZWLQMEjR8kuUwHv1FID0HhdYmm6VDrLNc8IQ"
                , TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  exchange.getTradeService();
        try {
            tradeService.setLeverage(parsing.parsing(CurrencyPair.BTC_USDT),25);
            //log.info("resultmap:{}",map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void setMarginTypeRaw() throws IOException {
        MHExchange exchange= MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class
                ,"NEq7SZ4xcE8jcRkeOvhJHJlgzV3X243g02KiF2TQqEIIPoOFI1nmaHhu8j1iSAMH"
                ,"J9GlHxSgCK759BBEEFLzbjYglsqiZWLQMEjR8kuUwHv1FID0HhdYmm6VDrLNc8IQ"
                , TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        BinancePerpetualTradeServiceRaw tradeServiceRaw= (BinancePerpetualTradeServiceRaw) exchange.getTradeService();
        try {
            tradeServiceRaw.setMarginType(parsing.parsing(CurrencyPair.BTC_USDT), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
