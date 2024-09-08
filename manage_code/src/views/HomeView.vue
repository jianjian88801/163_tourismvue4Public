<template>
  <div class="home_view">
    <div class="projectTitle">欢迎使用 {{ projectName }}</div>
    <div class="count_list">
      <el-collapse-transition v-if="btnAuth('yonghu', '首页总数')">
        <el-card v-show="countTypeList.closeyonghuCountType" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">用户</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenyonghuCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenyonghuCountType ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closeyonghuCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenyonghuCountType"
            >
              <div class="count_title">用户总数</div>
              <div class="count_num">{{ yonghuCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('jingquxinxi', '首页总数')">
        <el-card
          v-show="countTypeList.closejingquxinxiCountType"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">景区信息</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenjingquxinxiCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenjingquxinxiCountType ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closejingquxinxiCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenjingquxinxiCountType"
            >
              <div class="count_title">景区信息总数</div>
              <div class="count_num">{{ jingquxinxiCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('xianludingdan', '首页总数')">
        <el-card
          v-show="countTypeList.closexianludingdanCountType"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">线路订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenxianludingdanCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenxianludingdanCountType
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closexianludingdanCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenxianludingdanCountType"
            >
              <div class="count_title">线路订单总数</div>
              <div class="count_num">{{ xianludingdanCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
    <div class="card_list">
      <el-collapse-transition v-if="btnAuth('yonghu', '首页统计')">
        <el-card v-show="cardTypeList.closeyonghuChartType1" class="card_view">
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">用户</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenyonghuChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenyonghuChartType1 ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeyonghuChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="card_item" v-show="cardTypeList.hiddenyonghuChartType1">
              <div
                id="yonghuxingbieEchart1"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('jingquxinxi', '首页统计')">
        <el-card
          v-show="cardTypeList.closejingquxinxiChartType1"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">景区信息</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenjingquxinxiChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenjingquxinxiChartType1 ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closejingquxinxiChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenjingquxinxiChartType1"
            >
              <div
                id="jingquxinxijingdianfenleiEchart1"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('xianludingdan', '首页统计')">
        <el-card
          v-show="cardTypeList.closexianludingdanChartType1"
          class="card_view"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">线路订单</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenxianludingdanChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenxianludingdanChartType1
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closexianludingdanChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenxianludingdanChartType1"
            >
              <div
                id="xianludingdanjiageEchart1"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
  </div>
</template>

<script setup>
import { inject, nextTick, ref, getCurrentInstance } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context.$project.projectName
const countTypeList = ref({})
const getCountList = () => {
  countTypeList.value.closeyonghuCountType = true
  countTypeList.value.hiddenyonghuCountType = true
  if (btnAuth('yonghu', '首页总数')) {
    getyonghuCount()
  }
  countTypeList.value.closejingquxinxiCountType = true
  countTypeList.value.hiddenjingquxinxiCountType = true
  if (btnAuth('jingquxinxi', '首页总数')) {
    getjingquxinxiCount()
  }
  countTypeList.value.closexianludingdanCountType = true
  countTypeList.value.hiddenxianludingdanCountType = true
  if (btnAuth('xianludingdan', '首页总数')) {
    getxianludingdanCount()
  }
}
const yonghuCount = ref(0)
const getyonghuCount = () => {
  context
    ?.$http({
      url: 'yonghu/count',
      method: 'get',
    })
    .then((res) => {
      yonghuCount.value = res.data.data
    })
}
const jingquxinxiCount = ref(0)
const getjingquxinxiCount = () => {
  context
    ?.$http({
      url: 'jingquxinxi/count',
      method: 'get',
    })
    .then((res) => {
      jingquxinxiCount.value = res.data.data
    })
}
const xianludingdanCount = ref(0)
const getxianludingdanCount = () => {
  context
    ?.$http({
      url: 'xianludingdan/count',
      method: 'get',
    })
    .then((res) => {
      xianludingdanCount.value = res.data.data
    })
}
const countTypeClick = (e) => {
  countTypeList.value[e] = !countTypeList.value[e]
}
const init = () => {
  getCountList()
  getCardList()
}
//权限验证
const btnAuth = (e, a) => {
  return context?.$toolUtil.isAuth(e, a)
}
let echarts = inject('echarts')
const cardTypeClick = (e) => {
  cardTypeList.value[e] = !cardTypeList.value[e]
  setTimeout(() => {
    getCardList()
  }, 1000)
}
const cardTypeList = ref({
  closeyonghuChartType1: true,
  hiddenyonghuChartType1: true,
  closejingquxinxiChartType1: true,
  hiddenjingquxinxiChartType1: true,
  closexianludingdanChartType1: true,
  hiddenxianludingdanChartType1: true,
})
const getCardList = () => {
  if (btnAuth('yonghu', '首页统计')) {
    getyonghuChart1()
  }
  if (btnAuth('jingquxinxi', '首页统计')) {
    getjingquxinxiChart1()
  }
  if (btnAuth('xianludingdan', '首页统计')) {
    getxianludingdanChart1()
  }
}
const getyonghuChart1 = () => {
  nextTick(() => {
    var xingbieEchart1 = echarts.init(
      document.getElementById('yonghuxingbieEchart1'),
      'macarons'
    )
    context
      ?.$http({
        url: 'yonghu/group/xingbie',
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].xingbie)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].xingbie,
          })
        }
        var option = {}
        option = {
          title: {
            text: '用户性别统计',
            left: 'center',
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c} ({d}%)',
          },
          series: [
            {
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: pArray,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)',
                },
              },
            },
          ],
        }
        xingbieEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        xingbieEchart1.setOption(option)
        //根据窗口的大小变动图表
        xingbieEchart1.resize()
      })
  })
}
const getjingquxinxiChart1 = () => {
  nextTick(() => {
    var jingdianfenleiEchart1 = echarts.init(
      document.getElementById('jingquxinxijingdianfenleiEchart1'),
      'macarons'
    )
    context
      ?.$http({
        url: 'jingquxinxi/group/jingdianfenlei',
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].jingdianfenlei)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].jingdianfenlei,
          })
        }
        var option = {}
        option = {
          title: {
            text: '景点类型统计',
            left: 'center',
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c} ({d}%)',
          },
          series: [
            {
              type: 'pie',
              radius: ['25%', '55%'],
              center: ['50%', '60%'],
              data: pArray,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)',
                },
              },
            },
          ],
        }
        jingdianfenleiEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        jingdianfenleiEchart1.setOption(option)
        //根据窗口的大小变动图表
        jingdianfenleiEchart1.resize()
      })
  })
}
const getxianludingdanChart1 = () => {
  nextTick(() => {
    var jiageEchart1 = echarts.init(
      document.getElementById('xianludingdanjiageEchart1'),
      'macarons'
    )
    context
      ?.$http({
        url: `xianludingdan/value/xianlumingcheng/jiage`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let pArray = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].xianlumingcheng)
          yAxis.push(parseFloat(res[i].total))
          pArray.push({
            value: parseFloat(res[i].total),
            name: res[i].xianlumingcheng,
          })
        }
        var option = {}
        option = {
          title: {
            text: '线路订单额统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            type: 'category',
            data: xAxis,
            axisLabel: {
              rotate: 40,
            },
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: yAxis,
              type: 'bar',
            },
          ],
        }
        jiageEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        jiageEchart1.setOption(option)
        //根据窗口的大小变动图表
        jiageEchart1.resize()
      })
  })
}
init()
</script>
<style lang="scss">
.projectTitle {
  padding: 20px 0;
  margin: 20px 0 20px;
  color: #fff;
  font-weight: 500;
  display: flex;
  width: 100%;
  font-size: 24px;
  justify-content: center;
  align-items: center;
  height: auto;
}

