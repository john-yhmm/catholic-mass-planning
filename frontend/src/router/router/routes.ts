import { RouteRecordRaw } from 'vue-router';
import { Role } from '../constants/Role';

export const routeNames = {
  home: 'Home',
  login: 'Login',
  userList: 'UserList',
  userDetail: 'UserDetail',
  userChangePassword: 'UserChangePassword',
  companyList: 'CompanyList',
  companyDetail: 'CompanyDetail',
  clientList: 'clientList',
  clientDetail: 'clientDetail',
  currencyList: 'currencyList',
  currencyDetail: 'currencyDetail',
  creditLevelList: 'creditLevelList',
  creditLevelDetail: 'creditLevelDetail',
  supplierList: 'supplierList',
  supplierDetail: 'supplierDetail',
  resourceTypeList: 'resourceTypeList',
  resourceTypeDetail: 'resourceTypeDetail',
  resourceGroupList: 'resourceGroupList',
  resourceGroupDetail: 'resourceGroupDetail',
  brandList: 'brandList',
  brandDetail: 'brandDetail',
  categoryList: 'categoryList',
  categoryDetail: 'categoryDetail',
  unitList: 'unitList',
  unitDetail: 'unitDetail',
  buildingList: 'buildingList',
  buildingDetail: 'buildingDetail',
  resourceList: 'resourceList',
  resourceDetail: 'resourceDetail',
  siteList: 'siteList',
  siteDetail: 'siteDetail',
  accountList: 'accountList',
  accountDetail: 'accountDetail',
  projectList: 'projectList',
  projectDetail: 'projectDetail',
  projectBudgetList: 'projectBudgetList',
  projectBudgetDetail: 'projectBudgetDetail',
  contractList: 'contractList',
  contractdetail: 'contractDetail',
  ledgerSettingList: 'ledgerSettingList',
  ledgerSettingDetail: 'ledgerSettingDetail',
  systemSerializeList: 'systemSerializeList',
  systemSerializeDetail: 'systemSerializeDetail',
  generalLedgerList: 'generalLedgerList',
  generalLedgerDetail: 'generalLedgerDetail',
  generalJournalList: 'generalJournalList',
  generalJournalDetail: 'generalJournalDetail',
  accountHistoryList: 'accountHistoryList',
  supplierBillList: 'supplierBillList',
  hireLabourList: 'hireLabourList',
  hireLabourDetail: 'hireLabourDetail',
  paymentList: 'paymentList',
  paymentDetail: 'paymentDetail',
  stockInList: 'stockInList',
  stockInDetail: 'stockInDetail',
  stockLedgerList: 'stockLedgerList',
  stockTemplateList: 'stockTemplateList',
  stockTemplateDetail: 'stockTemplateDetail',
  StockIssueList: 'StockIssueList',
  StockIssueDetail: 'StockIssueDetail',
  assetManagementDetail: 'assetManagementDetail',
  assetManagementList: 'assetManagementList',
  glListingList: 'glListingList',
  trialBalanceList: 'trialBalanceList',
  stockTransferList: 'stockTransferList',
  stockTransferDetail: 'stockTransferDetail',
  stockAdjustmentList: 'stockAdjustmentList',
  stockAdjustmentDetail: 'stockAdjustmentDetail',
  stockBalanceList: 'stockBalanceList',
  siteCashDetail: 'SiteCashDetail',
  siteCashList: 'SiteCashList',
  incomeStatementList: 'incomeStatementList',
  workingSchedulingList: 'workingSchedulingList',
  workingSchedulingDetail: 'workingSchedulingDetail',
  workingScheduleReportList: 'workingScheduleReportList',
  balanceSheetList: 'balanceSheetList',
  rentalServiceDetail: 'RentalServiceDetail',
  rentalServiceList: 'RentalServiceList',
  purchaseOrderList: 'purchaseOrderList',
  purchaseOrderDetail: 'purchaseOrderDetail',
  accountReceivableList: 'AccountReceivableList',
  accountReceivableDetail: 'AccountReceivableDetail',
  budgetReportList: 'budgetReportList',
  monthlyProgressList: 'monthlyProgressList',
  monthlyProgressDetail: 'monthlyProgressDetail',
};

