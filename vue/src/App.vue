<template>
  <div id="app">

  </div>
</template>

<script>
    export default {
        name: 'App',
        mounted() {
        },
        methods: {
            call: async function () {
                const resource = await this.$axios.post('/call');
              console.log(resource);
            }
        },
    }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
