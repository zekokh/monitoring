package ug.progress.monitoring.customValidation;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by ZR on 01.06.2014.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=LoginConstraintValidator.class)

public @interface LoginConstraint {
    String message() default "Такой логин уже существует в системе";
    Class[] groups() default {};
    Class[] payload() default {};
}
