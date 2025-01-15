<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="流程标识" prop="key">
        <el-input v-model.trim="queryParams.key" placeholder="请输入流程标识" clearable size="small" style="width: 240px" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="流程名称" prop="name">
        <el-input v-model.trim="queryParams.name" placeholder="请输入流程名称" clearable size="small" style="width: 240px" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="流程分类" prop="category">
        <el-select v-model="queryParams.category" placeholder="请选择流程分类" clearable size="small" style="width: 240px">
          <el-option v-for="dict in categoryOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <!-- 查询、重置、添加按钮 -->
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">{{ '搜索' }}</el-button>
        <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetQuery">{{ '重置' }}</el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="openModelForm">{{ '新增' }}</el-button>
      </el-form-item>
    </el-form>

    <!-- 数据表格 -->
    <vxe-table
      ref="vxeTable"
      keep-source
      align="center"
      border resizable show-overflow highlight-hover-row :print-config="{}" :loading="loading" :data="modelList"
      @checkbox-all="handleSelectionChange" height="100%" @checkbox-change="handleSelectionChange">
      <vxe-column type="checkbox" width="50"></vxe-column>
      <vxe-table-column title="流程名称" align="center" field="name" />
      <vxe-table-column title="流程分类" align="center" field="category" width="120" />
      <vxe-table-column title="表单信息" align="center" field="formInfo" width="120" />
      <vxe-table-column title="最后发布" align="center" field="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime), '{y}-{m}-{d} {h}:{i}' }}</span>
        </template>
      </vxe-table-column>
      <vxe-table-column title="操作" align="center" width="280">
        <template slot-scope="scope">
        </template>
      </vxe-table-column>
    </vxe-table>

    <!-- 新建/编辑表单模态框 -->
    <vxe-modal v-model="openModel" width="27%" min-width="400" height="60%" min-height="600" show-footer show-zoom>
      <template #title>
        <span>{{ title }}</span>
      </template>
      <template #default>
        <el-form ref="modelForm" :model="modelForm" :disabled="formDisabled" :rules="rules" label-width="120px">
          <el-row :gutter="20">
            <!-- 表单项 -->
            <el-col :span="24">
              <el-form-item label="流程标识key:" prop="key">
                <el-input v-model="modelForm.key" :disabled="true" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="流程名称:" prop="name"><el-input v-model.trim="modelForm.name"  placeholder="请输入流程名称" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="流程分类:" prop="category">
                <el-select v-model="modelForm.category" placeholder="请选择">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="流程描述:" prop="description">
                <el-input v-model.trim="modelForm.description" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="流程类型:" prop="type">
                <el-radio-group v-model="modelForm.type">
                  <el-radio :label="10">BPMN设计器</el-radio>
                  <el-radio :label="20">SIMPLE设计器</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="表单类型:" prop="formType">
                <el-radio-group v-model="modelForm.formType">
                  <el-radio :label="10">流程表单</el-radio>
                  <el-radio :label="20">业务表单</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="modelForm.formType === 10">
              <el-form-item label="流程表单:" prop="formId">
                <el-select v-model="modelForm.formId" placeholder="请选择">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="modelForm.formType === 20">
              <el-form-item label="表单提交路由:" prop="formCustomCreatePath">
                <el-input v-model.trim="modelForm.formCustomCreatePath" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="modelForm.formType === 20">
              <el-form-item label="表单查看地址:" prop="formCustomViewPath">
                <el-input v-model.trim="modelForm.formCustomViewPath" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer" style="display: flex;justify-content: center">
          <el-button size="mini" type="primary" @click="submitForm" v-if="!formDisabled">确 定</el-button>
          <el-button size="mini" @click="cancel">取 消</el-button>
        </div>
      </template>
    </vxe-modal>

    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

  </div>
