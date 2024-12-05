import request from '@/utils/request'

export function getModelPage(query) {
  return request({
    url: '/flow/model/page',
    method: 'get',
    params: query
  })
}

export function getModel(id) {
  return request({
    url: '/flow/model/get?id=' + id,
    method: 'get'
  })
}

export function updateModel(data) {
  return request({
    url: '/flow/model/update',
    method: 'PUT',
    data: data
  })
}

// 任务状态修改
export function updateModelState(id, state) {
  return request({
    url: '/flow/model/update-state',
    method: 'put',
    data: {
      id,state
    }
  })
}

// 新建模型
export function createModel(data) {
  return request({
    url: '/flow/model/create',
    method: 'POST',
    data: data
  })
}

// 删除模型
export function deleteModel(id) {
  return request({
    url: '/flow/model/delete?id=' + id,
    method: 'DELETE'
  })
}

export function deployModel(id) {
  return request({
    url: '/flow/model/deploy?id=' + id,
    method: 'POST'
  })
}
