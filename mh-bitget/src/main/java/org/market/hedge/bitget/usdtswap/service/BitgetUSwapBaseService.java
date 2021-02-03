package org.market.hedge.bitget.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.market.hedge.bitget.usdtswap.BitgetUSwapAuthenticated;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class BitgetUSwapBaseService extends BaseExchangeService implements BaseService {

  protected final String apiKey;
  protected final BitgetUSwapAuthenticated bibox;
  protected final ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected BitgetUSwapBaseService(Exchange exchange) {
    super(exchange);
    this.bibox =
        RestProxyFactory.createProxy(
                BitgetUSwapAuthenticated.class,
            exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    //this.signatureCreator =BitgetUSwapDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    signatureCreator = null;
  }

  protected static void throwErrors() {
    /*if (response.getError() != null) {
      throw new ExchangeException(
          response.getError().getCode() + ": " + response.getError().getMsg());
    }*/
  }
}
