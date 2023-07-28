<template>
  <v-container class="fill-height bg-blue-grey-lighten-5" fluid>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="5">
        <LoginForm></LoginForm>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts" setup>
import { defineAsyncComponent } from 'vue';

const LoginForm = defineAsyncComponent(
  () => import('../components/LoginForm.vue')
);
</script>
