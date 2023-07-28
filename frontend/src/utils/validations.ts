/**
 * required(v);
 * catch - number, string, boolean inputs
 */
export const required = (v: any) =>
  (typeof v == 'number' && !Number.isNaN(v)) ||
  (typeof v == 'string' && !!v) ||
  (typeof v != 'string' &&
    typeof v != 'number' &&
    (v != null || v != undefined)) ||
  'required';

export const minLength = (length: number) => (v: string) =>
  !v || v.length >= length || `must be at least ${length} characters`;

export const maxLength = (length: number) => (v: string) =>
  !v || v.length <= length || `can only be at most ${length} characters`;

export const minNum = (length: number) => (v: number) =>
  !v || v >= length || `must be at least ${length}`;

export const maxNum = (length: number) => (v: number) =>
  !v || v <= length || `can only be at most ${length}`;

export const email = (v: string) =>
  !v || /^[\w-\.]+@([\w-]+\.)+[\w-]{2,}$/g.test(v) || 'invalid email';

/**
  * validate if (v + total of arg[1]) is less than arg[0]
 */
export const sameTotalQuantity =
  (total: number, ...values: number[]) =>
  (v: number) =>
    !v ||
    total ==
      (values.length > 0
        ? values.reduce(function (a, b) {
            return a + b;
          })
        : 0) +
        v ||
    'Total must be same=' + total;

export const fileSize = (size: number, multi: boolean = false) =>
  !multi
    ? (v: any) =>
        !v ||
        !v.length ||
        v[0].size < size * 1000000 ||
        `file should be less than ${size} MB`
    : (v: any) =>
        !v ||
        !v.length ||
        v.every((i: any) => i.size < size * 1000000) ||
        `all files should be less than ${size} MB`;

export const minDate = ( length : string ) => (v: string) =>
  !v || v >= length || 'Invalid Date';

export const maxDate = ( length : string ) => (v: string) =>
  !v || v <= length || 'Invalid Date';

export const decimalPlace = (v: string) => 
  !v || /^\d+(\.\d{1,2})*$/g.test(v) || 'invalid number';