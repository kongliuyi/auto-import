package net.riking.auto.commmon.validator;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 校验结果
 *
 * @author lizhilong
 */
@Setter
public class ValidationResult {

    // 校验结果是否有错
    @Getter
    private boolean hasErrors;

    // 校验错误信息
    private Map<String, String> errorMsg;


    public String getErrorsMsg() {
        StringBuilder builder = new StringBuilder();
        errorMsg.forEach((t1, t2) -> {
                    builder.append("属性[" + t1 + "]" + t2 + "/r/t");
                }
        );
        return builder.toString();
    }
}
