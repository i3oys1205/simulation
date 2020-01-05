<template>
  <div id="app">
    <el-row>
      <router-view name="layoutLeft"></router-view>
      <router-view name="layoutMidle"></router-view>
      <router-view name="layoutRight"></router-view>
    </el-row>
  </div>
</template>

<script>

export default {
  name: 'app',
  components: {
  }
}
</script>

<style>
</style>
