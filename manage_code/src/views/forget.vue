<template>
  <div>
    <canvas
      id="canvas"
      style="
        background: url(http://localhost:8080/tourism/file/5c406dea56f94e7a8862ce254a053b09.jpg)
          no-repeat center center / cover;
      "
    ></canvas>
    <div class="forget_view">
      <el-form :model="forgetForm" class="forget_box">
        <div class="forget_title">
          贵州红色文化旅游景区管理系统{{
            pageType == 1 ? '找回密码' : pageType == 2 ? '输入密保' : '重置密码'
          }}
        </div>
        <div class="tab_view">
          <div class="tab" :class="pageType == 1 ? 'tab_active' : ''">
            找回密码
          </div>
          <div
            class="tab"
            :class="pageType == 2 ? 'tab_active tab_active1' : ''"
          >
            输入密保
          </div>
          <div
            class="tab"
            :class="pageType == 3 ? 'tab_active tab_active1 tab_active2' : ''"
          >
            重置密码
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onUnmounted, onMounted } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
//动态背景
import canvasBg from '@/assets/js/canvas-bg-2.js'
import '@/assets/css/canvas-bg-2.css'
onMounted(() => {
  canvasBg()
})
onUnmounted(() => {
  nextTick(() => {
    canvasBg = null
  })
})
const pageType = ref(1)
const forgetForm = ref({})
const userForm = ref({})
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
</script>

<style lang="scss" scoped>
.forget_view {
  background: none !important;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  position: relative;
  flex-direction: column;
  // 表单盒子
  .forget_box {
    border-radius: 0px;
    padding: 30px 150px 50px 20px;
    margin: 0 auto;
    clip-path: polygon(0% 0%, 75% 0%, 100% 50%, 75% 100%, 0% 100%);
    background: rgba(0, 0, 0, 0.8);
    display: flex;
    width: 35vw;
    justify-content: flex-start;
    flex-wrap: wrap;
    // 标题
    .forget_title {
      padding: 0px;
      margin: 20px auto 30px;
      color: #fff;
      font-weight: 500;
      width: 80%;
      font-size: 18px;
      text-align: center;
    }
    // tab
    // 盒子
    .tab_view {
      padding: 0;
      margin: 0 auto 20px;
      display: flex;
      width: 80%;
      align-items: center;
      // 默认样式
      .tab {
        padding: 16px 0;
        color: #ccc;
        width: calc(100% / 3);
        font-size: 14px;
        box-sizing: border-box;
        text-align: center;
      }
      // 选中样式一
      .tab_active {
        clip-path: polygon(00% 0%, 92% 00%, 100% 50%, 92% 100%, 0 100%, 8% 50%);
        color: #fff;
        background: #9d7ad1;
      }
      // 选中样式二
      .tab_active1 {
        color: #fff;
        background: #9067ce;
      }
      // 选中样式三
      .tab_active2 {
        color: #fff;
        background: #8454cc;
      }
    }
    // item
    .list_item {
      margin: 10px auto;
      background: #00000030;
      display: flex;
      width: 80%;
      justify-content: flex-start;
      align-items: center;
      // label
      .item_label {
        color: #ccc;
        background: none;
        display: block;
        width: 120px;
        font-size: 14px;
        line-height: 36px;
        box-sizing: border-box;
        text-align: right;
        height: 36px;
      }
      // 输入框
      :deep(.list_inp) {
        border: 0px solid #ddd;
        border-radius: 0px;
        padding: 0 10px;
        color: #ccc;
        background: none;
        width: 80%;
        line-height: 36px;
        box-sizing: border-box;
        height: 36px;
        //去掉默认样式
        .el-input__wrapper {
          border: none;
          box-shadow: none;
          background: none;
          border-radius: 0;
          height: 100%;
          padding: 0;
        }
        .is-focus {
          box-shadow: none !important;
        }
      }
      //下拉框样式
      :deep(.list_sel) {
        border: 0px solid #ddd;
        border-radius: 0px;
        padding: 0 10px;
        color: #ccc;
        background: none;
        width: 80%;
        line-height: 36px;
        box-sizing: border-box;
        //去掉默认样式
        .select-trigger {
          height: 100%;
          .el-input {
            height: 100%;
            .el-input__wrapper {
              border: none;
              box-shadow: none;
              background: none;
              border-radius: 0;
              height: 100%;
              padding: 0;
            }
            .is-focus {
              box-shadow: none !important;
            }
          }
        }
      }
    }
    // 按钮盒子
    .list_btn {
      margin: 20px auto 0;
      display: flex;
      width: 80%;
      justify-content: center;
      align-items: center;
      flex-wrap: wrap;
      // 获取密保
      :deep(.el-button--success) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 40px;
        margin: 0 10px 0 0;
        color: #fff;
        background: linear-gradient(
            270deg,
            rgba(127, 85, 151, 1) 0%,
            rgba(179, 100, 145, 1) 100%
          ),
          #ad6190;
        width: auto;
        font-size: 14px;
        height: 36px;
      }
      // 获取密保悬浮
      :deep(.el-button--success:hover) {
      }
      // 确认密保
      :deep(.el-button--primary) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 40px;
        margin: 0 10px 0 0;
        outline: none;
        color: #fff;
        background: linear-gradient(
            270deg,
            rgba(127, 85, 151, 1) 0%,
            rgba(179, 100, 145, 1) 100%
          ),
          #ad6190;
        width: auto;
        font-size: 14px;
        height: 36px;
      }
      // 确认密保悬浮
      :deep(.el-button--primary:hover) {
      }
      // 重置密码
      :deep(.el-button--warning) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 40px;
        margin: 0 10px 0 0;
        outline: none;
        color: #fff;
        background: linear-gradient(
            270deg,
            rgba(127, 85, 151, 1) 0%,
            rgba(179, 100, 145, 1) 100%
          ),
          #ad6190;
        width: auto;
        font-size: 14px;
        height: 36px;
      }
      // 重置密码悬浮
      :deep(.el-button--warning:hover) {
      }
      .r-login {
        cursor: pointer;
        padding: 10px 0 0;
        color: #ccc;
        width: 100%;
        font-size: 14px;
        text-align: right;
      }
    }
  }
}
</style>
