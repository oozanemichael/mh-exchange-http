package org.market.hedge.okex;

import org.market.hedge.BaseMHExchange;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.core.TradingArea;
import si.mazi.rescu.SynchronizedValueFactory;

public class OkexExchange extends BaseMHExchange implements MHExchange {

    @Override
    protected void initServices() {

    }

    @Override
    public MHExchangeSpecification getDefaultExchangeSpecification(TradingArea tradingArea) {
        return null;
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        return null;
    }
}
