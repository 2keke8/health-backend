package com.zky.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zky.constant.MessageConstant;
import com.zky.entity.PageResult;
import com.zky.entity.QueryPageBean;
import com.zky.entity.Result;
import com.zky.pojo.CheckGroup;
import com.zky.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * @author 戴金华
 * @date 2019-11-08 11:47
 */
@RestController
@RequestMapping("/checkGroup")
=======
import static com.zky.constant.MessageConstant.*;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-23 10:34
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */


/**
 * 检查组管理
 */
@RestController
@RequestMapping("/checkgroup")

public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/findByPage.do")
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean){
        return checkGroupService.findByPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
    }

    //获取检查项
    @RequestMapping("/findById.do")
    public List<CheckItem> findById(Integer id){
        return checkGroupService.findById(id);
    }

    //获取检查组
    @RequestMapping("/findCheckGroupById.do")
    public CheckGroup findCheckGroupById(Integer id){
        return checkGroupService.findCheckGroupById(id);
    }


    //添加检查组
    @RequestMapping("/addCheckGroup.do")
    public Result addCheckGroup(@RequestBody CheckGroup checkGroup, Integer[] ids){
        checkGroupService.addCheckGroup(checkGroup,ids);
        return new Result();
    }

    //更新检查组
    @RequestMapping("/updateCheckGroup.do")
    public Result updateCheckGroup(@RequestBody CheckGroup checkGroup, Integer[] ids){
        checkGroupService.updateCheckGroup(checkGroup,ids);
        return new Result();
    }

    //删除检查组
    @RequestMapping("/deleteCheckGroup.do")
    public Result deleteCheckGroup(Integer id){
        checkGroupService.deleteCheckGroup(id);
        return new Result();
    }
}


    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.add(checkGroup, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ADD_CHECKGROUP_FAIL); //新增失败
        }
        return new Result(true, ADD_CHECKGROUP_SUCCESS);//新增成功
    }

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkGroupService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    /**
     * 根据id查询，进行编辑数据的回显
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }


    }

    //根据检查组合id查询对应的所有检查项id
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id) {
        try {
            List<Integer> checkitemIds =
                    checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //编辑
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.edit(checkGroup, checkitemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 查询所有检查组
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> checkGroupList = checkGroupService.findAll();
        if(checkGroupList != null && checkGroupList.size() > 0){
            Result result = new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);
            result.setData(checkGroupList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
    }

}
