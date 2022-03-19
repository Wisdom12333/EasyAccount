import { ElMessageBox } from "element-plus";

export default function (
  message: string,
  title: string,
  func: () => void
): void {
  ElMessageBox.alert(message, title, {
    confirmButtonText: "确定",
  }).then(() => {
    func();
  });
}
