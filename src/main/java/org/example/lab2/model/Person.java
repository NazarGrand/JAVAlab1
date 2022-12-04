package org.example.lab2.model;

/**
 * Person - описує людину. Поля fullName, yearOfBirth, retired - чи пенсіонер
 */
public class Person {
    private String fullName;
    private int yearOfBirth;
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
