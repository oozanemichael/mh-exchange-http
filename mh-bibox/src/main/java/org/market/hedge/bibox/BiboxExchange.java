package org.market.hedge.bibox;

import java.io.IOException;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import org.market.hedge.BaseMHExchange;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.bibox.coinswap.service.BiboxCoinSwapAccountService;
import org.market.hedge.bibox.coinswap.service.BiboxCoinSwapMarketDataService;
import org.market.hedge.bibox.coinswap.service.BiboxCoinSwapTradeService;
import org.market.hedge.bibox.service.BiboxAccountService;
import org.market.hedge.bibox.service.BiboxMarketDataService;
import org.market.hedge.bibox.service.BiboxTradeService;
import org.market.hedge.core.TradingArea;
import org.market.hedge.exception.NullTradingAreaException;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class BiboxExchange extends BaseMHExchange implements MHExchange {

  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  protected void initServices() {
    switch (mHexchangeSpecification.getTradingArea()) {
      case Spot:
        this.mHmarketDataService = new BiboxMarketDataService(this);
        this.mHaccountService = new BiboxAccountService(this);
        this.mHtradeService = new BiboxTradeService(this);
        return;
      case CoinSwap:
        this.mHaccountService = new BiboxCoinSwapAccountService(this);
        this.mHmarketDataService = new BiboxCoinSwapMarketDataService(this);
        this.mHtradeService = new BiboxCoinSwapTradeService(this);
        return;
      default:
        throw new NullTradingAreaException(mHexchangeSpecification.getTradingArea());
    }
  }

  @Override
  public MHExchangeSpecification getDefaultExchangeSpecification(TradingArea tradingArea) {
    this.streamingParsing=new BiboxStreamingParsing(tradingArea);
    MHExchangeSpecification exchangeSpecification =
            new MHExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setTradingArea(tradingArea);
    switch (tradingArea){
      case Spot:
      case CoinSwap:
        exchangeSpecification.setSslUri("https://api.bibox.com/");
        exchangeSpecification.setHost("bibox.com");
        exchangeSpecification.setPort(80);
        exchangeSpecification.setExchangeName("Bibox");
        exchangeSpecification.setExchangeDescription("AI ENHANCED ENCRYPTED DIGITAL ASSET EXCHANGE.");
        return exchangeSpecification;
      case Margin:
      default:
        throw new NullTradingAreaException(tradingArea);
    }

  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    return null;
  }

  @Override
  public void applySpecification(ExchangeSpecification exchangeSpecification) {

  }

  @Override
  public void remoteInit() throws IOException, ExchangeException {
    switch (mHexchangeSpecification.getTradingArea()){
      case Spot:
        exchangeMetaData = ((BiboxMarketDataService) marketDataService).getMetadata();
        return;
      default:
    }

  }
}
