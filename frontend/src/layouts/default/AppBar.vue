<template>
  <v-app-bar density="comfortable" elevation="1">
    <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

    <v-toolbar-title>Catholic Mass Planning</v-toolbar-title>
  </v-app-bar>
  <v-navigation-drawer v-model="drawer" temporary>
    <!--  -->
  </v-navigation-drawer>
</template>

<script lang="ts" setup>
import { ref } from "vue";

const drawer = ref(false);
</script>
