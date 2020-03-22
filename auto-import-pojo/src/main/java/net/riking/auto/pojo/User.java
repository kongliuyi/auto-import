package net.riking.auto.pojo;

import lombok.Builder;
import lombok.Getter;

import net.riking.auto.commmon.annotation.CnSize;
import net.riking.auto.commmon.annotation.Order;
import net.riking.auto.commmon.annotation.SourceFile;
import net.riking.auto.commmon.enums.FileType;
import net.riking.auto.commmon.validator.ValidationResult;
import net.riking.auto.commmon.validator.ValidationUtils;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Description
 * @Author kongLiuYi
 * @Date 2020/3/2 0002 13:37
 */
@Getter
@Builder
@Entity
@Table(name = "USERS")
@SourceFile(value = "USER", type = FileType.TXT_DELIMITER, delimiter = "|", head = 3)
public class User implements Serializable {
    private Long id;

    @NotNull(message = "名字不能为空")
    @CnSize(max = 8,message = "名字长度不能大于8")
    private String userName;

    @Size(min = 6,max = 30,message = "地址应该在6-30字符之间")
    private String password;

    @CnSize(max = 8)
    @Order(order=2)
    private String address;

    @Order(order=1)
    private String  brcd;


    public static void main(String[] args) {


        User user= User.builder()
                .userName("我爱你1W6")
                .password("12")
                .address("爱情公寓十八号")
                .build();
        ValidationResult result = ValidationUtils.validateEntity(user);

    }
/*
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> List<String> validate(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }*/
}
