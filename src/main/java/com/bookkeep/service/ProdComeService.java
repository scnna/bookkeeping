package com.bookkeep.service;

import com.bookkeep.DTO.ProdComeDTO;
import com.bookkeep.controller.BaseController;
import com.bookkeep.domain.ProdCome;
import com.bookkeep.mapper.ProdComeMapper;
import com.bookkeep.mapper.ProdMapper;
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
public class ProdComeService extends BaseController {

    @Autowired
    ProdComeMapper comeMapper;
    @Autowired
    ProdMapper prodMapper;

    public ReturnResult insert(ProdCome prodCome, HttpSession session) {
        try {
//            if (getUserId(session) == null) {
//                return ReturnResult.error("请您先登录");
//            }
//            int userId = (int) getUserId(session);
            if (prodCome == null) {
                return ReturnResult.error("不能为空");
            }
            if (StringUtils.isEmpty(prodCome.getId())) {
                prodCome.setCreateTime(new Date());
                prodCome.setCurrentCount(prodCome.getComeCount());
//                prodCome.setCreateUser(userId);
                comeMapper.insert(prodCome);
            } else {
                //如果id不为空，则视为修改
                ProdCome prodOld = comeMapper.selectByPrimaryKey(prodCome.getId());
                prodOld.setCurrentCount(prodOld.getCurrentCount() - prodOld.getComeCount() + prodCome.getComeCount());
                prodOld.setBatch(prodCome.getBatch());
                prodOld.setComeCount(prodCome.getComeCount());
                prodOld.setComeTime(prodCome.getComeTime());
                prodOld.setMemberId(prodCome.getMemberId());
                prodOld.setProdId(prodCome.getProdId());
                prodOld.setPrice(prodCome.getPrice());
                prodOld.setUpdateTime(new Date());
                comeMapper.updateByPrimaryKey(prodOld);
            }
            return ReturnResult.success("插入成功");
        } catch (Exception e) {
            return ReturnResult.error("插入失败" + e);
        }
    }

    public ReturnResult getList(HttpSession session) {
        ProdComeDTO prodComeDTO = new ProdComeDTO();
        return ReturnResult.success(comeMapper.findListByProdCome(prodComeDTO));
    }

    public ReturnResult delete(int id) {
        return ReturnResult.success(comeMapper.deleteByPrimaryKey(id));
    }

    public ReturnResult getMemberPage() {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        List<ProdComeDTO> docs = comeMapper.findListByProdCome(new ProdComeDTO());
        return ReturnResult.success(docs);
    }
}
