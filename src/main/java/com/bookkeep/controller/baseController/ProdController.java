package com.bookkeep.controller.baseController;

import com.bookkeep.controller.BaseController;
import com.bookkeep.domain.Prod;
import com.bookkeep.domain.ProdCome;
import com.bookkeep.mapper.ProdComeMapper;
import com.bookkeep.mapper.ProdMapper;
import com.bookkeep.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author scn
 * @create 2018-03-09 10:16
 * @desc 产品基础信息Controller
 **/
@RestController
@RequestMapping("/prod")
public class ProdController extends BaseController {

    @Autowired
    ProdMapper prodMapper;
    @Autowired
    ProdComeMapper comeMapper;

    @RequestMapping("/toInsert")
    public String toInsert(Model model) {
        return null;
    }

    @PostMapping("/insert")
    public ReturnResult insert(Prod prod, HttpSession session) {
        try {
            if (getUserId(session) == null) {
                return ReturnResult.error("请您先登录");
            }
            int userId = (int) getUserId(session);
            if (prod == null) {
                return ReturnResult.error("产品名称不能为空");
            }
            if (StringUtils.isEmpty(prod.getId())) {
                prod.setCreateTime(new Date());
                prod.setCreateUser(userId);
                prodMapper.insert(prod);
                //保存进货信息
                ProdCome come = new ProdCome();
                come.setProdId(prod.getId());
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
                come.setBatch(format.format(date));
                come.setComeTime(new SimpleDateFormat("yyyy-MM-dd hh:hh:mm").format(date));
                come.setComeCount(prod.getCurrentCount());
                come.setCreateTime(date);
                come.setPrice(prod.getPrice());
                come.setCurrentCount(prod.getCurrentCount());
                come.setCreateUser(1);
                comeMapper.insert(come);
            } else {
                Prod prodOld = prodMapper.selectByPrimaryKey(prod.getId());
                prodOld.setName(prod.getName());
                prodOld.setRemark(prod.getRemark());
                prodOld.setUpdateTime(new Date());
                prodMapper.updateByPrimaryKey(prodOld);
            }

            return ReturnResult.success("插入成功");
        } catch (Exception e) {
            return ReturnResult.error("插入失败" + e);
        }
    }

    @GetMapping("getList")
    public ReturnResult getList(HttpSession session) {
        Prod prod = new Prod();
        return ReturnResult.success(prodMapper.findListByProd(prod));
    }

    @GetMapping("getById")
    public ReturnResult getById(Integer id) {
        return ReturnResult.success(prodMapper.selectByPrimaryKey(id));
    }

    @GetMapping("delete")
    public String delete(int id) {
        try {

            prodMapper.deleteByPrimaryKey(id);
            return "删除成功";
        } catch (Exception e) {
            return "删除失败" + e;

        }

    }

    @GetMapping("/pagination")
    public ReturnResult getProdPage() {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
//        PageHelper.startPage(currentPage, pageSize);
        List<Prod> docs = prodMapper.findListByProd(new Prod());
//        PageInfo<Prod> pageInfo = new PageInfo<>(docs);
        return ReturnResult.success(docs);
    }
}
