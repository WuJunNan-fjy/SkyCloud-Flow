import request from '@/utils/request'

// 查询流程分类列表
export function listCategory(query) {
  return request({
    url: '/flow/category/page',
    method: 'get',
    params: query
  })
}
// 查询流程分类详细
export function getCategory(id) {
  return request({
    url: '/flow/category/getById/' + id,
    method: 'get'
  })
}

// 新增流程分类
export function addCategory(data) {
  return request({
    url: '/flow/category/create',
    method: 'post',
    data: data
  })
}

// 修改流程分类
export function updateCategory(data) {
  return request({
    url: '/flow/category/update',
    method: 'put',
    data: data
  })
}

// 删除流程分类
export function delCategory(id) {
  return request({
    url: '/flow/category/delete/' + id,
    method: 'delete'
  })
}

