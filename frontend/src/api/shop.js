import req from 'utils/axios';

const SHOP_PREFIX = "/shopadmin";

/** 
 * 获取商铺列表
 */
export const getShopList = (data) => {
  return req.get(SHOP_PREFIX + "/getshoplist", data)
}

/**
 * 根据ID查找shop
 * @param {*} data 
 */
export const getShopById = (data) => {
  return req.get(SHOP_PREFIX + "/getshopbyid", data)
}

/**
 * 获取商铺类型
 */
export const getShopCategory = () => {
  return req.get(SHOP_PREFIX + "/getshopinitinfo")
}

/**
 * 注册商铺
 */
export const registerShop = (data) => {
  return req.post(SHOP_PREFIX + "/registershop", data)
}

/**
 * 更新商铺信息
 * @param {*} data 
 */
export const modifyShop = (data) => {
  return req.post(SHOP_PREFIX + "/modifyshop", data)
}
