package org.market.hedge.bibox.coinswap.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import sun.plugin2.message.Message;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: PlaceLimitOrderReqBo
 * @Version: 1.0
 * @Desc: Bibox下一个订单的请求参数
 * @author: liusongqi
 * @Date: 2020/11/12 13:56
 */
@Data
public class BiboxPlaceLimitOrderReqBo {

    /**
     * 合约符号
     */
    @JsonProperty("pair")
    private String pair;

    /**
     * 数量转字符串
     */
    @JsonProperty("amount")
    @NotNull(message = "数量转字符串不可为空")
    private String amount;

    /**
     * 下单类型,1开多,2开空,3平多,4平空
     */
    @JsonProperty("order_side")
    @NotNull(message = "下单类型不可为空")
    private Integer orderSide;

    /**
     * 下单方式,1市价单,2限价单
     */
    @JsonProperty("order_type")
    @NotNull(message = "下单方式不可为空")
    private Integer orderType;

    /**
     * 下单价格
     */
    @JsonProperty("price")
    @NotNull(message = "下单价格不可为空")
    private String price;

    /**
     * 下单来源
     */
    @JsonProperty("order_from")
    @NotNull(message = "下单来源不可为空")
    private Integer orderFrom;

    /**
     * 自定义id
     */
    @JsonProperty("client_oid")
    private String clientOid;

}
