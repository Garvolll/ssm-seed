<template>
  <div class="">
    <el-form :label-position="labelPosition" label-width="80px" :model="form">
      <el-form-item label="商铺名称">
        <el-input v-model="form.shopName"></el-input>
      </el-form-item>
      <el-form-item label="商铺分类">
          <el-select v-model="form.shopCategory.shopCategoryId" placeholder="请选择" value-key="key">
            <el-option
              v-for="item in shopCategoryList"
              :key="item.shopCategoryId"
              :label="item.shopCategoryName"
              :value="item.shopCategoryId">
            </el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="所属区域">
          <el-select v-model="form.area.areaId" placeholder="请选择" value-key="key">
            <el-option
              v-for="item in areaList"
              :key="item.areaId"
              :label="item.areaName"
              :value="item.areaId">
            </el-option>
          </el-select>
      </el-form-item>
       <el-form-item label="详细地址">
        <el-input v-model="form.shopAddr"></el-input>
      </el-form-item>
      <el-form-item label="联系电话">
        <el-input v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="缩略图">
        <el-upload
          class="upload-demo"
          action="/"
          list-type="picture"
          multiple
          :auto-upload="false"
          :limit="1"
          :on-change="handleUploadChange"
          :on-remove="handleUploadRemove"
          :file-list="fileList">
          <el-button size="small" type="primary" v-if="!shopImg">点击上传</el-button>
        </el-upload>
      </el-form-item>
       <el-form-item label="店铺介绍">
        <el-input v-model="form.shopDesc"></el-input>
      </el-form-item>
      <el-button type="primary" @click="onSubmit">提交</el-button>
    </el-form>
    
  </div>
</template>

<script>
import { getShopCategory, getShopById, modifyShop } from "api/shop.js";

export default {
  name: "shop_modify",
  data() {
    return {
      labelPosition: "left",
      fileList: [],
      shopCategoryList: [],
      areaList: [],
      shopImg: null,
      form: {
        shopName: "",
        shopCategory: {
          shopCategoryId: ""
        },
        area: {
          areaId: ""
        },
        shopAddr: "",
        phone: "",
        shopDesc: ""
      },
      kaptchaSrc: ""
    };
  },
  async mounted() {
    const res = await getShopCategory();
    this.shopCategoryList = res.shopCategoryList;
    this.areaList = res.areaList;
    const shopId = this.$route.params.shopId;
    const data = await getShopById({ shopId: shopId });
    this.form = data.shop;
  },
  methods: {
    handleUploadRemove() {
      this.shopImg = null;
    },
    handleUploadChange(file, fileList) {
      this.shopImg = file.raw;
    },
    async onSubmit() {
      let formData = new FormData();
      formData.append("shopStr", JSON.stringify(this.form));
      formData.append("shopImg", this.shopImg);
      await modifyShop(formData);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
