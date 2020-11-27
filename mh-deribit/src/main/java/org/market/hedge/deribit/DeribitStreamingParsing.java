package org.market.hedge.deribit;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.StreamingParsing;
import org.market.hedge.core.TradingArea;

public class DeribitStreamingParsing extends StreamingParsing {
    public DeribitStreamingParsing(TradingArea tradingArea) {
        super(tradingArea);
    }

    @Override
    public ParsingCurrencyPair instancePerpetualSwap(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair("",currencyPair);
    }

    @Override
    public ParsingCurrencyPair instanceOption(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair("",currencyPair);
    }
}
