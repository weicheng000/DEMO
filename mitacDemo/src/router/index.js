// Composables
import { createRouter, createWebHistory } from 'vue-router'
import store from '@/store'

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '',
        name: 'Login',
        component: () => import('@/views/Login.vue'),
        meta: {
          auth: false
        }
      },
    ],
  },
  {
    path: '/table',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '',
        name: 'Table',
        component: () => import( '@/views/Home.vue')
      },
    ],
    meta: {
      auth: true
    }
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})
// 路由攔截器
router.beforeEach(async (to, from, next) => {
  if (to.matched.some(record => record.meta.auth)){
    let token = store.getters.getToken;
    if (token){
      next();
    } else {
      next({
        path: "/",
        query: {
          redirect: to.fullPath
        }
      })
    }
  } else next();
})

export default router
