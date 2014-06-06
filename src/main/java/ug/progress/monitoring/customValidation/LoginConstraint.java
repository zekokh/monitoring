package ug.progress.monitoring.customValidation;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by Ruslan Zekokh.
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
