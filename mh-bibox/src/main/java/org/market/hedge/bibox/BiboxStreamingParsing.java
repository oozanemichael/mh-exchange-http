package org.market.hedge.bibox;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.Direction;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.StreamingParsing;
import org.market.hedge.core.TradingArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BiboxStreamingParsing extends StreamingParsing {

    private static final Logger LOGGER= LoggerFactory.getLogger(BiboxStreamingParsing.class);

    public BiboxStreamingParsing(TradingArea tradingArea) {
        super(tradingArea);
    }

    /**
     * eg: 5BTC_USDT
     *
     * */
    @Override
    public ParsingCurrencyPair instanceCoinSwap(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair("5"+currencyPair.base.toString()+"_"+currencyPair.counter.toString(),currencyPair, args);

    }

    @Override
    public ParsingCurrencyPair instanceSpot(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair(null,currencyPair, args);
    }



}
