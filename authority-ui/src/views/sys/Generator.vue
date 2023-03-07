<template xmlns:el-form="http://www.w3.org/1999/html">
  <div class="left_Box">
    <el-card class="box-card">
      <div>
        <el-tag type="danger" style="font-size: 10px;margin-bottom: 10px">
          sys_user,sys_menu,sys_user_role,sys_role_menu,sys_role,sys_file 为系统内部表，请命名不要与以上一致!
        </el-tag>
        <br>
        <el-tag type="danger" style="font-size: 10px;margin-bottom: 10px">定义默认值时，如果定义的默认值为字符串类型，请加单引号''！</el-tag>
        <br>
        <el-tag type="danger" style="font-size: 10px">定义富文本时，请将数据类型选择为longtext！</el-tag>
      </div>
    </el-card>
    <el-form :inline="true">
      <el-form-item>
        <el-input type="primary" v-model="table" placeholder="输入数据库表名" @click="addDomain"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="primary" v-model="remark" placeholder="输入数据库表备注" @click="addDomain"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="addDomain">新增数据库表字段</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="danger" @click="submitForm('dynamicValidateForm')">生成前后端数据库代码</el-button>
      </el-form-item>
      <el-form-item>


      </el-form-item>
    </el-form>
    <div>
      <el-form ref="dynamicValidateForm" :inline="true" :model="dynamicValidateForm" class="demo_dynamic"
               label-width="20px">


        <div v-for="(domain, index) in dynamicValidateForm.domains" :key="index" class="dv_hang">
          <div v-if="index==0">
            <el-form-item
                :key="domain.key1"

                :prop="'domains.' + index + '.qsfw'"

            >

              <el-input placeholder="请输入数据字段名" disabled v-model="domain.name" :min="0" :precision="2" :step="0.1"
                        size="small"></el-input>
              <!-- <el-input v-model="domain.qsfw" size="small"></el-input> -->
            </el-form-item>
            <el-form-item
                :key="domain.key"
                :prop="'domains.' + index + '.lx'"

            >

              <el-select v-model="domain.datatype" disabled size="small" placeholder="选择数据类型和长度">
                <el-option label="varchar(64)" value="varchar(64)"></el-option>
                <el-option label="varchar(255)" value="varchar(255)"></el-option>
                <el-option label="int(1)" value="int(1)"></el-option>
                <el-option label="int(5)" value="int(5)"></el-option>
                <el-option label="int(10)" value="int(10)"></el-option>
                <el-option label="bigint(20)" value="bigint(20)"></el-option>
                <el-option label="double(10,2)" value="double(10,2)"></el-option>
                <el-option label="datetime" value="datetime"></el-option>
                <el-option label="text" value="text"></el-option>
                <el-option label="longtext" value="longtext"></el-option>
              </el-select>
            </el-form-item>


            <el-form-item
                :key="domain.key5"
                :prop="'domains.' + index + '.zsxs'"

            >

              <el-input placeholder="输入默认值" disabled v-model="domain.default" size="small"></el-input>

            </el-form-item>
            <el-form-item
                :key="domain.key6"
                :prop="'domains.' + index + '.zsxs'"

            >

              <el-input placeholder="字段注释" disabled v-model="domain.comment" :min="0" :precision="2" :step="0.1"
                        size="small"></el-input>


            </el-form-item>
            <el-form-item
                :key="domain.key4"
                :prop="'domains.' + index + '.lx'"
                width="100px"

            >

              <el-select v-model="domain.ismust" size="small" disabled placeholder="是否为必选项">
                <el-option label="必选项" value="1"></el-option>
                <el-option label="非必选项" value="0"></el-option>

              </el-select>
            </el-form-item>
            <el-form-item
                :key="domain.key3"
                :prop="'domains.' + index + '.lx'"

            >

              <el-select v-model="domain.formtype" disabled size="small" placeholder="选择表单类型">
                <el-option label="普通表单" value="0"></el-option>
                <el-option label="图片" value="1"></el-option>
                <el-option label="文件" value="2"></el-option>
                <el-option label="时间选择器" value="3"></el-option>
                <el-option label="日期选择器" value="4"></el-option>
                <el-option label="富文本" value="5"></el-option>
              </el-select>
            </el-form-item>
            <el-button class="delhang_class" v-if="index!=0" size="small" type="text"
                       @click.prevent="removeDomain(domain)">
              <el-icon class="el-icon-delete"></el-icon>
              删除行
            </el-button>
          </div>
          <div v-else>
            <el-form-item
                :key="domain.key1"

                :prop="'domains.' + index + '.name'"
                :rules="{
                    required: true,
                    message: '必选项不能为空',
                    trigger: 'blur',
                  }"


            >

              <el-input placeholder="请输入数据字段名" v-model="domain.name" :min="0" :precision="2" :step="0.1"
                        size="small"></el-input>
              <!-- <el-input v-model="domain.qsfw" size="small"></el-input> -->
            </el-form-item>
            <el-form-item
                :key="domain.key"
                :prop="'domains.' + index + '.datatype'"
                :rules="{
                    required: true,
                    message: '必选项不能为空',
                    trigger: 'change',
                  }"
            >

              <el-select v-model="domain.datatype" size="small" placeholder="选择数据类型和长度">
                <el-option label="varchar(64)" value="varchar(64)"></el-option>
                <el-option label="varchar(255)" value="varchar(255)"></el-option>
                <el-option label="int(1)" value="int(1)"></el-option>
                <el-option label="int(5)" value="int(5)"></el-option>
                <el-option label="int(10)" value="int(10)"></el-option>
                <el-option label="bigint(20)" value="bigint(20)"></el-option>
                <el-option label="double(10,2)" value="double(10,2)"></el-option>
                <el-option label="datetime" value="datetime"></el-option>
                <el-option label="text" value="text"></el-option>
                <el-option label="longtext" value="longtext"></el-option>
              </el-select>
            </el-form-item>


            <el-form-item
                :key="domain.key5"
                :prop="'domains.' + index + '.zsxs'"

            >

              <el-input placeholder="输入默认值" v-model="domain.default" size="small"></el-input>

            </el-form-item>
            <el-form-item
                :key="domain.key6"
                :prop="'domains.' + index + '.zsxs'"

            >

              <el-input placeholder="字段注释" v-model="domain.comment" :min="0" :precision="2" :step="0.1"
                        size="small"></el-input>


            </el-form-item>
            <el-form-item
                :key="domain.key4"
                :prop="'domains.' + index + '.ismust'"
                width="100px"
                :rules="{
                    required: true,
                    message: '必选项不能为空',
                    trigger: 'change',
                  }"
            >

              <el-select v-model="domain.ismust" size="small" placeholder="是否为必选项">
                <el-option label="必选项" value="1"></el-option>
                <el-option label="非必选项" value="0"></el-option>

              </el-select>
            </el-form-item>
            <el-form-item
                :key="domain.key3"
                :prop="'domains.' + index + '.formtype'"
                :rules="{
                    required: true,
                    message: '必选项不能为空',
                    trigger: 'change',
                  }"
            >

              <el-select v-model="domain.formtype" size="small" placeholder="选择表单类型">
                <el-option label="普通表单" value="0"></el-option>
                <el-option label="图片" value="1"></el-option>
                <el-option label="文件" value="2"></el-option>
                <el-option label="时间选择器" value="3"></el-option>
                <el-option label="日期选择器" value="4"></el-option>
                <el-option label="富文本" value="5"></el-option>
              </el-select>
            </el-form-item>
            <el-button class="delhang_class" v-if="index!=0" size="small" type="text"
                       @click.prevent="removeDomain(domain)">
              <el-icon class="el-icon-delete"></el-icon>
              删除
            </el-button>

          </div>
        </div>
      </el-form>
      　　
    </div>
  </div>
