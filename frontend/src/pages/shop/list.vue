<template>
  	<div>
        <el-table :data="shopList"  border style="width: 100%">
            <el-table-column prop="shopName" label="商店名">
            </el-table-column>
             <el-table-column prop="shopCategory.shopCategoryName" label="商店类别">
            </el-table-column>
             <el-table-column prop="owner.name" label="商店所有人">
            </el-table-column>
             <el-table-column prop="area.areaName" label="商店所在地区">
            </el-table-column>
            <el-table-column  label="状态">
              <template slot-scope="scope">
                    <p  v-if="scope.row.enableStatus==0">审核中</p>
                    <p  v-if="scope.row.enableStatus==1">可用</p>
                    <p  v-if="scope.row.enableStatus==-1">不可用</p>
                </template>
            </el-table-column>
            <el-table-column  label="操作">
                <template slot-scope="scope">
                    <el-button type="primary" @click="modifyShop(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="primary" v-if="scope.row.enableStatus==1" @click="modifyShop(scope.$index, scope.row)">进入商铺</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button @click="register">注册商铺</el-button>
    </div>
</template>

<script>
import { getShopList } from "api/shop.js";

export default {
  data() {
    return {
      shopList: []
    };
  },
  async created() {
    const res = await getShopList({}, 0, 100);
    this.shopList = res.shopList;
  },
  methods: {
    register() {
      this.$router.push({ path: "/shop/register" });
    },
    modifyShop(index,row){
      const shopId = row.shopId;
      console.log(shopId)
      this.$router.push({ name: "shopModify",params: { shopId: shopId }});
    }
  }
};
</script>

<style>
</style>
