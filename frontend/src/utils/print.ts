export function printWindow(printContents: string | undefined) {
  if (printContents) {
    const reprintWindow = window.open('', 'MsgWindow', 'width=500,height=500');
    if (reprintWindow) {
      reprintWindow.document.write(printContents);
      setTimeout(() => {
        reprintWindow.print();
        reprintWindow.close();
      }, 600);
    }
  }
}
