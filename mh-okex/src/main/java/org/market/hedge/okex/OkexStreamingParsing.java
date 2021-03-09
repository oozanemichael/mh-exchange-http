package org.market.hedge.okex;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.StreamingParsing;
import org.market.hedge.core.TradingArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SPOT：币币
 * SWAP：永续合约
 * FUTURES：交割合约
 * OPTION：期权
 * @author chuxianbo
 * */
public class OkexStreamingParsing extends StreamingParsing {

    private static final Logger logger= LoggerFactory.getLogger(OkexStreamingParsing.class);

    public OkexStreamingParsing(TradingArea tradingArea) {
        super(tradingArea);
    }


    @Override
    public ParsingCurrencyPair instanceSpot(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair(currencyPair.base.toString()+"-"+currencyPair.counter.toString()+"-SPOT",currencyPair, args);
    }

    /**
     * BTC-USD-SWAP
     * */
    @Override
    public ParsingCurrencyPair instancePerpetualSwap(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair(currencyPair.base.toString()+"-"+currencyPair.counter.toString()+"-SWAP",currencyPair, args);
    }

    @Override
    public ParsingCurrencyPair instanceCoinSwap(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair(currencyPair.base.toString()+"-"+currencyPair.counter.toString()+"-SWAP",currencyPair, args);
    }


}
