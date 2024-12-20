import request from '@/utils/request'

// 查询流程分类列表
export function getFormPage(query) {
  return request({
    url: '/flow/form/page',
    method: 'get',
    params: query
  })
}
// 查询流程分类详细
export function getForm(id) {
  return request({
    url: '/flow/form/getById/' + id,
    method: 'get'
  })
}

// 新增流程分类
export function createForm(data) {
  return request({
    url: '/flow/form/create',
    method: 'post',
    data: data
  })
}

// 修改流程分类
export function updateForm(data) {
  return request({
    url: '/flow/form/update',
    method: 'put',
    data: data
  })
}

// 删除流程分类
export function deleteForm(id) {
  return request({
    url: '/flow/form/delete/' + id,
    method: 'delete'
  })
}

