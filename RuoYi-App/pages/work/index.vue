<template>
  <view class="container">
    <!-- 顶部统计面板 -->
    <view class="stats-panel">
      <view class="month-stats">
        <text class="month-title">本月收支</text>
        <view class="amount-row">
          <view class="amount-item">
            <text class="label">收入</text>
            <text class="value income">¥0.00</text>
          </view>
          <view class="amount-item">
            <text class="label">支出</text>
            <text class="value expense">¥0.00</text>
          </view>
        </view>
        <view class="budget-progress">
          <text class="progress-label">预算使用进度</text>
          <view class="progress-bar">
            <view class="progress-inner" style="width: 0%"></view>
          </view>
          <text class="progress-text">0%</text>
        </view>
      </view>
    </view>

    <!-- 快速记账入口 -->
    <view class="quick-actions">
      <view class="action-btn income-btn">
        <uni-icons type="plus" size="24" color="#fff"></uni-icons>
        <text>收入</text>
      </view>
      <view class="action-btn expense-btn">
        <uni-icons type="minus" size="24" color="#fff"></uni-icons>
        <text>支出</text>
      </view>
    </view>

    <!-- 常用分类 -->
    <view class="common-categories">
      <text class="section-title">常用分类</text>
      <view class="category-grid">
        <view class="category-item" v-for="(item, index) in commonCategories" :key="index">
          <uni-icons :type="item.icon" size="30" color="#FF9800"></uni-icons>
          <text>{{ item.name }}</text>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      commonCategories: [
        { name: '餐饮', icon: 'shop' },
        { name: '交通', icon: 'car-filled' },
        { name: '购物', icon: 'cart' },
        { name: '娱乐', icon: 'star' },
        { name: '居家', icon: 'home' },
        { name: '工资', icon: 'wallet' },
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding: 20rpx;
}

.stats-panel {
  background: linear-gradient(135deg, #FF9800, #FB8C00);
  border-radius: 20rpx;
  padding: 30rpx;
  color: #fff;
  margin-bottom: 30rpx;

  .month-title {
    font-size: 32rpx;
    margin-bottom: 20rpx;
    display: block;
  }

  .amount-row {
    display: flex;
    justify-content: space-between;
    margin-bottom: 30rpx;

    .amount-item {
      .label {
        font-size: 28rpx;
        opacity: 0.9;
      }
      .value {
        font-size: 40rpx;
        font-weight: bold;
        display: block;
      }
    }
  }

  .budget-progress {
    .progress-label {
      font-size: 26rpx;
      opacity: 0.9;
      margin-bottom: 10rpx;
      display: block;
    }

    .progress-bar {
      height: 10rpx;
      background: rgba(255,255,255,0.3);
      border-radius: 5rpx;
      margin: 10rpx 0;

      .progress-inner {
        height: 100%;
        background: #fff;
        border-radius: 5rpx;
      }
    }

    .progress-text {
      font-size: 24rpx;
      opacity: 0.9;
    }
  }
}

.quick-actions {
  display: flex;
  justify-content: space-around;
  margin-bottom: 30rpx;

  .action-btn {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 45%;
    height: 160rpx;
    border-radius: 15rpx;
    color: #fff;

    text {
      margin-top: 10rpx;
      font-size: 28rpx;
    }
  }

  .income-btn {
    background: #4CAF50;
  }

  .expense-btn {
    background: #FF5722;
  }
}

.common-categories {
  background: #fff;
  border-radius: 20rpx;
  padding: 30rpx;

  .section-title {
    font-size: 32rpx;
    color: #333;
    margin-bottom: 20rpx;
    display: block;
  }

  .category-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20rpx;

    .category-item {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 20rpx;
      background: #f8f8f8;
      border-radius: 10rpx;

      text {
        margin-top: 10rpx;
        font-size: 26rpx;
        color: #666;
      }
    }
  }
}
</style>
