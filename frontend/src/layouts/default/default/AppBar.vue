<template>
  <v-app-bar :order="0" density="comfortable" elevation="1">
    <template #prepend>
      <v-app-bar-nav-icon @click="emits('nav-toggle')"></v-app-bar-nav-icon>
    </template>
    <template #title>
      <div id="appbar-title"></div>
    </template>
    <template #append>
      <div>
        <!--For Lacalization -->
        <LocaleSelect></LocaleSelect>
      </div>
      <!-- filter -->

      <div id="appbar-filter" class="pr-1 pr-sm-2 pr-md-4"></div>

      <!-- account menu -->
      <v-btn
        :icon="mdiAccount"
        variant="tonal"
        id="account-menu-activator"
        color="primary"
      ></v-btn>

      <!-- account menu -->
      <v-menu activator="#account-menu-activator" width="200">
        <v-list>
          <v-list-item
            v-for="(item, index) in userAccountMenus"
            :key="`user-account-menu-${index}`"
            :to="item.to"
            @click="item.onClick"
          >
            <v-list-item-title>{{ item.name }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </template>
  </v-app-bar>
</template>

<script lang="ts" setup>
import { mdiAccount, mdiAccountLock, mdiLogout } from '@mdi/js';
import { useRouter } from 'vue-router';
import { routeNames } from '../../router/routes';
import { useAuthStore } from '../../store/auth';
import LocaleSelect from '../../locales/LocaleSelect.vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n({ useScope: 'global' });

const emits = defineEmits(['nav-toggle']);
const authStore = useAuthStore();
const router = useRouter();

const onLogout = () => {
  authStore.clear();
  router.push({ name: routeNames.login }).catch(() => {});
};

const userAccountMenus = [
  { icon: mdiAccount, name: t('Account'), to: undefined, onClick: undefined },
  {
    icon: mdiAccountLock,
    name: t('Change Password'),
    to: { name: routeNames.userChangePassword, params: { id: 'me' } },
    onClick: undefined,
  },
  { icon: mdiLogout, name: t('Logout'), to: undefined, onClick: onLogout },
];
</script>
