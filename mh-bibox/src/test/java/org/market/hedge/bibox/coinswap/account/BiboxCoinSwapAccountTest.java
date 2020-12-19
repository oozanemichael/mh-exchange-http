package org.market.hedge.bibox.coinswap.account;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.bibox.BiboxExchange;
import org.market.hedge.core.BilateralPositionInfo;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.account.MHAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BiboxCoinSwapAccountTest {

    private static Logger logger= LoggerFactory.getLogger(BiboxCoinSwapAccountTest.class);


    @Test
    public void getBilateralPosition() throws IOException {
        MHExchange bibox= MHExchangeFactory.INSTANCE.createExchange(BiboxExchange.class,"edf57c404c7724434f328fd7ed6f37e08055f375","dc1df5368ea42a6b2761fa296a11be77288415e9", TradingArea.CoinSwap);
        StreamingParsingCurrencyPair parsing=bibox.getStreamingParsing().parsingCurrencyPair;
        logger.warn("sss----{}",parsing.parsing(CurrencyPair.BTC_USD).getParsing());
        MHAccountService accountService=bibox.getAccountService();
        BilateralPositionInfo result=accountService.getBilateralPosition(parsing.parsing(CurrencyPair.BTC_USD));
        result.getBuyPosition();
    }

}
