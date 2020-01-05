<template>
  <el-col :span="5">
    <div class="grid-content bg-purple">
      <el-menu :default-openeds="['1']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-folder-opened"></i>파일 리스트</template>
          <el-menu-item-group>
            <template slot="title">파일</template>

            <el-button class="w-97" type="success" v-on:click="newFile">새로 생성하기</el-button>
            <el-menu-item v-if="files.length===0"> 파일이 없습니다.</el-menu-item>
            <el-menu-item class="el-icon-tickets w-100"
                          v-for="(item,index) in files"
                          v-bind:key="index"
                          v-bind:index="`${index}-1`"
                          v-on:click="active = index"
            >
              <span v-if="edit.index !== index">
                {{item.name}}
              </span>

              <el-input placeholder="파일명 입력"
                        v-if="active === index && edit.flag && edit.index === index"
                        v-model="item.name"
                        @keyup.enter.native="fileNameEdit(item.name)"
              ></el-input>
              <i class="el-icon-edit file_icon"
                 v-if="active === index && !edit.flag"
                 v-on:click="(edit.index = index , edit.flag = true , input = item.name)"
              ></i>
              <i class="el-icon-delete file_icon"
                 v-if="active === index"
                 v-on:click="centerDialogVisible = true"
              ></i>

              <el-dialog title="경고" :visible.sync="centerDialogVisible" width="30%" center>
                <p>해당 파일을 정말 삭제 하시겠습니까?</p>
                <p>삭제된 파일은 복구 할수 없습니다.</p>
                <span slot="footer" class="dialog-footer">
                  <el-button @click="centerDialogVisible = false">취소</el-button>
                  <el-button type="Danger" @click="(centerDialogVisible = false , deleteFile())"
                  >
                    삭제
                  </el-button>
                </span>
              </el-dialog>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </div>
  </el-col>
</template>

<script>
    export default {
        name: "layoutLeft",
        data() {
            return {
                files: [],
                edit: {
                    index: '',
                    flag: false,
                },
                active: null,
                input: '',
                centerDialogVisible: false,
            }
        },
        watch: {
            active() {
                this.edit.index = '';
                this.edit.flag = false;
            }
        },
        methods: {
            newFile() {
                this.files.push({
                    name: "새 파일 " + this.files.length
                })
            },
            fileNameEdit(fileName) {
                if(!this.duplicationFileName(fileName)){
                    return;
                }
                this.edit.index = '';
                this.edit.flag = false;
            },
            deleteFile() {
                this.files.splice(this.active, 1);
                this.active = '';
            },
            duplicationFileName(fileName){
                for (const [key,value] of Object.entries(this.files)) {
                    if(Number(key) === Number(this.active)){
                        continue;
                    }
                    if (value.name.includes(fileName)){
                        alert("같은 파일명이 있습니다.");
                        return false;
                    }
                }
                return true;
            }
        }
    }
</script>

<style scoped>
  .w-100 {
    width: 100%;
  }

  .w-97 {
    width: 97%;
  }

  .file_icon {
    color: black !important;
    vertical-align: inherit !important;
    font-size: 15px !important;
  }
</style>
