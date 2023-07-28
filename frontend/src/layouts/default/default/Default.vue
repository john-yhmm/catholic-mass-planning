<template>
  <v-app>
    <v-theme-provider :theme="appStore.theme">
      <v-layout>
        <DefaultNavigation v-model:drawer="drawer"></DefaultNavigation>
        <DefaultAppBar @nav-toggle="onNavToggle"></DefaultAppBar>
        <DefaultView v-if="isLayoutMounted" />
      </v-layout>
    </v-theme-provider>
    <v-footer class="bg-grey-lighten-1">
      <v-row justify="center" no-gutters>
        <v-col class="text-center" cols="12">
          {{ v + '-' + new Date().getFullYear() }} —
          <strong class="font-weight-thin"
            >Powered by JUDO ERP Solutions</strong
          >
        </v-col>
      </v-row>
    </v-footer>
  </v-app>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import { useAppStore } from '../../store/app';
import DefaultAppBar from './AppBar.vue';
import DefaultNavigation from './Navigation.vue';
import DefaultView from './View.vue';
import { version } from '../../../package.json';

const appStore = useAppStore();
const v = version;

const drawer = ref(true);
const isLayoutMounted = ref(false);

const onNavToggle = () => {
  drawer.value = !drawer.value;
};

// make sure layout is mounted before child views are mounting
onMounted(() => {
  isLayoutMounted.value = true;
});
</script>
