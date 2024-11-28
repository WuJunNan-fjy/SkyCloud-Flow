<template>
  <div class="app-container">

    <vxe-toolbar>
      <!-- 左侧按钮 -->
      <template #buttons>
        <el-button plain size="mini" type="primary" icon="el-icon-plus" @click="handleAdd" >新增
        </el-button>
        <el-button plain size="mini" type="primary" icon="el-icon-edit" :disabled="single" @click="handleUpdate" >修改
        </el-button>
        <el-button plain size="mini" type="primary" icon="el-icon-delete" :disabled="multiple" @click="handleDelete" >删除
        </el-button>
      </template>
      <!-- 右侧按钮 -->
      <template #tools>

        <el-input placeholder="请输入分类名称" clearable v-model="queryParams.categoryName"
                  style="width:260px" >
          <el-button slot="append" icon="el-icon-search" @click="handleQuery" style="vertical-align: baseline;">搜索</el-button>
        </el-input>
        <el-popover placement="bottom-start" width="720" trigger="click" ref="popover"
                    @hide="handleCancel">

          <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="100px">
            <el-row style="padding-left: 10px;">

              <el-col :span="12">
                <el-form-item label="序号：" prop="categoryCode">
                  <el-input v-model="queryParams.categoryCode" placeholder="请输入分类编号" clearable size="small"
                            @keyup.enter.native="handleQuery" />
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item
                  style="display: flex;width: 100%;justify-content: flex-end;padding-right: 15px;margin-bottom: 0px;">
                  <el-button size="mini" class="popverReset" @click="resetQuery">重置</el-button>
                  <el-button size="mini" class="popverCancel" @click="handleCancel">取消</el-button>
                  <el-button type="primary" class="popverOk" size="mini" @click="handleQuery">搜索</el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <el-button slot="reference" plain icon="el-icon-search" style="margin-left: 10px">
            {{ '高级筛选' }}
          </el-button>
        </el-popover>

      </template>
    </vxe-toolbar>
    <div class="categoryTable">
      <vxe-table ref="vxeTable" keep-source align="center" border resizable show-overflow highlight-hover-row
                 :print-config="{}" :loading="loading" :data="categoryList" height="100%"
                 :checkbox-config="{checkMethod: checkSelectable, highlight: true}" @checkbox-all="handleSelectionChange"
                 @checkbox-change="handleSelectionChange">
        <vxe-column type="checkbox" width="50"></vxe-column>
        <vxe-table-column title="分类名称" align="center" field="categoryName" />
        <vxe-table-column title="分类编号" align="center" field="categoryCode" />
        <vxe-table-column title="序号" align="center" field="num" />
        <vxe-table-column title="备注" align="center" field="remark" />
        <vxe-table-column title="操作" align="center" width="180" class-name="">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row,false)"
                       v-hasPermi="['system:category:edit']" >修改</el-button>
            <el-button size="mini" type="text" icon="el-icon-view" @click="handleUpdate(scope.row,true)" >详情</el-button>
            <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"  >删除</el-button>
          </template>
        </vxe-table-column>
      </vxe-table>
    </div>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList" />


    <!-- 添加或修改流程分类对话框 -->
    <vxe-modal v-model="open" width="40%" min-width="600" height="60%" min-height="600" show-footer show-zoom>
      <template #title>
        <span>{{title}}</span>
      </template>
      <template #default>
        <el-form ref="form" :model="form" :disabled="formDisabled" :rules="rules" label-width="100px">
          <el-row :gutter="20">
            <el-col :span="24">
              <el-form-item label="分类名称" prop="categoryName">
                <el-input v-model.trim="form.categoryName" placeholder="请输入内容" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="分类编号" prop="categoryCode">
                <el-input v-model.trim="form.categoryCode" :disabled="etype == 'update'" placeholder="请输入分类编号" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="序号" prop="num">
                <el-input-number v-model="form.num" :min="1" label="请输入序号" style="width: 100%;"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="备注" prop="remark">
                <el-input v-model.trim="form.remark" type="textarea" placeholder="请输入内容" />
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>

      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button size="mini" type="primary" @click="submitForm" v-if="!formDisabled">确 定</el-button>
          <el-button size="mini" @click="cancel">取 消</el-button>
        </div>
      </template>
    </vxe-modal>

  </div>
</template>

