import { RouteLocationNamedRaw } from 'vue-router';
import { Role } from './Role';
import {
  mdiCog,
  mdiAccount,
  mdiHome,
  mdiDomain,
  mdiShape,
  mdiFolderOpen,
  mdiInformationVariant,
  mdiFolderOpenOutline,
  mdiCalendarMonth,
  mdiFinance,
  mdiSitemap,
  mdiFileChart,
  mdiFileChartCheck,
  mdiWarehouse,
  mdiImport,
  mdiWallet,
  mdiTruck,
  mdiPencil,
  mdiContentPaste,
  mdiPurse,
  mdiBasket,
} from '@mdi/js';
import { routeNames } from '../router/routes';

export interface Menu {
  icon: string;
  name: string;
  to?: RouteLocationNamedRaw;
  forRole: Role[];
  children?: Menu[];
}

export const menus: Menu[] = [
  {
    icon: mdiHome,
    name: 'Home',
    to: { name: routeNames.home },
    forRole: [Role.SYSADMIN],
  },
  {
    icon: mdiInformationVariant,
    name: 'General',
    forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
    children: [
      {
        name: 'Companies',
        icon: mdiDomain,
        to: { name: routeNames.companyList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Clients',
        icon: mdiDomain,
        to: { name: routeNames.clientList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Currencies',
        icon: mdiDomain,
        to: { name: routeNames.currencyList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Credit Levels',
        icon: mdiDomain,
        to: { name: routeNames.creditLevelList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Suppliers',
        icon: mdiDomain,
        to: { name: routeNames.supplierList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'System Serializes',
        icon: mdiDomain,
        to: { name: routeNames.systemSerializeList },
        forRole: [Role.SYSADMIN,Role.FINANCE_MANAGER],
      },
    ],
  },
  {
    icon: mdiShape,
    name: 'Resource Management',
    forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
    children: [
      {
        name: 'Resource Types',
        icon: mdiShape,
        to: { name: routeNames.resourceTypeList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Resource Groups',
        icon: mdiShape,
        to: { name: routeNames.resourceGroupList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Brands',
        icon: mdiShape,
        to: { name: routeNames.brandList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Categories',
        icon: mdiShape,
        to: { name: routeNames.categoryList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Units',
        icon: mdiShape,
        to: { name: routeNames.unitList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Resources',
        icon: mdiShape,
        to: { name: routeNames.resourceList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
    ],
  },
  {
    icon: mdiCog,
    name: 'System Management',
    forRole: [Role.SYSADMIN],
    children: [
      {
        name: 'Users',
        icon: mdiAccount,
        to: { name: routeNames.userList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
    ],
  },
  {
    icon: mdiFolderOpen,
    name: 'Project Management',
    forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
    children: [
      {
        name: 'Project Management with Design Document',
        icon: mdiAccount,
        to: { name: routeNames.projectList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Site Management',
        icon: mdiAccount,
        to: { name: routeNames.siteList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Building Management',
        icon: mdiAccount,
        to: { name: routeNames.buildingList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
    ],
  },
  {
    icon: mdiFolderOpenOutline,
    name: 'Project Sale Management',
    forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
    children: [
      {
        name: 'Contract Management',
        icon: mdiAccount,
        to: { name: routeNames.contractList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Rental Service',
        icon: mdiAccount,
        to: { name: routeNames.rentalServiceList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Account Receivable',
        icon: mdiAccount,
        to: { name: routeNames.accountReceivableList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Monthly Progress Update',
        icon: mdiAccount,
        to: { name: routeNames.monthlyProgressList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
    ],
  },
  {
    icon: mdiCalendarMonth,
    name: 'Project Planning and Scheduling',
    forRole: [
      Role.SYSADMIN,
      Role.CLIENT_ADMIN,
      Role.FINANCE_MANAGER,
      Role.QS_ENGINEER,
      Role.SITE_ENGINEER,
    ],
    children: [
      {
        name: 'Project Budget',
        icon: mdiAccount,
        to: { name: routeNames.projectBudgetList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Working Scheduling',
        icon: mdiAccount,
        to: { name: routeNames.workingSchedulingList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.QS_ENGINEER,
          Role.SITE_ENGINEER,
        ],
      },
      {
        name: 'Working Schedule Report',
        icon: mdiAccount,
        to: { name: routeNames.workingScheduleReportList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.QS_ENGINEER,
          Role.SITE_ENGINEER,
        ],
      },
      {
        name: 'Budgeting Report',
        icon: mdiAccount,
        to: { name: routeNames.budgetReportList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
    ],
  },
  {
    icon: mdiContentPaste,
    name: 'Procurement Management',
    forRole: [
      Role.SYSADMIN,
      Role.CLIENT_ADMIN,
      Role.FINANCE_MANAGER,
      Role.QS_ENGINEER,
      Role.SITE_ENGINEER,
      Role.SITE_MANAGER,
    ],
    children: [
      {
        name: 'Purchase Order',
        icon: mdiPurse,
        to: { name: routeNames.purchaseOrderList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.SITE_MANAGER,
        ],
      },
      {
        name: 'Supplier Bill',
        icon: mdiWallet,
        to: { name: routeNames.supplierBillList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.SITE_MANAGER,
        ],
      },
      {
        name: 'Hire Labour',
        icon: mdiWallet,
        to: { name: routeNames.hireLabourList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.QS_ENGINEER,
          Role.SITE_ENGINEER,
        ],
      },
      {
        name: 'Bill Payment',
        icon: mdiWallet,
        to: { name: routeNames.paymentList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.QS_ENGINEER,
          Role.SITE_ENGINEER,
        ],
      },
    ],
  },
  {
    icon: mdiWarehouse,
    name: 'Warehouse Management',
    forRole: [
      Role.SYSADMIN,
      Role.CLIENT_ADMIN,
      Role.FINANCE_MANAGER,
      Role.SITE_MANAGER,
      Role.SITE_ENGINEER,
    ],
    children: [
      {
        name: 'Stock In',
        icon: mdiImport,
        to: { name: routeNames.stockInList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.SITE_MANAGER,
        ],
      },
      {
        name: 'Stock Adjustment',
        icon: mdiPencil,
        to: { name: routeNames.stockAdjustmentList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.SITE_MANAGER,
        ],
      },
      {
        name: 'Stock Transfer',
        icon: mdiTruck,
        to: { name: routeNames.stockTransferList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.SITE_MANAGER,
        ],
      },
      {
        name: 'Stock Ledger',
        icon: mdiWallet,
        to: { name: routeNames.stockLedgerList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.SITE_MANAGER,
          Role.SITE_ENGINEER,
        ],
      },
      {
        name: 'Stock Template',
        icon: mdiImport,
        to: { name: routeNames.stockTemplateList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Stock Balance',
        icon: mdiImport,
        to: { name: routeNames.stockBalanceList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.SITE_MANAGER,
          Role.SITE_ENGINEER,
        ],
      },
      {
        name: 'Stock Issue',
        icon: mdiImport,
        to: { name: routeNames.StockIssueList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.SITE_MANAGER,
          Role.SITE_ENGINEER,
        ],
      },
    ],
  },
  {
    icon: mdiFileChart,
    name: 'Finance Setup',
    forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
    children: [
      {
        name: 'Chart of Account',
        icon: mdiAccount,
        to: { name: routeNames.accountList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Ledger Setting',
        icon: mdiAccount,
        to: { name: routeNames.ledgerSettingList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Asset Management',
        icon: mdiAccount,
        to: { name: routeNames.assetManagementList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
    ],
  },
  {
    icon: mdiSitemap,
    name: 'Asset Management',
    forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
    children: [
      {
        name: 'Depreciation Schedule',
        icon: mdiAccount,
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
    ],
  },
  {
    icon: mdiFileChartCheck,
    name: 'Finance Ledger Entry',
    forRole: [
      Role.SYSADMIN,
      Role.CLIENT_ADMIN,
      Role.FINANCE_MANAGER,
      Role.SITE_MANAGER,
    ],
    children: [
      {
        name: 'General Ledger',
        icon: mdiAccount,
        to: { name: routeNames.generalLedgerList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'General Journal',
        icon: mdiAccount,
        to: { name: routeNames.generalJournalList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Site Cash',
        icon: mdiAccount,
        to: { name: routeNames.siteCashList },
        forRole: [
          Role.SYSADMIN,
          Role.CLIENT_ADMIN,
          Role.FINANCE_MANAGER,

          Role.SITE_MANAGER,
        ],
      },
    ],
  },
  {
    icon: mdiFinance,
    name: 'Finance Reports',
    forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
    children: [
      {
        name: 'General Ledger Listing',
        icon: mdiAccount,
        to: { name: routeNames.glListingList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Trial Balance',
        icon: mdiAccount,
        to: { name: routeNames.trialBalanceList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Income Statement',
        icon: mdiAccount,
        to: { name: routeNames.incomeStatementList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Balance Sheet',
        icon: mdiAccount,
        to: { name: routeNames.balanceSheetList },
        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
      {
        name: 'Account History',
        to: { name: routeNames.accountHistoryList },
        icon: mdiAccount,

        forRole: [Role.SYSADMIN, Role.CLIENT_ADMIN, Role.FINANCE_MANAGER],
      },
    ],
  },
];

export const getMenus = (role: Role): Menu[] => {
  let roleMenu = menus.filter((menu) => menu.forRole.includes(role));
  return roleMenu.map((menu) => {
    if (menu.children)
      menu.children = menu.children.filter((childMenu) =>
        childMenu.forRole.includes(role)
      );
    return menu;
  });
};

export const getCurrentMenu = (routeName: any, menus: Menu[]) => {
  for (let index = 0; index < menus.length; index++) {
    const menu = menus[index];
    if (
      menu.to?.name == routeName ||
      (menu.children &&
        menu.children.find((menu) => menu.to?.name == routeName))
    )
      return menu;
  }

  return undefined;
};
