import { ElNotification } from "element-plus";

/**
 * @param message
 */
export default (message: string): void => {
  ElNotification({
    type: "error",
    message: message,
  });
};
