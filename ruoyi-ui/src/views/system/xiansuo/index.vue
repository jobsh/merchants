<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入客户姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户姓名" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入客户姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户姓名" prop="companyId">
        <el-input
          v-model="queryParams.companyId"
          placeholder="请输入客户姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户姓名" prop="province">
        <el-input
          v-model="queryParams.province"
          placeholder="请输入客户姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户姓名" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入客户姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="线索来源" prop="resource">
        <el-input
          v-model="queryParams.resource"
          placeholder="请输入线索来源"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人" prop="sysUserId">
        <el-input
          v-model="queryParams.sysUserId"
          placeholder="请输入负责人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否线索变为客户(0：否，1：是)" prop="isCustomer">
        <el-input
          v-model="queryParams.isCustomer"
          placeholder="请输入是否线索变为客户(0：否，1：是)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否线索变为客户(0：否，1：是)" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择是否线索变为客户(0：否，1：是)">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最新跟进时间" prop="updateTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择最新跟进时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:xiansuo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:xiansuo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:xiansuo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:xiansuo:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="xiansuoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="最新跟进时间" align="center" prop="id" />
      <el-table-column label="客户姓名" align="center" prop="name" />
      <el-table-column label="客户姓名" align="center" prop="phone" />
      <el-table-column label="客户姓名" align="center" prop="companyId" />
      <el-table-column label="客户姓名" align="center" prop="province" />
      <el-table-column label="客户姓名" align="center" prop="city" />
      <el-table-column label="线索来源" align="center" prop="resource" />
      <el-table-column label="负责人" align="center" prop="sysUserId" />
      <el-table-column label="是否线索变为客户(0：否，1：是)" align="center" prop="isCustomer" />
      <el-table-column label="是否线索变为客户(0：否，1：是)" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最新跟进时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:xiansuo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:xiansuo:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改客户线索对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户姓名" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户姓名" prop="companyId">
          <el-input v-model="form.companyId" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户姓名" prop="province">
          <el-input v-model="form.province" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户姓名" prop="city">
          <el-input v-model="form.city" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="线索来源" prop="resource">
          <el-input v-model="form.resource" placeholder="请输入线索来源" />
        </el-form-item>
        <el-form-item label="负责人" prop="sysUserId">
          <el-input v-model="form.sysUserId" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="是否线索变为客户(0：否，1：是)" prop="isCustomer">
          <el-input v-model="form.isCustomer" placeholder="请输入是否线索变为客户(0：否，1：是)" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listXiansuo, getXiansuo, delXiansuo, addXiansuo, updateXiansuo, exportXiansuo } from "@/api/system/xiansuo";

export default {
  name: "Xiansuo",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 客户线索表格数据
      xiansuoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        phone: null,
        companyId: null,
        province: null,
        city: null,
        resource: null,
        sysUserId: null,
        isCustomer: null,
        createTime: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "客户姓名不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "客户姓名不能为空", trigger: "blur" }
        ],
        companyId: [
          { required: true, message: "客户姓名不能为空", trigger: "blur" }
        ],
        province: [
          { required: true, message: "客户姓名不能为空", trigger: "blur" }
        ],
        city: [
          { required: true, message: "客户姓名不能为空", trigger: "blur" }
        ],
        resource: [
          { required: true, message: "线索来源不能为空", trigger: "blur" }
        ],
        sysUserId: [
          { required: true, message: "负责人不能为空", trigger: "blur" }
        ],
        isCustomer: [
          { required: true, message: "是否线索变为客户(0：否，1：是)不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "是否线索变为客户(0：否，1：是)不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "最新跟进时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询客户线索列表 */
    getList() {
      this.loading = true;
      listXiansuo(this.queryParams).then(response => {
        this.xiansuoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        phone: null,
        companyId: null,
        province: null,
        city: null,
        resource: null,
        sysUserId: null,
        isCustomer: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户线索";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getXiansuo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户线索";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateXiansuo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addXiansuo(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除客户线索编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delXiansuo(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有客户线索数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportXiansuo(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