<script>
import {
  listCategory,
  getCategory,
  delCategory,
  addCategory,
  updateCategory
} from "@/api/flow/category";

export default {
  name: "Category",
  components: {
  },
  dicts: [],
  data() {
    return {
      //修改或新增add、update
      etype: '',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      //表单禁用
      formDisabled: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 流程分类表格数据
      categoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryName: null,
        categoryCode: null,
        num: null,
        orgid: null,
        userid: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        categoryCode: [{
          required: true,
          message: "分类编号不能为空",
          trigger: "blur"
        }],
        categoryName: [{
          required: true,
          message: "分类名称不能为空",
          trigger: "blur"
        }],
      }
    };
  },
  created() {
    this.getList();
  },
  watch: {},
  methods: {
    /** 查询流程分类列表 */
    getList() {
      this.loading = true;
      listCategory(this.queryParams).then(response => {
        this.categoryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.formDisabled = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        file: null,
        id: null,
        categoryName: null,
        categoryCode: null,
        num: null,
        orgid: null,
        userid: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
      };
      this.resetForm("form");
    },
    closedfunc() {
      this.reset();
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 取消按钮操作 */
    handleCancel() {
      this.$refs.popover.doClose();
    },
    // 多选框选中数据
    handleSelectionChange(e) {
      let selection = e.records;
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    //返回附件列表
    loadSuccess(e) {
      var arr = [];
      for (var obj of e) {
        arr.push(obj.fileid);
      }
      this.form.file = arr.join(',');
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.formDisabled = false;
      this.etype = 'add';
      this.reset();
      this.open = true;
      this.title = "添加流程分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row, bool) {

      this.formDisabled = bool;
      this.etype = 'update';
      this.reset();
      const id = row.id || this.ids
      getCategory(id).then(response => {
        let data = response.data;
        data.file = ''; //附件必传校验字段
        this.form = data;
        let title = "修改流程分类";
        if (bool) {
          title = "查看流程分类";
        }
        this.title = title;
        this.open = true;
      });
    },
    /** 提交按钮 */
    async submitForm() {
      this.verifyForm(this.$refs["form"].validate, () => {
        return new Promise((resolve) => {
          if (this.etype != 'add') {
            updateCategory(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              resolve(true);
            });
          } else {
            addCategory(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.etype = 'update';
              this.getList();
              resolve(true);
            });
          }
        })
      }).then(async (res) => {
        if (res) {
          this.etype = 'update'
          this.$refs.indexSub.submitForm().then(bool => {
            //保存子表
            if (bool) {
              this.open = false;
            }
          });
        }
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除选中的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delCategory(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },

    //复选框禁用
    checkSelectable(e) {
      let row = e.row;
      //根据字段值判断是否禁用复选框
      return (row.remark != '' && row.remark != '1');
    },

  }
};
</script>
<style scoped>
.app-container {
  padding: 20px 25px;
  height: 100%;
  overflow: hidden;
}
::v-deep.sendAddBtnCls.el-button--default.is-plain,
.sendAddBtnCls.el-button--default.is-plain:hover {
  border-color: #0F6192;
  color: #0F6192 !important;
  margin-left: .7vw;
  font-size: 14px;
  padding: 8px .4vw;
}
::v-deep .sendAddBtnCls.el-button--default.is-plain img {
  width: 14px;
  height: 14px;
  display: inline-block;
  vertical-align: bottom;
  margin-right: 8px;
}
::v-deep .popverOk.el-button--primary {
  background-color: #0F6192;
  border-color: #0F6192;
}
::v-deep .popverReset.el-button--default,
::v-deep .popverCancel.el-button--default {
  border-color: #0F6192;
  color: #0F6192;
}
.categoryListInput ::v-deep .el-input-group__append button.el-button {
  border-color: #0F6192;
  background-color: #0F6192;
  border-radius: 0;
  color: #fff;
  padding: 9px .4vw;
  height: 32px;
  width: 70px;
  display: flex;
  align-items: center;
}
.categoryListInput ::v-deep .el-input-group__append button.el-button .el-icon-search{
  margin-top: -3px;
}
::v-deep .categoryListInput.el-input--medium .el-input__inner {
  height: 32px;
  line-height: 32px;
  border-color: #0F6192;
  border-radius: 0;
}
.categoryTable {
  height: calc(100% - 100px);
}
</style>
