<template>
  <div>
    <canvas
      id="canvas"
      style="
        background: url(http://localhost:8080/tourism/file/5c406dea56f94e7a8862ce254a053b09.jpg)
          no-repeat center center / cover;
      "
    ></canvas>
    <div class="register_view">
      <el-form :model="registerForm" class="register_form">
        <div class="title_view">{{ projectName }}注册</div>
        <div class="list_item">
          <div class="list_label">账号：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.zhanghao"
            placeholder="请输入账号"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">姓名：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.xingming"
            placeholder="请输入姓名"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">密码：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.mima"
            placeholder="请输入密码"
            type="password"
          />
        </div>
        <div class="list_item">
          <div class="list_label">确认密码：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.mima2"
            type="password"
            placeholder="请输入确认密码"
          />
        </div>
        <div class="list_item">
          <div class="list_label">头像：</div>
          <div
            :style="{ width: '100%', margin: '0 0 0 10px' }"
            class="list_file_list"
          >
            <uploads
              action="file/upload"
              tip="请上传头像"
              :limit="3"
              :fileUrls="registerForm.touxiang ? registerForm.touxiang : ''"
              @change="touxiangUploadSuccess"
            >
            </uploads>
          </div>
        </div>
        <div class="list_item">
          <div class="list_label">性别：</div>
          <el-select
            class="list_sel"
            v-model="registerForm.xingbie"
            placeholder="请选择性别"
          >
            <el-option
              v-for="item in yonghuxingbieLists"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </div>

        <div class="list_item">
          <div class="list_label">手机：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.shouji"
            placeholder="请输入手机"
            type="text"
          />
        </div>
        <div class="list_btn">
          <el-button class="register" type="success" @click="handleRegister"
            >注册</el-button
          >
          <div class="r-login" @click="close">已有账号，直接登录</div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onUnmounted, onMounted } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context?.$project.projectName
//动态背景
import canvasBg from '@/assets/js/canvas-bg-2.js'
import '@/assets/css/canvas-bg-2.css'
onMounted(() => {
  canvasBg()
})
// onUnmounted(()=>{
// 	nextTick(()=>{
// 		canvasBg = null
// 	})
// })
//获取注册类型
import { useRoute } from 'vue-router'
const route = useRoute()
const tableName = ref('yonghu')

//公共方法
const getUUID = () => {
  return new Date().getTime()
}
const registerForm = ref({
  xingbie: '',
})
const yonghuxingbieLists = ref([])
const init = () => {
  yonghuxingbieLists.value = '男,女'.split(',')
}
const touxiangUploadSuccess = (fileUrls) => {
  registerForm.value.touxiang = fileUrls
}
// 多级联动参数
//注册按钮
const handleRegister = () => {
  let url = tableName.value + '/register'
  if (!registerForm.value.zhanghao) {
    context?.$toolUtil.message(`账号不能为空`, 'error')
    return false
  }
  if (!registerForm.value.mima) {
    context?.$toolUtil.message(`密码不能为空`, 'error')
    return false
  }
  if (registerForm.value.mima != registerForm.value.mima2) {
    context?.$toolUtil.message('两次密码输入不一致', 'error')
    return false
  }
  if (registerForm.value.touxiang != null) {
    registerForm.value.touxiang = registerForm.value.touxiang.replace(
      new RegExp(context?.$config.url, 'g'),
      ''
    )
  }
  if (
    registerForm.value.shouji &&
    !context?.$toolUtil.isMobile(registerForm.value.shouji)
  ) {
    context?.$toolUtil.message(`手机应输入手机格式`, 'error')
    return false
  }

  context
    ?.$http({
      url: url,
      method: 'post',
      data: registerForm.value,
    })
    .then((res) => {
      context?.$toolUtil.message('注册成功', 'success', (obj) => {
        context?.$router.push({
          path: '/login',
        })
      })
    })
}
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
init()
</script>
<style lang="scss" scoped>
.register_view {
  background: none !important;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  position: relative;
  flex-direction: column;
  // 表单盒子
  .register_form {
    border-radius: 0px;
    padding: 30px 150px 30px 20px;
    margin: 0 auto;
    clip-path: polygon(0% 0%, 75% 0%, 100% 50%, 75% 100%, 0% 100%);
    background: rgba(0, 0, 0, 0.8);
    display: flex;
    width: 35vw;
    justify-content: flex-start;
    flex-wrap: wrap;
  }
  // 标题样式
  .title_view {
    padding: 0px;
    margin: 0 auto 20px;
    color: #fff;
    font-weight: 500;
    width: 80%;
    font-size: 18px;
    text-align: center;
  }
  // item盒子
  .list_item {
    padding: 5px 5px 5px 0;
    margin: 10px auto;
    background: #00000030;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    // label
    .list_label {
      color: #ccc;
      background: none;
      width: 100px;
      font-size: 14px;
      line-height: 26px;
      box-sizing: border-box;
      text-align: right;
    }
    // 输入框
    :deep(.list_inp) {
      border: 0px solid #ddd;
      border-radius: 0px;
      padding: 0 10px;
      color: #ccc;
      background: none;
      width: 100%;
      line-height: 26px;
      box-sizing: border-box;
      height: 26px;
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
  }
  //下拉框样式
  :deep(.list_sel) {
    border: 0px solid #ddd;
    border-radius: 0px;
    padding: 0 10px;
    color: #ccc;
    background: none;
    width: 100%;
    line-height: 26px;
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
  //按钮盒子
  .list_btn {
    margin: 10px auto;
    display: flex;
    width: 80%;
    align-items: center;
    flex-wrap: wrap;
    //注册按钮
    .register {
      border: none;
      border-radius: 0px;
      margin: 0 auto;
      color: #fff;
      background: linear-gradient(
          270deg,
          rgba(127, 85, 151, 1) 0%,
          rgba(179, 100, 145, 1) 100%
        ),
        #ad6190;
      width: 100%;
      font-size: 16px;
      height: 40px;
    }
    //注册按钮悬浮样式
    .register:hover {
      border: none;
      border-radius: 0;
      background: linear-gradient(
          270deg,
          rgba(127, 85, 151, 1) 0%,
          rgba(179, 100, 145, 1) 100%
        ),
        #ad6190;
      width: 100%;
      font-size: 16px;
      height: 40px;
    }
    //已有账号
    .r-login {
      cursor: pointer;
      padding: 10px 0 0;
      color: #ccc;
      width: 100%;
      font-size: 14px;
      text-align: right;
    }
  }
  //图片上传样式
  .list_file_list {
    //提示语
    :deep(.el-upload__tip) {
      margin: 7px 0 0;
      color: #999;
      display: flex;
      font-size: 14px;
      justify-content: flex-start;
      align-items: center;
    }
    //外部盒子
    :deep(.el-upload--picture-card) {
      border: 1px solid #666;
      cursor: pointer;
      border-radius: 0px;
      margin: 5px 0 0;
      background: none;
      width: 160px;
      line-height: 70px;
      text-align: center;
      height: 60px;
      //图标
      .el-icon {
        color: #999;
        font-size: 26px;
      }
    }
    :deep(.el-upload-list__item) {
      border: 1px solid #666;
      cursor: pointer;
      border-radius: 0px;
      margin: 5px 0 0;
      background: none;
      width: 160px;
      line-height: 70px;
      text-align: center;
      height: 60px;
    }
  }
}
</style>
