/* *****************************************************************************
 * Copyright (C) 2020  QHHQ Co.,Ltd
 * All Rights Reserved.
 * 本软件为物连家美网络技术有限公司研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.market.hedge.bibox.coinswap.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @ClassName: BiboxPlaceLimitOrderRespVo
 * @Version: 1.0
 * @Desc: java类作用描述
 * @author: liusongqi
 * @Date: 2020/11/12 14:19
 */
@Data
public class BiboxPlaceLimitOrderRespVo {
    /**
     * 0代表成功，否则代表失败
     */
    private String state;

    /**
     * 订单id
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 自定义id
     */
    @JsonProperty("client_oid")
    private String clientOid;

    /**
     * 订单状态 1等待成交，2部分成交，3全部成交，4部分撤销，5全部撤销，100下单失败
     */
    private String status;


}
