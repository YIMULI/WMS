package com.yjxxt.wms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.AddGoods;
import com.yjxxt.wms.bean.Goods;
import com.yjxxt.wms.dao.AddGoodsMapper;
import com.yjxxt.wms.dao.AdminMapper;
import com.yjxxt.wms.dao.GoodsMapper;
import com.yjxxt.wms.query.AddGoodsQuery;
import com.yjxxt.wms.util.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddGoodsService extends BaseService<AddGoods,Integer> {

    @Resource
    private AddGoodsMapper addGoodsMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private AdminMapper adminMapper;

    /***+
     * 获取list列表显示数据 Map
     * @param addGoodsQuery
     * @return
     */
    public Map<String,Object> getMap(AddGoodsQuery addGoodsQuery){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(addGoodsQuery.getPage(),addGoodsQuery.getLimit());
        PageInfo<AddGoods> pageInfo = new PageInfo<>(addGoodsMapper.selectByParams(addGoodsQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }


    /**
     * 添加入库记录
     * @param addGoods
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addChance(AddGoods addGoods){
        checkParams(addGoods.getGoodsId(),addGoods.getAddNum());//验证 商品id 入库数量
        AssertUtil.isTrue(addGoods.getAdminId() == null || addGoods.getAdminId() == 0,"管理员编号不能为空或0");
        AssertUtil.isTrue(adminMapper.selectByPrimaryKey(addGoods.getAdminId())==null,"管理员编号不存在");
        //分配
        addGoods.setAddDate(new Date());
        addGoods.setIsValid(1);
        //添加是否成功
        AssertUtil.isTrue(insertSelective(addGoods)<1,"入库失败");
        Goods goods = goodsMapper.selectByPrimaryKey(addGoods.getGoodsId());

        if(goods != null ) {//存在即更新
            goods.setGoodsNum(goods.getGoodsNum() + addGoods.getAddNum());//商品表中更新数量
            AssertUtil.isTrue(goodsMapper.updateByPrimaryKey(goods) < 1, "入库失败");
        }else{//不存在即插入
            AssertUtil.isTrue(goodsMapper.insertSelective(goods)<1,"入库失败");
        }
    }


    /**
     * 验证 商品id 添加数量
     * @param goodsId
     * @param addNum
     */
    private void checkParams(Integer goodsId, Integer addNum) {
        AssertUtil.isTrue(goodsId==null,"商品id不能为空");
        AssertUtil.isTrue(addNum==null || addNum<=0,"商品入库数量不能为空、0或负数");
    }


    /***
     * 作用域获取对象
     * @param id
     * @return
     */
    public AddGoods queryByPrimaryKey(Integer id){
        return   addGoodsMapper.queryByPrimaryKey(id);
    }

    /***
     * 更新入库记录
     * @param addGoods
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void replaceChance(AddGoods addGoods){
        checkParams(addGoods.getGoodsId(),addGoods.getAddNum());//验证 商品id 添加数量
        System.out.println(addGoods.getId());

        AddGoods temp = addGoodsMapper.selectByPrimaryKey(addGoods.getGoodsId());//根据商品id获取商品入库记录
        AssertUtil.isTrue(temp==null,"商品不存在");

        AssertUtil.isTrue(addGoods.getAdminId() == null || addGoods.getAdminId() == 0,"管理员编号不能为空或0");
        AssertUtil.isTrue(adminMapper.selectByPrimaryKey(addGoods.getAdminId())==null,"管理员编号不存在");//检测管理员编号

        //差数选择   更新后的入库数量可能大于原入库 也可能小于原入库
        int sellNum = 0;//存放差数
        if(temp.getAddNum()<addGoods.getAddNum()){//库内入库数小于新入库数 等于原有商品数加上差数
            sellNum = addGoods.getAddNum()-temp.getAddNum();
            AssertUtil.isTrue(addGoodsMapper.updateByPrimaryKeySelective(addGoods)<1,"入库记录更新失败");
            Goods goods = goodsMapper.selectByPrimaryKey(addGoods.getGoodsId());
            goods.setGoodsNum(goods.getGoodsNum()+sellNum);//商品表中更新数量
            AssertUtil.isTrue(goodsMapper.updateByPrimaryKey(goods)<1,"入库记录更新失败");
        }else if(temp.getAddNum()>addGoods.getAddNum()){//库内入库数大于新入库数 等于原有商品数加上差数
            sellNum = temp.getAddNum()-addGoods.getAddNum();
            AssertUtil.isTrue(addGoodsMapper.updateByPrimaryKeySelective(addGoods)<1,"入库记录更新失败");
            Goods goods = goodsMapper.selectByPrimaryKey(addGoods.getGoodsId());
            goods.setGoodsNum(goods.getGoodsNum()-sellNum);//商品表中更新数量
            AssertUtil.isTrue(goodsMapper.updateByPrimaryKey(goods)<1,"入库记录更新失败");
        }else{//出库数未改动
            AssertUtil.isTrue(addGoodsMapper.updateByPrimaryKeySelective(addGoods)<1,"入库记录更新失败");
        }

    }


    /***
     * 删除入库记录
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteChance(Integer[] ids) {//接收一个id组成的数据 integer
        AssertUtil.isTrue((ids==null || ids.length==0),"未选择删除入库记录");
        AssertUtil.isTrue(addGoodsMapper.deleteBatch(ids)<0,"删除入库记录失败");//删除数据判断返回值 有效行数是否大于0
    }

}
