<template>
  <div>
    <div class="home_box">
      <!-- 景区信息推荐 -->
      <div class="recomList_view">
        <div class="recomList_title">景区信息推荐</div>
        <div class="recommend_list_two">
          <mySwiper
            :data="jingquxinxiRecomList"
            :type="3"
            :loop="false"
            :navigation="true"
            :pagination="false"
            :paginationType="2"
            :scrollbar="false"
            :slidesPerView="5"
            :spaceBetween="20"
            :autoHeight="false"
            :centeredSlides="false"
            :freeMode="true"
            :effectType="0"
            :direction="horizontal"
            :autoplay="false"
            :slidesPerColumn="1"
          >
            <template #default="scope">
              <div
                class="recommend_item animation_box"
                @click="detailClick('jingquxinxi', scope.row.id)"
              >
                <div class="recommend_img_box">
                  <img
                    class="recommend_img"
                    v-if="isHttp(scope.row.jingdiantupian)"
                    :src="scope.row.jingdiantupian.split(',')[0]"
                    alt=""
                  />
                  <img
                    class="recommend_img"
                    v-else
                    :src="
                      scope.row.jingdiantupian
                        ? $config.url + scope.row.jingdiantupian.split(',')[0]
                        : ''
                    "
                    alt=""
                  />
                </div>
                <div class="recommend_content">
                  <div class="recommend_title">
                    {{ scope.row.jingdianmingcheng }}
                  </div>
                  <div class="recommend_title">
                    {{ scope.row.jingdianfenlei }}
                  </div>
                  <div class="recommend_title">
                    {{ scope.row.menpiaojiage }}
                  </div>
                </div>
              </div>
            </template>
          </mySwiper>
        </div>
        <div class="recommend_more_view" @click="moreClick('jingquxinxi')">
          <span class="recommend_more_text">更多</span>
          <el-icon><DArrowRight /></el-icon>
        </div>
      </div>
      <!-- 景区信息首页展示 -->
      <div class="homeList_view">
        <div class="homeList_title">景区信息展示</div>
        <div class="home_list_two">
          <mySwiper
            :data="jingquxinxiHomeList"
            :type="3"
            :loop="true"
            :navigation="false"
            :pagination="true"
            :paginationType="2"
            :scrollbar="false"
            :slidesPerView="1"
            :spaceBetween="0"
            :autoHeight="false"
            :centeredSlides="false"
            :freeMode="true"
            :effectType="0"
            :direction="horizontal"
            :autoplay="true"
            :slidesPerColumn="1"
          >
            <template #default="scope">
              <div
                class="home_item animation_box"
                @click="detailClick('jingquxinxi', scope.row.id)"
              >
                <div class="home_img_box">
                  <img
                    class="home_img"
                    v-if="isHttp(scope.row.jingdiantupian)"
                    :src="scope.row.jingdiantupian.split(',')[0]"
                    alt=""
                  />
                  <img
                    class="home_img"
                    v-else
                    :src="
                      scope.row.jingdiantupian
                        ? $config.url + scope.row.jingdiantupian.split(',')[0]
                        : ''
                    "
                    alt=""
                  />
                </div>
                <div class="home_content">
                  <div class="home_title">
                    {{ scope.row.jingdianmingcheng }}
                  </div>
                  <div class="home_title">
                    {{ scope.row.jingdianfenlei }}
                  </div>
                  <div class="home_title">
                    {{ scope.row.menpiaojiage }}
                  </div>
                </div>
              </div>
            </template>
          </mySwiper>
        </div>
        <div class="homeList_more_view" @click="moreClick('jingquxinxi')">
          <span class="homeList_more_text">更多</span>
          <el-icon><DArrowRight /></el-icon>
        </div>
      </div>
      <!-- 旅游新闻 -->
      <div class="newsList_view">
        <div class="newsList_title">旅游新闻</div>
        <div class="news_list_one">
          <div
            class="news_item animation_box"
            v-for="(item, index) in newsList"
            :key="index"
            @click="newsDetailClick(item.id)"
          >
            <div class="news_img_box">
              <img
                class="news_img"
                v-if="isHttp(item.picture)"
                :src="item.picture.split(',')[0]"
                alt=""
              />
              <img
                class="news_img"
                v-else
                :src="
                  item.picture ? $config.url + item.picture.split(',')[0] : ''
                "
                alt=""
              />
            </div>
            <div class="news_content">
              <div class="news_title">{{ item.title }}</div>
              <div class="news_text">{{ item.introduction }}</div>
              <div class="news_time">{{ item.addtime.split(' ')[0] }}</div>
            </div>
          </div>
        </div>
        <div class="news_more_view" @click="moreClick('news')">
          <span class="news_more_text">更多</span>
          <el-icon><DArrowRight /></el-icon>
        </div>
      </div>
    </div>
    <formModel ref="newsFormModelRef"></formModel>
  </div>
