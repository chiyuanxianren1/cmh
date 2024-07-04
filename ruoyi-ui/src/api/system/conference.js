import request from '@/utils/request'

// 查询会议管理列表
export function listConference(query) {
  return request({
    url: '/system/conference/list',
    method: 'get',
    params: query
  })
}

// 查询会议管理详细
export function getConference(id) {
  return request({
    url: '/system/conference/' + id,
    method: 'get'
  })
}

// 新增会议管理
export function addConference(data) {
  return request({
    url: '/system/conference',
    method: 'post',
    data: data
  })
}

// 修改会议管理
export function updateConference(data) {
  return request({
    url: '/system/conference',
    method: 'put',
    data: data
  })
}

// 删除会议管理
export function delConference(id) {
  return request({
    url: '/system/conference/' + id,
    method: 'delete'
  })
}
