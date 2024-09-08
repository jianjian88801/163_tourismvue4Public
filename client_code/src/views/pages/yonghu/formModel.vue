<template>
  <div
    class="app-contain"
    :style="{
      padding: '20px 20px',
      margin: '20px auto 40px',
      alignItems: 'flex-start',
      flexWrap: 'wrap',
      background: '#fff',
      display: 'flex',
      width: '1200px',
      position: 'relative',
      justifyContent: 'space-between',
    }"
  >
    <div class="bread_view">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }"
          >首页</el-breadcrumb-item
        >
        <el-breadcrumb-item
          class="second_breadcrumb"
          v-for="(item, index) in breadList"
          :key="index"
          >{{ item.name }}</el-breadcrumb-item
        >
      </el-breadcrumb>
    </div>
    <div class="back_view">
      <el-button class="back_btn" @click="backClick" type="primary"
        >返回</el-button
      >
    </div>
    <div class="detail_view">
      <div class="swiper_view">
        <mySwiper
          :data="bannerList"
          :type="3"
          :loop="false"
          :navigation="true"
          :pagination="true"
          :paginationType="1"
          :scrollbar="false"
          :slidesPerView="1"
          :spaceBetween="20"
          :autoHeight="false"
          :centeredSlides="false"
          :freeMode="false"
          :effectType="1"
          :direction="horizontal"
          :autoplay="false"
          :slidesPerColumn="1"
        >
          <template #default="scope">
            <img
              :style="{ objectFit: 'contain', width: '100%', height: '480px' }"
              :src="scope.row ? $config.url + scope.row : ''"
            />
          </template>
        </mySwiper>
      </div>

      <div class="info_view">
        <div class="title_view">
          <div class="detail_title"></div>
        </div>
        <div class="info_item">
          <div class="info_label">账号</div>
          <div class="info_text">{{ detail.zhanghao }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">姓名</div>
          <div class="info_text">{{ detail.xingming }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">性别</div>
          <div class="info_text">{{ detail.xingbie }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">手机</div>
          <div class="info_text">{{ detail.shouji }}</div>
        </div>
        <div class="btn_view">
          <el-button
            v-if="
              centerType &&
              (detail.ispay == '未支付' || !detail.ispay) &&
              btnFrontAuth('yonghu', '支付')
            "
            class="approval_btn"
            @click="payClick"
            >支付</el-button
          >
          <el-button
            class="edit_btn"
            v-if="centerType && btnAuth('yonghu', '修改')"
            type="primary"
            @click="editClick"
            >修改</el-button
          >
          <el-button
            class="del_btn"
            v-if="centerType && btnAuth('yonghu', '删除')"
            type="danger"
            @click="delClick"
            >删除</el-button
          >
        </div>
      </div>
    </div>
    <el-tabs type="border-card" v-model="activeName" class="tabs_view">
    </el-tabs>
  </div>
</template>
<script setup>
import axios from 'axios'
import {
  ref,
  getCurrentInstance,
  watch,
  onUnmounted,
  onMounted,
  nextTick,
  computed,
} from 'vue'
import { ElMessageBox } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const route = useRoute()
const router = useRouter()
//基础信息
const tableName = 'yonghu'
const formName = '用户'
//基础信息
const breadList = ref([
  {
    name: formName,
  },
])
//权限验证
const btnAuth = (e, a) => {
  if (centerType.value) {
    return context?.$toolUtil.isBackAuth(e, a)
  } else {
    return context?.$toolUtil.isAuth(e, a)
  }
}
//查看权限验证
const btnFrontAuth = (e, a) => {
  if (centerType.value) {
    return context?.$toolUtil.isBackAuth(e, a)
  } else {
    return context?.$toolUtil.isFrontAuth(e, a)
  }
}
// 返回
const backClick = () => {
  history.back()
}
// 轮播图
const bannerList = ref([])
// 详情
const title = ref('')
const detail = ref({})
const activeName = ref('first')
const getDetail = () => {
  context
    ?.$http({
      url: `${tableName}/detail/${route.query.id}`,
      method: 'get',
    })
    .then((res) => {
      detail.value = res.data.data
    })
}
// 下载文件
const downClick = (file) => {
  if (!file) {
    context?.$toolUtil.message('文件不存在', 'error')
  }
  let arr = file.replace(new RegExp('file/', 'g'), '')
  axios
    .get(
      (location.href.split(context?.$config.name).length > 1
        ? location.href.split(context?.$config.name)[0]
        : '') +
        context?.$config.name +
        '/file/download?fileName=' +
        arr,
      {
        headers: {
          token: context?.$toolUtil.storageGet('frontToken'),
        },
        responseType: 'blob',
      }
    )
    .then(({ data }) => {
      const binaryData = []
      binaryData.push(data)
      const objectUrl = window.URL.createObjectURL(
        new Blob(binaryData, {
          type: 'application/pdf;chartset=UTF-8',
        })
      )
      const a = document.createElement('a')
      a.href = objectUrl
      a.download = arr
      // a.click()
      // 下面这个写法兼容火狐
      a.dispatchEvent(
        new MouseEvent('click', {
          bubbles: true,
          cancelable: true,
          view: window,
        })
      )
      window.URL.revokeObjectURL(data)
    })
}
// 判断是否从个人中心跳转
const centerType = ref(false)
const init = () => {
  if (route.query.centerType) {
    centerType.value = true
  }
  getDetail()
}
//修改
const editClick = () => {
  router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
}
//删除
const delClick = () => {
  ElMessageBox.confirm(`是否删除此${formName}？`, '提示', {
    confirmButtonText: '是',
    cancelButtonText: '否',
    type: 'warning',
  }).then(() => {
    context
      ?.$http({
        url: `${tableName}/delete`,
        method: 'post',
        data: [detail.value.id],
      })
      .then((res) => {
        context?.$toolUtil.message('删除成功', 'success', () => {
          history.back()
        })
      })
  })
}
onMounted(() => {
  init()
})
</script>
<style lang="scss" scoped>
// 返回盒子
.back_view {
  border-radius: 0px;
  padding: 0 20px;
  margin: 20px auto;
  background: none;
  display: block;
  width: 100%;
  text-align: right;
  // 返回按钮
  .back_btn {
    border: 1px solid #eee;
    cursor: pointer;
    border-radius: 4px;
    padding: 0 30px;
    outline: none;
    color: #666;
    background: #f9f9f9;
    width: auto;
    font-size: 14px;
    height: 32px;
  }
  // 返回按钮-悬浮
  .back_btn:hover {
  }
}
// 面包屑盒子
.bread_view {
  border-radius: 0px;
  padding: 10px 20px;
  margin: 0px auto;
  background: url(http://localhost:8080/tourism/file/cf02fa1d4a2442b581a3d62afcc09015.png)
      no-repeat left center,
    #fbfbfb;
  width: calc(100% - 40px);
  border-color: #eee;
  border-width: 1px;
  position: relative;
  border-style: solid;
  :deep(.breadcrumb) {
    font-size: 14px;
    line-height: 1;
    .el-breadcrumb__separator {
      margin: 0 9px;
      color: #fff;
      font-weight: 500;
    }
    .first_breadcrumb {
      .el-breadcrumb__inner {
        color: #fff;
        display: inline-block;
      }
    }
    .second_breadcrumb {
      .el-breadcrumb__inner {
        color: #fff;
        display: inline-block;
      }
    }
  }
}

.detail_view {
  border-radius: 0;
  padding: 20px 0;
  background: #fff;
  display: flex;
  width: 100%;
  border-color: #27bacc30;
  border-width: 0px;
  justify-content: space-between;
  position: relative;
  border-style: solid;
  flex-wrap: wrap;
  // 轮播图
  .swiper_view {
    padding: 0;
    margin: 0 auto;
    background: none;
    width: 80%;
    float: left;
    height: 480px;
  }

  // 文字区
  .info_view {
    border: 0px solid #eee;
    padding: 0 20px;
    margin: 20px 0 20px;
    background: none;
    width: 100%;
    box-sizing: border-box;
    order: 3;

    .title_view {
      border: 1px solid #eee;
      padding: 0 0 0 16px;
      margin: 0 0 12px;
      background: url(http://localhost:8080/tourism/file/cf02fa1d4a2442b581a3d62afcc09015.png)
          no-repeat left center,
        #fbfbfb;
      display: flex;
      width: 100%;
      line-height: 38px;
      align-items: center;

      .detail_title {
        color: #fff;
        font-weight: 500;
        font-size: 16px;
      }
      .follow {
        border: 0px solid #ffffff50;
        cursor: pointer;
        padding: 4px 10px;
        color: #333;
        display: flex;
        line-height: 1;
        right: 30px;
        border-radius: 4px;
        background: none;
        width: auto;
        justify-content: center;
        align-items: center;
        position: absolute;
        .iconfont {
          margin: 0 4px 0 0;
          color: #666;
          font-size: 24px;
        }
        .iconfontActive {
          margin: 0 4px 0 0;
          color: #f79d1c;
          font-size: 24px;
        }
        span {
          color: #666;
          font-size: 15px;
        }
        .textActive {
          color: #f79d1c;
          font-size: 16px;
        }
      }
      .follow:hover {
      }
      .follow:active {
        transform: scale(0.9);
      }
    }

    .info_item {
      border-radius: 0px;
      padding: 0px;
      margin: 0 2% 10px 0;
      background: none;
      display: inline-block;
      width: 30%;
      border-color: #eee;
      border-width: 0px;
      align-items: center;
      border-style: solid;

      .info_label {
        margin: 0 12px 10px 0;
        color: #666;
        font-weight: 600;
        width: auto;
      }
      .info_text {
      }
    }
    .btn_view {
      padding: 0;
      margin: 20px 0 20px;
      display: flex;
      flex-wrap: wrap;
      // 修改-按钮
      .edit_btn {
        border: none;
        padding: 0 10px;
        color: #fff;
        background: #1fc273;
        line-height: 32px;
        height: 32px;
      }
      // 悬浮
      .edit_btn:hover {
      }
      // 删除-按钮
      .del_btn {
        border: none;
        padding: 0 10px;
        color: #fff;
        background: #c21f30;
        line-height: 32px;
        height: 32px;
      }
      // 悬浮
      .del_btn:hover {
      }
    }
  }
}

//底部盒子
.tabs_view {
  border: 0px solid #ddd;
  border-radius: 0px;
  padding: 0 20px;
  box-shadow: none;
  margin: 20px auto;
  background: none;
  width: 100%;
  :deep(.el-tabs__header) {
    background: transparent;
    border: none;
  }
  // 头部
  :deep(.el-tabs__nav-scroll) {
    padding: 0px 20px;
    margin: 0;
    background: none;
    border-color: #c21f30;
    border-width: 0 0 2px;
    border-style: solid;
    height: 46px;
    .el-tabs__nav {
      .el-tabs__item {
        border: 0;
        padding: 0 30px;
        margin: 0 10px;
        color: #333;
        font-weight: 500;
        display: inline-block;
        font-size: 14px;
        line-height: 46px;
        transition: all 0s;
        background: none;
        position: relative;
        list-style: none;
        text-align: center;
        min-width: 60px;
        height: 46px;
      }
      .el-tabs__item:hover {
        border: 0;
        border-radius: 0;
        color: #fff;
        background: #c21f30;
        line-height: 46px;
        height: 46px;
      }
      .is-active {
        border: 0;
        border-radius: 0;
        padding: 0 30px;
        color: #fff;
        background: #c21f30;
        line-height: 46px;
        text-align: center;
        min-width: 60px;
        height: 46px;
      }
    }
  }
  // 内容区
  :deep(.el-tabs__content) {
    border-radius: 0px;
    padding: 20px;
    color: #666;
    background: none;
    font-size: 14px;
    border-color: #eee;
    border-width: 0;
    border-style: solid;
  }
}
</style>
