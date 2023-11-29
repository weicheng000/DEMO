// Composables
import { createRouter, createWebHistory } from 'vue-router'

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
        component: () => import( '@/views/Home.vue'),
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
  if (to.matched.some(record => record.meta.auth) && to.matched.auth){
    let token = localStorage.getItem('token');
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
  }
  next();
})

export default router
