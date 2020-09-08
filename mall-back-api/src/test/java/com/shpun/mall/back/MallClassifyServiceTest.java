package com.shpun.mall.back;

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
     * 造数据
     */
    @Test
    public void insertFirstClassify2() {
        MallClassify classify4 = buildMallClassify(4,"海鲜水产", 0);
        classifyService.insertSelective(classify4);

        MallClassify classify5 = buildMallClassify(5,"粮油调味", 0);
        classifyService.insertSelective(classify5);

        MallClassify classify6 = buildMallClassify(6,"熟食卤味", 0);
        classifyService.insertSelective(classify6);

        MallClassify classify7 = buildMallClassify(7,"冰品面点", 0);
        classifyService.insertSelective(classify7);

        MallClassify classify8 = buildMallClassify(8,"牛奶面包", 0);
        classifyService.insertSelective(classify8);

        MallClassify classify9 = buildMallClassify(9,"酒水冲饮", 0);
        classifyService.insertSelective(classify9);

        MallClassify classify10 = buildMallClassify(null,"休闲零食", 0);
        classifyService.insertSelective(classify10);
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

    /**
     * 造数据
     */
    @Test
    public void insertSecondClassify2() {
        // 海鲜水产
        MallClassify classify4_1 = buildMallClassify(null,"鱼", 4);
        classifyService.insertSelective(classify4_1);
        MallClassify classify4_2 = buildMallClassify(null,"虾", 4);
        classifyService.insertSelective(classify4_2);
        MallClassify classify4_3 = buildMallClassify(null,"贝", 4);
        classifyService.insertSelective(classify4_3);
        MallClassify classify4_4 = buildMallClassify(null,"蟹", 4);
        classifyService.insertSelective(classify4_4);
        MallClassify classify4_5 = buildMallClassify(null,"鲜墨章鱼", 4);
        classifyService.insertSelective(classify4_5);

        // 粮油调味
        MallClassify classify5_1 = buildMallClassify(null,"大米", 5);
        classifyService.insertSelective(classify5_1);
        MallClassify classify5_2 = buildMallClassify(null,"食用油", 5);
        classifyService.insertSelective(classify5_2);
        MallClassify classify5_3 = buildMallClassify(null,"方便食品", 5);
        classifyService.insertSelective(classify5_3);
        MallClassify classify5_4 = buildMallClassify(null,"调味汁", 5);
        classifyService.insertSelective(classify5_4);
        MallClassify classify5_5 = buildMallClassify(null,"调味酱", 5);
        classifyService.insertSelective(classify5_5);
        MallClassify classify5_6 = buildMallClassify(null,"调味粉", 5);
        classifyService.insertSelective(classify5_6);
        MallClassify classify5_7 = buildMallClassify(null,"五谷杂粮", 5);
        classifyService.insertSelective(classify5_7);

        // 熟食卤味
        MallClassify classify6_1 = buildMallClassify(null,"卤味", 6);
        classifyService.insertSelective(classify6_1);
        MallClassify classify6_2 = buildMallClassify(null,"豆浆包点", 6);
        classifyService.insertSelective(classify6_2);
        MallClassify classify6_3 = buildMallClassify(null,"风味小吃", 6);
        classifyService.insertSelective(classify6_3);
        MallClassify classify6_4 = buildMallClassify(null,"家常菜", 6);
        classifyService.insertSelective(classify6_4);
        MallClassify classify6_5 = buildMallClassify(null,"家宴大菜", 6);
        classifyService.insertSelective(classify6_5);
        MallClassify classify6_6 = buildMallClassify(null,"主食汤品", 6);
        classifyService.insertSelective(classify6_6);
        MallClassify classify6_7 = buildMallClassify(null,"西式美食", 6);
        classifyService.insertSelective(classify6_7);
        MallClassify classify6_8 = buildMallClassify(null,"沙拉料理", 6);
        classifyService.insertSelective(classify6_8);

        // 冰品面点
        MallClassify classify7_1 = buildMallClassify(null,"冰淇淋", 7);
        classifyService.insertSelective(classify7_1);
        MallClassify classify7_2 = buildMallClassify(null,"冷饮甜品", 7);
        classifyService.insertSelective(classify7_2);
        MallClassify classify7_3 = buildMallClassify(null,"包子馒头", 7);
        classifyService.insertSelective(classify7_3);
        MallClassify classify7_4 = buildMallClassify(null,"水饺", 7);
        classifyService.insertSelective(classify7_4);
        MallClassify classify7_5 = buildMallClassify(null,"汤圆", 7);
        classifyService.insertSelective(classify7_5);
        MallClassify classify7_6 = buildMallClassify(null,"肉燕云吞", 7);
        classifyService.insertSelective(classify7_6);
        MallClassify classify7_7 = buildMallClassify(null,"特色点心", 7);
        classifyService.insertSelective(classify7_7);
        MallClassify classify7_8 = buildMallClassify(null,"火锅配菜", 7);
        classifyService.insertSelective(classify7_8);

        // 牛奶面包
        MallClassify classify8_1 = buildMallClassify(null,"鲜奶", 8);
        classifyService.insertSelective(classify8_1);
        MallClassify classify8_2 = buildMallClassify(null,"酸奶", 8);
        classifyService.insertSelective(classify8_2);
        MallClassify classify8_3 = buildMallClassify(null,"乳酸菌", 8);
        classifyService.insertSelective(classify8_3);
        MallClassify classify8_4 = buildMallClassify(null,"常温奶", 8);
        classifyService.insertSelective(classify8_4);
        MallClassify classify8_5 = buildMallClassify(null,"进口奶", 8);
        classifyService.insertSelective(classify8_5);
        MallClassify classify8_6 = buildMallClassify(null,"面包", 8);
        classifyService.insertSelective(classify8_6);
        MallClassify classify8_7 = buildMallClassify(null,"蛋糕", 8);
        classifyService.insertSelective(classify8_7);
        MallClassify classify8_8 = buildMallClassify(null,"生日蛋糕", 8);
        classifyService.insertSelective(classify8_8);

        // 酒水冲饮
        MallClassify classify9_1 = buildMallClassify(null,"啤酒", 9);
        classifyService.insertSelective(classify9_1);
        MallClassify classify9_2 = buildMallClassify(null,"可乐汽水", 9);
        classifyService.insertSelective(classify9_2);
        MallClassify classify9_3 = buildMallClassify(null,"果汁", 9);
        classifyService.insertSelective(classify9_3);
        MallClassify classify9_4 = buildMallClassify(null,"白酒", 9);
        classifyService.insertSelective(classify9_4);
        MallClassify classify9_5 = buildMallClassify(null,"葡萄酒", 9);
        classifyService.insertSelective(classify9_5);
        MallClassify classify9_6 = buildMallClassify(null,"洋酒", 9);
        classifyService.insertSelective(classify9_6);
        MallClassify classify9_7 = buildMallClassify(null,"日韩酒", 9);
        classifyService.insertSelective(classify9_7);
        MallClassify classify9_8 = buildMallClassify(null,"水", 9);
        classifyService.insertSelective(classify9_8);

        // 酒水冲饮
        MallClassify classify10_1 = buildMallClassify(null,"进口零食", 10);
        classifyService.insertSelective(classify10_1);
        MallClassify classify10_2 = buildMallClassify(null,"坚果炒货", 10);
        classifyService.insertSelective(classify10_2);
        MallClassify classify10_3 = buildMallClassify(null,"蜜饯果干", 10);
        classifyService.insertSelective(classify10_3);
        MallClassify classify10_4 = buildMallClassify(null,"饼干糕点", 10);
        classifyService.insertSelective(classify10_4);
        MallClassify classify10_5 = buildMallClassify(null,"薯片米饼", 10);
        classifyService.insertSelective(classify10_5);
        MallClassify classify10_6 = buildMallClassify(null,"巧克力", 10);
        classifyService.insertSelective(classify10_6);
        MallClassify classify10_7 = buildMallClassify(null,"肉干肉松", 10);
        classifyService.insertSelective(classify10_7);
        MallClassify classify10_8 = buildMallClassify(null,"海产小食", 10);
        classifyService.insertSelective(classify10_8);

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