.showIcons {
  transition: transform 0.3s;
  margin-right: 10px;
}

.showIcons1 {
  transform: rotate(-180deg);
}

// 总数盒子
.count_list {
  padding: 0 0 20px;
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: flex-start;
  flex-wrap: wrap;
  // 总数card
  .card_view {
    border: 1px solid rgba(254, 182, 203, 0.5);
    border-radius: 0;
    box-shadow: 0px 0px 0px rgba(0, 0, 0, 0.12);
    margin: 0 1% 20px;
    background: rgba(255, 255, 255, 0.5);
    flex: 1;
    width: 31%;
    box-sizing: border-box;
    height: auto;
    // card头部
    .el-card__header {
      border: 0px solid rgba(254, 182, 203, 0.5);
      background: rgba(255, 255, 255, 0.3);
      width: 100%;
      border-width: 0 0 1px;
      // 头部盒子
      .index_card_head {
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
        height: 10px;
        // 标题
        .card_head_title {
          color: #555;
          font-size: 14px;
        }
        // 按钮盒子
        .card_head_right {
          display: flex;
          align-items: center;
          // 按钮
          .el-icon {
            cursor: pointer;
            color: #666;
            font-size: 20px;
          }
        }
      }
    }
    // body
    .el-card__body {
      padding: 0;
      // body盒子
      .count_item {
        padding: 30px;
        text-align: center;
        // 总数标题
        .count_title {
          color: #333;
          font-size: 16px;
          line-height: 2;
        }
        // 总数数字
        .count_num {
          color: #f00;
          font-size: 30px;
          line-height: 2;
        }
      }
    }
  }
}
// 首页盒子
.home_view {
  padding: 0px 20px;
  margin: 0;
  background: url(http://localhost:8080/tourism/file/3003369554f34c828841c05292b6461a.jpg)
    no-repeat center top / 100% 100% !important;
  min-height: 100vh;
  height: auto;
}
// 统计图盒子
.card_list {
  padding: 0 0 20px;
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: flex-start;
  flex-wrap: wrap;
  // 统计图card
  .card_view {
    border: 1px solid rgba(254, 182, 203, 0.5);
    border-radius: 0;
    box-shadow: 0px 0px 0px rgba(0, 0, 0, 0.12);
    margin: 0 1% 30px;
    background: rgba(255, 255, 255, 0.5);
    width: 48%;
    box-sizing: border-box;
    height: auto;
    // 头部
    .el-card__header {
      border: 0px solid rgba(254, 182, 203, 0.5);
      background: rgba(255, 255, 255, 0.3);
      width: 100%;
      border-width: 0 0 1px;
      // 头部盒子
      .index_card_head {
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
        height: 10px;
        // 标题
        .card_head_title {
          color: #555;
          font-size: 14px;
        }
        // 按钮盒子
        .card_head_right {
          display: flex;
          align-items: center;
          // 按钮
          .el-icon {
            cursor: pointer;
            color: #666;
            font-size: 20px;
          }
        }
      }
    }
    // body
    .el-card__body {
      padding: 0;
      // body盒子
      .card_item {
        padding: 30px;
        text-align: center;
      }
    }
  }
}
</style>
