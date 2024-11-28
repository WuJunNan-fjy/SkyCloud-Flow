<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="流程标识" prop="key">
        <el-input v-model.trim="queryParams.key" placeholder="请输入流程标识" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="流程名称" prop="name">
        <el-input v-model.trim="queryParams.name" placeholder="请输入流程名称" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="流程分类" prop="category">
        <el-select v-model="queryParams.category" placeholder="请选择流程分类" clearable size="small" style="width: 240px">
          <el-option v-for="dict in categoryOptions" :key="dict.dictValue" :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" >{{ '搜索' }}</el-button>
        <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetQuery" >{{ '重置' }}</el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleDesign" >{{ '新增' }}</el-button>
      </el-form-item>
    </el-form>

    <vxe-table ref="vxeTable" keep-source align="center" border resizable show-overflow highlight-hover-row
               :print-config="{}" :loading="loading" :data="desginList" @checkbox-all="handleSelectionChange" height="100%"
               @checkbox-change="handleSelectionChange">
      <vxe-column type="checkbox" width="50"></vxe-column>
      <vxe-table-column title="流程名称" align="center" field="procInstName"/>
      <vxe-table-column title="流程图标" align="center" field="procInstIcon"/>
      <vxe-table-column title="可见范围" align="center" field="visibleRange"/>
      <vxe-table-column title="流程分类" align="center" field="category" width="120"/>
      <vxe-table-column title="表单信息" align="center" field="formInfo" width="120"/>
      <vxe-table-column title="最后发布" align="center" field="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </vxe-table-column>
      <vxe-table-column title="操作" align="center" width="280" class-name="">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['system:role:edit']">{{'修改' }}
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-circle-check" @click="handleDataScope(scope.row)"
                     v-hasPermi="['system:role:edit']">数据权限
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-plus" @click="handleAddRoleUser(scope.row)"
                     v-hasPermi="['system:role:addRoleUser']">添加人员
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['system:role:remove']">{{ '删除' }}
          </el-button>
        </template>
      </vxe-table-column>
    </vxe-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList" />


  </div>
</template>
<script>
export default {
  name: "Design",
  components: {
  },
  data() {
    return {
      loading: false, // 遮罩层
      desginList: [], // 列表数据
      single: true, // 是否单选
      multiple: true, // 是否多选
      queryParams:{
        key: undefined,
        name: undefined,
        category: undefined,
        pageNum: 1,
        pageSize: 10
      },
      categoryOptions: []
    };
  },
  created() {
  },
  methods: {
    /** 设计按钮操作 */
    handleDesign(row) {
      this.$router.push({
        name: "BpmModelEditor",
        query:{
          modelId: row.id
        }
      });
    },
  }
}
</script>
<style lang="scss">

</style>
