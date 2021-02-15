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
        MHExchange bibox= MHExchangeFactory.INSTANCE.createExchange(BiboxExchange.class,"50a67d14c3edd429ae212a31cf29df3b31c31feb","1023dfbb1d5e08c49452029b18e6a5003e7b3c68", TradingArea.CoinSwap);
        StreamingParsingCurrencyPair parsing=bibox.getStreamingParsing().parsingCurrencyPair;
        logger.warn("sss----{}",parsing.parsing(CurrencyPair.BTC_USD).getParsing());
        MHAccountService accountService=bibox.getAccountService();
        BilateralPositionInfo result=accountService.getBilateralPosition(parsing.parsing(CurrencyPair.BTC_USD));
        result.getBuyPosition();
    }

}
