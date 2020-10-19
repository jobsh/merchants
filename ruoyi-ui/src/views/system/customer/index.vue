<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="负责人id" prop="xiansuoId">
        <el-input
          v-model="queryParams.xiansuoId"
          placeholder="请输入负责人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户等级" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入客户等级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户等级" prop="companyId">
        <el-input
          v-model="queryParams.companyId"
          placeholder="请输入客户等级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户等级" prop="province">
        <el-input
          v-model="queryParams.province"
          placeholder="请输入客户等级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户等级" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入客户等级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户等级" prop="genjinStatus">
        <el-select v-model="queryParams.genjinStatus" placeholder="请选择客户等级" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="客户来源" prop="resource">
        <el-input
          v-model="queryParams.resource"
          placeholder="请输入客户来源"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人id" prop="sysUserId">
        <el-input
          v-model="queryParams.sysUserId"
          placeholder="请输入负责人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人id" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择负责人id">
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
          v-hasPermi="['system:customer:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:customer:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:customer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:customer:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户id" align="center" prop="id" />
      <el-table-column label="客户id" align="center" prop="xiansuoId" />
      <el-table-column label="客户名称" align="center" prop="name" />
      <el-table-column label="客户名称" align="center" prop="phone" />
      <el-table-column label="客户等级" align="center" prop="level" :formatter="levelFormat" />
      <el-table-column label="客户等级" align="center" prop="companyId" />
      <el-table-column label="客户等级" align="center" prop="province" />
      <el-table-column label="客户等级" align="center" prop="city" />
      <el-table-column label="客户等级" align="center" prop="genjinStatus" />
      <el-table-column label="客户来源" align="center" prop="resource" />
      <el-table-column label="负责人id" align="center" prop="sysUserId" />
      <el-table-column label="负责人id" align="center" prop="createTime" width="180">
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
            v-hasPermi="['system:customer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:customer:remove']"
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

    <!-- 添加或修改我的客户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="最新跟进时间" prop="xiansuoId">
          <el-input v-model="form.xiansuoId" placeholder="请输入最新跟进时间" />
        </el-form-item>
        <el-form-item label="客户名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="客户名称" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="客户等级" prop="level">
          <el-input v-model="form.level" placeholder="请输入客户等级" />
        </el-form-item>
        <el-form-item label="客户等级" prop="companyId">
          <el-input v-model="form.companyId" placeholder="请输入客户等级" />
        </el-form-item>
        <el-form-item label="客户等级" prop="province">
          <el-input v-model="form.province" placeholder="请输入客户等级" />
        </el-form-item>
        <el-form-item label="客户等级" prop="city">
          <el-input v-model="form.city" placeholder="请输入客户等级" />
        </el-form-item>
        <el-form-item label="客户等级">
          <el-radio-group v-model="form.genjinStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="客户来源" prop="resource">
          <el-input v-model="form.resource" placeholder="请输入客户来源" />
        </el-form-item>
        <el-form-item label="负责人id" prop="sysUserId">
          <el-input v-model="form.sysUserId" placeholder="请输入负责人id" />
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
import { listCustomer, getCustomer, delCustomer, addCustomer, updateCustomer, exportCustomer } from "@/api/system/customer";

export default {
  name: "Customer",
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
      // 我的客户表格数据
      customerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 客户等级字典
      levelOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        xiansuoId: null,
        name: null,
        phone: null,
        level: null,
        companyId: null,
        province: null,
        city: null,
        genjinStatus: null,
        resource: null,
        sysUserId: null,
        createTime: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        xiansuoId: [
          { required: true, message: "最新跟进时间不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "客户名称不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "客户名称不能为空", trigger: "blur" }
        ],
        level: [
          { required: true, message: "客户等级不能为空", trigger: "blur" }
        ],
        companyId: [
          { required: true, message: "客户等级不能为空", trigger: "blur" }
        ],
        province: [
          { required: true, message: "客户等级不能为空", trigger: "blur" }
        ],
        city: [
          { required: true, message: "客户等级不能为空", trigger: "blur" }
        ],
        genjinStatus: [
          { required: true, message: "客户等级不能为空", trigger: "blur" }
        ],
        resource: [
          { required: true, message: "客户来源不能为空", trigger: "blur" }
        ],
        sysUserId: [
          { required: true, message: "负责人id不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "负责人id不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "最新跟进时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("用户等级").then(response => {
      this.levelOptions = response.data;
    });
  },
  methods: {
    /** 查询我的客户列表 */
    getList() {
      this.loading = true;
      listCustomer(this.queryParams).then(response => {
        this.customerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 客户等级字典翻译
    levelFormat(row, column) {
      return this.selectDictLabel(this.levelOptions, row.level);
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
        xiansuoId: null,
        name: null,
        phone: null,
        level: null,
        companyId: null,
        province: null,
        city: null,
        genjinStatus: 0,
        resource: null,
        sysUserId: null,
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
      this.title = "添加我的客户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCustomer(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改我的客户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomer(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustomer(this.form).then(response => {
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
      this.$confirm('是否确认删除我的客户编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCustomer(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有我的客户数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCustomer(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
