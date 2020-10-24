package org.market.hedge.huobi.option;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.Direction;
import org.market.hedge.core.TradingArea;
import org.market.hedge.huobi.HuobiExchange;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.account.MHAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class HuobiOptionGetPosition {

    static Logger logger= LoggerFactory.getLogger(HuobiOptionGetPosition.class);

    public static void main(String[] args) {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"914069ad-uymylwhfeg-c8084d47-d25e0"
                ,"fc791193-3ab4649c-e052fc6a-7016f"
                , TradingArea.Option);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHAccountService accountService=huobi.getAccountService();
        try {
            logger.info("{}",accountService.getPosition(parsing.parsing(CurrencyPair.BTC_USDT
                    ,new Date(1599148800000L)
                    ,new BigDecimal("11500")
                    , Direction.Put)));
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

}
