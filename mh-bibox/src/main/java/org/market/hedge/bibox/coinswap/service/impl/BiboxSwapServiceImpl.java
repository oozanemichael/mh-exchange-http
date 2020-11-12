/* *****************************************************************************
 * Copyright (C) 2020  QHHQ Co.,Ltd
 * All Rights Reserved.
 * 本软件为物连家美网络技术有限公司研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.market.hedge.bibox.coinswap.service.impl;

import org.market.hedge.bibox.coinswap.BiboxSwap;
import org.market.hedge.bibox.coinswap.req.BiboxPlaceLimitOrderReqBo;
import org.market.hedge.bibox.coinswap.resp.BiboxPlaceLimitOrderRespVo;
import org.market.hedge.bibox.coinswap.service.BiboxSwapService;
import org.market.hedge.dto.trade.MHLimitOrder;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @ClassName: BiboxSwapServiceImpl
 * @Version: 1.0
 * @Desc: java类作用描述
 * @author: liusongqi
 * @Date: 2020/11/12 14:37
 */
public class BiboxSwapServiceImpl implements BiboxSwapService {

    @Resource
    private BiboxSwap biboxSwap;

    @Override
    public String placeLimitOrder(BiboxPlaceLimitOrderReqBo biboxPlaceLimitOrderReqBo) throws IOException {
        BiboxPlaceLimitOrderRespVo result =
                biboxSwap.placeLimitOrder(
                        getHuobiOptionCreateOrderRequest(LimitOrder),
                        exchange.getExchangeSpecification().getApiKey(),
                        HuobiDigest.HMAC_SHA_256,
                        2,
                        HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                        signatureCreator);

        return checkResult(result).getOrder_id().toString();
    }
}
