package org.example.lab2.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import javax.validation.constraints.AssertTrue;
import java.io.Serializable;
import java.util.Set;

/**
 * Person - описує людину. Поля fullName, yearOfBirth, retired - чи пенсіонер
 */
public class Person implements Serializable {
    @Size(min=1, max = 15, message = "{Size.fullName}")
    private String fullName;

    @Max(value = 2022, message = "{Max.yearOfBirth}")
    private int yearOfBirth;
    @AssertTrue
    private boolean retired;

    public Person() {
    }

    public Person(String fullName, int yearOfBirth, boolean retired) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.retired = retired;
    }

    public static class Builder<T extends Person.Builder<T>> {
        private String fullName;
        private int yearOfBirth;
        private boolean retired;

        public Builder() {}

        public T fullName(String fullName) {
            this.fullName = fullName;
            return (T) this;
        }

        public T yearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return (T) this;
        }

        public T retired(boolean retired) {
            this.retired = retired;
            return (T) this;
        }

        public Person validate(Person obj) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Person>> violations = validator.validate(obj);
            if (violations.isEmpty()) {
                return obj;
            } else {
                StringBuilder sb = new StringBuilder();
                for (var violation : violations) {
                    sb.append(violation.getInvalidValue()).append(" : ").append(violation.getMessage());
                }
                return null;
            }
        }
    }

    protected Person(Builder<?> builder) {
        this.fullName = builder.fullName;
        this.yearOfBirth = builder.yearOfBirth;
        this.retired = builder.retired;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    @Override
    public String toString() {
        return " Person{"
                + "fullName='" + fullName + '\''
                + ", yearOfBirth=" + yearOfBirth
                + ", retired=" + retired
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        if (yearOfBirth != person.yearOfBirth) return false;
        if (retired != person.retired) return false;
        return fullName.equals(person.fullName);
    }

    @Override
    public int hashCode() {
        int result = fullName.hashCode();
        result = 31 * result + yearOfBirth;
        result = 31 * result + (retired ? 1 : 0);
        return result;
    }
}
