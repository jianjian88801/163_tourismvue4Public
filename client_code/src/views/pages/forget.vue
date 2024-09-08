<template>
	<div>
		<div class="forget_view">
			<el-form :model="forgetForm" class="forget_box">
				<div class="forget_title">贵州红色文化旅游景区管理系统{{pageType==1?'找回密码':pageType==2?'输入密保':'重置密码'}}</div>
				<div class="tab_view">
					<div class="tab" :class="pageType==1?'tab_active':''">找回密码</div>
					<div class="tab" :class="pageType==2?'tab_active tab_active1':''">输入密保</div>
					<div class="tab" :class="pageType==3?'tab_active tab_active1 tab_active2':''">重置密码</div>
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
	const pageType = ref(1)
	const forgetForm = ref({})
	const userForm = ref({})
	//返回登录
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
</script>

<style lang="scss" scoped>
	.forget_view {
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
		.forget_box {
			border-radius: 8px;
			padding: 30px 80px 30px 40px;
			margin: 0;
			clip-path: polygon(2% 0, 100% 0%, 98% 100%, 0% 100%);
			background: rgba(255,255,255,.9);
			display: block;
			width: 680px;
			justify-content: center;
			align-items: flex-start;
			flex-wrap: wrap;
			// 标题
			.forget_title {
				padding: 0 0 30px;
				margin: 0 0 0 20px;
				color: #3f3f3f;
				background: none;
				font-weight: 600;
				width: calc(100% - 0px);
				font-size: 22px;
				text-align: center;
			}
			// tab
			// 盒子
			.tab_view {
				padding: 0 10% 50px;
				display: flex;
				width: 100%;
				align-items: center;
				// 默认样式
				.tab {
					padding: 12px 0;
					width: calc(100% / 3);
					font-size: 14px;
					box-sizing: border-box;
					text-align: center;
				}
				// 选中样式一
				.tab_active {
					clip-path: polygon(00% 0%, 92% 00%, 100% 50%, 92% 100%, 0 100%, 8% 50%);
					color: #fff;
					background: #cc000099;
				}
				// 选中样式二
				.tab_active1 {
					color: #fff;
					background: #cc000099;
				}
				// 选中样式三
				.tab_active2 {
					color: #fff;
					background: #cc000099;
				}
			}
			// item
			.list_item {
				margin: 10px auto 30px;
				display: flex;
				width: 100%;
				justify-content: flex-start;
				align-items: center;
				// label
				.item_label {
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
					width: calc(100% - 120px);
					line-height: 40px;
					box-sizing: border-box;
					height: 40px;
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
				//下拉框样式
				:deep(.list_sel) {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 10px;
					background: #fff;
					width: calc(100% - 120px);
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
			// 按钮盒子
			.list_btn {
				margin: 30px 0 0;
				display: flex;
				width: 100%;
				justify-content: center;
				align-items: center;
				flex-wrap: wrap;
				// 获取密保
				:deep(.el-button--success) {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 30px;
					margin: 0 auto;
					color: #fff;
					background: rgba(194,31,48,1);
					width: 120px;
					font-size: 14px;
					height: 36px;
				}
				// 获取密保悬浮
				:deep(.el-button--success:hover) {
					opacity: 0.8;
				}
				// 确认密保
				:deep(.el-button--primary) {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 30px;
					margin: 0 auto;
					color: #fff;
					background: rgba(194,31,48,1);
					width: 120px;
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
					border-radius: 4px;
					padding: 0 30px;
					margin: 0 auto;
					color: #fff;
					background: rgba(194,31,48,1);
					width: 120px;
					font-size: 14px;
					height: 36px;
				}
				// 重置密码悬浮
				:deep(.el-button--warning:hover) {
				}
				.r-login {
					cursor: pointer;
					padding: 0;
					margin: 20px 0 0 120px;
					color: #666;
					width: calc(100% - 120px);
					font-size: 14px;
					text-align: right;
				}
			}
		}
	}
</style>
