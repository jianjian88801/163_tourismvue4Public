<template>
  <div
    class="app-contain"
    :style="{
      minHeight: '100vh',
      padding: '0',
      margin: '20px auto 60px',
      borderRadius: '20px',
      background: '#fff',
      width: '1200px',
      position: 'relative',
      height: '100%',
    }"
  >
    <div class="bread_view">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }"
          >首页</el-breadcrumb-item
        >
        <el-breadcrumb-item
          class="second_breadcrumb"
          v-for="(item, index) in breadList"
          :key="index"
          >{{ item.name }}</el-breadcrumb-item
        >
      </el-breadcrumb>
    </div>
    <el-form
      ref="formRef"
      :model="form"
      class="add_form"
      label-width="120px"
      :rules="rules"
    >
      <el-row>
        <el-col :span="24">
          <el-form-item label="账号" prop="zhanghao">
            <el-input
              class="list_inp"
              v-model="form.zhanghao"
              placeholder="账号"
              type="text"
              :readonly="!isAdd || disabledForm.zhanghao ? true : false"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="姓名" prop="xingming">
            <el-input
              class="list_inp"
              v-model="form.xingming"
              placeholder="姓名"
              type="text"
              :readonly="!isAdd || disabledForm.xingming ? true : false"
            />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="密码" prop="mima">
            <el-input
              class="list_inp"
              v-model="form.mima"
              placeholder="密码"
              type="password"
              :readonly="!isAdd || disabledForm.mima ? true : false"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="头像" prop="touxiang">
            <uploads
              :disabled="!isAdd || disabledForm.touxiang ? true : false"
              action="file/upload"
              tip="请上传头像"
              :limit="3"
              style="width: 100%; text-align: left"
              :fileUrls="form.touxiang ? form.touxiang : ''"
              @change="touxiangUploadSuccess"
            >
            </uploads>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="性别" prop="xingbie">
            <el-select
              class="list_sel"
              :disabled="!isAdd || disabledForm.xingbie ? true : false"
              v-model="form.xingbie"
              placeholder="请选择性别"
              style="width: 100%"
            >
              <el-option
                v-for="(item, index) in xingbieLists"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="手机" prop="shouji">
            <el-input
              class="list_inp"
              v-model="form.shouji"
              placeholder="手机"
              type="text"
              :readonly="!isAdd || disabledForm.shouji ? true : false"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <div class="formModel_btn_box">
        <el-button class="formModel_cancel" @click="backClick">取消</el-button>
        <el-button class="formModel_confirm" @click="save" type="success">
          保存
        </el-button>
      </div>
    </el-form>
  </div>
</template>
<script setup>
import {
  ref,
  getCurrentInstance,
  watch,
  onUnmounted,
  onMounted,
  nextTick,
  computed,
} from 'vue'
import { useRoute, useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const route = useRoute()
const router = useRouter()
//基础信息
const tableName = 'yonghu'
const formName = '用户'
//基础信息
const breadList = ref([
  {
    name: formName,
  },
])
//获取唯一标识
const getUUID = () => {
  return new Date().getTime()
}
//form表单
const form = ref({
  zhanghao: '',
  xingming: '',
  mima: '',
  touxiang: '',
  xingbie: '',
  shouji: '',
})
const formRef = ref(null)
const id = ref(0)
const type = ref('')
const disabledForm = ref({
  zhanghao: false,
  xingming: false,
  mima: false,
  touxiang: false,
  xingbie: false,
  shouji: false,
})
const isAdd = ref(false)
//表单验证
//匹配整数
const validateIntNumber = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isIntNumer(value)) {
    callback(new Error('请输入整数'))
  } else {
    callback()
  }
}
//匹配数字
const validateNumber = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isNumber(value)) {
    callback(new Error('请输入数字'))
  } else {
    callback()
  }
}
//匹配手机号码
const validateMobile = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isMobile(value)) {
    callback(new Error('请输入正确的手机号码'))
  } else {
    callback()
  }
}
//匹配电话号码
const validatePhone = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isPhone(value)) {
    callback(new Error('请输入正确的电话号码'))
  } else {
    callback()
  }
}
//匹配邮箱
const validateEmail = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isEmail(value)) {
    callback(new Error('请输入正确的邮箱地址'))
  } else {
    callback()
  }
}
//匹配身份证
const validateIdCard = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.checkIdCard(value)) {
    callback(new Error('请输入正确的身份证号码'))
  } else {
    callback()
  }
}
//匹配网站地址
const validateUrl = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isURL(value)) {
    callback(new Error('请输入正确的URL地址'))
  } else {
    callback()
  }
}
const rules = ref({
  zhanghao: [{ required: true, message: '请输入', trigger: 'blur' }],
  xingming: [],
  mima: [{ required: true, message: '请输入', trigger: 'blur' }],
  touxiang: [],
  xingbie: [],
  shouji: [{ validator: validateMobile, trigger: 'blur' }],
})
//头像上传回调
const touxiangUploadSuccess = (e) => {
  form.value.touxiang = e
}
//性别列表
const xingbieLists = ref([])
//methods

