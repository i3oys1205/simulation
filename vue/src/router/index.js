import Vue from 'vue'
import Router from 'vue-router'
import layoutLeft from '@/components/layout/layoutLeft'
import layoutMidle from '@/components/layout/layoutMidle'
import layoutRight from '@/components/layout/layoutRight'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      components:
        {
          layoutLeft : layoutLeft,
          layoutMidle : layoutMidle,
          layoutRight : layoutRight
        }
    }
  ]
})
