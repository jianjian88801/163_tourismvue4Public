	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import menpiaoyuding from '@/views/menpiaoyuding/list'
	import news from '@/views/news/list'
	import discussjingquxinxi from '@/views/discussjingquxinxi/list'
	import discussmeishituijian from '@/views/discussmeishituijian/list'
	import xianludingdan from '@/views/xianludingdan/list'
	import storeup from '@/views/storeup/list'
	import forum from '@/views/forum/list'
	import meishigoumai from '@/views/meishigoumai/list'
	import jingdianfenlei from '@/views/jingdianfenlei/list'
	import yonghu from '@/views/yonghu/list'
	import jingquxinxi from '@/views/jingquxinxi/list'
	import meishituijian from '@/views/meishituijian/list'
	import lvyouxianlu from '@/views/lvyouxianlu/list'
	import messages from '@/views/messages/list'
	import config from '@/views/config/list'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/menpiaoyuding',
			name: '门票预订',
			component: menpiaoyuding
		}
		,{
			path: '/news',
			name: '旅游新闻',
			component: news
		}
		,{
			path: '/discussjingquxinxi',
			name: '景区信息评论评论',
			component: discussjingquxinxi
		}
		,{
			path: '/discussmeishituijian',
			name: '美食推荐评论评论',
			component: discussmeishituijian
		}
		,{
			path: '/xianludingdan',
			name: '线路订单',
			component: xianludingdan
		}
		,{
			path: '/storeup',
			name: '我的收藏',
			component: storeup
		}
		,{
			path: '/forum',
			name: '我的发布',
			component: forum
		}
		,{
			path: '/meishigoumai',
			name: '美食购买',
			component: meishigoumai
		}
		,{
			path: '/jingdianfenlei',
			name: '景点分类',
			component: jingdianfenlei
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/jingquxinxi',
			name: '景区信息',
			component: jingquxinxi
		}
		,{
			path: '/meishituijian',
			name: '美食推荐',
			component: meishituijian
		}
		,{
			path: '/lvyouxianlu',
			name: '旅游线路',
			component: lvyouxianlu
		}
		,{
			path: '/messages',
			name: '留言板',
			component: messages
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
