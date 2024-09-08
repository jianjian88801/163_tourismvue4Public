<template>
	<div class="app-contain" :style='{"width":"1200px","padding":"0","margin":"0 auto","position":"relative","borderRadius":"20px","background":"#fff"}'>
		<div class="back_view" v-if="centerType">
			<el-button class="back_btn" @click="backClick" type="primary">返回</el-button>
		</div>
		<div class="section_title">
			{{myType?'我的发布':formName}}
		</div>
		<el-form :model="searchQuery" class="list_search">
			<div class="search_view">
				<div class="search_label">
					标题：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.title" placeholder="标题" size="small" clearable>
					</el-input>
				</div>
			</div>
			<div class="search_btn_view">
				<el-button class="search_btn" type="primary" @click="searchClick">搜索</el-button>
				<el-button class="add_btn" @click="addClick" v-if="!myType&&btnAuth('forum','新增')" type="success">发布新帖</el-button>
			</div>
		</el-form>
		<div class="forum_list">
			<div class="forum_item" v-for="(item,index) in list" :key="index" @mouseenter="forumEnter(index)"
				@mouseleave="forumLeave" @click.stop="detailClick(item.id)">
				<div class="forum_item_left">
					<span class="forum_item_title">{{item.title}}</span>
					<span class="forum_item_name">(发布人：{{item.username}})</span>
				</div>
				<div class="forum_item_btn_box" v-if="item.userid==userid&&forumShowIndex==index&&(btnAuth('forum','修改')||btnAuth('forum','删除'))">
					<el-button class="forum_edit_btn" v-if="btnAuth('forum','修改')" type="primary" @click.stop="editClick(item.id)">修改</el-button>
					<el-button class="forum_del_btn" v-if="btnAuth('forum','删除')" type="danger" @click.stop="delClick(item.id)">删除</el-button>
				</div>
				<div class="forum_time" v-else>{{item.addtime}}</div>
			</div>
			<el-pagination
				background 
				:layout="layouts.join(',')"
				:total="total" 
				:page-size="listQuery.limit"
				prev-text="上一页"
				next-text="下一页"
				:hide-on-single-page="false"
				:style='{"border":"0px solid #eee","padding":"4px 0","margin":"10px 0 20px","whiteSpace":"nowrap","color":"#333","textAlign":"center","flexWrap":"wrap","background":"none","display":"flex","width":"100%","fontWeight":"500","justifyContent":"center"}'
				@size-change="sizeChange"
				@current-change="currentChange" 
				@prev-click="prevClick"
				@next-click="nextClick"  />
		</div>
		
		<el-dialog v-model="formVisible" :title="formTitle" width="70%" destroy-on-close>
			<el-form class="add_form" :model="form" label-width="120px" :rules="rules" ref="formRef">
				<el-row>
					<el-col :span="24">
						<el-form-item label="标题" prop="title">
							<el-input class="list_inp" v-model="form.title" placeholder="请输入标题"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="类型">
							<el-radio-group class="list_radio" v-model="form.isdone">
								<el-radio label="开放">公开</el-radio>
								<el-radio label="关闭">私人</el-radio>
							</el-radio-group>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="内容" prop="content">
							<editor :value="form.content" placeholder="请输入内容"
								class="list_editor" @change="contentChange"></editor>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer>
				<span class="formModel_btn_box">
					<el-button class="formModel_cancel" @click="formVisible=false">关闭</el-button>
					<el-button class="formModel_confirm" type="primary" @click="save">{{form.id?'修改保存':'发布'}}</el-button>
				</span>
			</template>
		</el-dialog>
		<el-dialog v-model="detailVisible" :title="''" width="70%" destroy-on-close>
			<div class="forum_detail">
				<div class="forum_title">
					{{detailForm.title}}
				</div>
				<div class="forum_info">
					<span class="forum_name">发布人：{{detailForm.username}}</span>
					<span class="forum_time">发布时间：{{detailForm.addtime}}</span>
				</div>
				<el-divider />
				<div class="forum_content" v-html="detailForm.content"></div>
			</div>
			<el-card class="forum_comment_box">
				<el-divider class="comment_divider" content-position="left">评论列表</el-divider>
				<div class="comment_add_box">
					<el-button class="comment_add" @click="commentClick" type="success">发表评论</el-button>
				</div>
				<div class="forum_comment_list">
					<div class="forum_comment" v-for="(item,index) in detailForm.childs" :key="index"
						@mouseenter="commentEnter(index)" @mouseleave="commentLeave">
						<div class="forum_comment_user">
							<img v-if="item.avatarurl" class="forum_comment_user_avatar"
								:src="$config.url + item.avatarurl.split(',')[0]" alt="">
							<img v-else class="forum_comment_user_avatar" src="@/assets/avatar.png" alt="">
							<span class="forum_comment_username">用户：{{item.username}}</span>
						</div>
						<div class="forum_comment_content" v-html="item.content"></div>
						<div class="forum_comment_reply_view">
							<div class="forum_comment_del" v-if="commentShowIndex==index&&item.userid==userid"
								@click="commentDelClick(item.id)">删除</div>
							<div class="forum_comment_reply" v-if="commentShowIndex==index"
								@click="commentReplyClick(item.id)">回复</div>
						</div>
						<div class="forum_comment_second_list" v-if="item.childs">
							<el-divider class="forum_comment_second_divider" content-position="left">回复</el-divider>
							<div class="forum_comment_second" v-for="(items,indexs) in item.childs" :key="indexs"
								@mouseenter="commentEnter1(indexs)" @mouseleave="commentLeave1">
								<div class="forum_comment_second_user">
									<img v-if="items.avatarurl" class="forum_comment_second_user_avatar"
										:src="$config.url + items.avatarurl.split(',')[0]" alt="">
									<img v-else class="forum_comment_second_user_avatar" src="@/assets/avatar.png" alt="">
									<span class="forum_comment_second_username">用户：{{items.username}}</span>
								</div>
								<div class="forum_comment_second_content" v-html="items.content"></div>
								<div class="forum_comment_second_reply_view">
									<div class="forum_comment_second_del" 
										v-if="commentShowIndex==index&&items.userid==userid&&commentShowIndex1==indexs"
										@click="commentDelClick(items.id)">删除</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</el-card>
		</el-dialog>
		<el-dialog v-model="commentVisible" :title="'发表评论'" width="50%" destroy-on-close>
			<el-form class="add_form" :model="commentForm" label-width="120px">
				<el-row>
					<el-col :span="24">
						<el-form-item prop="content" label="内容">
							<editor :value="commentForm.content" placeholder="请输入评论"
								class="list_editor" @change="commentChange"></editor>
						</el-form-item>
					</el-col>
				</el-row>
				
			</el-form>
			<template #footer>
				<span class="formModel_btn_box">
					<el-button class="formModel_cancel" @click="commentVisible=false">关闭</el-button>
					<el-button class="formModel_confirm" type="primary" @click="commentSave">发表</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script setup>
	import {
		ref,
		nextTick,
		getCurrentInstance
	} from 'vue';
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//基础信息
	const tableName = 'forum'
	const formName = '旅游日记'
	const router = useRouter()
	const route = useRoute()
	//基础信息
	//权限验证
	const btnAuth = (e, a) => {
		return context?.$toolUtil.isAuth(e, a)
	}
	const list = ref([])
	const listLoading = ref(false)
	const listQuery = ref({
		page: 1,
		limit: 20,
		isdone: '开放',
		sort: 'addtime',
		order: 'desc'
	})
	const myListQuery = ref({
		page: 1,
		limit: 20,
		parentid: 0,
		sort: 'addtime',
		order: 'desc'
	})
	const searchQuery = ref({})
	//分页
	const layouts = ref(["prev","pager","next"])
	const total = ref(0)
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	const prevClick = () => {
		listQuery.value.page = listQuery.value.page - 1
		getList()
	}
	const nextClick = () => {
		listQuery.value.page = listQuery.value.page + 1
		getList()
	}
	//分页
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	const getList = () => {
		listLoading.value = true
		let params = myType.value ? JSON.parse(JSON.stringify(myListQuery.value)) : JSON.parse(JSON.stringify(listQuery
			.value))
		if (searchQuery.value.title && searchQuery.value.title != '') {
			params['title'] = `%${searchQuery.value.title}%`
		}
		context?.$http({
			url: `forum/${myType.value?'page':'flist'}`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = res.data.data.total
		})
	}
	const userid = ref(0)
	const form = ref({
		title: '',
		isdone: '开放',
		content: '',
		parentid: 0,
		userid: userid.value,
		username: context?.$toolUtil.storageGet('frontName')
	})
	const rules = ref({
		title: [{
			required: true,
			message: '请输入标题',
			trigger: 'blur'
		}, ],
		content: [{
			required: true,
			message: '请输入内容',
			trigger: 'blur'
		}, ],
	})
	const formRef = ref(null)
	const formVisible = ref(false)
	const formTitle = ref('')
	const forumShowIndex = ref(-1)
	//判断按钮显示
	const forumEnter = (index) => {
		forumShowIndex.value = index
	}
	const forumLeave = () => {
		forumShowIndex.value = -1
	}
	const resetForm = () => {
		form.value = {
			title: '',
			isdone: '开放',
			content: '',
			parentid: 0,
			userid: userid.value,
			username: context?.$toolUtil.storageGet('frontName')
		}
	}
	//发布新贴
	const addClick = () => {
		resetForm()
		formTitle.value = '发布新帖'
		formVisible.value = true
	}
	//修改帖子
	const editClick = (id = null) => {
		if (id) {
			context?.$http({
				url: `forum/detail/${id}`,
				method: 'get'
			}).then(res => {
				form.value = res.data.data
				formTitle.value = '修改帖子'
				formVisible.value = true
			})
		}

	}
	//帖子内容回调
	const contentChange = (e)=>{
		form.value.content = e
	}
	//判断是否调我的发布列表
	const myType = ref(false)
	//判断是否从个人中心跳转
	const centerType = ref(false)
	//返回
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	//初始化
	const init = () => {
		if (route.query.centerType) {
			centerType.value = true
		}
		if (route.query.myType) {
			myType.value = true
		}
		userid.value = context?.$toolUtil.storageGet('userid')
		getList()
	}
	const detailVisible = ref(false)
	const detailForm = ref({})
	//详情
	const detailClick = (id = null) => {
		if (id) {
			context?.$http({
				url: `forum/list/${id}`,
				method: 'get'
			}).then(res => {
				detailForm.value = res.data.data
				detailVisible.value = true
			})
		}
	}
	//删除
	const delClick = (id = null) => {
		if (id) {
			ElMessageBox.confirm(`是否删除选中帖子`, '提示', {
				confirmButtonText: '是',
				cancelButtonText: '否',
				type: 'warning',
			}).then(() => {
				context?.$http({
					url: `forum/delete`,
					method: 'post',
					data: [id]
				}).then(res => {
					context?.$toolUtil.message('删除成功', 'success', () => {
						getList()
					})
				})
			})
		}
	}
	//发布帖子
	const save = () => {
		formRef.value.validate((valid) => {
			if (valid) {
				context?.$http({
					url: `forum/${form.value.id?'update':'add'}`,
					method: 'post',
					data: form.value
				}).then(res => {
					context?.$toolUtil.message(`${form.value.id?'修改':'发布'}成功`, 'success', () => {
						getList()
						formVisible.value = false
					})
				})
			}
		})
	}
	//评论
	const commentForm = ref({
		content: '',
		parentid: '',
		userid: '',
		username: '',
		avatarurl: '',
	})
	const commentVisible = ref(false)
	//重置评论
	const resetCommentForm = () => {
		commentForm.value = {
			content: '',
			parentid: '',
			userid: context?.$toolUtil.storageGet('userid'),
			username: context?.$toolUtil.storageGet('frontName'),
			avatarurl: context?.$toolUtil.storageGet('headportrait') ? context?.$toolUtil.storageGet('headportrait') : '',
		}
	}
	//发表评论
	const commentClick = () => {
		resetCommentForm()
		commentForm.value.parentid = detailForm.value.id
		commentVisible.value = true
	}
	//回复按钮显示
	const commentShowIndex = ref(-1)
	const commentShowIndex1 = ref(-1)
	const commentEnter = (index) => {
		commentShowIndex.value = index
	}
	const commentLeave = () => {
		commentShowIndex.value = -1
	}
	const commentEnter1 = (index) => {
		commentShowIndex1.value = index
	}
	const commentLeave1 = () => {
		commentShowIndex1.value = -1
	}
	//评论内容回调
	const commentChange = (e)=>{
		commentForm.value.content = e
	}
	//发表保存
	const commentSave = () => {
		if(commentForm.value.content==''){
			context?.$toolUtil.message(`请输入内容`, 'error')
			return false
		}
		let sensitiveWords = "";
		let sensitiveWordsArr = [];
		if(sensitiveWords) {
			sensitiveWordsArr = sensitiveWords.split(",");
		}
		for(var i=0; i<sensitiveWordsArr.length; i++){
			//全局替换
			var reg = new RegExp(sensitiveWordsArr[i],"g");
			//判断内容中是否包括敏感词
			if (commentForm.value.content.indexOf(sensitiveWordsArr[i]) > -1) {
				// 将敏感词替换为 **
				commentForm.value.content = commentForm.value.content.replace(reg,"**");
			}
		}
		context?.$http({
			url: 'forum/add',
			method: 'post',
			data: commentForm.value
		}).then(res => {
			context?.$toolUtil.message(`发表成功`, 'success', () => {
				detailClick(detailForm.value.id)
				commentVisible.value = false
			})
		})
	}
	//评论删除
	const commentDelClick = (id = null) => {
		if (id) {
			ElMessageBox.confirm(`是否删除选中评论`, '提示', {
				confirmButtonText: '是',
				cancelButtonText: '否',
				type: 'warning',
			}).then(() => {
				context?.$http({
					url: `forum/delete`,
					method: 'post',
					data: [id]
				}).then(res => {
					context?.$toolUtil.message('删除成功', 'success', () => {
						detailClick(detailForm.value.id)
					})
				})
			})
		}
	}
	//评论回复
	const commentReplyClick = (id = null) => {
		if (id) {
			resetCommentForm()
			commentForm.value.parentid = id
			commentVisible.value = true
		}
	}
	init()
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
	//搜索
	.list_search {
		border: 0px solid #ddd;
		padding: 20px 0 0 20px;
		margin: 0;
		background: #ffff;
		display: flex;
		width: calc(100% - 0px);
		justify-content: center;
		align-items: center;
		.search_view {
			margin: 0 10px 0 0;
			display: flex;
			align-items: center;
			.search_label {
				margin: 0 10px 0 0;
				color: #333;
				font-weight: 500;
				display: inline-block;
				font-size: 14px;
				line-height: 40px;
				height: 40px;
			}
			.search_box {
				display: flex;
				width: calc(100% - 100px);
				// 输入框
				:deep(.search_inp) {
					padding: 0 10px;
					border-color: #eee;
					outline-offset: 0px;
					line-height: 36px;
					box-sizing: border-box;
					border-radius: 00px;
					box-shadow: 0 0 0px rgba(85, 255, 255, 0.5);
					outline: 0px solid #efefef;
					background: #eee;
					width: 100%;
					border-width: 0 0 1px;
					border-style: solid;
					min-width: 200px;
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
		}
		.search_btn_view {
			margin: 20px 0;
			display: flex;
			// 搜索按钮
			.search_btn {
				border: 0;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 20px;
				margin: 0 10px 0 0;
				color: #fff;
				background: rgba(194,31,48,1);
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 搜索按钮-悬浮
			.search_btn:hover {
				opacity: 0.8;
			}
			// 新增按钮
			.add_btn {
				border: 0;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 20px;
				margin: 0 10px 0 0;
				color: #fff;
				background: #999;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 新增按钮-悬浮
			.add_btn:hover {
				opacity: 0.8;
			}
		}
	}
	// 列表盒子
	.forum_list {
		border: 0px solid #eee;
		border-radius: 0 0 20px 20px;
		padding: 0 20px 20px;
		box-shadow: 0 0px 0px rgba(0, 0, 0, .3);
		margin: 20px auto 40px;
		background: #fff;
		display: flex;
		width: 100%;
		justify-content: space-between;
		box-sizing: border-box;
		flex-wrap: wrap;
		// item
		.forum_item {
			cursor: pointer;
			padding: 0 0 10px;
			margin: 0 0 10px;
			color: #666;
			display: flex;
			border-color: #ddd;
			box-sizing: border-box;
			border-radius: 0px;
			background: none;
			width: 100%;
			justify-content: space-between;
			border-width: 0 0 1px;
			align-items: center;
			border-style: dotted;
			height: 40px;
			// item左部
			.forum_item_left {
				display: flex;
				align-items: center;
				// 标题
				.forum_item_title {
					font-weight: 600;
					font-size: 14px;
				}
				// 发布人
				.forum_item_name {
					color: #999;
					font-weight: 500;
					display: none;
					font-size: 12px;
				}
			}
			// 按钮盒子
			.forum_item_btn_box {
				// 修改按钮
				.forum_edit_btn {
					border: 0px solid rgba(2,102,181,.5);
					cursor: pointer;
					border-radius: 0px;
					padding: 0 20px;
					margin: 0 10px 0 0;
					outline: none;
					color: rgba(2,102,181,1);
					background: none;
					width: auto;
					font-size: 14px;
					height: 30px;
				}
				// 修改按钮-悬浮
				.forum_edit_btn:hover {
				}
				// 删除按钮
				.forum_del_btn {
					border: 0px solid #cc000050;
					cursor: pointer;
					border-radius: 0px;
					padding: 0 24px;
					margin: 0 10px 0 0;
					outline: none;
					color: #c00;
					background: none;
					width: auto;
					font-size: 14px;
					height: 30px;
				}
				// 删除按钮-悬浮
				.forum_del_btn:hover {
				}
			}
			// 时间
			.forum_time {
				color: #999;
				font-size: 14px;
			}
		}

		.forum_item:hover {
			color: #333;
			background: none;
		}
	}

	//帖子详情
	.forum_detail {
		padding: 20px 20px 0;
		margin: 0;
		background: #fff;
		width: 100%;

		.forum_title {
			margin: 0;
			color: #333;
			font-weight: 600;
			width: 100%;
			font-size: 18px;
			text-align: center;
		}

		.forum_info {
			padding: 0 20px;
			margin: 20px 0;
			color: #999;
			width: 100%;
			font-size: 14px;
			text-align: center;
			.forum_name {
				border: 0px solid #f5f5f5;
				padding: 0 4px 0 0;
				border-width: 0 0px 0 0;
			}
			.forum_time {
				padding: 0 0 0 4px;
			}
		}

		.forum_content {
			border: 0px solid #eee;
			padding: 0 20px;
			color: #666;
			background: none;
			width: 100%;
			font-size: 14px;
			text-align: left;
		}
	}
	// 评论盒子
	.forum_comment_box {
		border: 0px solid #ddd;
		border-radius: 0 0 20px 20px;
		box-shadow: none;
		padding: 0 20px;
		margin: 0;
		// 分割线
		:deep(.comment_divider) {
			border: none;
			background: #ddd;
			// 分割线文字样式
			.el-divider__text {
				color: #333;
				font-size: 16px;
			}
		}
		// 新增评论盒子
		.comment_add_box {
			text-align: right;
			// 按钮
			.comment_add {
				border: 0px solid #0099ff30;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 10px 0 0;
				color: #fff;
				background: rgba(194,31,48,1);
				width: auto;
				font-size: 14px;
				line-height: 36px;
				height: 36px;
			}
			// 按钮-悬浮
			.comment_add:hover {
			}
		}
		//帖子评论
		.forum_comment_list {
			margin: 30px 0 0;
			width: 100%;
			text-align: left;
			// 评论item
			.forum_comment {
				padding: 20px;
				margin: 0 0 20px;
				background: none;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 4px;
				float: left;
				border-style: double;
				// 评论用户盒子
				.forum_comment_user {
					border-radius: 4px 4px 0 0;
					padding: 5px;
					background: none;
					display: flex;
					align-items: center;
					// 用户头像
					.forum_comment_user_avatar {
						border-radius: 50%;
						margin: 0 10px 0 0;
						width: 40px;
						height: 40px;
					}
					// 用户名
					.forum_comment_username {
						color: #333;
						font-size: 16px;
					}
				}
				// 评论内容
				.forum_comment_content {
					border-radius: 0px;
					padding: 0px;
					margin: 0;
					color: #666;
					background: none;
					width: calc(100% - 0px);
					font-size: 14px;
					border-color: #eee;
					border-width: 0px;
					border-style: solid;
				}
				// 回复按钮盒子
				.forum_comment_reply_view {
					padding: 0 20px 0 0;
					margin: 20px 0;
					display: flex;
					width: 100%;
					justify-content: flex-end;
					align-items: center;
					box-sizing: border-box;
					height: 30px;
					// 删除按钮
					.forum_comment_del {
						cursor: pointer;
						border: 1px solid #cc000030;
						border-radius: 4px;
						padding: 6px 20px;
						margin: 0 0 0 10px;
						color: #c00;
						background: #cc000010;
						font-size: 14px;
					}
					// 删除按钮-悬浮
					.forum_comment_del:hover {
					}
					// 回复按钮
					.forum_comment_reply {
						cursor: pointer;
						border: 1px solid #27bacc30;
						border-radius: 4px;
						padding: 6px 20px;
						margin: 0 0 0 10px;
						color: #27bacc;
						background: #27bacc10;
						font-size: 14px;
					}
					// 回复按钮-悬浮
					.forum_comment_reply:hover {
					}
				}
				// 二级评论盒子
				.forum_comment_second_list {
					padding: 0px;
					margin: 0 0 0 0px;
					width: calc(100% - 0px);
					// 分割线
					:deep(.forum_comment_second_divider) {
						border: none;
						margin: 10px 0 40px;
						background: #ddd;
						// 分割线文字样式
						.el-divider__text {
							color: #333;
							font-size: 16px;
						}
					}
					// 二级评论item
					.forum_comment_second {
						border: 0px solid #eee;
						border-radius: 4px;
						padding: px;
						margin: 0 0 0px;
						background: none;
						width: 100%;
						border-width: 0 0 0px;
						// 用户盒子
						.forum_comment_second_user {
							display: flex;
							align-items: center;
							// 用户头像
							.forum_comment_second_user_avatar {
								border-radius: 50%;
								margin: 0 10px 0 0;
								width: 40px;
								height: 40px;
							}
							// 用户名
							.forum_comment_second_username {
								color: #666;
								font-size: 14px;
							}
						}
						// 二级内容
						.forum_comment_second_content {
							border-radius: 4px;
							padding: 0px;
							margin: 6px 0 0 ;
							color: #999;
							background: none;
							width: calc(100% - 0px);
							font-size: 14px;
						}
						// 二级按钮盒子
						.forum_comment_second_reply_view {
							padding: 0 20px 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							box-sizing: border-box;
							height: 30px;
							// 二级删除
							.forum_comment_second_del {
								cursor: pointer;
								border: 1px solid #cc000030;
								border-radius: 4px;
								padding: 6px 20px;
								margin: 0 0 0 10px;
								color: #c00;
								background: #cc000010;
								font-size: 14px;
							}
							// 二级删除-悬浮
							.forum_comment_second_del:hover {
							}
						}
					}
				}
			}
		}
	}
	
	
	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
			margin: 0 10px 0 0;
			color: #666;
			font-weight: 400;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 24px;
			height: 24px;
		}
		// 上一页
		:deep(.btn-prev) {
			border: 0px solid #ddd;
			border-radius: 4px;
			padding: 0 4px;
			margin: 0 2px;
			color: #666;
			background: none;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 24px;
			min-width: 24px;
			height: 24px;
		}
		// 下一页
		:deep(.btn-next) {
			border: 0px solid #ddd;
			border-radius: 4px;
			padding: 0 4px;
			margin: 0 2px;
			color: #666;
			background: none;
			display: inline-block;
			vertical-align: top;
			font-size: 14px;
			line-height: 24px;
			min-width: 24px;
			height: 24px;
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
			border: 0px solid #ddd;
			cursor: not-allowed;
			padding: 0 4px;
			margin: 0 2px;
			color: #C0C4CC;
			display: inline-block;
			vertical-align: top;
			font-size: 14px;
			line-height: 24px;
			border-radius: 4px;
			background: none;
			min-width: 24px;
			height: 24px;
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
			border: 0px solid #ddd;
			cursor: not-allowed;
			padding: 0 4px;
			margin: 0 2px;
			color: #C0C4CC;
			display: inline-block;
			vertical-align: top;
			font-size: 14px;
			line-height: 24px;
			border-radius: 4px;
			background: none;
			min-width: 24px;
			height: 24px;
		}
		// 页码
		:deep(.el-pager) {
			padding: 0;
			margin: 0;
			display: inline-block;
			vertical-align: top;
			// 数字
			.number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 24px;
				border-radius: 100%;
				background: #f4f4f5;
				text-align: center;
				min-width: 24px;
				height: 24px;
			}
			// 数字悬浮
			.number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 24px;
				border-radius: 100%;
				background: rgba(194,31,48,1);
				text-align: center;
				min-width: 24px;
				height: 24px;
			}
			// 选中
			.number.is-active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 24px;
				border-radius: 100%;
				background: rgba(194,31,48,1);
				text-align: center;
				min-width: 24px;
				height: 24px;
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			box-shadow: none;
			margin: 0 0 0 5px;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 24px;
			height: 24px;
			.el-select {
				border: 0px solid #DCDFE6;
				cursor: pointer;
				padding: 0;
				color: #606266;
				display: inline-block;
				font-size: 13px;
				line-height: 24px;
				border-radius: 2px;
				outline: 0;
				background: #f4f4f5;
				width: 100%;
				text-align: center;
				height: 24px;
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
		// 跳页
		:deep(.el-pagination__jump) {
			margin: 0 0 0 24px;
			color: #606266;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			// 输入框
			.el-input {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				margin: 0 6px;
				color: #606266;
				display: inline-block;
				font-size: 14px;
				line-height: 24px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 38px;
				text-align: center;
				height: 24px;
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
	}
	// 表单
	.add_form{
		border: 0px solid #27bacc30;
		border-radius: 0px;
		padding: 30px 7%;
		background: #fff;
		// form item
		:deep(.el-form-item) {
			border: 0px solid #eee;
			padding: 6px 0;
			margin: 0 0 20px 0;
			background: none;
			display: flex;
			//label
			.el-form-item__label {
			 background: none;
			 display: block;
			 width: auto;
			 min-width: 150px;
			 text-align: right;
			}
			// 内容盒子
			.el-form-item__content {
				display: flex;
				width: calc(100% - 150px);
				justify-content: flex-start;
				align-items: center;
				flex-wrap: wrap;
				// 输入框
				.list_inp {
					padding: 0 10px;
					background: none;
					width: 100%;
					border-color: #ddd;
					border-width: 0 0 1px;
					line-height: 36px;
					box-sizing: border-box;
					border-style: solid;
					min-width: 350px;
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
				// 富文本
				.list_editor {
					border-radius: 0;
					padding: 0;
					margin: 0;
					background: none;
					width: 99%;
					min-height: 250px;
					border-color: #eee;
					border-width: 1px;
					border-style: solid;
					height: auto;
				}
				//单选框样式
				.list_radio {
					display: flex;
					width: calc(100% - 120px);
					align-items: center;
					flex-wrap: wrap;
					//未选中样式
					.el-radio {
						margin: 0 20px 0 0;
						display: flex;
						width: auto;
						justify-content: center;
						align-items: center;
						//单选框
						.el-radio__inner {
							background: #fff;
							border-color: #999;
						}
						//提示文字
						.el-radio__label{
							color: #999;
						}
					}
					//选中样式
					.is-checked {
						//单选框
						.el-radio__inner {
							background: rgba(194,31,48,1);
							border-color: rgba(194,31,48,1);
						}
						//提示文字
						.el-radio__label{
							color: rgba(194,31,48,1);
						}
					}
				}
			}
		}
	}
	// 按钮盒子
	.formModel_btn_box {
		padding: 0 150px;
		display: flex;
		width: 100%;
		justify-content: center;
		align-items: center;
		.formModel_cancel {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 2px;
			padding: 0 40px;
			margin: 0 20px 0 0;
			color: #fff;
			background: #999;
			width: auto;
			font-size: 14px;
			line-height: 40px;
			height: 40px;
		}
		.formModel_cancel:hover {
		}
		
		.formModel_confirm {
			border: 0;
			cursor: pointer;
			border-radius: 2px;
			padding: 0 40px;
			margin: 0 20px 0 0;
			color: #fff;
			background: rgba(194,31,48,1);
			width: auto;
			font-size: 14px;
			line-height: 40px;
			height: 40px;
		}
		.formModel_confirm:hover {
		}
	}
</style>