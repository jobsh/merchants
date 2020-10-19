import request from '@/utils/request'

// 查询客户线索列表
export function listXiansuo(query) {
  return request({
    url: '/system/xiansuo/list',
    method: 'get',
    params: query
  })
}

// 查询客户线索详细
export function getXiansuo(id) {
  return request({
    url: '/system/xiansuo/' + id,
    method: 'get'
  })
}

// 新增客户线索
export function addXiansuo(data) {
  return request({
    url: '/system/xiansuo',
    method: 'post',
    data: data
  })
}

// 修改客户线索
export function updateXiansuo(data) {
  return request({
    url: '/system/xiansuo',
    method: 'put',
    data: data
  })
}

// 删除客户线索
export function delXiansuo(id) {
  return request({
    url: '/system/xiansuo/' + id,
    method: 'delete'
  })
}

// 导出客户线索
export function exportXiansuo(query) {
  return request({
    url: '/system/xiansuo/export',
    method: 'get',
    params: query
  })
}