//methods
//获取info
const getInfo = () => {
  context
    ?.$http({
      url: `${tableName}/info/${id.value}`,
      method: 'get',
    })
    .then((res) => {
      let reg = new RegExp('../../../file', 'g')
      form.value = res.data.data
    })
}
const crossRow = ref('')
const crossTable = ref('')
const crossTips = ref('')
const crossColumnName = ref('')
const crossColumnValue = ref('')
//初始化
const init = (
  formId = null,
  formType = 'add',
  formNames = '',
  row = null,
  table = null,
  statusColumnName = null,
  tips = null,
  statusColumnValue = null
) => {
  if (formId) {
    id.value = formId
    type.value = formType
  }
  if (formType == 'add') {
    isAdd.value = true
  } else if (formType == 'info') {
    isAdd.value = false
    getInfo()
  } else if (formType == 'edit') {
    isAdd.value = true
    getInfo()
  } else if (formType == 'cross') {
    isAdd.value = true
    // getInfo()
    for (let x in row) {
      if (x == 'zhanghao') {
        form.value.zhanghao = row[x]
        disabledForm.value.zhanghao = true
        continue
      }
      if (x == 'xingming') {
        form.value.xingming = row[x]
        disabledForm.value.xingming = true
        continue
      }
      if (x == 'mima') {
        form.value.mima = row[x]
        disabledForm.value.mima = true
        continue
      }
      if (x == 'touxiang') {
        form.value.touxiang = row[x]
        disabledForm.value.touxiang = true
        continue
      }
      if (x == 'xingbie') {
        form.value.xingbie = row[x]
        disabledForm.value.xingbie = true
        continue
      }
      if (x == 'shouji') {
        form.value.shouji = row[x]
        disabledForm.value.shouji = true
        continue
      }
    }
    if (row) {
      crossRow.value = row
    }
    if (table) {
      crossTable.value = table
    }
    if (tips) {
      crossTips.value = tips
    }
    if (statusColumnName) {
      crossColumnName.value = statusColumnName
    }
    if (statusColumnValue) {
      crossColumnValue.value = statusColumnValue
    }
  }
  context
    ?.$http({
      url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
      method: 'get',
    })
    .then((res) => {
      var json = res.data.data
    })
  xingbieLists.value = '男,女'.split(',')
}
//初始化
//取消
const backClick = () => {
  history.back()
}
//提交
const save = () => {
  if (form.value.touxiang != null) {
    form.value.touxiang = form.value.touxiang.replace(
      new RegExp(context?.$config.url, 'g'),
      ''
    )
  }
  var table = crossTable.value
  var objcross = JSON.parse(JSON.stringify(crossRow.value))
  let crossUserId = ''
  let crossRefId = ''
  let crossOptNum = ''
  if (type.value == 'cross') {
    if (crossColumnName.value != '') {
      if (!crossColumnName.value.startsWith('[')) {
        for (let o in objcross) {
          if (o == crossColumnName.value) {
            objcross[o] = crossColumnValue.value
          }
        }
        //修改跨表数据
        changeCrossData(objcross)
      } else {
        crossUserId = context?.$toolUtil.storageGet('userid')
        crossRefId = objcross['id']
        crossOptNum = crossColumnName.value.replace(/\[/, '').replace(/\]/, '')
      }
    }
  }
  formRef.value.validate((valid) => {
    if (valid) {
      if (crossUserId && crossRefId) {
        form.value.crossuserid = crossUserId
        form.value.crossrefid = crossRefId
        let params = {
          page: 1,
          limit: 1000,
          crossuserid: form.value.crossuserid,
          crossrefid: form.value.crossrefid,
        }
        context
          ?.$http({
            url: `${tableName}/page`,
            method: 'get',
            params: params,
          })
          .then((res) => {
            if (res.data.data.total >= crossOptNum) {
              context?.$toolUtil.message(`${crossTips.value}`, 'error')
              return false
            } else {
              context
                ?.$http({
                  url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
                  method: 'post',
                  data: form.value,
                })
                .then((res) => {
                  context?.$toolUtil.message(`操作成功`, 'success', () => {
                    history.back()
                  })
                })
            }
          })
      } else {
        context
          ?.$http({
            url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
            method: 'post',
            data: form.value,
          })
          .then((res) => {
            context?.$toolUtil.message(`操作成功`, 'success', () => {
              history.back()
            })
          })
      }
    }
  })
}
//修改跨表数据
const changeCrossData = (row) => {
  context
    ?.$http({
      url: `${crossTable.value}/update`,
      method: 'post',
      data: row,
    })
    .then((res) => {})
}
onMounted(() => {
  type.value = route.query.type ? route.query.type : 'add'
  let row = null
  let table = null
  let statusColumnName = null
  let tips = null
  let statusColumnValue = null
  if (type.value == 'cross') {
    row = context?.$toolUtil.storageGet('crossObj')
      ? JSON.parse(context?.$toolUtil.storageGet('crossObj'))
      : {}
    table = context?.$toolUtil.storageGet('crossTable')
    statusColumnName = context?.$toolUtil.storageGet('crossStatusColumnName')
    tips = context?.$toolUtil.storageGet('crossTips')
    statusColumnValue = context?.$toolUtil.storageGet('crossStatusColumnValue')
  }
  init(
    route.query.id ? route.query.id : null,
    type.value,
    '',
    row,
    table,
    statusColumnName,
    tips,
    statusColumnValue
  )
})
</script>
<style lang="scss" scoped>
// 面包屑盒子
.bread_view {
  border-radius: 0px;
  padding: 10px 20px;
  margin: 0px auto;
  background: url(http://localhost:8080/tourism/file/cf02fa1d4a2442b581a3d62afcc09015.png)
      no-repeat left center,
    #fbfbfb;
  width: calc(100% - 40px);
  border-color: #eee;
  border-width: 1px;
  position: relative;
  border-style: solid;
  :deep(.breadcrumb) {
    font-size: 14px;
    line-height: 1;
    .el-breadcrumb__separator {
      margin: 0 9px;
      color: #fff;
      font-weight: 500;
    }
    .first_breadcrumb {
      .el-breadcrumb__inner {
        color: #fff;
        display: inline-block;
      }
    }
    .second_breadcrumb {
      .el-breadcrumb__inner {
        color: #fff;
        display: inline-block;
      }
    }
  }
}
// 表单
.add_form {
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
      // 下拉框
      .list_sel {
        border-radius: 0px;
        padding: 0 10px;
        background: none;
        width: 100%;
        border-color: #ddd;
        border-width: 0 0 1px;
        line-height: 36px;
        box-sizing: border-box;
        border-style: solid;
        min-width: 300px;
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
      //图片上传样式
      .el-upload-list {
        //提示语
        .el-upload__tip {
          margin: 7px 0 0;
          color: #999;
          display: flex;
          font-size: 14px;
          justify-content: flex-start;
          align-items: center;
        }
        //外部盒子
        .el-upload--picture-card {
          border: 1px solid #ddd;
          cursor: pointer;
          border-radius: 2px;
          background: none;
          width: 120px;
          line-height: 90px;
          text-align: center;
          height: 80px;
          //图标
          .el-icon {
            color: #999;
            font-size: 24px;
          }
        }
        .el-upload-list__item {
          border: 1px solid #ddd;
          cursor: pointer;
          border-radius: 2px;
          background: none;
          width: 120px;
          line-height: 90px;
          text-align: center;
          height: 80px;
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
    background: rgba(194, 31, 48, 1);
    width: auto;
    font-size: 14px;
    line-height: 40px;
    height: 40px;
  }
  .formModel_confirm:hover {
  }
}
</style>
