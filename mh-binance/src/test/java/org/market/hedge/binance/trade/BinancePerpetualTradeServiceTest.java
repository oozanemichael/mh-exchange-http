package org.market.hedge.binance.trade;

import org.junit.Test;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualMarketDataServiceTest;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.trade.MHTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

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

}
