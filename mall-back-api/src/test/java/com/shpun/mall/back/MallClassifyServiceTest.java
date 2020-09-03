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

    @Test
    public void insertFirstClassify() {
        MallClassify classify1 = buildMallClassify(1,"水果", 0);
        // classify_id = 1
        classifyService.insertSelective(classify1);

        MallClassify classify2 = buildMallClassify(1,"蔬菜", 0);
        // classify_id = 2
        classifyService.insertSelective(classify2);

        MallClassify classify3 = buildMallClassify(1,"肉禽蛋", 0);
        // classify_id = 3
        classifyService.insertSelective(classify3);
    }

    @Test
    public void insertSecondClassify() {
        MallClassify classify1_1 = buildMallClassify(2,"热带水果", 1);
        classifyService.insertSelective(classify1_1);
        MallClassify classify1_2 = buildMallClassify(2,"果切果盘", 1);
        classifyService.insertSelective(classify1_2);
        MallClassify classify1_3 = buildMallClassify(2,"台湾水果", 1);
        classifyService.insertSelective(classify1_3);
        MallClassify classify1_4 = buildMallClassify(2,"水果礼盒", 1);
        classifyService.insertSelective(classify1_4);
        MallClassify classify1_5 = buildMallClassify(2,"进口水果", 1);
        classifyService.insertSelective(classify1_5);

        MallClassify classify2_1 = buildMallClassify(2,"苗菜", 2);
        classifyService.insertSelective(classify2_1);
        MallClassify classify2_2 = buildMallClassify(2,"叶菜", 2);
        classifyService.insertSelective(classify2_2);
        MallClassify classify2_3 = buildMallClassify(2,"根茎菜", 2);
        classifyService.insertSelective(classify2_3);
        MallClassify classify2_4 = buildMallClassify(2,"调味菜", 2);
        classifyService.insertSelective(classify2_4);
        MallClassify classify2_5 = buildMallClassify(2,"菌菇", 2);
        classifyService.insertSelective(classify2_5);
        MallClassify classify2_6 = buildMallClassify(2,"品牌蔬菜", 2);
        classifyService.insertSelective(classify2_6);
        MallClassify classify2_7 = buildMallClassify(2,"地方菜", 2);
        classifyService.insertSelective(classify2_7);

        MallClassify classify3_1 = buildMallClassify(2,"冻猪肉", 3);
        classifyService.insertSelective(classify3_1);
        MallClassify classify3_2 = buildMallClassify(2,"鲜牛羊肉", 3);
        classifyService.insertSelective(classify3_2);
        MallClassify classify3_3 = buildMallClassify(2,"鲜家禽", 3);
        classifyService.insertSelective(classify3_3);
        MallClassify classify3_4 = buildMallClassify(2,"蛋", 3);
        classifyService.insertSelective(classify3_4);
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
