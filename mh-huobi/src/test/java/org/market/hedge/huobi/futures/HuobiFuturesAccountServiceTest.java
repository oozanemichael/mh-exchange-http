package org.market.hedge.huobi.futures;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.BilateralPositionInfo;
import org.market.hedge.core.TradingArea;
import org.market.hedge.huobi.HuobiExchange;
import org.market.hedge.huobi.futures.service.HuobiFuturesAccountService;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class HuobiFuturesAccountServiceTest {

    static Logger log= LoggerFactory.getLogger(HuobiFuturesAccountServiceTest.class);

    @Test
    public void getPosition(){
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class,
                "914069ad-uymylwhfeg-c8084d47-d25e0",
                "fc791193-3ab4649c-e052fc6a-7016f",
                TradingArea.Futures);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        HuobiFuturesAccountService accountService= (HuobiFuturesAccountService) huobi.getAccountService();
        try {
            BilateralPositionInfo position=accountService.getBilateralPosition(parsing.parsing(CurrencyPair.BTC_USDT,new Date(1602835200000L)));
            if (Objects.nonNull(position.getBuyPosition())){
                log.info("getBuyPosition :{}",position.getBuyPosition().getVolume());
            }
            log.info("{}",position.getSellPosition().getVolume());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
