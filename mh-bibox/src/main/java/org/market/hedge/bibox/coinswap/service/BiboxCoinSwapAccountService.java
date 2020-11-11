package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.dto.account.FundingRecord.Type;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.trade.params.HistoryParamsFundingType;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrency;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;
import org.market.hedge.bibox.dto.BiboxAdapters;
import org.market.hedge.bibox.dto.account.BiboxFundsCommandBody;
import org.market.hedge.bibox.service.BiboxAccountServiceRaw;
import org.market.hedge.service.account.MHAccountService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** @author odrotleff */
public class BiboxCoinSwapAccountService extends BiboxCoinSwapAccountServiceRaw implements MHAccountService {

  public BiboxCoinSwapAccountService(Exchange exchange) {
    super(exchange);
  }
}
