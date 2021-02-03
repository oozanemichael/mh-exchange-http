package org.market.hedge.bitget.usdtswap;


import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface BitgetUSwapAuthenticated extends BitgetUSwap {


}
