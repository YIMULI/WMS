package com.yjxxt.wms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.Goods;
import com.yjxxt.wms.bean.SellGoods;
import com.yjxxt.wms.dao.GoodsMapper;
import com.yjxxt.wms.dao.SellGoodsMapper;
import com.yjxxt.wms.dao.UserMapper;
import com.yjxxt.wms.query.SellGoodsQuery;
import com.yjxxt.wms.util.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SellGoodsService extends BaseService<SellGoods,Integer> {

    @Resource
    private SellGoodsMapper sellGoodsMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private UserMapper userMapper;

    /***
     * 作用域获取对象
     * @param id
     * @return
     */
    public SellGoods queryByPrimaryKey(Integer id){
      return   sellGoodsMapper.queryByPrimaryKey(id);
    }

    /***+
     * 获取list列表显示数据 Map
     * @param
     * @return
     */
    public Map<String,Object> getMap(SellGoodsQuery sellGoodsQuery){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(sellGoodsQuery.getPage(),sellGoodsQuery.getLimit());
        PageInfo<SellGoods> pageInfo = new PageInfo<>(sellGoodsMapper.selectByParams(sellGoodsQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加出库记录
     * @param sellGoods
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addChance(SellGoods sellGoods){
        checkParams(sellGoods.getGoodsId(),sellGoods.getSellNum());//验证 商品编号 出库数量
        AssertUtil.isTrue(sellGoods.getUserId() == null || sellGoods.getUserId() == 0,"用户编号不能为空或0");
        AssertUtil.isTrue(userMapper.selectByPrimaryKey(sellGoods.getUserId()) == null,"用户编号不存在");

        //分配
        sellGoods.setIsValid(1);
        sellGoods.setSellDate(new Date());
        //添加是否成功
        AssertUtil.isTrue(insertSelective(sellGoods)<1,"出库失败");
        Goods goods = goodsMapper.selectByPrimaryKey(sellGoods.getGoodsId());
        goods.setGoodsNum(goods.getGoodsNum()-sellGoods.getSellNum());//商品表中更新数量
        AssertUtil.isTrue(goodsMapper.updateByPrimaryKey(goods)<1,"出库失败");
    }

    /**
     * 验证 商品id 出库数量 以及出库的数量是否大于原有的数量
     * @param goodsId
     * @param sellNum
     */
    private void checkParams(Integer goodsId, Integer sellNum) {
        AssertUtil.isTrue(goodsId==null,"商品id不能为空");
        AssertUtil.isTrue(sellNum==null || sellNum<=0,"商品出库数量不能为空、0或负数");
        AssertUtil.isTrue(goodsMapper.selectByPrimaryKey(goodsId)==null,"商品不存在");
        AssertUtil.isTrue(goodsMapper.selectByPrimaryKey(goodsId).getGoodsNum()<sellNum,"出库数量不能大于原有的数量");
    }



    /***
     * 更新出库记录
     * @param sellGoods
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void replaceChance(SellGoods sellGoods){
        System.out.println(sellGoods);
        checkParams(sellGoods.getGoodsId(),sellGoods.getSellNum());//验证 商品id 出库数量

        SellGoods temp = sellGoodsMapper.selectByPrimaryKey(sellGoods.getGoodsId());//根据商品id获取商品出库记录
        AssertUtil.isTrue(temp==null,"商品不存在");

       AssertUtil.isTrue(userMapper.selectByPrimaryKey(sellGoods.getUserId()) == null,"用户不存在");//用户编号是否存在

        //差数选择   更新后的出库数量可能大于原出库 也可能小于原出库
        int sellNum = 0;//存放差数
        if(temp.getSellNum()<sellGoods.getSellNum()){//库内出库数小于新出库数 等于原有商品数减去差数
            sellNum = sellGoods.getSellNum()-temp.getSellNum();
            AssertUtil.isTrue(sellGoodsMapper.updateByPrimaryKeySelective(sellGoods)<1,"出库记录更新失败");
            Goods goods = goodsMapper.selectByPrimaryKey(sellGoods.getGoodsId());
            goods.setGoodsNum(goods.getGoodsNum()-sellNum);//商品表中更新数量
            AssertUtil.isTrue(goodsMapper.updateByPrimaryKey(goods)<1,"出库记录更新失败");
        }else if(temp.getSellNum()>sellGoods.getSellNum()){//库内出库数大于新出库数 等于原有商品数加上差数
            sellNum = temp.getSellNum()-sellGoods.getSellNum();
            AssertUtil.isTrue(sellGoodsMapper.updateByPrimaryKeySelective(sellGoods)<1,"出库记录更新失败");
            Goods goods = goodsMapper.selectByPrimaryKey(sellGoods.getGoodsId());
            goods.setGoodsNum(goods.getGoodsNum()+sellNum);//商品表中更新数量
            AssertUtil.isTrue(goodsMapper.updateByPrimaryKey(goods)<1,"出库记录更新失败");
        }else{//出库数未改动
            AssertUtil.isTrue(sellGoodsMapper.updateByPrimaryKeySelective(sellGoods)<1,"出库记录更新失败");
        }

    }


    /***
     * 删除出库库记录
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteChance(Integer[] ids) {//接收一个id组成的数据 integer
        AssertUtil.isTrue((ids==null || ids.length==0),"未选择删除入库记录");
        AssertUtil.isTrue(sellGoodsMapper.deleteBatch(ids)<0,"删除入库记录失败");//删除数据判断返回值 有效行数是否大于0
    }
}