</template>
<script>
import { getModel, getModelList, createModel, updateModel, deleteModel, deployModel, updateModelState } from '@/api/flow/model'
import {handleGetSnowFlakeId} from "@/utils/sky";
export default {
  name: "FlowModel",
  data() {
    return {
      loading: false, // 遮罩层
      modelList: [], // 列表数据
      queryParams: { key: undefined, name: undefined, category: undefined, pageNum: 1, pageSize: 10 }, // 查询参数
      categoryOptions: [], // 分类选择项
      options: [], // 动态表单选项
      // 默认表单数据
      modelForm: {
        key: '',                  // 流程标识key
        name: '',                 // 流程名称
        category: '',             // 流程分类
        description: '',          // 流程描述
        type: 10,                 // 流程类型，默认值 10 (BPMN设计器)
        formType: 10,             // 表单类型，默认值 10 (流程表单)
        formId: '',               // 流程表单，默认值为空
        formCustomCreatePath: '', // 表单提交路由
        formCustomViewPath: '',   // 表单查看地址
      },
      // 表单验证规则
      rules: {
        // key: [{ required: true, message: '请输入流程标识key', trigger: 'blur' }],
        // flowName: [{ required: true, message: '请输入流程名称', trigger: 'blur' }],
        // flowCategory: [{ required: true, message: '请选择流程分类', trigger: 'change' }],
        // type: [{ required: true, message: '请选择流程类型', trigger: 'change' }],
        // formType: [{ required: true, message: '请选择表单类型', trigger: 'change' }],
        // formId: [{ required: true, message: '请选择流程表单', trigger: 'change' }],
        // formCustomCreatePath: [{ required: true, message: '请输入表单提交路由', trigger: 'blur' }],
        // formCustomViewPath: [{ required: true, message: '请输入表单查看地址', trigger: 'blur' }],
      },
      title: '', // 模态框标题
      openModel: false, // 是否显示模态框
      etype: '', // 编辑类型
      formDisabled: false, // 表单是否禁用
      total: 0, // 数据总数
    };
  },
  created() {
    // 初始化
    this.getList()
  },
  methods: {
    // 查询方法
    handleQuery() {
      console.log('查询');
    },
    // 重置查询
    resetQuery() {
      this.queryParams = { key: undefined, name: undefined, category: undefined, pageNum: 1, pageSize: 10 };
      this.handleQuery();
    },
    // 操作方法
    handleUpdate(row) {
      console.log('修改', row);
    },
    handleDataScope(row) {
      console.log('数据权限', row);
    },
    handleAddRoleUser(row) {
      console.log('添加人员', row);
    },
    handleDelete(row) {
      console.log('删除', row);
    },
    handleSelectionChange() {
      console.log('选择项改变');
    },
    // 表单提交
    submitForm() {
      console.log('表单提交', this.modelForm);
      this.$refs['modelForm'].validate(valid => {
        if (valid) {
          // 提交表单
          createModel(this.modelForm).then(response => {
            this.$modal.msgSuccess("保存成功");
            this.close();
          });
        }
      });
    },
    cancel() {
      this.openModel = false;
    },
    // 获取列表
    getList() {
      console.log('获取列表');
      getModelList(this.queryParams).then(response => {
        this.modelList = response.rows;
        this.total = response.total;
      });
    },
    openModelForm() {
      this.openModel = true;
      this.title = '新增流程';
      this.resetModelForm();
      this.handleGetSnowFlakeId().then(response => {
        if (response && response.data) {
          let keyId = response.data;
          this.modelForm.key = 'P_' + keyId;
        }
      })
    },
    resetModelForm() {
      this.modelForm = {
         key: '',                  // 流程标识key
         name: '',                 // 流程名称
         category: '',             // 流程分类
         description: '',          // 流程描述
         type: 10,                 // 流程类型，默认值 10 (BPMN设计器)
         formType: 10,             // 表单类型，默认值 10 (流程表单)
         formId: '',               // 流程表单，默认值为空
         formCustomCreatePath: '', // 表单提交路由
         formCustomViewPath: '',   // 表单查看地址
      }
      this.resetForm("modelForm");
    },
    close(){
      this.openModel = false;
      this.modelForm = {}; // 重置表单数据
    }
  }
};
</script>
<style lang="scss">
/* 让输入框、选择框和单选框占据满宽度 */
.el-input, .el-select, .el-radio-group, .el-textarea {
  width: 100%; /* 确保元素占满宽度 */
}

/* 给表单项增加间距 */
.el-form-item {
  margin-bottom: 20px;
}

/* 增加表单行之间的间距 */
.el-row {
  margin-bottom: 15px;
}

/* 优化弹框头部样式 */
.el-modal__header {
  background-color: #f4f7fa; /* 修改背景色 */
  padding: 15px;
  font-size: 18px;
}

/* 优化弹框主体区域样式 */
.el-modal__body {
  background-color: #fff;
  padding: 20px; /* 增加内边距 */
}

/* 按钮右对齐，增加间距 */
.dialog-footer {
  text-align: right;
}

/* 给按钮增加圆角 */
.el-button {
  margin-right: 10px;
  border-radius: 4px; /* 圆角边框 */
}

/* 输入框和选择框外边框颜色 */
.el-input__inner, .el-select .el-input__inner, .el-textarea__inner {
  border: 1px solid #dcdfe6;
}
</style>

