<template>
  <view class="container">
    <view class="record-list">
      <view v-for="(group, date) in groupedRecords" :key="date" class="date-group">
        <view class="date-card">
          <view class="date-header">{{ date }}</view>
          <view v-for="(item, index) in group" :key="index" class="record-item" :class="{'slide-in': true}">
            <view class="record-content">
              <view class="record-left">
                <view class="category-icon" :class="item.type">
                  <uni-icons :type="getCategoryIcon(item.category)" size="24" color="#fff"></uni-icons>
                </view>
                <view class="record-info">
                  <text class="record-category">{{ item.category }}</text>
                </view>
              </view>
              <view class="record-right">
                <text :class="['record-amount', item.type]">{{ item.type === 'expense' ? '-' : '+' }}{{ item.amount }}</text>
              </view>
            </view>
          </view>
        </view>
      </view>
    </view>
    <view class="fab-btn" @click="onAddRecord">
      <uni-icons type="plus" size="24" color="#fff"></uni-icons>
    </view>
  </view>
</template>

<script>
import { listDetail } from "@/api/ledger/detail"
export default {
  data() {
    return {
      recordList: []
    }
  },
  computed: {
    groupedRecords() {
      const groups = {};
      this.recordList.forEach(record => {
        if (!groups[record.date]) {
          groups[record.date] = [];
        }
        groups[record.date].push(record);
      });
      console.log("groups", groups)
      return groups;
    }
  },
  methods: {
    getCategoryIcon(category) {
      const iconMap = {
        '餐饮': 'shop',
        '工资': 'wallet',
        '购物': 'cart',
        '交通': 'car',
        '娱乐': 'star',
        '居家': 'home',
        '医疗': 'heart',
        '教育': 'staff',
        '通讯': 'phone',
        '服饰': 'gift',
        '其他收入': 'plus',
        '其他支出': 'minus'
      }
      return iconMap[category] || 'info'
    },
    onAddRecord() {
      uni.navigateTo({
        url: '/pages/record/add'
      })
    },
    getList() {
      listDetail().then(res => {
        if (res.code === 200) {
          this.recordList = res.rows.map(item => ({
            category: item.categoryName,
            amount: item.amount.toFixed(2),
            date: item.detailDate,
            type: item.detailType === '支出' ? 'expense' : 'income'
          }))
        }
      })
    }
  },
  onLoad() {
    this.getList()
  },
}
</script>

<style lang="scss" scoped>
.container {
  padding: 0;
  min-height: 100vh;
  background-color: #FFFFFF;
}

.record-list {
  padding: 0 12rpx;
}

.date-group {
  margin-bottom: 32rpx;
}

.date-header {
  padding: 20rpx 24rpx;
  font-size: 30rpx;
  color: #333;
  font-weight: 600;
}

.date-card {
  background-color: #fff;
  border-radius: 20rpx;
  padding: 20rpx;
  margin: 0 16rpx;
  box-shadow: 0 4rpx 16rpx rgba(0, 0, 0, 0.08);
  background: linear-gradient(135deg, #FFF8F6, #FFF);
  border: 1rpx solid rgba(255, 140, 105, 0.1);
  margin-top: 50rpx;
}

.record-item {
  margin-bottom: 8rpx;
  
  &:last-child {
    margin-bottom: 0;
  }
  
  &.slide-in {
    animation: slideIn 0.3s ease-out;
  }
}

.record-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12rpx 0;
  border-bottom: 1px solid #f5f5f5;
}

.record-left {
  display: flex;
  align-items: center;
  gap: 16rpx;
}

.category-icon {
  width: 50rpx;
  height: 50rpx;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  
  &.expense {
    background: linear-gradient(135deg, #FF8C69, #FFA07A);
  }
  
  &.income {
    background: linear-gradient(135deg, #FF8C69, #FFA07A);
  }
}

.record-info {
  display: flex;
  flex-direction: column;
}

.record-category {
  font-size: 24rpx;
  color: #333;
  font-weight: 500;
}

.record-amount {
  font-size: 26rpx;
  font-weight: 600;
  
  &.expense {
    color: #FF8C69;
  }
  
  &.income {
    color: #FF8C69;
  }
}

@keyframes slideIn {
  from {
    opacity: 0;
    transform: translateY(20rpx);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.fab-btn {
  position: fixed;
  right: 40rpx;
  bottom: 120rpx;
  width: 90rpx;
  height: 90rpx;
  border-radius: 50%;
  background: linear-gradient(135deg, #FF8C69, #FFA07A);
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.15);
}
</style>
