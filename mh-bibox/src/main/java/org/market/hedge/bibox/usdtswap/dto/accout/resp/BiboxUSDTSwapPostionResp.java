package org.market.hedge.bibox.usdtswap.dto.accout.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiboxUSDTSwapPostionResp {

    /**
     * 合约符号
     */
    String pair;

    /**
     * 持仓张数
     */
    String contract;

    /**
     * 持仓保证金
     */
    String margin;

    /**
     * 开仓均价
     */
    String price;

    /**
     * 开仓方向：1开多，2开空
     */
    String order_side;

    /**
     * 强平价格
     */
    String price_force;

    /**
     * 0:全仓，其它:逐仓杠杆倍数
     */
    String leverage;

    /**
     * 开多未成交订单张数
     */
    String icontract_up;

    /**
     * 开空未成交订单张数
     */
    String icontract_down;

    public BiboxUSDTSwapPostionResp(
            @JsonProperty("sss") String pair,
            @JsonProperty("contract")  String contract,
            @JsonProperty("margin")  String margin,
            @JsonProperty("price")  String price,
            @JsonProperty("order_side")  String order_side,
            @JsonProperty("price_force")  String price_force,
            @JsonProperty("leverage")  String leverage,
            @JsonProperty("icontract_up")  String icontract_up,
            @JsonProperty("icontract_down")  String icontract_down) {
        this.pair = pair;
        this.contract = contract;
        this.margin = margin;
        this.price = price;
        this.order_side = order_side;
        this.price_force = price_force;
        this.leverage = leverage;
        this.icontract_up = icontract_up;
        this.icontract_down = icontract_down;
    }

    public String getPair() {
        return pair;
    }

    public String getContract() {
        return contract;
    }

    public String getMargin() {
        return margin;
    }

    public String getPrice() {
        return price;
    }

    public String getOrder_side() {
        return order_side;
    }

    public String getPrice_force() {
        return price_force;
    }

    public String getLeverage() {
        return leverage;
    }

    public String getIcontract_up() {
        return icontract_up;
    }

    public String getIcontract_down() {
        return icontract_down;
    }
}
