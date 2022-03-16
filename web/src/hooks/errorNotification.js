import {ElNotification} from "element-plus";

/**
 * @param message
 */
export default (message) => {
    ElNotification({
        type: "error",
        message: message,
    });
}