</template>

<script setup>
import { ref, getCurrentInstance } from 'vue'
import { useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const router = useRouter()
//景区信息推荐
const jingquxinxiRecomList = ref([])
const getjingquxinxiRecomList = () => {
  let autoSortUrl = 'jingquxinxi/autoSort'
  if (context?.$toolUtil.storageGet('frontToken')) {
    autoSortUrl = 'jingquxinxi/autoSort2'
  }
  context
    ?.$http({
      url: autoSortUrl,
      method: 'get',
      params: {
        page: 1,
        limit: 16,
      },
    })
    .then((res) => {
      jingquxinxiRecomList.value = res.data.data.list
    })
}
//景区信息首页展示
const jingquxinxiHomeList = ref([])
const getjingquxinxiHomeList = () => {
  context
    ?.$http({
      url: 'jingquxinxi/list',
      method: 'get',
      params: {
        page: 1,
        limit: 8,
      },
    })
    .then((res) => {
      jingquxinxiHomeList.value = res.data.data.list
    })
}
//旅游新闻弹窗
import formModel from './news/formModel'
const newsFormModelRef = ref(null)
//旅游新闻
const newsList = ref([])
const getNewsList = () => {
  context
    ?.$http({
      url: 'news/list',
      method: 'get',
      params: {
        page: 1,
        limit: 8,
      },
    })
    .then((res) => {
      newsList.value = res.data.data.list
    })
}
const newsDetailClick = (id = null) => {
  if (id) {
    newsFormModelRef.value.init(id)
  }
}
//判断图片链接是否带http
const isHttp = (str) => {
  return str && str.substr(0, 4) == 'http'
}
//跳转详情
const detailClick = (table, id) => {
  router.push(`/index/${table}Detail?id=${id}`)
}
const moreClick = (table) => {
  router.push(`/index/${table}List`)
}
const init = () => {
  //景区信息推荐
  getjingquxinxiRecomList()
  //景区信息首页展示
  getjingquxinxiHomeList()
  //旅游新闻
  getNewsList()
}
init()
</script>

<style lang="scss">
.home_box {
  border-radius: 4px;
  padding: 0px;
  margin: 0 auto;
  background: none;
  display: flex;
  width: 1200px;
  justify-content: space-between;
  align-items: flex-start;
  flex-wrap: wrap;
}

// 推荐
.recomList_view {
  padding: 0 0 20px;
  margin: 20px auto 0;
  background: #fff;
  width: 100%;
  position: relative;
  order: 0;
  .recomList_title {
    padding: 0 0 0 16px;
    margin: 0;
    color: #fff;
    background: url(http://localhost:8080/tourism/file/cf02fa1d4a2442b581a3d62afcc09015.png)
        no-repeat left center,
      #fbfbfb;
    font-weight: 500;
    width: 100%;
    font-size: 16px;
    border-color: #eee;
    border-width: 1px;
    line-height: 38px;
    border-style: solid;
    text-align: left;
  }
  // list
  .recommend_list_two {
    padding: 0 20px;
    margin: 20px 0 0;
    display: flex;
    width: 100%;
    flex-wrap: wrap;
    .recommend_item {
      cursor: pointer;
      margin: 0;
      background: none;
      width: 100%;
      .recommend_img_box {
        margin: 0 0 6px;
        width: 100%;
        height: 200px;
        .recommend_img {
          object-fit: cover;
          width: 100%;
          height: 100%;
        }
      }
      .recommend_content {
        .recommend_title {
          overflow: hidden;
          color: #333;
          white-space: nowrap;
          background: none;
          font-weight: 500;
          width: 100%;
          font-size: 14px;
          line-height: 24px;
          text-overflow: ellipsis;
          text-align: left;
        }
        .recommend_price {
          color: #c00;
          font-weight: 500;
          width: 100%;
          font-size: 14px;
          line-height: 1;
          text-align: right;
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .recommend_more_view {
    cursor: pointer;
    border: 0px solid #ddd;
    padding: 0;
    margin: 0;
    color: #fff;
    line-height: 38px;
    right: 10px;
    border-radius: 0px;
    top: 0;
    background: none;
    width: 89px;
    position: absolute;
    text-align: right;
    height: 38px;
    .recommend_more_text {
      color: #fb621c;
    }
    .el-icon {
      color: #fb621c;
    }
  }
}
// 推荐
// 新闻资讯
.newsList_view {
  padding: 0px;
  margin: 20px 0 0;
  background: #fff;
  flex: 1;
  width: calc(100% - 395px);
  position: relative;
  order: 1;
  height: auto;

  .newsList_title {
    padding: 0 0 0 16px;
    margin: 0 auto 20px;
    color: #fff;
    background: url(http://localhost:8080/tourism/file/cf02fa1d4a2442b581a3d62afcc09015.png)
        no-repeat left center,
      #fbfbfb;
    font-weight: 500;
    width: 100%;
    font-size: 16px;
    border-color: #eee;
    border-width: 1px;
    line-height: 38px;
    border-style: solid;
    text-align: left;
  }
  // list
  .news_list_one {
    padding: 0px 20px;
    margin: 0px 0 0;
    background: #fff;
    display: flex;
    width: 100%;
    min-height: 280px;
    justify-content: space-between;
    flex-wrap: wrap;
    .news_item {
      cursor: pointer;
      margin: 0 0 16px 0;
      background: #fff;
      display: flex;
      width: calc(100% - 0px);
      align-items: center;
      .news_img_box {
        display: none;
        width: 100px;
        height: 100px;
        .news_img {
          border: 6px solid #ebe9dd;
          object-fit: cover;
          width: 100%;
          height: 100%;
        }
      }
      .news_content {
        margin: 0 0 0 0px;
        display: flex;
        width: calc(100% - 80px);
        flex-wrap: wrap;
        .news_title {
          padding: 0 0 0 15px;
          margin: 0 0 0px;
          overflow: hidden;
          white-space: nowrap;
          background: url(http://clfile.zggen.cn/20240123/e664a8ade7064178b06105b4d98da3ca.png)
            no-repeat left center;
          font-weight: 500;
          width: 100%;
          font-size: 14px;
          text-overflow: ellipsis;
          text-align: left;
          order: 2;
        }
        .news_text {
          padding: 0 10px 0 0;
          overflow: hidden;
          display: none;
          font-size: 14px;
          line-height: 24px;
          height: 48px;
          order: 3;
        }
        .news_time {
          color: #333;
          font-weight: 600;
          display: none;
          width: 100%;
          text-align: left;
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .news_more_view {
    cursor: pointer;
    border: 0px solid #ddd;
    padding: 0px 0;
    margin: 0;
    color: #999;
    line-height: 38px;
    right: 10px;
    border-radius: 0px;
    top: 0px;
    background: none;
    width: 89px;
    position: absolute;
    text-align: right;
    height: 38px;
    .news_more_text {
      color: #fb621c;
    }
    .el-icon {
      color: #fb621c;
    }
  }
}
// 新闻资讯
// 首页展示
.homeList_view {
  padding: 0px;
  margin: 20px 0 0 20px;
  background: #fff;
  width: 375px;
  position: relative;
  order: 2;

  .homeList_title {
    padding: 0 0 0 16px;
    margin: 0;
    color: #fff;
    background: url(http://localhost:8080/tourism/file/cf02fa1d4a2442b581a3d62afcc09015.png)
        no-repeat left center,
      #fbfbfb;
    font-weight: 500;
    width: 100%;
    font-size: 16px;
    border-color: #eee;
    border-width: 1px;
    line-height: 38px;
    border-style: solid;
    text-align: left;
  }
  // list
  .home_list_two {
    padding: 0 20px;
    margin: 20px auto;
    display: flex;
    width: 100%;
    flex-wrap: wrap;
    .home_item {
      margin: 0;
      background: none;
      width: 100%;
      .home_img_box {
        cursor: pointer;
        margin: 0;
        width: 100%;
        position: relative;
        height: 260px;
        .home_img {
          object-fit: cover;
          width: 100%;
          height: 100%;
        }
      }
      .home_content {
        .home_title {
          border: 1px solid #eee;
          margin: 0;
          color: #333;
          white-space: nowrap;
          font-weight: 500;
          font-size: 14px;
          line-height: 30px;
          text-overflow: ellipsis;
          overflow: hidden;
          top: 0;
          background: #fbfbfb;
          width: calc(100% - 0px);
          position: absolute;
          text-align: center;
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .homeList_more_view {
    cursor: pointer;
    border: 0px solid #ddd;
    padding: 0;
    margin: 0;
    z-index: 9;
    color: #999;
    line-height: 38px;
    right: 10px;
    border-radius: 0px;
    top: 0px;
    background: none;
    width: 89px;
    position: absolute;
    text-align: right;
    height: 38px;
    .homeList_more_text {
      color: #fb621c;
    }
    .el-icon {
      color: #fb621c;
    }
  }
}
// 首页展示
</style>
