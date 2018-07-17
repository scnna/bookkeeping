package com.bookkeep.service;

import com.bookkeep.DTO.ProdOutDTO;
import com.bookkeep.controller.BaseController;
import com.bookkeep.domain.ProdOut;
import com.bookkeep.mapper.ProdComeMapper;
import com.bookkeep.mapper.ProdOutMapper;
import com.bookkeep.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author scn
 * @create 2018-03-09 10:16
 * @desc 产品进厂信息Controller
 **/
@Service
public class ProdOutService extends BaseController {

    @Autowired
    ProdOutMapper outMapper;
    @Autowired
    ProdComeMapper comeMapper;

    public ReturnResult insert(ProdOut prodOut, HttpSession session) {
//            if (getUserId(session) == null) {
//                return ReturnResult.error("请您先登录");
//            }
//            int userId = (int) getUserId(session);
        if (prodOut == null) {
            return ReturnResult.error("不能为空");
        }
        if (StringUtils.isEmpty(prodOut.getId())) {
            prodOut.setCreateTime(new Date());
//                    memberProd.setCreateUser(userId);
            outMapper.insert(prodOut);
        } else {
            ProdOut prodOld=outMapper.selectByPrimaryKey(prodOut.getId());

            prodOld.setOutNum(prodOld.getOutNum());
            prodOld.setOutTime(new Date());
            prodOld.setComeId(prodOld.getComeId());
            prodOld.setUpdateTime(new Date());
            outMapper.updateByPrimaryKey(prodOld);
        }
        return ReturnResult.success("插入成功");
    }

    public ReturnResult getList(HttpSession session) {
        ProdOutDTO prodOutDTO = new ProdOutDTO();
        return ReturnResult.success(outMapper.findListByProdOut(prodOutDTO));
    }

    public String delete(int id) {
        try {
//            memberProdMapper.deleteByPrimaryKey(id);
            return "删除成功";
        } catch (Exception e) {
            return "删除失败" + e;

        }

    }

    public ReturnResult getProdOutPage(String prodId) {
        ProdOutDTO outDTO = new ProdOutDTO();
        if (StringUtils.isEmpty(prodId)) {
        }
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        List<ProdOutDTO> docs = outMapper.findListByProdOut(new ProdOutDTO());
        return ReturnResult.success(docs);
    }
}
