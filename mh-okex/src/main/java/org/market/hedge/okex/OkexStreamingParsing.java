package org.market.hedge.okex;

import org.market.hedge.core.StreamingParsing;
import org.market.hedge.core.TradingArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OkexStreamingParsing extends StreamingParsing {

    private static final Logger logger= LoggerFactory.getLogger(OkexStreamingParsing.class);

    public OkexStreamingParsing(TradingArea tradingArea) {
        super(tradingArea);
    }


}
