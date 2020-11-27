package org.market.hedge.deribit;

import org.market.hedge.BaseMHExchange;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.core.TradingArea;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.exception.NullTradingAreaException;
import org.market.hedge.deribit.dto.marketdata.HuobiAsset;
import org.market.hedge.deribit.dto.marketdata.HuobiAssetPair;
import org.market.hedge.deribit.futures.service.HuobiFuturesAccountService;
import org.market.hedge.deribit.futures.service.HuobiFuturesMarketDataService;
import org.market.hedge.deribit.futures.service.HuobiFuturesTradeService;
import org.market.hedge.deribit.option.service.HuobiOptionAccountService;
import org.market.hedge.deribit.option.service.HuobiOptionMarketDataService;
import org.market.hedge.deribit.option.service.HuobiOptionTradeService;
import org.market.hedge.deribit.service.HuobiAccountService;
import org.market.hedge.deribit.service.HuobiMarketDataService;
import org.market.hedge.deribit.service.HuobiMarketDataServiceRaw;
import org.market.hedge.deribit.service.HuobiTradeService;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import org.market.hedge.deribit.swap.service.HuobiSwapMarketDataService;
import org.market.hedge.deribit.swap.service.HuobiSwapTradeService;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;

public class HuobiExchange extends BaseMHExchange implements MHExchange {

  private final SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  protected void initServices() {
    switch (mHexchangeSpecification.getTradingArea()){
      case Spot:
        this.mHmarketDataService = new HuobiMarketDataService(this);
        this.mHtradeService = new HuobiTradeService(this);
        this.mHaccountService = new HuobiAccountService(this);
        break;
      case Option:
        this.mHmarketDataService = new HuobiOptionMarketDataService(this);
        this.mHtradeService = new HuobiOptionTradeService(this);
        this.mHaccountService = new HuobiOptionAccountService(this);
        break;
      case PerpetualSwap:
        this.mHmarketDataService=new HuobiSwapMarketDataService(this);
        this.mHtradeService=new HuobiSwapTradeService(this);
        break;
      case Futures:
        this.mHmarketDataService=new HuobiFuturesMarketDataService(this);
        this.mHtradeService=new HuobiFuturesTradeService(this);
        this.mHaccountService=new HuobiFuturesAccountService(this);
        break;
      default:
        throw new NullTradingAreaException(mHexchangeSpecification.getTradingArea());
    }

  }

  @Override
  public MHExchangeSpecification getDefaultExchangeSpecification(TradingArea tradingArea) {
    this.streamingParsing=new HuobiStreamingParsing(tradingArea);
    MHExchangeSpecification exchangeSpecification =
        new MHExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setTradingArea(tradingArea);
    switch (tradingArea){
      case Spot:
        exchangeSpecification.setSslUri("https://api.huobi.pro");
        exchangeSpecification.setHost("api.huobi.pro");
        exchangeSpecification.setPort(80);
        exchangeSpecification.setExchangeName("Huobi");
        exchangeSpecification.setExchangeDescription(
                "Huobi is a Chinese digital currency trading platform and exchange based in Beijing");
        return exchangeSpecification;
      case Option:
      case PerpetualSwap:
      case Futures:
        exchangeSpecification.setSslUri("https://api.hbdm.com");
        exchangeSpecification.setHost("api.hbdm.com");
        exchangeSpecification.setPort(80);
        exchangeSpecification.setExchangeName("Huobi");
        exchangeSpecification.setExchangeDescription(
                "Huobi is a Chinese digital currency trading platform and exchange based in Beijing");
        return exchangeSpecification;
      default:
        break;
    }
    throw new NullTradingAreaException(tradingArea);
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }


  @Override
  public void remoteInit() throws IOException, ExchangeException {
    switch (mHexchangeSpecification.getTradingArea()){
      case Spot:
        HuobiAssetPair[] assetPairs =
                ((HuobiMarketDataServiceRaw) mHmarketDataService).getHuobiAssetPairs();
        HuobiAsset[] assets = ((HuobiMarketDataServiceRaw) mHmarketDataService).getHuobiAssets();
        exchangeMetaData = HuobiAdapters.adaptToExchangeMetaData(assetPairs, assets, exchangeMetaData);
        break;
      case Option:
      default:
        break;
    }
  }
}
