package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.bibox.BiboxException;
import org.market.hedge.bibox.coinswap.dto.trade.req.BiboxCoinSwapOrder;
import org.market.hedge.bibox.coinswap.dto.trade.req.BiboxCoinSwapOrderReqBo;
import org.market.hedge.bibox.dto.BiboxCommands;
import org.market.hedge.bibox.dto.BiboxSingleResponse;
import org.market.hedge.dto.trade.MHLimitOrder;

public class BiboxCoinSwapTradeServiceRaw  extends BiboxCoinSwapBaseService{
    /**
     * Constructor
     *
     * @param exchange
     */
    protected BiboxCoinSwapTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public String placeBiboxLimitOrder(MHLimitOrder LimitOrder) {

        try {
            BiboxCoinSwapOrder cmd =
                    new BiboxCoinSwapOrder(new BiboxCoinSwapOrderReqBo(
                            LimitOrder.getParsingCurrencyPair().getParsing(),
                            LimitOrder.getOriginalAmount().toPlainString(),
                            null,
                            null,
                            null,
                            null,
                            null
                    ));
            BiboxSingleResponse<String> response =
                    bibox.trade(BiboxCommands.of(cmd).json(), apiKey, signatureCreator);
            throwErrors(response);
            return response.get().getResult();
        } catch (BiboxException e) {
            throw new ExchangeException(e.getMessage());
        }
    }
}
