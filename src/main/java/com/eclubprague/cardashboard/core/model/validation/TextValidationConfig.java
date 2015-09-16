package com.eclubprague.cardashboard.core.model.validation;

import com.eclubprague.cardashboard.core.model.resources.StringResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 16.09.2015.
 */
public class TextValidationConfig {

    private final List<Rule> rules;
    private StringResource errorMessage;

    private TextValidationConfig(List<Rule> rules) {
        this.rules = rules;
    }

    public boolean isValid(String input) {
        for (Rule rule : rules) {
            if (!rule.option.isValid(input, rule.data)) {
                errorMessage = rule.message;
                return false;
            }
        }
        return true;
    }

    public StringResource getErrorMessage() {
        return errorMessage;
    }

    public static Builder Builder() {
        return new Builder();
    }

    public static class Result {
        public final boolean success;
        public final StringResource errorMessage;

        public Result(boolean success, StringResource errorMessage) {
            this.errorMessage = errorMessage;
            this.success = success;
        }
    }

    public static class Builder {
        private final List<Rule> rules;

        public Builder() {
            rules = new ArrayList<>();
        }

        public Builder addRule(TextValidationOptions option, StringResource message) {
            rules.add(new Rule(option, message, null));
            return this;
        }

        public Builder addRule(TextValidationOptions option, StringResource message, Object data) {
            rules.add(new Rule(option, message, data));
            return this;
        }

        public Builder matches(StringResource regex, StringResource message) {
            addRule(TextValidationOptions.REGEX, message, regex);
            return this;
        }

        public Builder required(StringResource message) {
            addRule(TextValidationOptions.REQUIRED, message);
            return this;
        }

        public Builder decimal(StringResource message) {
            addRule(TextValidationOptions.DECIMAL, message);
            return this;
        }

        public TextValidationConfig build() {
            return new TextValidationConfig(rules);
        }

    }

    private static class Rule {
        public final TextValidationOptions option;
        public final StringResource message;
        public final Object data;

        public Rule(TextValidationOptions option, StringResource message, Object data) {
            this.data = data;
            this.option = option;
            this.message = message;
        }
    }
}
