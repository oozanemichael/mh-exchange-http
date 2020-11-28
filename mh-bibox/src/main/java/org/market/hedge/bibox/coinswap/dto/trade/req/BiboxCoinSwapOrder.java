package org.market.hedge.bibox.coinswap.dto.trade.req;

import org.market.hedge.bibox.dto.BiboxCommand;

public class BiboxCoinSwapOrder extends BiboxCommand<BiboxCoinSwapOrderReqBo> {

    public BiboxCoinSwapOrder(BiboxCoinSwapOrderReqBo body) {
        super("", body);
    }
}
