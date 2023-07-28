import { Role } from '../constants/Role';
import { Status } from '../constants/Status';
import { CommonDto } from '../interfaces/CommonDto';

export interface UserListParams {
  [key: string]: any;
  name: string;
  role: Role | string;
  status: Status | string;
  companyId: string | null;
  company: string;
  projectId: string | null;
  project: string;
}

export const UserListParamsModel = (): UserListParams => ({
  name: '',
  status: '',
  role: '',
  companyId: null,
  company: '',
  projectId: null,
  project:'',
});

export interface User extends CommonDto {
  member?: object | any;
  memberId?: number | string;
  role?: object | any;
  roleName: string | Role;
  status: string | Status;
  username: string;
  password?: string;
  confirmPassword?: string;
  companyId: string | null;
  companyName?: string;
  projectId: string | null;
  projectName?: string;
}

export const UserModel = (): User => ({
  username: '',
  roleName: '',
  status: Status.ACTIVE,
  password: '',
  confirmPassword: '',
  companyId: '',
  companyName: '',
  projectId: '',
  projectName: '',
});

export interface UserChangePassword {
  id: string | number;
  oldPassword: string;
  newPassword: string;
  confirmNewPassword: string;
}

export const UserChangePasswordModel = (): UserChangePassword => ({
  id: '',
  oldPassword: '',
  newPassword: '',
  confirmNewPassword: '',
});
