/**
 * User API Resource
 * working example of user api with typescript
 */
import { ApiResources } from '..';

// define api resource name
type UserApiResource =
  | 'login'
  | 'getList'
  | 'getById'
  | 'save'
  | 'update'
  | 'changePassword'
  | 'changePasswordForUser';

export const userApiResource: ApiResources<UserApiResource> = {
  login: { method: 'post', url: '/login' },
  getList: { method: 'get', url: '/auth/users' },
  getById: { method: 'get', url: '/auth/user/:id' },
  save: { method: 'post', url: '/auth/user' },
  update: { method: 'put', url: '/auth/updateuser' },
  changePassword: {
    method: 'put',
    url: '/auth/change-password-by-current-user',
  },
  changePasswordForUser: { method: 'put', url: '/auth/change-password-by-id' },
};
