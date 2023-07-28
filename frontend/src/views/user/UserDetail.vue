<template>
  <Detail
    v-bind="{
      title: t('Users'),
      loading: status == ApiStatus.LOADING,
      error: status == ApiStatus.ERROR,
      message: status == ApiStatus.ERROR && error.message,
      formValid,
      breadcrumbs,
      actions,
    }"
  >
    <template #form>
      <v-form
        v-model="formValid"
        ref="detailFormRef"
        @submit.prevent="
          () => {
            // disable enter triggers on inputs
          }
        "
      >
        <v-container>
          <v-row>
            <v-col cols="12" md="4">
              <v-text-field
                name="username"
                v-model="userModel.username"
                :rules="[
                  rules.required,
                  rules.minLength(5),
                  rules.maxLength(50),
                ]"
                :label="t('Username')"
                :readonly="!!userModel.id"
              ></v-text-field>
            </v-col>
            <v-col cols="12" md="4">
              <v-select
                name="roleName"
                :items="roleList"
                v-model="userModel.roleName"
                :label="t('Role')"
                :rules="[rules.required]"
                @update:modelValue="verifyType"
                :readonly="!!userModel.id"
              ></v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12" md="4" v-if="companyFlag">
              <CompanyPicker
                v-model:id="userModel.companyId"
                v-model:name="userModel.companyName"
                :label="t('Company')"
                :params="{ status: Status.ACTIVE }"
                :rules="[() => !!userModel.companyId || 'required']"
              ></CompanyPicker>
            </v-col>

            <v-col
              cols="12"
              md="4"
              v-if="companyFlag && Role.CLIENT_ADMIN != userModel.roleName"
            >
              <project-picker
                :label="t('PROJECT')"
                v-model:id="userModel.projectId"
                v-model:name="userModel.projectName"
                :company-id="userModel.companyId"
                :params="{ status: Status.ACTIVE }"
                :rules="[() => !!userModel.projectId || 'required']"
                :readonly="!!userModel.id"
              ></project-picker>
            </v-col>
          </v-row>
          <v-row v-if="!userModel.id">
            <v-col cols="12" md="4">
              <v-text-field
                name="password"
                v-model="userModel.password"
                :rules="[rules.required, rules.minLength(8)]"
                :label="t('Password')"
                :type="showPassword ? 'text' : 'password'"
                :append-inner-icon="showPassword ? mdiEye : mdiEyeOff"
                @click:append-inner="showPassword = !showPassword"
              ></v-text-field>
            </v-col>
            <v-col cols="12" md="4">
              <v-text-field
                name="confirmPassword"
                v-model="userModel.confirmPassword"
                :rules="[
                  rules.required,
                  (v) => v == userModel.password || 'Password are not match',
                ]"
                :label="t('Confirm Password')"
                type="password"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row v-if="userModel.id">
            <v-col cols="12" md="4">
              <v-btn :to="goChangePassword()" color="primary" variant="tonal">
                {{ t('Change Password') }}
              </v-btn>
            </v-col>
            <v-col cols="12" md="4" v-if="userModel.id">
              <v-switch
                name="status"
                :label="userModel.status"
                v-model="userModel.status"
                :true-value="Status.ACTIVE"
                :false-value="Status.INACTIVE"
              ></v-switch>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </template>
  </Detail>
</template>

<script lang="ts" setup>
// working example with vue Composition api

import { ref, onMounted, computed } from 'vue';
import Detail from '../../layouts/default/Detail.vue';
import { routeNames } from '../../router/routes';
import { required, minLength, maxLength } from '../../utils/validations';
import useApi, { ApiStatus } from '../../api';
import { userApiResource } from '../../api/resources/userResource';
import { useRoute, useRouter } from 'vue-router';
import { Role } from '../../constants/Role';
import { Status } from '../../constants/Status';
import { mdiContentSave, mdiArrowLeft, mdiEyeOff, mdiEye } from '@mdi/js';
import { User, UserModel } from '../../models/UserModel';
import { ActionButton } from '../../interfaces/ActionButton';
import CompanyPicker from '../../components/company/CompanyPicker.vue';
import ProjectPicker from '../../components/project/ProjectPicker.vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n({ useScope: 'global' });

const route = useRoute();
const router = useRouter();
const formValid = ref(true);
const detailFormRef = ref<null | any>(null);
const companyFlag = ref(false);
const userModel = ref<User>(UserModel());
const rules = {
  required,
  minLength,
  maxLength,
};
const showPassword = ref(false);

const roleList = Object.keys(Role).map((i) => ({ title: i, value: i }));

const { call, response, error, status } = useApi();

const getDetail = async (id: any) => {
  await call(userApiResource.getById, null, { id });

  if (status.value == ApiStatus.SUCCESS) {
    userModel.value = response.value?.data as User;
    userModel.value.roleName = userModel.value.roleName || '';
  }
  verifyType();
};

const onSave = async () => {
  const { valid } = await detailFormRef.value.validate();
  if (!valid) return;

  let apiUrl = userApiResource.save;
  if (userModel.value.id) apiUrl = userApiResource.update;

  await call(apiUrl, { data: userModel.value });

  if (status.value == ApiStatus.SUCCESS) {
    // back?
    router.push({ name: routeNames.userList }).catch(() => {});
  }
};

type Breadcrumb = {
  title: string;
  to?: { name: string; params?: Record<string, string | number> };
};

// custom breadcrumbs
const breadcrumbs = computed<Breadcrumb[]>(() => [
  { title: t('System') },
  { title: t('Users'), to: { name: routeNames.userList } },
  { title: t('Detail') },
]);

const actions = computed<ActionButton[]>(() => [
  {
    icon: mdiArrowLeft,
    label: t('Back'),
    onClick: () => {
      router.push({ name: routeNames.userList }).catch(() => {});
    },
    color: '',
    useLoading: false,
    useDisabled: false,
  },
  {
    icon: mdiContentSave,
    label: t('Save'),
    onClick: onSave,
    color: 'primary',
    useLoading: true,
    useDisabled: true,
  },
]);

const verifyType = () => {
  const typeList = [
    Role.CLIENT_ADMIN,
    Role.FINANCE_MANAGER,
    Role.SITE_MANAGER,
    Role.SITE_ENGINEER,
    Role.QS_ENGINEER,
  ];
  if (typeList.includes(userModel.value.roleName as Role)) {
    companyFlag.value = true;
  } else {
    companyFlag.value = false;
    userModel.value.companyId = '';
    userModel.value.companyName = '';
    userModel.value.projectId = '';
    userModel.value.projectName = ' ';
  }
};

onMounted(() => {
  let { id } = route.params;
  if (id != 'new') getDetail(id);
});

const goChangePassword = () => ({
  name: routeNames.userChangePassword,
  params: { id: route.params.id },
});
</script>
