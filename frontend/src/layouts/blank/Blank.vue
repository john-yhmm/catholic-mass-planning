<template>
  <v-app>
    <v-theme-provider :theme="appStore.theme">
      <v-layout>
        <BlankView />
      </v-layout>
    </v-theme-provider>
    <v-footer class="bg-grey-lighten-1">
      <v-row justify="center" no-gutters>
        <v-col class="text-center py-2" cols="12">
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
import { useAppStore } from '../../store/app';
import BlankView from './View.vue';
import { version } from '../../../package.json';

const appStore = useAppStore();
const v = version;
</script>
