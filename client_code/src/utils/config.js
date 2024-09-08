const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '景区信息管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'景区信息',
							url:'/index/jingquxinxiList'
						},
					]
				},
				{
					name: '线路订单管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'线路订单',
							url:'/index/xianludingdanList'
						},
					]
				},
				{
					name: '新闻资讯管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'旅游新闻',
							url:'/index/newsList'
						},
					]
				},
				{
					name: '美食推荐管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'美食推荐',
							url:'/index/meishituijianList'
						},
					]
				},
				{
					name: '论坛交流',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'旅游日记',
							url:'/index/forumList'
						},
					]
				},
				{
					name: '留言板管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'留言板',
							url:'/index/messagesList'
						},
					]
				},
				{
					name: '旅游路线管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'旅游线路',
							url:'/index/lvyouxianluList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "贵州红色文化旅游景区管理系统"
        } 
    }
}
export default config
