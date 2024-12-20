import { listSimpleDictDatas } from '@/api/system/dict/data'
const state = {
  dict: new Array(),
  dictDatas: {}
}
const mutations = {
  SET_DICT: (state, { key, value }) => {
    if (key !== null && key !== "") {
      state.dict.push({
        key: key,
        value: value
      })
    }
  },
  REMOVE_DICT: (state, key) => {
    try {
      for (let i = 0; i < state.dict.length; i++) {
        if (state.dict[i].key == key) {
          state.dict.splice(i, 1)
          return true
        }
      }
    } catch (e) {
    }
  },
  CLEAN_DICT: (state) => {
    state.dict = new Array()
  },
  SET_DICT_DATAS: (state, dictDatas) => {
    state.dictDatas = dictDatas
  }
}

const actions = {
  // 设置字典
  setDict({ commit }, data) {
    commit('SET_DICT', data)
  },
  // 删除字典
  removeDict({ commit }, key) {
    commit('REMOVE_DICT', key)
  },
  // 清空字典
  cleanDict({ commit }) {
    commit('CLEAN_DICT')
  },
  loadDictDatas({ commit }) {
    listSimpleDictDatas().then(response => {
      if (!response || !response.data) {
        console.warn('No data received from the server.')
        return
      }
      const dictDataMap = {}
      response.data.forEach(dictData => {
        const enumValueObj = dictDataMap[dictData.dictType]
        if (!enumValueObj) {
          dictDataMap[dictData.dictType] = []
        }
        dictDataMap[dictData.dictType].push({
          value: dictData.value,
          label: dictData.label,
          colorType: dictData.colorType,
          cssClass: dictData.cssClass,
        })
      })
      commit('SET_DICT_DATAS', dictDataMap)
    }).catch(error => {
      console.error('Error loading dictionary data:', error) // 错误日志
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

