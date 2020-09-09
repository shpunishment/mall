package com.shpun.mall.back.test;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.MallClassify;
import com.shpun.mall.common.service.MallClassifyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallClassifyServiceTest {

    @Autowired
    private MallClassifyService classifyService;

    /**
     * 测试
     */
    @Test
    public void insertFirstClassify() {
        MallClassify classify1 = buildMallClassify(1,"水果", 0);
        classifyService.insertSelective(classify1);

        MallClassify classify2 = buildMallClassify(2,"蔬菜", 0);
        classifyService.insertSelective(classify2);

        MallClassify classify3 = buildMallClassify(3,"肉禽蛋", 0);
        classifyService.insertSelective(classify3);
    }

    /**
     * 测试
     */
    @Test
    public void insertSecondClassify() {
        // 水果
        MallClassify classify1_1 = buildMallClassify(null,"热带水果", 1);
        classifyService.insertSelective(classify1_1);
        MallClassify classify1_2 = buildMallClassify(null,"果切果盘", 1);
        classifyService.insertSelective(classify1_2);
        MallClassify classify1_3 = buildMallClassify(null,"进口水果", 1);
        classifyService.insertSelective(classify1_3);
        MallClassify classify1_4 = buildMallClassify(null,"台湾水果", 1);
        classifyService.insertSelective(classify1_4);
        MallClassify classify1_5 = buildMallClassify(null,"水果礼盒", 1);
        classifyService.insertSelective(classify1_5);

        // 蔬菜
        MallClassify classify2_1 = buildMallClassify(null,"苗菜", 2);
        classifyService.insertSelective(classify2_1);
        MallClassify classify2_2 = buildMallClassify(null,"叶菜", 2);
        classifyService.insertSelective(classify2_2);
        MallClassify classify2_3 = buildMallClassify(null,"根茎菜", 2);
        classifyService.insertSelective(classify2_3);
        MallClassify classify2_4 = buildMallClassify(null,"调味菜", 2);
        classifyService.insertSelective(classify2_4);
        MallClassify classify2_5 = buildMallClassify(null,"豆瓜果茄", 2);
        classifyService.insertSelective(classify2_5);
        MallClassify classify2_6 = buildMallClassify(null,"菌菇", 2);
        classifyService.insertSelective(classify2_6);
        MallClassify classify2_7 = buildMallClassify(null,"地方菜", 2);
        classifyService.insertSelective(classify2_7);
        MallClassify classify2_8 = buildMallClassify(null,"豆制品", 2);
        classifyService.insertSelective(classify2_8);

        // 肉禽蛋
        MallClassify classify3_1 = buildMallClassify(null,"土猪肉", 3);
        classifyService.insertSelective(classify3_1);
        MallClassify classify3_2 = buildMallClassify(null,"冻猪肉", 3);
        classifyService.insertSelective(classify3_2);
        MallClassify classify3_3 = buildMallClassify(null,"鲜牛羊肉", 3);
        classifyService.insertSelective(classify3_3);
        MallClassify classify3_4 = buildMallClassify(null,"冻牛羊肉", 3);
        classifyService.insertSelective(classify3_4);
        MallClassify classify3_5 = buildMallClassify(null,"鲜家禽", 3);
        classifyService.insertSelective(classify3_5);
        MallClassify classify3_6 = buildMallClassify(null,"冻家禽", 3);
        classifyService.insertSelective(classify3_6);
        MallClassify classify3_7 = buildMallClassify(null,"蛋", 3);
        classifyService.insertSelective(classify3_7);
        MallClassify classify3_8 = buildMallClassify(null,"牛排", 3);
        classifyService.insertSelective(classify3_8);
    }

    private MallClassify buildMallClassify(Integer pic, String classifyName, Integer pid){
        MallClassify classify = new MallClassify();
        classify.setCreateId(Const.ADMIN_ID);
        classify.setPic(pic);
        classify.setClassifyName(classifyName);
        classify.setPid(pid);
        return classify;
    }

    @Test
    public void selectByPid(){
        List<MallClassify> classifyList0 = classifyService.getByPid(0);
        List<MallClassify> classifyList1 = classifyService.getByPid(1);
        List<MallClassify> classifyList2 = classifyService.getByPid(2);
        List<MallClassify> classifyList3 = classifyService.getByPid(3);

        System.out.println();
    }

    @Test
    public void up(){
        classifyService.up(2);
    }

    @Test
    public void down() {
        classifyService.down(1);
    }

    @Test
    public void top() {
        classifyService.top(1);
    }

    @Test
    public void delete(){
        classifyService.deleteByPrimaryKey(1);
    }

}
