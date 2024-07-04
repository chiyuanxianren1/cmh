import request from '@/utils/request'

// 查询租户管理列表
export function listTenement(query) {
  return request({
    url: '/system/tenement/list',
    method: 'get',
    params: query
  })
}

// 查询租户管理详细
export function getTenement(id) {
  return request({
    url: '/system/tenement/' + id,
    method: 'get'
  })
}

// 新增租户管理
export function addTenement(data) {
  return request({
    url: '/system/tenement',
    method: 'post',
    data: data
  })
}

// 修改租户管理
export function updateTenement(data) {
  return request({
    url: '/system/tenement',
    method: 'put',
    data: data
  })
}

// 删除租户管理
export function delTenement(id) {
  return request({
    url: '/system/tenement/' + id,
    method: 'delete'
  })
}