export const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('../layouts/blank/Blank.vue'),
    children: [
      {
        path: '',
        redirect: { name: routeNames.login },
      },
      {
        path: 'login',
        name: routeNames.login,
        component: () =>
          import(/* webpackChunkName: "Login" */ '../views/Login.vue'),
        meta: {
          guest: true,
        },
      },
    ],
  },
  {
    path: '/admin',
    component: () => import('../layouts/default/Default.vue'),
    children: [
      {
        path: '',
        name: routeNames.home,
        component: () =>
          import(/* webpackChunkName: "Home" */ '../views/Home.vue'),
        meta: {
          requiresAuth: true,
          allowedRoles: [], // optional, specifiy for more restrict
        },
      },
      {
        path: 'users',
        name: routeNames.userList,
        component: () =>
          import(
            /* webpackChunkName: "UserList" */ '../views/user/UserList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.FINANCE_MANAGER,
            Role.CLIENT_ADMIN,
          ], // optional, specifiy for more restrict
        },
      },
      {
        path: 'users/:id',
        name: routeNames.userDetail,
        component: () =>
          import(
            /* webpackChunkName: "userDetail" */ '../views/user/UserDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.FINANCE_MANAGER,
            Role.CLIENT_ADMIN,
          ], // optional, specifiy for more restrict
        },
      },
      {
        path: 'users/:id/change-password',
        name: routeNames.userChangePassword,
        component: () =>
          import(
            /* webpackChunkName: "userChangePassword" */ '../views/user/UserChangePassword.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.FINANCE_MANAGER,
            Role.CLIENT_ADMIN,
          ], // optional, specifiy for more restrict
        },
      },
      {
        path: 'companies',
        name: routeNames.companyList,
        component: () =>
          import(
            /* webpackChunkName: "companyList" */ '../views/company/CompanyList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.FINANCE_MANAGER,
            Role.CLIENT_ADMIN,
          ],
        },
      },
      {
        path: 'companies/:id',
        name: routeNames.companyDetail,
        component: () =>
          import(
            /* webpackChunkName: "companyDetail" */ '../views/company/CompanyDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'clients',
        name: routeNames.clientList,
        component: () =>
          import(
            /* webpackChunkName: "clientList" */ '../views/client/ClientList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'clients/:id',
        name: routeNames.clientDetail,
        component: () =>
          import(
            /* webpackChunkName: "clientDetail" */ '../views/client/ClientDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'currencies',
        name: routeNames.currencyList,
        component: () =>
          import(
            /* webpackChunkName: "currencyList" */ '../views/currency/CurrencyList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'currencies/:id',
        name: routeNames.currencyDetail,
        component: () =>
          import(
            /* webpackChunkName: "currencyDetail" */ '../views/currency/CurrencyDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'credit-levels',
        name: routeNames.creditLevelList,
        component: () =>
          import(
            /* webpackChunkName: "creditLevelList" */ '../views/creditLevel/CreditLevelList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'credit-levels/:id',
        name: routeNames.creditLevelDetail,
        component: () =>
          import(
            /* webpackChunkName: "creditLevelDetail" */ '../views/creditLevel/CreditLevelDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'suppliers',
        name: routeNames.supplierList,
        component: () =>
          import(
            /* webpackChunkName: "supplierList" */ '../views/supplier/SupplierList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'suppliers/:id',
        name: routeNames.supplierDetail,
        component: () =>
          import(
            /* webpackChunkName: "supplierDetail" */ '../views/supplier/SupplierDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'system-serializes',
        name: routeNames.systemSerializeList,
        component: () =>
          import(
            /* webpackChunkName: "supplierList" */ '../views/systemSerialize/SystemSerializeList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [Role.SYSADMIN],
        },
      },
      {
        path: 'system-serializes/:id',
        name: routeNames.systemSerializeDetail,
        component: () =>
          import(
            /* webpackChunkName: "supplierDetail" */ '../views/systemSerialize/SystemSerializeDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [Role.SYSADMIN],
        },
      },
      {
        path: 'projects',
        name: routeNames.projectList,
        component: () =>
          import(
            /* webpackChunkName: "projectList" */ '../views/project/ProjectList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'projects/:id',
        name: routeNames.projectDetail,
        component: () =>
          import(
            /* webpackChunkName: "projectDetail" */ '../views/project/ProjectDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'resource-types',
        name: routeNames.resourceTypeList,
        component: () =>
          import(
            /* webpackChunkName: "resourceTypeList" */ '../views/resourceType/ResourceTypeList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'resource-types/:id',
        name: routeNames.resourceTypeDetail,
        component: () =>
          import(
            /* webpackChunkName: "resourceTypeDetail" */ '../views/resourceType/ResourceTypeDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'resource-groups',
        name: routeNames.resourceGroupList,
        component: () =>
          import(
            /* webpackChunkName: "resourceGroupList" */ '../views/resourceGroup/ResourceGroupList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'resource-groups/:id',
        name: routeNames.resourceGroupDetail,
        component: () =>
          import(
            /* webpackChunkName: "resourceGroupDetail" */ '../views/resourceGroup/ResourceGroupDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'brands',
        name: routeNames.brandList,
        component: () =>
          import(
            /* webpackChunkName: "brandList" */ '../views/brand/BrandList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'brands/:id',
        name: routeNames.brandDetail,
        component: () =>
          import(
            /* webpackChunkName: "brandDetail" */ '../views/brand/BrandDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'categories',
        name: routeNames.categoryList,
        component: () =>
          import(
            /* webpackChunkName: "categoryList" */ '../views/category/CategoryList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'categories/:id',
        name: routeNames.categoryDetail,
        component: () =>
          import(
            /* webpackChunkName: "categoryDetail" */ '../views/category/CategoryDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'units',
        name: routeNames.unitList,
        component: () =>
          import(
            /* webpackChunkName: "unitList" */ '../views/unit/UnitList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'units/:id',
        name: routeNames.unitDetail,
        component: () =>
          import(
            /* webpackChunkName: "unitDetail" */ '../views/unit/UnitDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'buildings',
        name: routeNames.buildingList,
        component: () =>
          import(
            /* webpackChunkName: "buildingList" */ '../views/building/BuildingList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'buildings/:id',
        name: routeNames.buildingDetail,
        component: () =>
          import(
            /* webpackChunkName: "buildingDetail" */ '../views/building/BuildingDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'resources',
        name: routeNames.resourceList,
        component: () =>
          import(
            /* webpackChunkName: "resourceList" */ '../views/resource/ResourceList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'resources/:id',
        name: routeNames.resourceDetail,
        component: () =>
          import(
            /* webpackChunkName: "resourceDetail" */ '../views/resource/ResourceDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'sites',
        name: routeNames.siteList,
        component: () =>
          import(
            /* webpackChunkName: "siteList" */ '../views/site/SiteList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'sites/:id',
        name: routeNames.siteDetail,
        component: () =>
          import(
            /* webpackChunkName: "siteDetail" */ '../views/site/SiteDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'accounts',
        name: routeNames.accountList,
        component: () =>
          import(
            /* webpackChunkName: "accountList" */ '../views/account/AccountList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'accounts/:id',
        name: routeNames.accountDetail,
        component: () =>
          import(
            /* webpackChunkName: "accountDetail" */ '../views/account/AccountDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'ledger-settings',
        name: routeNames.ledgerSettingList,
        component: () =>
          import(
            /* webpackChunkName: "ledgerSettingList" */ '../views/ledgerSetting/LedgerSettingList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'ledger-settings/:id',
        name: routeNames.ledgerSettingDetail,
        component: () =>
          import(
            /* webpackChunkName: "ledgerSettingDetail" */ '../views/ledgerSetting/LedgerSettingDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'general-ledgers',
        name: routeNames.generalLedgerList,
        component: () =>
          import(
            /* webpackChunkName: "generalLedgerList" */ '../views/generalLedger/GeneralLedgerList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'general-ledgers/:id',
        name: routeNames.generalLedgerDetail,
        component: () =>
          import(
            /* webpackChunkName: "generalLedgerDetail" */ '../views/generalLedger/GeneralLedgerDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'general-journals',
        name: routeNames.generalJournalList,
        component: () =>
          import(
            /* webpackChunkName: "generalJournalList" */ '../views/generalJournal/GeneralJournalList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'general-journals/:id',
        name: routeNames.generalJournalDetail,
        component: () =>
          import(
            /* webpackChunkName: "generalJournalDetail" */ '../views/generalJournal/GeneralJournalDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'account-histories',
        name: routeNames.accountHistoryList,
        component: () =>
          import(
            /* webpackChunkName: "accountHistoryList" */ '../views/accountHistory/AccountHistoryList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'asset-managements',
        name: routeNames.assetManagementList,
        component: () =>
          import(
            /* webpackChunkName: "assetManagementList" */ '../views/assetManagement/AssetManagementList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'asset-managements/:id',
        name: routeNames.assetManagementDetail,
        component: () =>
          import(
            /* webpackChunkName: "assetManagementDetail" */ '../views/assetManagement/AssetManagementDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'gl-listings',
        name: routeNames.glListingList,
        component: () =>
          import(
            /* webpackChunkName: "glListingList" */ '../views/glListing/GlListingList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-ins',
        name: routeNames.stockInList,
        component: () =>
          import(
            /* webpackChunkName: "stockInList" */ '../views/stockIn/StockInList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-ins/:id',
        name: routeNames.stockInDetail,
        component: () =>
          import(
            /* webpackChunkName: "stockInDetail" */ '../views/stockIn/StockInDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-ledgers',
        name: routeNames.stockLedgerList,
        component: () =>
          import(
            /* webpackChunkName: "stockLedgerList" */ '../views/stockLedger/StockLedgerList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
            Role.SITE_ENGINEER,
          ],
        },
      },
      {
        path: 'stock-transfers',
        name: routeNames.stockTransferList,
        component: () =>
          import(
            /* webpackChunkName: "stockTransferList" */ '../views/stockTransfer/StockTransferList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-transfers/:id',
        name: routeNames.stockTransferDetail,
        component: () =>
          import(
            /* webpackChunkName: "stockTransferDetail" */ '../views/stockTransfer/StockTransferDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-templates',
        name: routeNames.stockTemplateList,
        component: () =>
          import(
            /* webpackChunkName: "stockTemplateList" */ '../views/stockTemplate/StockTemplateList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-template/:id',
        name: routeNames.stockTemplateDetail,
        component: () =>
          import(
            /* webpackChunkName: "stockTemplateDetail" */ '../views/stockTemplate/StockTemplateDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'trial-balances',
        name: routeNames.trialBalanceList,
        component: () =>
          import(
            /* webpackChunkName: "trialBalanceList" */ '../views/trialBalance/TrialBalanceList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-adjustments',
        name: routeNames.stockAdjustmentList,
        component: () =>
          import(
            /* webpackChunkName: "stockAdjustmentList" */ '../views/stockAdjustment/StockAdjustmentList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-adjustments/:id',
        name: routeNames.stockAdjustmentDetail,
        component: () =>
          import(
            /* webpackChunkName: "stockAdjustmentDetail" */ '../views/stockAdjustment/StockAdjustmentDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'project-budget',
        name: routeNames.projectBudgetList,
        component: () =>
          import(
            /* webpackChunkName: "projectBudgetList" */ '../views/projectBudget/ProjectBudgetList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'project-budget/:id',
        name: routeNames.projectBudgetDetail,
        component: () =>
          import(
            /* webpackChunkName: "projectBudgetDetail" */ '../views/projectBudget/ProjectBudgetDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-balances',
        name: routeNames.stockBalanceList,
        component: () =>
          import(
            /* webpackChunkName: "stockBalanceList" */ '../views/stockBalance/StockBalanceList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_ENGINEER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'supplier-bills',
        name: routeNames.supplierBillList,
        component: () =>
          import(
            /* webpackChunkName: "supplierBillList" */ '../views/supplierBill/SupplierBillList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'hire-labours',
        name: routeNames.hireLabourList,
        component: () =>
          import(
            /* webpackChunkName: "hireLabourList" */ '../views/hireLabour/HireLabourList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.QS_ENGINEER,
            Role.SITE_ENGINEER,
          ],
        },
      },
      {
        path: 'hire-labour/:id',
        name: routeNames.hireLabourDetail,
        component: () =>
          import(
            /* webpackChunkName: "hireLabourDetail" */ '../views/hireLabour/HireLabourDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.QS_ENGINEER,
            Role.SITE_ENGINEER,
          ],
        },
      },
      {
        path: 'site-cashs',
        name: routeNames.siteCashList,
        component: () =>
          import(
            /* webpackChunkName: "siteCashList" */ '../views/siteCash/SiteCashList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'site-cashs/:id',
        name: routeNames.siteCashDetail,
        component: () =>
          import(
            /* webpackChunkName: "siteCashDetail" */ '../views/siteCash/SiteCashDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'income-statements',
        name: routeNames.incomeStatementList,
        component: () =>
          import(
            /* webpackChunkName: "incomeStatementList" */ '../views/incomeStatement/IncomeStatementList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'payments',
        name: routeNames.paymentList,
        component: () =>
          import(
            /* webpackChunkName: "paymentList" */ '../views/payment/PaymentList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.QS_ENGINEER,
            Role.SITE_ENGINEER,
          ],
        },
      },
      {
        path: 'payment/:id',
        name: routeNames.paymentDetail,
        component: () =>
          import(
            /* webpackChunkName: "paymentDetail" */ '../views/payment/PaymentDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.QS_ENGINEER,
            Role.SITE_ENGINEER,
          ],
        },
      },
      {
        path: 'contracts',
        name: routeNames.contractList,
        component: () =>
          import(
            /* webpackChunkName: "contractList" */ '../views/contratManagement/ContractList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'contracts/:id',
        name: routeNames.contractdetail,
        component: () =>
          import(
            /* webpackChunkName: "contractdetail" */ '../views/contratManagement/ContractDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'working-scheduling',
        name: routeNames.workingSchedulingList,
        component: () =>
          import(
            /* webpackChunkName: "workingSchedulingList" */ '../views/workingScheduling/WorkingSchedulingList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.QS_ENGINEER,
            Role.SITE_ENGINEER,
          ],
        },
      },
      {
        path: 'working-scheduling/:id',
        name: routeNames.workingSchedulingDetail,
        component: () =>
          import(
            /* webpackChunkName: "workingSchedulingDetail" */ '../views/workingScheduling/WorkingSchedulingDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.QS_ENGINEER,
            Role.SITE_ENGINEER,
          ],
        },
      },
      {
        path: 'balance-sheet',
        name: routeNames.balanceSheetList,
        component: () =>
          import(
            /* webpackChunkName: "balanceSheetList" */ '../views/balanceSheet/BalanceSheetList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'rentalServices',
        name: routeNames.rentalServiceList,
        component: () =>
          import(
            /* webpackChunkName: "rentalServiceList" */ '../views/rentalService/RentalServiceList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'rentalServices/:id',
        name: routeNames.rentalServiceDetail,
        component: () =>
          import(
            /* webpackChunkName: "rentalServiceDetail" */ '../views/rentalService/RentalServiceDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'purchase-orders',
        name: routeNames.purchaseOrderList,
        component: () =>
          import(
            /* webpackChunkName: "purchaseOrderList" */ '../views/purchaseOrder/PurchaseOrderList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'purchase-orders/:id',
        name: routeNames.purchaseOrderDetail,
        component: () =>
          import(
            /* webpackChunkName: "purchaseOrderDetail" */ '../views/purchaseOrder/PurchaseOrderDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'budget-report',
        name: routeNames.budgetReportList,
        component: () =>
          import(
            /* webpackChunkName: "budgetReportList" */ '../views/budgetReport/BudgetReportList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-issue',
        name: routeNames.StockIssueList,
        component: () =>
          import(
            /* webpackChunkName: "stockIssueList" */ '../views/stockIssue/StockIssueList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_ENGINEER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'stock-issue/:id',
        name: routeNames.StockIssueDetail,
        component: () =>
          import(
            /* webpackChunkName: "stockIssueDetail" */ '../views/stockIssue/StockIssueDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.SITE_ENGINEER,
            Role.SITE_MANAGER,
          ],
        },
      },
      {
        path: 'account-receivables',
        name: routeNames.accountReceivableList,
        component: () =>
          import(
            /* webpackChunkName: "accountReceivableList" */ '../views/accountReceivable/AccountReceivableList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'account-receivables/:id',
        name: routeNames.accountReceivableDetail,
        component: () =>
          import(
            /* webpackChunkName: "accountReceivableDetail" */ '../views/accountReceivable/AccountReceivableDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'working-schedule-report',
        name: routeNames.workingScheduleReportList,
        component: () =>
          import(
            /* webpackChunkName: "workingScheduleReportList" */ '../views/workingScheduleReport/WorkingScheduleReportList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
            Role.QS_ENGINEER,
            Role.SITE_ENGINEER,
          ],
        },
      },
      {
        path: 'monthly-progress-updates',
        name: routeNames.monthlyProgressList,
        component: () =>
          import(
            /* webpackChunkName: "monthlyProgressList" */ '../views/monthlyProgress/MonthlyProgressList.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
      {
        path: 'monthly-progress-updates/:id',
        name: routeNames.monthlyProgressDetail,
        component: () =>
          import(
            /* webpackChunkName: "monthlyProgressDetail" */ '../views/monthlyProgress/MonthlyProgressDetail.vue'
          ),
        meta: {
          requiresAuth: true,
          allowedRoles: [
            Role.SYSADMIN,
            Role.CLIENT_ADMIN,
            Role.FINANCE_MANAGER,
          ],
        },
      },
    ],
  },
];
