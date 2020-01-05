<template>
  <el-col :span="8" v-if="active !== '' ">
    <el-form ref="form" label-width="120px">
      <div class="grid-content bg-purple-light">
        <el-form-item label="자동화 타입">
          <el-button type="text">SELENIUM</el-button>
          <el-button type="text" disabled>GUITAR</el-button>
        </el-form-item>
      </div>
      <div class="grid-content bg-purple-light">
        <el-form-item label="행동 설정">
          <el-radio-group v-model="actionRadio">
            <el-radio-button label="click"></el-radio-button>
            <el-radio-button label="input"></el-radio-button>
            <el-radio-button label="wait"></el-radio-button>
            <el-radio-button label="url"></el-radio-button>
          </el-radio-group>
          <el-button type="success f-r"
                     v-if="actionRadio === ''"
                     v-on:click.stop="setSave">저장
          </el-button>
          <el-button type="success f-r"
                     v-if="actionRadio"
                     v-on:click.stop="setAction">추가
          </el-button>
        </el-form-item>

        <el-form-item label="수신 데이터"
                      v-if="actionRadio === 'click' || actionRadio === 'input'
                          ||actionRadio==='wait' || actionRadio==='url'">
          <el-input
            placeholder=" 수신 데이터를 입력하세요"
            v-model="actionData">
          </el-input>

        </el-form-item>
        <el-form-item label="XPath"
                      v-if="actionRadio === 'click' || actionRadio === 'input'">
          <el-input
            placeholder="Xpath를 입력하세요"
            v-model="actionXpath">
          </el-input>
        </el-form-item>
        <el-form-item label="입력"
                      v-if="actionRadio === 'input'">
          <el-input
            placeholder="데이터를 입력하세요"
            v-model="actionInput">
          </el-input>
        </el-form-item>
        <el-form-item label="대기시간"
                      v-if="actionRadio === 'wait'">
          <el-input
            placeholder="대기시간 을 입력하세요"
            v-model="actionInput">
          </el-input>
        </el-form-item>
        <el-form-item label="URL"
                      v-if="actionRadio === 'url'">
          <el-input
            placeholder="이동할 URL 을 입력하세요"
            v-model="actionInput">
          </el-input>
        </el-form-item>
      </div>
    </el-form>

    <el-tree
      :data="action"
      node-key="id"
      default-expand-all
      draggable
      :allow-drop="allowDrop"
      :render-content="renderContent">
    </el-tree>

  </el-col>
</template>

<script>
    import {mapState, mapActions} from 'vuex'

    export default {
        name: "layoutMidle",
        computed: {
            ...mapState(['active'])
        },
        data() {
            return {
                actionData: '',
                actionRadio: '',
                actionInput: '',
                actionXpath: '',
                action: [],
            }
        },
        watch: {
            active: {
                deep: true,
                handler() {
                    this.action.splice(0);
                }
            }
        },
        methods: {
            setSave(){
                if (this.action.length === 0){
                    this.$EventBus.$emit('msg', '입력된 자동화 패턴이 없습니다.');
                    return;
                }
            },
            setAction() {
                this.action.push({
                    data : this.actionData,
                    type : this.actionRadio,
                    input: this.actionInput,
                    path : this.actionXpath,
                    label: this.actionRadio,
                })
                this.actionData  = '';
                this.actionRadio = '';
                this.actionInput = '';
                this.actionXpath = '';
                this.actionRadio = '';
            },
            allowDrop(draggingNode, dropNode, type) {
                return type !== 'inner';

            },
            renderContent(h, {node, data, store}) {
                return (
                    < span >
                    < span > {node.label} < /span>
                    < span style = "margin-left:20px;"> {data.data} < /span>
                    < span style = "margin-left:20px;" >
                      < el-button size = "mini" type = "text" on-click = {()=>this.remove(node, data)}>Delete < /el-button>
                    < /span>
                    < /span>);
            },

            remove(node, data) {
                const parent = node.parent;
                const children = parent.data.children || parent.data;
                const index = children.findIndex(d => d.id === data.id);
                children.splice(index, 1);
            },
        }
    }
</script>

<style scoped>
  .f-r {
    float: right;
  }
</style>
