package com.shpun.mall.back;

import com.shpun.mall.common.model.MallCart;
import com.shpun.mall.common.service.MallCartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallCartServiceTest {

    @Autowired
    private MallCartService cartService;

    @Test
    public void insert() {
        MallCart cart1 = buildMallCart(null, 1, 1, 1);
        cartService.insertSelective(cart1);

        MallCart cart2 = buildMallCart(null, 1, 2, 1);
        cartService.insertSelective(cart2);

        MallCart cart3 = buildMallCart(null, 1, 3, 9);
        cartService.insertSelective(cart3);
    }

    @Test
    public void update() {
        MallCart cart1 = buildMallCart(1, 1, 1, 2);
        cartService.updateByPrimaryKeySelective(cart1);

        MallCart cart2 = buildMallCart(2, 1, 2, 3);
        cartService.updateByPrimaryKeySelective(cart2);
    }

    @Test
    public void delete() {
        cartService.deleteByPrimaryKey(1);
    }

    @Test
    public void deleteAll() {
        cartService.deleteAllByUserId(1);
    }

    private MallCart buildMallCart(Integer cartId, Integer userId, Integer productId, Integer quantity) {
        MallCart cart = new MallCart();
        cart.setCartId(cartId);
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        return cart;
    }

}
