import Vue from 'vue'
import Router from 'vue-router'
import App from '@/App'

const shop = r => require.ensure([], () => r(require('../pages/shop/index')), 'shop')
const shopList = r => require.ensure([], () => r(require('../pages/shop/list')), 'shop')
const shopModify = r => require.ensure([], () => r(require('../pages/shop/modify')), 'shop')
const shopRigister = r => require.ensure([], () => r(require('../pages/shop/register')), 'shop')

Vue.use(Router)

export default [{
  path: '/',
  component: App,
  children: [
    //地址为空时跳转home页面
    {
      path: '',
      redirect: '/shop'
    },
    //商铺
    {
      path: '/shop',
      component: shop,
      redirect: '/shop/list',
      children: [{
        path: 'register',
        component: shopRigister,
      }, {
        name: 'shopModify',
        path: 'modify',
        component: shopModify
      }, {
        path: 'list',
        component: shopList
      }]
    }
  ]
}]
