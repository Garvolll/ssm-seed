package com.o2o.web.shopAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.o2o.Exception.ShopCategoryOperationException;
import com.o2o.Exception.ShopOperationException;
import com.o2o.dto.ShopCategoryExecution;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Area;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import com.o2o.entity.User;
import com.o2o.enums.ShopCategoryStateEnum;
import com.o2o.enums.ShopStateEnum;
import com.o2o.service.AreaService;
import com.o2o.service.ShopCategoryService;
import com.o2o.service.ShopService;
import com.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-23
 * Time: 15:36
 */
@RestController
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @GetMapping(value = "/getshoplist")
    public Map<String,Object> getShopList(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
//        User user = (User) request.getSession().getAttribute("user");
        User user = new User();
        user.setUserId(1L);
        try{
            Shop shopCondition = new Shop();
//            shopCondition.setOwner(user);
            ShopExecution se =shopService.getShopList(shopCondition,0,100);
            // 列出店铺成功之后，将店铺放入session中作为权限验证依据，即该帐号只能操作它自己的店铺
            // request.getSession().setAttribute("shopList", se.getShopList());
            modelMap.put("user",user);
            modelMap.put("success",true);
            modelMap.put("shopList",se.getShopList());
        }catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    /**
    * @Description: 根据id查找商铺
    * @Param: [request]
    */
    @GetMapping(value = "/getshopbyid")
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > -1) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }
    /**
    * @Description: 注册商铺
    * @Param: [request]
    */
    @PostMapping(value = "/registershop")
    public Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //1.接受并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errmsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errmsg", "上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
//            User owner = new User();
            User owner = (User) request.getSession().getAttribute("user");
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution se;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    modelMap.put("data", se.getShop());
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList == null || shopList.size() == 0) {
                        shopList = new ArrayList<Shop>();
                    }
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errmsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errmsg", e.getMessage());
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errmsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errmsg", "请输入店铺信息");
            return modelMap;
        }
    }

    /**
    * @Description: 更新商铺信息
    * @Param: [request]
    */
    @PostMapping(value = "/modifyshop")
    public Map<String, Object> modifyShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //1.接受并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errmsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }
        //2.修改店铺信息
        if (shop != null && shop.getShopId() != null) {
            ShopExecution se;
            try {
                if (shopImg == null) {
                    se = shopService.modifyShop(shop, null, null);
                } else {
                    se = shopService.modifyShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                }
                if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                    modelMap.put("data", se.getShop());
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errmsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errmsg", e.getMessage());
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errmsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errmsg", "请输入店铺ID");
            return modelMap;
        }
    }

    /**
    * @Description: 获取商铺地区、类别等信息
    * @Param: []
    */
    @GetMapping(value = "/getshopinitinfo")
    public Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    @PostMapping(value = "addShopCategory")
    public Map<String,Object> addShopCategory(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //1.接受并转化相应的参数，包括店铺信息以及图片信息
        String shopCategoryStr = HttpServletRequestUtil.getString(request, "shopCategoryStr");
        ObjectMapper mapper = new ObjectMapper();
        ShopCategory shopCategory = null;
        try {
            shopCategory = mapper.readValue(shopCategoryStr, ShopCategory.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errmsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopCategoryImg = null;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopCategoryImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopCategoryImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errmsg", "上传图片不能为空");
            return modelMap;
        }
        //2.添加商铺类别信息
        if (shopCategory != null && shopCategoryImg != null) {
            ShopCategoryExecution se;
            try {
                se = shopCategoryService.insertShopCategory(shopCategory, shopCategoryImg.getInputStream(), shopCategoryImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    modelMap.put("data", se.getShopCategory());
                    List<ShopCategory> shopCategoryList = (List<ShopCategory>) request.getSession().getAttribute("shopCategoryList");
                    if (shopCategoryList == null || shopCategoryList.size() == 0) {
                        shopCategoryList = new ArrayList<ShopCategory>();
                    }
                    shopCategoryList.add(se.getShopCategory());
                    request.getSession().setAttribute("shopCategoryList", shopCategoryList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errmsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errmsg", e.getMessage());
            } catch (ShopCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errmsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errmsg", "请输入店铺类别信息");
            return modelMap;
        }
    }


    @PostMapping(value = "updateShopCategory")
    public Map<String,Object> updateShopCategory(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //1.接受并转化相应的参数，包括店铺信息以及图片信息
        String shopCategoryStr = HttpServletRequestUtil.getString(request, "shopCategoryStr");
        ObjectMapper mapper = new ObjectMapper();
        ShopCategory shopCategory = null;
        try {
            shopCategory = mapper.readValue(shopCategoryStr, ShopCategory.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errmsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopCategoryImg = null;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopCategoryImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopCategoryImg");
        }
        //2.添加商铺类别信息
        if (shopCategory != null && shopCategoryImg != null) {
            ShopCategoryExecution sce;
            try {
                sce = shopCategoryService.updateShopCategory(shopCategory, shopCategoryImg.getInputStream(), shopCategoryImg.getOriginalFilename());
                if (sce.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    modelMap.put("data", sce.getShopCategory());
                    List<ShopCategory> shopCategoryList = (List<ShopCategory>) request.getSession().getAttribute("shopCategoryList");
                    if (shopCategoryList == null || shopCategoryList.size() == 0) {
                        shopCategoryList = new ArrayList<ShopCategory>();
                    }
                    shopCategoryList.add(sce.getShopCategory());
                    request.getSession().setAttribute("shopCategoryList", shopCategoryList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errmsg", sce.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errmsg", e.getMessage());
            } catch (ShopCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errmsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errmsg", "请输入店铺类别信息");
            return modelMap;
        }
    }

    @PostMapping(value = "deleteShopCategory")
    @Transactional
    public Map<String,Object> deleteShopCategory(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long shopCategoryId = HttpServletRequestUtil.getLong(request,"shopCategoryId");
        try{
            boolean res =  shopCategoryService.deleteShopCategory(shopCategoryId);
            if(res){
                modelMap.put("success", true);
            }else {
                modelMap.put("success", false);
                modelMap.put("errmsg", ShopCategoryStateEnum.INNER_ERROR.getStateInfo());
            }
        }catch (ShopCategoryOperationException e){
            modelMap.put("success", false);
            modelMap.put("errmsg", e.getMessage());
        }
        return modelMap;
    }
}
