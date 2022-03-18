import { ElMessageBox } from "element-plus";

export default function (message, title, func) {
  ElMessageBox.alert(message, title, {
    confirmButtonText: "确定",
  }).then(() => {
    func();
  });
}
