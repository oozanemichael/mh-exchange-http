package org.market.hedge.bibox.coinswap.dto.trade.req;

import org.market.hedge.bibox.dto.BiboxCommand;
import org.market.hedge.bibox.dto.trade.BiboxTradeCommandBody;

public class BiboxCoinSwapOrder extends BiboxCommand<BiboxCoinSwapOrderReqBo> {

    public BiboxCoinSwapOrder(BiboxCoinSwapOrderReqBo body) {
        super("", body);
    }
}
