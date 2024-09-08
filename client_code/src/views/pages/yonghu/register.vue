<template>
	<div>
		<div class="register_view">
			<el-form :model="registerForm" class="register_form">
				<div class="title_view">{{projectName}}注册</div>
				<div class="list_item">
					<div class="list_label">账号：</div>
					<input class="list_inp"
					 v-model="registerForm.zhanghao" 
					 placeholder="请输入账号"
					 type="text"
					 />
				</div>
				<div class="list_item">
					<div class="list_label">姓名：</div>
					<input class="list_inp"
					 v-model="registerForm.xingming" 
					 placeholder="请输入姓名"
					 type="text"
					 />
				</div>
				<div class="list_item">
					<div class="list_label">密码：</div>
					<input class="list_inp"
					 v-model="registerForm.mima" 
					 placeholder="请输入密码"
					 type="password"
					 />
				</div>
				<div class="list_item">
					<div class="list_label">确认密码：</div>
					<input class="list_inp" v-model="registerForm.mima2" type="password" placeholder="请输入确认密码" />
				</div>
				<div class="list_item">
					<div class="list_label">头像：</div>
					<div class="list_file_list">
						<uploads
							action="file/upload" 
							tip="请上传头像" 
							:limit="3" 
							style="width: 100%;text-align: left;"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''" 
							@change="touxiangUploadSuccess">
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
						<el-option v-for="item in yonghuxingbieLists" :label="item" :value="item"></el-option>
					</el-select>
				</div>
				<div class="list_item">
					<div class="list_label">手机：</div>
					<input class="list_inp"
					 v-model="registerForm.shouji" 
					 placeholder="请输入手机"
					 type="text"
					 />
				</div>
				<div class="list_btn">
					<el-button class="register" type="success" @click="handleRegister">注册</el-button>
					<div class="r-login" @click="close">已有账号，直接登录</div>
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
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context?.$project.projectName
	//获取注册类型
	import { useRoute } from 'vue-router';
	const route = useRoute()
	const tableName = ref('yonghu')
	
	//公共方法
	const getUUID=()=> {
		return new Date().getTime();
	}
	
	const registerForm = ref({
        xingbie: '',
	})
	const yonghuxingbieLists = ref([])
	const init=()=>{
		yonghuxingbieLists.value = "男,女".split(',')
	}
    const touxiangUploadSuccess=(fileUrls)=> {
        registerForm.value.touxiang = fileUrls;
    }
	// 多级联动参数
	//注册按钮
	const handleRegister = () => {
		let url = tableName.value +"/register";
		if((!registerForm.value.zhanghao)){
			context?.$toolUtil.message(`账号不能为空`,'error')
			return false
		}
		if((!registerForm.value.mima)){
			context?.$toolUtil.message(`密码不能为空`,'error')
			return false
		}
		if(registerForm.value.mima!=registerForm.value.mima2){
			context?.$toolUtil.message('两次密码输入不一致','error')
			return false
		}
		if(registerForm.value.touxiang!=null){
			registerForm.value.touxiang = registerForm.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if(registerForm.value.shouji&&(!context?.$toolUtil.isMobile(registerForm.value.shouji))){
			context?.$toolUtil.message(`手机应输入手机格式`,'error')
			return false
		}
		
		context?.$http({
			url:url,
			method:'post',
			data:registerForm.value
		}).then(res=>{
			context?.$toolUtil.message('注册成功','success', obj=>{
				context?.$router.push({
					path: "/login"
				});
			})
		})
	}
	//返回登录
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
	init()
</script>
<style lang="scss" scoped>
	.register_view {
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
		.register_form {
			border-radius: 8px;
			padding: 30px 80px 30px 40px;
			margin: 0;
			clip-path: polygon(2% 0, 100% 0%, 98% 100%, 0% 100%);
			background: rgba(255,255,255,.9);
			display: block;
			width: 680px;
			justify-content: center;
			flex-wrap: wrap;
		}
		// 标题样式
		.title_view {
			padding: 0 0 30px;
			margin: 0 0 0 20px;
			color: #3f3f3f;
			background: none;
			font-weight: 600;
			width: calc(100% - 0px);
			font-size: 22px;
			text-align: center;
		}
		// item盒子
		.list_item {
			margin: 0 0 12px;
			display: flex;
			width: 100%;
			justify-content: flex-start;
			align-items: center;
			// label
			.list_label {
				color: #333;
				width: 120px;
				font-size: 14px;
				box-sizing: border-box;
				text-align: right;
			}
			// 输入框
			:deep(.list_inp) {
				border: 1px solid #ddd;
				border-radius: 4px;
				padding: 0 10px;
				width: calc(100% - 100px);
				line-height: 36px;
				box-sizing: border-box;
				height: 36px;
				//去掉默认样式
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
		//下拉框样式
		:deep(.list_sel) {
			border: 1px solid #ddd;
			border-radius: 4px;
			padding: 0 10px;
			background: #fff;
			width: calc(100% - 100px);
			line-height: 36px;
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
		//图片上传样式
		.list_file_list  {
			//提示语
			:deep(.el-upload__tip){
				margin: 7px 0 0;
				color: #999;
				display: flex;
				font-size: 14px;
				justify-content: flex-start;
				align-items: center;
			}
			//外部盒子
			:deep(.el-upload--picture-card){
				border: 1px solid #ddd;
				cursor: pointer;
				background-color: #fff;
				border-radius: 4px;
				width: 160px;
				line-height: 70px;
				text-align: center;
				height: 60px;
				//图标
				.el-icon{
					color: #bbb;
					font-size: 32px;
				}
			}
			:deep(.el-upload-list__item) {
				border: 1px solid #ddd;
				cursor: pointer;
				background-color: #fff;
				border-radius: 4px;
				width: 160px;
				line-height: 70px;
				text-align: center;
				height: 60px;
			}
		}
		//按钮盒子
		.list_btn {
			margin: 20px 0 0;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			//注册按钮
			.register {
					border: none;
					border-radius: 4px;
					margin: 0 20px;
					color: #fff;
					background: rgba(194,31,48,1);
					width: 120px;
					font-size: 16px;
					height: 40px;
			}
			//注册按钮悬浮样式
			.register:hover {
				border: none;
				border-radius: 4px;
				margin: 0 20px;
				background: rgba(194,31,48,1);
				width: 120px;
				font-size: 16px;
				opacity: 1;
				height: 40px;
			}
			//已有账号
			.r-login {
				cursor: pointer;
				padding: 01;
				color: #666;
				width: auto;
				font-size: 14px;
				text-align: right;
			}
		}
	}
</style>