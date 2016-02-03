package com.wind.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.commons.Constant;
import com.wind.commons.Constant.DeleteStatus;
import com.wind.entity.Moment;
import com.wind.service.IMomentService;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class MomentController {
    @Resource
    IMomentService momentService;
    
    /**
     * 添加用户
     * 
     * @author qianchun  @date 2016年2月1日 下午3:42:16
     */
    public Moment create(Moment moment) {
        return momentService.create(moment);
    }
    
    /**
     * 删除
     * 
     * @author qianchun  @date 2016年2月1日 下午5:28:19
     * @param uid
     * @return
     */
    public boolean delete(long uid) {
        return momentService.updateStatus(uid, Constant.DeleteStatus.YES);
    }
    
    
    public boolean update(Map<String, Object> params) {
        return momentService.update(params);
    }
    /**
     * 主键id查询
     * 
     * @author qianchun  @date 2016年2月1日 下午3:41:49
     */
    public Moment findById(long id) {
        return momentService.findById(id);
    }
    
    /**
     * 条件查询
     * 
     * @author qianchun  @date 2016年2月1日 下午3:41:33
     */
    public List<Moment> findList() {
        Map<String, Object> params = new HashMap<String, Object>();
        return momentService.findList(params);
    }
    @Test
    public void main() {
//        Moment moment = createMoment();
        boolean flag = false;
        List<Moment> momentList = null;
        
//        moment = create(moment);
//        moment = findById(1l);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", 1l);
        params.put("updateTime", System.currentTimeMillis());
        params.put("content", "编辑第一个此刻");
        flag = update(params);
        
        momentList = findList();
        JSONArray array = JSONArray.fromObject(momentList);
         System.out.println(array.toString());
    }
    
    public Moment createMoment() {
        Moment moment = new Moment();
        JSONArray emptyArrayJson = JSONArray.fromObject(new ArrayList<>());
        
        moment.setTitle("创建第一个此刻");
        moment.setStatus(DeleteStatus.NO);
        moment.setContent("今天创建第一个此刻");

        moment.setUid(1l);
        moment.setCreateTime(System.currentTimeMillis());
        moment.setUpdateTime(System.currentTimeMillis());
        moment.setPublishTime(System.currentTimeMillis());

        moment.setPraiseUid(emptyArrayJson.toString());
        moment.setCollectionUid(emptyArrayJson.toString());
        return moment;
    }
}    