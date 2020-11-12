/* *****************************************************************************
 * Copyright (C) 2020  QHHQ Co.,Ltd
 * All Rights Reserved.
 * 本软件为物连家美网络技术有限公司研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.market.hedge.bibox.coinswap.req.BiboxPlaceLimitOrderReqBo;

import java.io.IOException;

/**
 * @ClassName: SwapService
 * @Version: 1.0
 * @Desc: Bibox币本位永续合约 相关接口
 * @author: liusongqi
 * @Date: 2020/11/12 14:33
 */
public interface BiboxSwapService extends TradeService {

    /**
     * 下一个订单
     */
    default Object placeLimitOrder(BiboxPlaceLimitOrderReqBo biboxPlaceLimitOrderReqBo)throws IOException{
        throw new NotYetImplementedForExchangeException();
    }
}
