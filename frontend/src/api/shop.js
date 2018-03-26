import req from 'utils/axios';

const SHOP_PREFIX = "/shopadmin";

export const getShopCategory = () => {
  return req.get(SHOP_PREFIX + "/getshopinitinfo")
}

export const registerShop = (data) => {
  return req.post(SHOP_PREFIX + "/registershop", data)
}
