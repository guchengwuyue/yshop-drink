<template>
 <el-drawer v-model="drawer" :title="dialogTitle" size="50%">
    <div>
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="用户信息" name="first">
           <el-descriptions title="基本信息" :column="2">
            <el-descriptions-item label="用户头像"><el-image style="width: 50px; height: 50px" :src="DetailData.avatar" /></el-descriptions-item>
            <el-descriptions-item label="用户昵称">{{ DetailData.nickname }}</el-descriptions-item>
            <el-descriptions-item label="余额">{{ DetailData.nowMoney }}</el-descriptions-item>
            <el-descriptions-item label="生日">{{ DetailData.birthday }}</el-descriptions-item>
            <el-descriptions-item label="手机号">{{ DetailData.mobile }}</el-descriptions-item>
            <el-descriptions-item label="身份证号码">{{ DetailData.cardId }}</el-descriptions-item>
          </el-descriptions>
          <el-descriptions title="用户概况" :column="2">
            <el-descriptions-item label="积分">{{ DetailData.integral }}</el-descriptions-item>
            <el-descriptions-item label="佣金金额">{{ DetailData.brokeragePrice }}</el-descriptions-item>
            <el-descriptions-item label="连续签到天数">{{ DetailData.signNum }}</el-descriptions-item>
            <el-descriptions-item label="登录ip">{{ DetailData.loginIp }}</el-descriptions-item>
            <el-descriptions-item label="等级">{{ DetailData.level }}</el-descriptions-item>
            <el-descriptions-item label="推广id">{{ DetailData.spreadUid }}</el-descriptions-item>
            <el-descriptions-item label="购买次数">{{ DetailData.payCount }}</el-descriptions-item>
            <el-descriptions-item label="下级人数">{{ DetailData.spreadCount }}</el-descriptions-item>
            <el-descriptions-item label="登陆类型">{{ DetailData.loginType }}</el-descriptions-item>
            <el-descriptions-item label="注册时间">{{ formatDate(DetailData.createTime) }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
        <el-tab-pane label="消费记录" name="second">
             <el-table  :data="list">
              <el-table-column label="支出/获得" align="center" prop="pm">
                  <template #default="scope">
                    <span v-if="scope.row.pm == 0">支出</span>
                    <span v-else-if="scope.row.category == 1">获得</span>
                    <span v-else>未知</span>
                  </template>
              </el-table-column>
              <el-table-column label="账单标题" align="center" prop="title" />
              <el-table-column label="明细种类" align="center" prop="category">
                 <template #default="scope">
                    <span v-if="scope.row.category == 'now_money'">余额</span>
                    <span v-else-if="scope.row.category == 'integral'">积分</span>
                    <span v-else>未知</span>
                  </template>
              </el-table-column>
              <el-table-column label="明细类型" align="center" prop="type">
                <template #default="scope">
                    <span v-if="scope.row.type == 'recharge'">充值</span>
                    <span v-else-if="scope.row.type == 'brokerage'">返佣</span>
                    <span v-else-if="scope.row.type == 'pay_product'">消费</span>
                    <span v-else-if="scope.row.type == 'extract'">提现</span>
                    <span v-else-if="scope.row.type == 'pay_product_refund'">退款</span>
                    <span v-else-if="scope.row.type == 'system_add'">系统添加</span>
                    <span v-else-if="scope.row.type == 'system_sub'">系统减少</span>
                    <span v-else-if="scope.row.type == 'deduction'">减去</span>
                    <span v-else-if="scope.row.type == 'gain'">奖励</span>
                    <span v-else-if="scope.row.type == 'sign'">签到</span>
                    <span v-else>未知</span>
                  </template>
              </el-table-column>
              <el-table-column label="明细数字(元)" align="center" prop="number">
                    <template #default="scope">
                      <span v-if="scope.row.pm == 1">+</span>
                      <span v-else>-</span>
                      <span>{{ scope.row.number }}</span>
                    </template>
              </el-table-column>
              <el-table-column label="剩余(元)" align="center" prop="balance" />
              <el-table-column label="备注" align="center" prop="mark" width="200" />
              <el-table-column
                label="添加时间"
                align="center"
                prop="createTime"
                :formatter="dateFormatter"
                width="100"
              />
            </el-table>
            <!-- 分页 -->
            <Pagination
              :total="total"
              v-model:page="queryParams.pageNo"
              v-model:limit="queryParams.pageSize"
              @pagination="getList"
            />
        </el-tab-pane>
      </el-tabs>
     
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import * as UserApi from '@/api/member/user'
import { formatDate } from '@/utils/formatTime'
import type { TabsPaneContext } from 'element-plus'
import * as UserBillApi from '@/api/member/userBill'
import { dateFormatter } from '@/utils/formatTime'

const { t } = useI18n() // 国际化
// const message = useMessage() // 消息弹窗
const dialogTitle = ref('') // 弹窗的标题
const drawer = ref(false)
const DetailData = ref({})
const activeName = ref('first')
const total = ref(0) // 列表的总页数
const list = ref([]) // 列表的数据
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  uid: null,
  linkId: null,
  pm: null,
  title: null,
  category: null,
  type: null,
  number: null,
  balance: null,
  mark: null,
  createTime: [],
  status: null
})
/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  drawer.value = true
  dialogTitle.value = t('action.' + type)
  DetailData.value = await UserApi.getUser(id)
  queryParams.uid = id
  getList()
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab.paneName, event)
  // queryParams.pageNo = 1
  // queryParams.type = tab.paneName
  //getList()
}
/** 查询列表 */
const getList = async () => {
  try {
    const data = await UserBillApi.getUserBillPage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}
</script>
<style scoped>
</style>