import { format as formatDateAs, add } from 'date-fns';
import { FileMimeType } from '../constants/FileMimeType';
import { AxiosResponse } from 'axios';

declare global {
  interface Navigator {
    msSaveBlob?: (blob: any, defaultName?: string) => boolean;
    msSaveOrOpenBlob?: (blob: any, defaultName?: string) => boolean;
  }
}
interface DownloadFileOptions {
  type: FileMimeType;
}

export const formatDate = (
  date: Date | number,
  pattern: string = 'yyyy-MM-dd'
) => formatDateAs(date, pattern);
// pattern: https://date-fns.org/v2.29.3/docs/format

export const addDaysInclusive = (
  startDate: Date | number,
  daysToAdd: number
) => {
  const adjustedDays = daysToAdd > 0 ? daysToAdd - 1 : 0;
  return add(startDate, { days: adjustedDays });
};

export const convertImageUrlToBase64 = (imgUrl: string, callback: Function) => {
  const image = new Image();
  image.crossOrigin = 'anonymous';
  image.onload = () => {
    const canvas = document.createElement('canvas');
    const ctx = canvas.getContext('2d');
    if (ctx) {
      canvas.height = image.naturalHeight;
      canvas.width = image.naturalWidth;
      ctx.drawImage(image, 0, 0);
      const dataUrl = canvas.toDataURL();
      callback && callback(dataUrl);
    }
  };
  image.src = imgUrl;
};

export const convertImageFileToBase64 = (file: any, callback: Function) => {
  const fileReader = new FileReader();
  fileReader.onload = () => {
    const srcData = fileReader.result;
    callback && callback(srcData);
  };
  fileReader.readAsDataURL(file);
};

export const downloadFile = async (
  response: AxiosResponse,
  options: DownloadFileOptions
) => {
  try {
    const { data, headers } = response;
    const { type } = options;

    const blob = new Blob([data], { type });

    // IE doesn't allow using a blob object directly as link href
    // instead it is necessary to use msSaveOrOpenBlob
    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
      window.navigator.msSaveOrOpenBlob(blob);
      return;
    }

    const link = document.createElement('a');
    document.body.appendChild(link);

    let fileName = 'File-' + new Date().getTime();
    const disposition = headers['content-disposition'];
    const fileNameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
    const searchedName = fileNameRegex.exec(disposition);

    if (searchedName?.length) {
      fileName = searchedName[1].replace(/['"]/g, '');
    }

    // Create a link pointing to the ObjectURL containing the blob.
    const dataUrl = window.URL.createObjectURL(blob);

    link.href = dataUrl;
    link.download = fileName;
    link.click();

    setTimeout(function () {
      // delay revoking the ObjectURL
      window.URL.revokeObjectURL(dataUrl);
    }, 500);
  } catch (error) {
    console.error('Error downloading file', error);
  }
};

export const arrayBufferToJson = (arrayBuffer: ArrayBuffer): any => {
  const decoder = new TextDecoder('utf-8');
  const jsonString = decoder.decode(arrayBuffer);
  const jsonObject = JSON.parse(jsonString);
  return jsonObject;
};
