<template>
  <div>
    <canvas
      id="canvas"
      style="
        background: url(http://localhost:8080/tourism/file/5c406dea56f94e7a8862ce254a053b09.jpg)
          no-repeat center center / cover;
      "
    ></canvas>
    <div class="login_view">
      <el-form :model="loginForm" class="login_form">
        <div class="title_view">贵州红色文化旅游景区管理系统登录</div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">账号：</div>
          <input
            class="list_inp"
            v-model="loginForm.username"
            placeholder="请输入账号"
          />
        </div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">密码：</div>
          <input
            class="list_inp"
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            @keydown.enter.native="handleLogin"
          />
        </div>
        <div class="list_type" v-if="userList.length > 1">
          <div class="list_label">用户类型：</div>
          <el-select v-model="loginForm.role" placeholder="请选择用户类型">
            <el-option
              v-for="(item, index) in userList"
              :label="item.roleName"
              :value="item.roleName"
            ></el-option>
          </el-select>
        </div>
        <div class="btn_view">
          <el-button
            class="login"
            v-if="loginType == 1"
            type="success"
            @click="handleLogin"
            >登录</el-button
          >
        </div>
      </el-form>
    </div>
    <Vcode
      :show="isShow"
      @success="success"
      @close="close"
      @fail="fail"
    ></Vcode>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted, onUnmounted } from 'vue'
const userList = ref([])
const menus = ref([])
const loginForm = ref({
  role: '',
  username: '',
  password: '',
})
const tableName = ref('')
const loginType = ref(1)
const context = getCurrentInstance()?.appContext.config.globalProperties
//动态背景
import canvasBg from '@/assets/js/canvas-bg-2.js'
import '@/assets/css/canvas-bg-2.css'
// onUnmounted(()=>{
// 	nextTick(()=>{
// 		canvasBg = null
// 	})
// })
const handleLogin = () => {
  if (!loginForm.value.username) {
    context?.$toolUtil.message('请输入用户名', 'error')

    return
  }
  if (!loginForm.value.password) {
    context?.$toolUtil.message('请输入密码', 'error')

    return
  }
  if (userList.value.length > 1) {
    if (!loginForm.value.role) {
      context?.$toolUtil.message('请选择角色', 'error')
      verifySlider.value.reset()
      return
    }
    for (let i = 0; i < menus.value.length; i++) {
      if (menus.value[i].roleName == loginForm.value.role) {
        tableName.value = menus.value[i].tableName
      }
    }
  } else {
    tableName.value = userList.value[0].tableName
    loginForm.value.role = userList.value[0].roleName
  }
  login()
}
const login = () => {
  context
    ?.$http({
      url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
      method: 'post',
    })
    .then(
      (res) => {
        context?.$toolUtil.storageSet('Token', res.data.token)
        context?.$toolUtil.storageSet('role', loginForm.value.role)
        context?.$toolUtil.storageSet('sessionTable', tableName.value)
        context?.$toolUtil.storageSet('adminName', loginForm.value.username)
        context?.$router.push('/')
      },
      (err) => {}
    )
}
//获取菜单
const getMenu = () => {
  let params = {
    page: 1,
    limit: 1,
    sort: 'id',
  }

  context
    ?.$http({
      url: 'menu/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      menus.value = JSON.parse(res.data.data.list[0].menujson)
      for (let i = 0; i < menus.value.length; i++) {
        if (menus.value[i].hasBackLogin == '是') {
          userList.value.push(menus.value[i])
        }
      }
      loginForm.value.role = userList.value[0].roleName
      context?.$toolUtil.storageSet('menus', JSON.stringify(menus.value))
    })
}
//初始化
const init = () => {
  getMenu()
}
onMounted(() => {
  init()

  canvasBg()
})
</script>

<style lang="scss" scoped>
.login_view {
  background: none !important;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  position: relative;
  flex-direction: column;
  // 表单盒子
  .login_form {
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
      font-size: 13px;
      line-height: 36px;
      text-align: right;
    }
    // 输入框
    .list_inp {
      border: 0px solid #ddd;
      border-radius: 0px;
      padding: 0 10px;
      color: #ccc;
      background: none;
      width: 100%;
      line-height: 36px;
      height: 36px;
    }
  }
  .list_type {
    margin: 10px auto;
    background: #00000030;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    order: 3;
    .list_label {
      color: #ccc;
      background: none;
      width: 100px;
      font-size: 13px;
      line-height: 36px;
      text-align: right;
    }
    // 下拉框样式
    :deep(.el-select) {
      border: 0px solid #ddd;
      border-radius: 0px;
      padding: 0 20px 0 10px;
      color: #ccc;
      background: none;
      width: 100%;
      font-size: 14px;
      line-height: 36px;
      box-sizing: border-box;
      height: 36px;
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
  .btn_view {
    padding: 0;
    margin: 10px auto 0;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    order: 5;
    // 登录
    .login {
      border: 0;
      cursor: pointer;
      border-radius: 0px;
      padding: 0 24px;
      margin: 0 auto 10px;
      color: #fff;
      background: linear-gradient(
          270deg,
          rgba(127, 85, 151, 1) 0%,
          rgba(179, 100, 145, 1) 100%
        ),
        #ad6190;
      letter-spacing: 2px;
      width: 100%;
      font-size: 15px;
      height: 40px;
    }
  }
}
</style>
