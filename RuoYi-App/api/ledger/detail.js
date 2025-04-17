import upload from '@/utils/upload'
import request from '@/utils/request'

// 查询记账明细列表
export function listDetail(query) {
    return request({
      url: '/ledger/detail/list',
      method: 'get',
      params: query
    })
  }
  
  // 查询记账明细详细
  export function getDetail(detailId) {
    return request({
      url: '/ledger/detail/' + detailId,
      method: 'get'
    })
  }
  
  // 新增记账明细
  export function addDetail(data) {
    return request({
      url: '/ledger/detail',
      method: 'post',
      data: data
    })
  }
  
  // 修改记账明细
  export function updateDetail(data) {
    return request({
      url: '/ledger/detail',
      method: 'put',
      data: data
    })
  }
  
  // 删除记账明细
  export function delDetail(detailId) {
    return request({
      url: '/ledger/detail/' + detailId,
      method: 'delete'
    })
  }