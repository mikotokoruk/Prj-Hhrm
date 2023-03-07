<template>
  <div>
    <el-row>
      <!--  数据面板-->
      <el-col :span="24">
        <div class="num">
          <el-card :body-style="{display:'flex',padding:0}">
            <i class="el-icon-user icon" style="background:#2ec7c9"></i>
            <div class="detail">
              <p class="price">{{ userSize }}</p>
              <p class="desc">用户总数</p>
            </div>
          </el-card>


          <el-card :body-style="{display:'flex',padding:0}">
            <i class="el-icon-picture-outline-round icon" style="background:#ffb980"></i>
            <div class="detail">
              <p class="price">{{ roleSize }}</p>
              <p class="desc">角色总数</p>
            </div>
          </el-card>


          <el-card :body-style="{display:'flex',padding:0}">
            <i class="el-icon-s-cooperation icon" style="background:#2ec7c9"></i>
            <div class="detail">
              <p class="price">{{ menuSize }}</p>
              <p class="desc">菜单总数</p>
            </div>
          </el-card>

        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="6">
        <el-card style="text-align: center;color: #01AAED;font-size: 20px"><span>系统简介</span></el-card>
        <el-collapse accordion>
          <el-collapse-item>
            <template slot="title">
              简介1
            </template>
            <div>1</div>

          </el-collapse-item>
          <el-collapse-item title="简介2">
            <div>mybatis-plus、velocity</div>

          </el-collapse-item>
          <el-collapse-item title="简介3">
            <div>screw框架</div>
          </el-collapse-item>
          <el-collapse-item title="简介4">
            <div>smart-doc</div>

          </el-collapse-item>
        </el-collapse>
      </el-col>
      <el-col :span="18">
        <div style="width:100%;height: 300px" id="firstechaerts"></div>
      </el-col>

    </el-row>


  </div>

</template>

<script>
import * as echarts from 'echarts';

export default {

  name: "Home",
  data() {
    return {
      userSize: 0,
      roleSize: 0,
      menuSize: 0,
      ydata: [],
      xdata: [],


    }
  },
  created() {
    this.request.get("/user").then(res => {
      this.userSize = res.data.length
    })
    this.request.get("/role").then(res => {
      this.roleSize = res.data.length
    })
    this.request.get("/menu").then(res => {
      this.menuSize = res.data.length
    })

  },
  mounted() {
    this.request.get('/echarts/systemparameter').then(res => {
      console.log(res.data)
      this.ydata = res.data.yAxis
      this.xdata = res.data.series
      this.queryData()
    })
  },
  methods: {


    queryData() {

      let myChart = echarts.init(document.getElementById('firstechaerts'));
      var option = {
        title: {
          text: '系统参数'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {},
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          boundaryGap: [0, 0.1]
        },
        yAxis: {
          type: 'category',
          data: this.ydata
        },
        series: [
          {
            name: '数量',
            type: 'bar',
            data: this.xdata
          },

        ]
      };

      option && myChart.setOption(option);
    },

  }

}
</script>

<style lang="less" scoped>


.num {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between; // 贴边
  .icon {
    width: 80px;
    height: 80px;
    font-size: 30px;
    line-height: 80px;
    color: #ffffff;
    text-align: center;
  }

  .detail {
    margin-left: 15px;
    display: flex;
    flex-direction: column;
    justify-content: center;

    .price {
      height: 30px;
      line-height: 30px;
      font-size: 30px;
      margin-bottom: 10px;
    }

    .desc {
      font-size: 14px;
      text-align: center;
      color: #999999;
    }
  }

  .el-card {
    width: 32%;
    margin-bottom: 20px;
  }
}
</style>
