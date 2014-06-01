package ug.progress.monitoring.customValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ug.progress.monitoring.entity.UserEntity;
import ug.progress.monitoring.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ZR on 01.06.2014.
 */
public class LoginConstraintValidator implements ConstraintValidator<LoginConstraint, UserEntity> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    UserService userService;

    @Override
    public void initialize(final LoginConstraint loginConstraint) {
    }

    /**
     * Проверяет валидность логина
     * @param user валидируемый объект
     * @param constraintValidatorContext контекст
     * @return валиден или нет
     */
    @Override
    public boolean isValid(final UserEntity user, ConstraintValidatorContext constraintValidatorContext) {
        logger.debug("apply login validation");
        boolean unique = false;
        if ((user == null)||(userService==null)) {
            return true;
        }
        try {
            final String idValue = BeanUtils.getProperty(user, "id");
            final String loginValue = BeanUtils.getProperty(user, "login");

            if ((idValue == null) || (idValue.isEmpty())) {
                unique = userService.isLoginUnique(loginValue, 0L);
            } else {
                unique = userService.isLoginUnique(loginValue, Long.parseLong(idValue));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return unique;
    }
}
