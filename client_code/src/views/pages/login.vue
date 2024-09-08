<template>
	<div>
		<div class="login_view">
			<el-form :model="loginForm" class="login_form">
				<div class="title_view">贵州红色文化旅游景区管理系统登录</div>
				<div class="list_item" v-if="loginType==1">
					<div class="list_label">
						账号：
					</div>
					<input class="list_inp" v-model="loginForm.username" placeholder="请输入账号" />
				</div>
				<div class="list_item" v-if="loginType==1">
					<div class="list_label">
						密码：
					</div>
					<input class="list_inp" v-model="loginForm.password" type="password" placeholder="请输入密码" @keydown.enter.native="handleLogin" />
				</div>
				<div class="list_type" v-if="userList.length>1">
					<div class="list_label">
						用户类型：
					</div>
				  <el-select v-model="loginForm.role" placeholder="请选择用户类型">
				    <el-option v-for="(item,index) in userList" :label="item.roleName" :value="item.roleName"></el-option>
				  </el-select>
				</div>
				<div class="remember_view" v-if="loginType==1">
					<el-checkbox v-model="rememberPassword" label="记住密码" size="large" :true-label="true"
						:false-label="false" />
				</div>
				<div class="btn_view">
					<el-button class="login" v-if="loginType==1" type="success" @click="handleLogin">登录</el-button>
					<el-button class="register" type="primary" @click="handleRegister('yonghu')">注册用户</el-button>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
	} from "vue";
	import menu from '@/utils/menu'
	const userList = ref([])
	const menus = ref([])
	const loginForm = ref({
		role: '',
		username: '',
		password: ''
	})
	const tableName = ref('')
	const loginType = ref(1)
	//是否记住密码
	const rememberPassword = ref(true)
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//注册
    const handleRegister = (tableName) => {
    	context?.$router.push(`/${tableName}Register`)
    	
    }
	const handleLogin = () => {
		if (!loginForm.value.username) {
			context?.$toolUtil.message('请输入用户名', 'error')
			
			return;
		}
		if (!loginForm.value.password) {
			context?.$toolUtil.message('请输入密码', 'error')
			
			return;
		}
		if (userList.value.length > 1) {
			if (!loginForm.value.role) {
				context?.$toolUtil.message('请选择角色', 'error')
				verifySlider.reset()
				return;
			}
			for (let i = 0; i < menus.value.length; i++) {
				if (menus.value[i].roleName == loginForm.value.role) {
					tableName.value = menus.value[i].tableName;
				}
			}
		} else {
			tableName.value = userList.value[0].tableName;
			loginForm.value.role = userList.value[0].roleName;
		}
		login()
	}
	const login = () => {
		context?.$http({
			url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
			method: 'post'
		}).then(res => {
			//是否保存当前账号密码至缓存
			if (rememberPassword.value) {
				let loginForm1 = JSON.parse(JSON.stringify(loginForm.value))
				delete loginForm1.code
				context?.$toolUtil.storageSet("frontLoginForm", JSON.stringify(loginForm1));
			} else {
				context?.$toolUtil.storageRemove("frontLoginForm")
			}
			context?.$toolUtil.storageSet("frontToken", res.data.token);
			context?.$toolUtil.storageSet("frontRole", loginForm.value.role);
			context?.$toolUtil.storageSet("frontSessionTable", tableName.value);
			let path = context?.$toolUtil.storageGet('toPath')
			if (path) {
				context?.$router.push(path)
				context?.$toolUtil.storageRemove('toPath')
				return
			}
			context?.$router.push(`/index/${tableName.value}Center`)
		},err=>{
		})
	}
	//获取菜单
	const getMenu= async ()=> {
		let arr = menu.list()
		if(!arr){
			let res = await context?.$http.get("menu/list")
			context?.$toolUtil.storageSet("menus", res.data.data.list[0].menujson);
			arr = JSON.parse(res.data.data.list[0].menujson)
		}
		menus.value = arr
		for (let i = 0; i < menus.value.length; i++) {
			if (menus.value[i].hasFrontLogin=='是') {
				userList.value.push(menus.value[i])
			}
		}
    }
	//初始化
	const init = async () => {
		await getMenu();
		//获取缓存是否有保存的账号密码
		let form = context?.$toolUtil.storageGet('frontLoginForm')
		if (form) {
			loginForm.value = JSON.parse(form)
		}else {
			loginForm.value.role = userList.value[0].roleName
		}
	}
	onMounted(()=>{
		init()
	})
