<template>

  <div>
    <el-form :inline="true">


      <el-form-item>
        <el-button type="primary" @click="dialogVisible = true">新增目录或者菜单</el-button>
      </el-form-item>

    </el-form>
    <el-table
        :data="tableData"
        :header-cell-class-name="'header'"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
        border
        default-expand-all
        row-key="id"
        style="width: 100%;margin-bottom: 20px;">
      <el-table-column
          label="菜单名称"
          prop="title"

          width="180">
      </el-table-column>
      <el-table-column
          align="center"
          label="图标"
          prop="icon"
          width="50">
        <template slot-scope="scope">

          <i :class="scope.row.icon" style="font-size: 25px;color: orange"></i>
        </template>
      </el-table-column>

      <el-table-column
          align="center"
          label="权限标识"
          prop="perms"
          width="180">
      </el-table-column>
      <el-table-column
          align="center"
          label="访问路由"
          prop="path">
      </el-table-column>
      <el-table-column
          align="center"
          label="组件名称"
          prop="component">
      </el-table-column>


      <el-table-column
          align="center"
          label="排序"
          prop="orderNum"
          width="50">
      </el-table-column>
      <el-table-column
          align="center"
          label="当前状态"
          prop="statu">
        <template slot-scope="scope">
          <el-button v-if="scope.row.statu==0" plain type="success">正常</el-button>
          <el-button v-if="scope.row.statu==1" plain type="danger">禁用</el-button>
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="类型"
          prop="path">
        <template slot-scope="scope">
          <el-button v-if="scope.row.path==''" plain type="success">目录</el-button>
          <el-button v-else plain type="danger">菜单</el-button>
        </template>
      </el-table-column>
      <el-table-column

          align="center"
          label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handelEdit(scope.row)">编辑</el-button>
          &nbsp;
          <template>
            <el-popconfirm title="菜单数据可能会产生不可知的bug，确定删除吗？" @confirm="del(scope.row.id)">
              <el-button slot="reference" type="danger">删除</el-button>
            </el-popconfirm>
          </template>
        </template>

      </el-table-column>


    </el-table>


    <el-dialog :visible.sync="dialogVisible" title="添加菜单或者目录" v-bind="$attrs" @close="onClose" @open="onOpen"
               v-on="$listeners">
      <el-form ref="editForm" :model="editForm" :rules="ruleForm" label-width="100px" size="medium">
        <el-form-item label="类型" prop="type">
          <el-radio-group v-model="editForm.type" size="medium">
            <el-radio-button v-for="(item, index) in typeOptions" :key="index+1" :disabled="item.disabled"
                             :label="item.value">{{ item.label }}
            </el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上级菜单" prop="parentId">
          <el-select v-model="editForm.parentId" :style="{width: '100%'}" clearable placeholder="请选择上级菜单">
            <el-option v-for="(item, index) in parentIdOptions" :key="index+1" :disabled="item.disabled"
                       :label="item.label" :value="item.value"></el-option>

            <el-option v-for="(item, index) in tableData" v-if="item.parentId=='0'&&item.path.length==0" :key="index+2"
                       :disabled="item.disabled"
                       :label="item.title" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="editForm.title" :maxlength="10" :style="{width: '100%'}" clearable
                    placeholder="请输入菜单或者目录标题" prefix-icon='el-icon-switch-button' show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="路由地址" prop="path">
          <el-input v-model="editForm.path" :maxlength="30" :style="{width: '100%'}" clearable
                    placeholder="请输入路由地址" prefix-icon='el-icon-s-promotion'></el-input>
        </el-form-item>
        <el-form-item label="组件地址" prop="component">
          <el-input v-model="editForm.component" :style="{width: '100%'}" clearable
                    placeholder="请输入组件地址" prefix-icon='el-icon-receiving'></el-input>
        </el-form-item>
        <el-form-item label="权限标识" prop="perms">
          <el-input v-model="editForm.perms" :maxlength="10" :style="{width: '100%'}" clearable
                    placeholder="请输入权限标识" prefix-icon='el-icon-files'></el-input>
        </el-form-item>
        <el-form-item label="排序号" prop="orderNum">
          <el-input-number v-model="editForm.orderNum" :max='1000' :min='1' :step='1' placeholder="排序号">
          </el-input-number>
        </el-form-item>
        <el-form-item label="图标" label-width="100px">
          <el-select v-model="editForm.icon" clearable placeholder="请选择" style="width: 100%">
            <el-option v-for="item in options" :key="item" :label="item" :value="item">
              <i class=""></i>
              <i :class="item"/> {{ item }}
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="当前状态" prop="statu">
          <el-radio-group v-model="editForm.statu" size="medium">
            <el-radio-button v-for="(item, index) in statuOptions" :key="index" :disabled="item.disabled"
                             :label="item.value">{{ item.label }}
            </el-radio-button>
          </el-radio-group>
        </el-form-item>

      </el-form>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "Menu",

  data() {
    return {
      options: [
        "el-icon-platform-eleme",
        "el-icon-eleme",
        "el-icon-delete-solid",
        "el-icon-delete",
        "el-icon-s-tools",
        "el-icon-setting",
        "el-icon-user-solid",
        "el-icon-user",
        "el-icon-phone",
        "el-icon-phone-outline",
        "el-icon-more",
        "el-icon-more-outline",
        "el-icon-star-on",
        "el-icon-star-off",
        "el-icon-s-goods",
        "el-icon-goods",
        "el-icon-warning",
        "el-icon-warning-outline",
        "el-icon-question",
        "el-icon-info",
        "el-icon-remove",
        "el-icon-circle-plus",
        "el-icon-success",
        "el-icon-error",
        "el-icon-zoom-in",
        "el-icon-zoom-out",
        "el-icon-remove-outline",
        "el-icon-circle-plus-outline",
        "el-icon-circle-check",
        "el-icon-circle-close",
        "el-icon-s-help",
        "el-icon-help",
        "el-icon-minus",
        "el-icon-plus",
        "el-icon-check",
        "el-icon-close",
        "el-icon-picture",
        "el-icon-picture-outline",
        "el-icon-picture-outline-round",
        "el-icon-upload",
        "el-icon-upload2",
        "el-icon-download",
        "el-icon-camera-solid",
        "el-icon-camera",
        "el-icon-video-camera-solid",
        "el-icon-video-camera",
        "el-icon-message-solid",
        "el-icon-bell",
        "el-icon-s-cooperation",
        "el-icon-s-order",
        "el-icon-s-platform",
        "el-icon-s-fold",
        "el-icon-s-unfold",
        "el-icon-s-operation",
        "el-icon-s-promotion",
        "el-icon-s-home",
        "el-icon-s-release",
        "el-icon-s-ticket",
        "el-icon-s-management",
        "el-icon-s-open",
        "el-icon-s-shop",
        "el-icon-s-marketing",
        "el-icon-s-flag",
        "el-icon-s-comment",
        "el-icon-s-finance",
        "el-icon-s-claim",
        "el-icon-s-custom",
        "el-icon-s-opportunity",
        "el-icon-s-data",
        "el-icon-s-check",
        "el-icon-s-grid",
        "el-icon-menu",
        "el-icon-share",
        "el-icon-d-caret",
        "el-icon-caret-left",
        "el-icon-caret-right",
        "el-icon-caret-bottom",
        "el-icon-caret-top",
        "el-icon-bottom-left",
        "el-icon-bottom-right",
        "el-icon-back",
        "el-icon-right",
        "el-icon-bottom",
        "el-icon-top",
        "el-icon-top-left",
        "el-icon-top-right",
        "el-icon-arrow-left",
        "el-icon-arrow-right",
        "el-icon-arrow-down",
        "el-icon-arrow-up",
        "el-icon-d-arrow-left",
        "el-icon-d-arrow-right",
        "el-icon-video-pause",
        "el-icon-video-play",
        "el-icon-refresh",
        "el-icon-refresh-right",
        "el-icon-refresh-left",
        "el-icon-finished",
        "el-icon-sort",
        "el-icon-sort-up",
        "el-icon-sort-down",
        "el-icon-rank",
        "el-icon-loading",
        "el-icon-view",
        "el-icon-c-scale-to-original",
        "el-icon-date",
        "el-icon-edit",
        "el-icon-edit-outline",
        "el-icon-folder",
        "el-icon-folder-opened",
        "el-icon-folder-add",
        "el-icon-folder-remove",
        "el-icon-folder-delete",
        "el-icon-folder-checked",
        "el-icon-tickets",
        "el-icon-document-remove",
        "el-icon-document-delete",
        "el-icon-document-copy",
        "el-icon-document-checked",
        "el-icon-document",
        "el-icon-document-add",
        "el-icon-printer",
        "el-icon-paperclip",
        "el-icon-takeaway-box",
        "el-icon-search",
        "el-icon-monitor",
        "el-icon-attract",
        "el-icon-mobile",
        "el-icon-scissors",
        "el-icon-umbrella",
        "el-icon-headset",
        "el-icon-brush",
        "el-icon-mouse",
        "el-icon-coordinate",
        "el-icon-magic-stick",
        "el-icon-reading",
        "el-icon-data-line",
        "el-icon-data-board",
        "el-icon-pie-chart",
        "el-icon-data-analysis",
        "el-icon-collection-tag",
        "el-icon-film",
        "el-icon-suitcase",
        "el-icon-suitcase-1",
        "el-icon-receiving",
        "el-icon-collection",
        "el-icon-files",
        "el-icon-notebook-1",
        "el-icon-notebook-2",
        "el-icon-toilet-paper",
        "el-icon-office-building",
        "el-icon-school",
        "el-icon-table-lamp",
        "el-icon-house",
        "el-icon-no-smoking",
        "el-icon-smoking",
        "el-icon-shopping-cart-full",
        "el-icon-shopping-cart-1",
        "el-icon-shopping-cart-2",
        "el-icon-shopping-bag-1",
        "el-icon-shopping-bag-2",
        "el-icon-sold-out",
        "el-icon-sell",
        "el-icon-present",
        "el-icon-box",
        "el-icon-bank-card",
        "el-icon-money",
        "el-icon-coin",
        "el-icon-wallet",
        "el-icon-discount",
        "el-icon-price-tag",
        "el-icon-news",
        "el-icon-guide",
        "el-icon-male",
        "el-icon-female",
        "el-icon-thumb",
        "el-icon-cpu",
        "el-icon-link",
        "el-icon-connection",
        "el-icon-open",
        "el-icon-turn-off",
        "el-icon-set-up",
        "el-icon-chat-round",
        "el-icon-chat-line-round",
        "el-icon-chat-square",
        "el-icon-chat-dot-round",
        "el-icon-chat-dot-square",
        "el-icon-chat-line-square",
        "el-icon-message",
        "el-icon-postcard",
        "el-icon-position",
        "el-icon-turn-off-microphone",
        "el-icon-microphone",
        "el-icon-close-notification",
        "el-icon-bangzhu",
        "el-icon-time",
        "el-icon-odometer",
        "el-icon-crop",
        "el-icon-aim",
        "el-icon-switch-button",
        "el-icon-full-screen",
        "el-icon-copy-document",
        "el-icon-mic",
        "el-icon-stopwatch",
        "el-icon-medal-1",
        "el-icon-medal",
        "el-icon-trophy",
        "el-icon-trophy-1",
        "el-icon-first-aid-kit",
        "el-icon-discover",
        "el-icon-place",
        "el-icon-location",
        "el-icon-location-outline",
        "el-icon-location-information",
        "el-icon-add-location",
        "el-icon-delete-location",
        "el-icon-map-location",
        "el-icon-alarm-clock",
        "el-icon-timer",
        "el-icon-watch-1",
        "el-icon-watch",
        "el-icon-lock",
        "el-icon-unlock",
        "el-icon-key",
        "el-icon-service",
        "el-icon-mobile-phone",
        "el-icon-bicycle",
        "el-icon-truck",
        "el-icon-ship",
        "el-icon-basketball",
        "el-icon-football",
        "el-icon-soccer",
        "el-icon-baseball",
        "el-icon-wind-power",
        "el-icon-light-rain",
        "el-icon-lightning",
        "el-icon-heavy-rain",
        "el-icon-sunrise",
        "el-icon-sunrise-1",
        "el-icon-sunset",
        "el-icon-sunny",
        "el-icon-cloudy",
        "el-icon-partly-cloudy",
        "el-icon-cloudy-and-sunny",
        "el-icon-moon",
        "el-icon-moon-night",
        "el-icon-dish",
        "el-icon-dish-1",
        "el-icon-food",
        "el-icon-chicken",
        "el-icon-fork-spoon",
        "el-icon-knife-fork",
        "el-icon-burger",
        "el-icon-tableware",
        "el-icon-sugar",
        "el-icon-dessert",
        "el-icon-ice-cream",
        "el-icon-hot-water",
        "el-icon-water-cup",
        "el-icon-coffee-cup",
        "el-icon-cold-drink",
        "el-icon-goblet",
        "el-icon-goblet-full",
        "el-icon-goblet-square",
        "el-icon-goblet-square-full",
        "el-icon-refrigerator",
        "el-icon-grape",
        "el-icon-watermelon",
        "el-icon-cherry",
        "el-icon-apple",
        "el-icon-pear",
        "el-icon-orange",
        "el-icon-coffee",
        "el-icon-ice-tea",
        "el-icon-ice-drink",
        "el-icon-milk-tea",
        "el-icon-potato-strips",
        "el-icon-lollipop",
        "el-icon-ice-cream-square",
        "el-icon-ice-cream-round"
      ],
      dialogVisible: false,
      tableData: [],
      editForm: {
        title: '',
        path: '',
        component: '',
        perms: '',
        orderNum: 1,
        type:0,
        statu: 0,
        parentId: 0,
      },
      ruleForm: {
        title: [{
          required: true,
          message: '请输入菜单或者目录标题',
          trigger: 'blur'
        }],
        icon: [{
          required: true,
          message: '请选择图标',
          trigger: 'blur'
        }],
        path: [],
        component: [],
        perms: [{
          required: true,
          message: '请输入权限标识',
          trigger: 'blur'
        }],
        orderNum: [{
          required: true,
          message: '排序号',
          trigger: 'blur'
        }],
        type: [{
          required: true,
          message: '类型不能为空',
          trigger: 'change'
        }],
        statu: [{
          required: true,
          message: '当前状态不能为空',
          trigger: 'change'
        }],
        parentId: [],
      },
      typeOptions: [{
        "label": "目录",
        "value": 0
      }, {
        "label": "菜单",
        "value": 1
      }],
      statuOptions: [{
        "label": "正常",
        "value": 0
      }, {
        "label": "禁用",
        "value": 1
      }],
      parentIdOptions: [{
        "label": "根目录",
        "value": 0
      }],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/menu/list").then(res => {
        this.tableData = res.data


      })

    },
    onOpen() {
      this.dialogVisible = true
    },
    handelEdit(row) {
      this.editForm = JSON.parse(JSON.stringify(row))
      if(row.path==null||row.path==''){
        this.editForm.type=0
      }else{
        this.editForm.type=1
      }
      this.dialogVisible = true
    },
    onClose() {
      this.$refs['editForm'].resetFields()
    },
    close() {
      this.onClose()
      this.dialogVisible = false
    },
    handelConfirm() {
      this.$refs['editForm'].validate(valid => {

        if (!valid) return

        if(this.editForm.type==0){

          this.$alert('您当前选择类型为菜单，路由和组件信息将会清除，确认吗？', '重要提示', {
            confirmButtonText: '确定',
            callback:()=>{

                this.editForm.path = ''
                this.editForm.component = ''
                this.editForm.parentId = 0
              this.request.post("/menu", this.editForm).then(res => {

                this.$message.success('操作成功')
                this.load()
                this.close()
              })
            }
          });

        }
        // if (this.editForm.type == 1) {
        //   this.editForm.path = ''
        //   this.editForm.component = ''
        //   this.editForm.parentId = 0
        //   this.request.post("/menu", this.editForm).then(res => {
        //
        //     this.$message.success('操作成功')
        //     this.load()
        //     this.close()
        //   })
        // }
        if (this.editForm.type == 1) {
          if (this.editForm.path.length == 0 ||
              this.editForm.component.length == 0) {
            this.$message.error('菜单的路由和组件不能为空')
            return;
          }
          this.request.post("/menu", this.editForm).then(res => {

            this.$message.success('操作成功')
            this.load()
            this.close()
          })
        }



      })
    },
    del(id) {
      this.request.delete("/menu/" + id).then(res => {
        this.$message.success('删除成功')
        this.load()
      })
    }
  },
}
</script>

<style scoped>
.header {
  background-color: #B3C0D1 !important;
}
</style>
