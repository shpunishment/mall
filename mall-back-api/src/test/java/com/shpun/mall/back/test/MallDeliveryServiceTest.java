package com.shpun.mall.back.test;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.MallDelivery;
import com.shpun.mall.common.service.MallDeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/10 15:59
 */
@SpringBootTest
public class MallDeliveryServiceTest {

    @Autowired
    private MallDeliveryService deliveryService;

    @Test
    public void insert() {
        MallDelivery delivery1 = buildMallDelivery("配送员1", "17811112222");
        deliveryService.insertSelective(delivery1);

        MallDelivery delivery2 = buildMallDelivery("配送员2", "17833334444");
        deliveryService.insertSelective(delivery2);

        MallDelivery delivery3 = buildMallDelivery("配送员3", "17855556666");
        deliveryService.insertSelective(delivery3);

    }

    private MallDelivery buildMallDelivery(String name, String phone) {
        MallDelivery delivery = new MallDelivery();
        delivery.setCreateId(Const.ADMIN_ID);
        delivery.setName(name);
        delivery.setPhone(phone);
        return delivery;
    }

}
