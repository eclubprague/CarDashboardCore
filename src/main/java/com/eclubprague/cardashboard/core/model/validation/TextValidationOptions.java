package com.eclubprague.cardashboard.core.model.validation;

import com.eclubprague.cardashboard.core.utils.StringValidator;

/**
 * Created by Michael on 16.09.2015.
 */
public enum TextValidationOptions {
    REGEX {
        @Override
        public boolean isValid(String input, Object data) {
            return StringValidator.isValid(input, (String) data);
        }
    }, EMAIL {
        @Override
        public boolean isValid(String input, Object data) {
            return StringValidator.isEmail(input);
        }
    }, INTEGER {
        @Override
        public boolean isValid(String input, Object data) {
            return StringValidator.isInteger(input);
        }
    }, DECIMAL {
        @Override
        public boolean isValid(String input, Object data) {
            return StringValidator.isDecimal(input);
        }
    }, REQUIRED {
        @Override
        public boolean isValid(String input, Object data) {
            return !StringValidator.isEmpty(input);
        }
    };

    public abstract boolean isValid(String input, Object data);
}
