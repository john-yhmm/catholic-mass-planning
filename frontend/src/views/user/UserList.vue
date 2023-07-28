<template>
  <List
    :title="t('Users')"
    :breadcrumbs="breadcrumbs"
    :actions="actions"
    :filters="filters"
  >
    <template #datatable="{ clearFilter }">
      <ListDataTable
        v-bind="userListMeta"
        :api-params="apiParams"
        @clear-filter="clearFilter"
      >
        <!-- slots may show type error, can ignore it -->

        <template v-slot:[`item.username`]="{ item }">
          <router-link
            class="text-decoration-none text-primary"
            :to="getDetailRoute(item)"
          >
            {{ item.raw.username }}
          </router-link>
        </template>
        <template v-slot:[`item.role`]="{ item }">
          {{ item.raw.roleName }}
        </template>
        <template v-slot:[`item.status`]="{ item }">
          <ListStatus :status="item.raw.status"></ListStatus>
        </template>
        <template v-slot:[`item.createdDate`]="{ item }">
          {{ formatDate(item.raw.createdDateInMilliSeconds, 'yyyy-MM-dd hh:mm:ss') }}
        </template>
        <template v-slot:[`item.updatedDate`]="{ item }">
          {{ formatDate(item.raw.updatedDateInMilliSeconds, 'yyyy-MM-dd hh:mm:ss') }}
        </template>
      </ListDataTable>
    </template>
  </List>
</template>

<script lang="ts" setup>
// working example with vue Composition api

import { computed, ref } from 'vue';
import { userApiResource } from '../../api/resources/userResource';
import ListDataTable from '../../components/common/ListDataTable.vue';
import UserListSearch from '../../components/user/UserListSearch.vue';
import { ListMeta } from '../../interfaces/ListMeta';
import List from '../../layouts/default/List.vue';
import { routeNames } from '../../router/routes';
import { mdiPlus } from '@mdi/js';
import { UserListParams } from '../../models/UserModel';
import { ActionButton } from '../../interfaces/ActionButton';
import ListStatus from '../../components/common/ListStatus.vue';
import { formatDate } from '../../utils';
import { useI18n } from 'vue-i18n';

const { t } = useI18n({ useScope: 'global' });

const userListMeta = computed<ListMeta>(() => {
  return {
    headers: [
      {
        title: t('Username'),
        align: 'start',
        sortable: true,
        key: 'username',
        width: 200,
      },
      { title: t('Role'), key: 'role', width: 150 },
      { title: t('Company'), key: 'companyName', width: 150 },
      { title: t('Status'), key: 'status', width: 150 },
      { title: t('Created By'), key: 'createdBy', width: 150 },
      { title: t('Created Date'), key: 'createdDate', width: 150 },
      { title: t('Updated By'), key: 'updatedBy', width: 150 },
      { title: t('Updated Date'), key: 'updatedDate', width: 150 },
    ],
    apiResource: userApiResource.getList,
    responseKey: 'list',
    defaultSort: [{ key: 'createdDate', order: 'desc' }],
  };
});

// apiParams must be undefined
const apiParams = ref();

type Breadcrumb = {
  title: string;
  to?: { name: string; params?: Record<string, string | number> };
};
// custom breadcrumbs
const breadcrumbs = computed<Breadcrumb[]>(() => [
  { title: t('System') },
  { title: t('Users'), to: { name: routeNames.userList } },
]);

const actions = computed<ActionButton[]>(() => [
  {
    icon: mdiPlus,
    label: t('Add'),
    to: { name: routeNames.userDetail, params: { id: 'new' } },
    color: 'primary',
  },
]);

const filters = {
  component: UserListSearch,
  onSearch: (params: UserListParams) => (apiParams.value = { ...params }),
};

const getDetailRoute = (item: any) => {
  return { name: routeNames.userDetail, params: { id: item.raw.id } };
};
</script>
