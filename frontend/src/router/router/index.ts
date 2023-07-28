import { createRouter, createWebHistory, Router } from 'vue-router';
import { LandingRoute } from '../constants/LandingRoute';
import { Role } from '../constants/Role';
import { useAuthStore } from '../store/auth';
import { routes } from './routes';

const router: Router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();

  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (!authStore.isAuth) {
      next({
        name: 'Login',
        query: { redirect: to.fullPath },
      });
    } else {
      // authed
      const allowedRoles = to.meta?.allowedRoles as Role[];

      if (!allowedRoles || allowedRoles.length == 0) next();
      else {
        if (allowedRoles.includes(authStore.userRole)) next();
        else {
          if (LandingRoute[authStore.userRole]) {
            next({ name: LandingRoute[authStore.userRole] });
          } else {
            next({ path: '/' });
          }
        }
      }
    }
  } else if (to.matched.some((record) => record.meta.guest)) {
    // for login page
    if (!authStore.isAuth) next();
    else {
      if (LandingRoute[authStore.userRole]) {
        next({ name: LandingRoute[authStore.userRole] });
      } else {
        next({ path: '/' });
      }
    }
  } else {
    next();
  }
});

export default router;