</template>
<script>

import router from "@/router";
import {Loading} from "element-ui";

export default {
  name: 'dynamicForm',

  data() {
    return {

      table: '',
      remark: '',
      tableData: {
        table: '',
        remark: '',
        sql: '',
        domanins: []
      },


      dynamicValidateForm: {

        domains: [{
          name: 'id',//字段名称
          datatype: 'bigint(20)',//数据类型长度
          formtype: "0",//表单类型
          ismust: "1",//是否为必选项
          default: '',//默认值
          comment: '序号'//字段注释
        }]
      },
    }
  },
  methods: {
    removeDomain(item) {//删除行
      var index = this.dynamicValidateForm.domains.indexOf(item)
      if (index !== -1) {
        this.dynamicValidateForm.domains.splice(index, 1)
      }
      this.$message.success('删除成功！ ')
    },
    addDomain() {//新增行
      this.dynamicValidateForm.domains.push({
        name: '',//字段名称
        datatype: 'varchar(64)',//数据类型长度
        formtype: '0',//表单类型
        ismust: '1',//是否为必选项
        default: null,//默认值
        comment: '',//字段注释
        key3: Date.now() + 1,
        key4: Date.now() + 2,
        key5: Date.now() + 3,
        key6: Date.now() + 4
      });
    },
    submitForm(formName) {//保存按钮

      if (this.table == '' || this.remark == '') {
        this.$message.error('表名称和备注不能为空')
        return false
      }

      this.$refs[formName].validate((valid) => {
        if (valid) {

          let table = this.dynamicValidateForm.domains

          //赋值给到tableData
          let sql = ""

          sql += "CREATE TABLE `" + this.table + "` (\n";
          sql += "`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序列号',\n";
          table.forEach(column => {
            if (column.name != 'id') {
              column.default = column.default ? column.default : null;
              sql += "`" + column.name + "` " + column.datatype + " COLLATE utf8mb4_unicode_ci DEFAULT " + column.default + " COMMENT '" + column.comment + "',\n"

            }
          })
          sql += "PRIMARY KEY (`id`) USING BTREE \n"
          sql += ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='" + this.remark + "';\n\n\n"


          this.tableData.table = this.table
          this.tableData.sql = sql
          this.tableData.remark = this.remark
          this.createform(table).then(val => {
            let loadingInstance = Loading.service({fullscreen: true, text: "正在构建代码，请您不要进行其他操作！"});
            this.tableData.domanins = val
            console.log(this.tableData.domanins)
            this.request.post("/generator", this.tableData).then(res => {

              if (res.code == 200) {
                this.$message.info("创建成功，请重启springboot项目！")
                loadingInstance.close()
                router.go(0)
              } else {

              }

            })
          })
          //发送请求创建前后端代码
          // this.$message.success(sql)
          // let loadingInstance = Loading.service({fullscreen: true, text: "正在构建代码，请您不要进行其他操作！"});
          // this.request.post("/generator", this.tableData).then(res => {
          //
          //   if (res.code == 200) {
          //     this.$message.info("创建成功，请重启springboot项目！")
          //     loadingInstance.close()
          //     router.go(0)
          //   } else {
          //     this.tableData.domanins={}
          //   }
          //
          // })


        } else {
          console.log('error submit!!');

          return false;
        }
      });
    },
    createform(table) {
      let Arr = []
      table.forEach(item => {
        if (item.name != "id") {
          Arr.push({
            name: item.name,
            datatype: item.datatype,
            formtype: item.formtype,
            ismust: item.ismust,
            comment: item.comment
          })
        }
      })

      return Promise.resolve(Arr)
    }

  },
}
</script>
<style>
.box-card {
  width: 100%;
  margin-bottom: 10px;
}
</style>