</script>

<style lang="scss" scoped>
	.login_view {
		background-repeat: no-repeat;
		flex-direction: column;
		background-size: cover !important;
		background: url(http://codegen.caihongy.cn/20240127/0f12f954eb2d454094f2cd37e986bec8.jpg);
		display: flex;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		position: relative;
		background-position: center bottom;
		// 表单盒子
		.login_form {
			border-radius: 8px;
			padding: 20px 80px 30px 40px;
			margin: 0;
			clip-path: polygon(2% 0, 100% 0%, 98% 100%, 0% 100%);
			background: rgba(255,255,255,.9);
			display: block;
			width: 680px;
			justify-content: center;
			position: relative;
			flex-wrap: wrap;
		}
		.title_view {
			padding: 0 0 30px;
			margin: 10px 0 0 20px;
			color: #333;
			background: none;
			font-weight: 600;
			width: calc(100% - 0px);
			font-size: 22px;
			text-align: center;
		}
		// item盒子
		.list_item {
			margin: 0 0 20px;
			display: flex;
			width: 100%;
			align-items: center;
			// label
			.list_label {
				padding: 0 6px 0 0;
				color: #333;
				width: 90px;
				font-size: 14px;
				text-align: right;
			}
			// 输入框
			.list_inp {
				border: 1px solid #ddd;
				border-radius: 4px;
				padding: 0 10px;
				width: calc(100% - 90px);
				line-height: 40px;
				height: 40px;
			}
		}
		.list_type {
			margin: 0 0 20px;
			display: flex;
			width: 100%;
			align-items: center;
			.list_label {
				padding: 0 6px 0 0;
				color: #333;
				width: 90px;
				font-size: 14px;
				text-align: right;
			}
			// 下拉框样式
			:deep(.el-select) {
				border: 1px solid #ddd;
				border-radius: 4px;
				padding: 0 10px;
				color: #666;
				background: #fff;
				width: calc(100% - 90px);
				line-height: 40px;
				box-sizing: border-box;
				//去掉默认样式
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;
						.el-input__wrapper{
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
		// 记住密码样式
		.remember_view {
			padding: 0 90px;
			margin: 0px auto;
			width: 100%;
			text-align: left;
		
			// 未选中样式
			:deep(.el-checkbox) {
				margin: 0;
				display: flex;
				width: 100%;
				justify-content: flex-start;
				align-items: center;
				// 复选框
				.el-checkbox__inner {
					border: 1px solid #DCDFE6;
					background: #fff;
				}
				// 提示文字
				.el-checkbox__label{
					color: #999;
				}
			}
			// 选中样式
			:deep(.is-checked) {
				//复选框
				.el-checkbox__inner {
					background-color: rgba(194,31,48,1);
					border-color: rgba(194,31,48,1);
				}
				// 提示文字
				.el-checkbox__label{
					color: rgba(194,31,48,1);
				}
			}
		}
		// 按钮盒子
		.btn_view {
			padding: 20px 0px 0 90px;
			display: flex;
			width: 100%;
			align-items: center;
			flex-wrap: wrap;
			// 登录
			.login {
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				margin: 0 10px 10px 0;
				outline: none;
				color: #fff;
				background: rgba(194,31,48,1);
				width: 100px;
				font-size: 16px;
				height: 36px;
			}
			// 注册
			.register {
				border: 1px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 10px 10px 0;
				outline: none;
				color: #333;
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
		}
	}

</style>