import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import messagesList from '@/views/pages/messages/list'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import jingdianfenleiList from '@/views/pages/jingdianfenlei/list'
import jingdianfenleiDetail from '@/views/pages/jingdianfenlei/formModel'
import jingdianfenleiAdd from '@/views/pages/jingdianfenlei/formAdd'
import jingquxinxiList from '@/views/pages/jingquxinxi/list'
import jingquxinxiDetail from '@/views/pages/jingquxinxi/formModel'
import jingquxinxiAdd from '@/views/pages/jingquxinxi/formAdd'
import storeupList from '@/views/pages/storeup/list'
import menpiaoyudingList from '@/views/pages/menpiaoyuding/list'
import menpiaoyudingDetail from '@/views/pages/menpiaoyuding/formModel'
import menpiaoyudingAdd from '@/views/pages/menpiaoyuding/formAdd'
import meishituijianList from '@/views/pages/meishituijian/list'
import meishituijianDetail from '@/views/pages/meishituijian/formModel'
import meishituijianAdd from '@/views/pages/meishituijian/formAdd'
import meishigoumaiList from '@/views/pages/meishigoumai/list'
import meishigoumaiDetail from '@/views/pages/meishigoumai/formModel'
import meishigoumaiAdd from '@/views/pages/meishigoumai/formAdd'
import forumList from '@/views/pages/forum/list'
import lvyouxianluList from '@/views/pages/lvyouxianlu/list'
import lvyouxianluDetail from '@/views/pages/lvyouxianlu/formModel'
import lvyouxianluAdd from '@/views/pages/lvyouxianlu/formAdd'
import xianludingdanList from '@/views/pages/xianludingdan/list'
import xianludingdanDetail from '@/views/pages/xianludingdan/formModel'
import xianludingdanAdd from '@/views/pages/xianludingdan/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'messagesList',
			component: messagesList
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'jingdianfenleiList',
			component: jingdianfenleiList
		}, {
			path: 'jingdianfenleiDetail',
			component: jingdianfenleiDetail
		}, {
			path: 'jingdianfenleiAdd',
			component: jingdianfenleiAdd
		}
		, {
			path: 'jingquxinxiList',
			component: jingquxinxiList
		}, {
			path: 'jingquxinxiDetail',
			component: jingquxinxiDetail
		}, {
			path: 'jingquxinxiAdd',
			component: jingquxinxiAdd
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		, {
			path: 'menpiaoyudingList',
			component: menpiaoyudingList
		}, {
			path: 'menpiaoyudingDetail',
			component: menpiaoyudingDetail
		}, {
			path: 'menpiaoyudingAdd',
			component: menpiaoyudingAdd
		}
		, {
			path: 'meishituijianList',
			component: meishituijianList
		}, {
			path: 'meishituijianDetail',
			component: meishituijianDetail
		}, {
			path: 'meishituijianAdd',
			component: meishituijianAdd
		}
		, {
			path: 'meishigoumaiList',
			component: meishigoumaiList
		}, {
			path: 'meishigoumaiDetail',
			component: meishigoumaiDetail
		}, {
			path: 'meishigoumaiAdd',
			component: meishigoumaiAdd
		}
		, {
			path: 'forumList',
			component: forumList
		}
		, {
			path: 'lvyouxianluList',
			component: lvyouxianluList
		}, {
			path: 'lvyouxianluDetail',
			component: lvyouxianluDetail
		}, {
			path: 'lvyouxianluAdd',
			component: lvyouxianluAdd
		}
		, {
			path: 'xianludingdanList',
			component: xianludingdanList
		}, {
			path: 'xianludingdanDetail',
			component: xianludingdanDetail
		}, {
			path: 'xianludingdanAdd',
			component: xianludingdanAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
