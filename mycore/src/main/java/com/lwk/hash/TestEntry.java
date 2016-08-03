package com.lwk.hash;

/**
 * Created by liwenke on 16/7/27.
 */
public class TestEntry {

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestEntry)) return false;

        TestEntry testEntry = (TestEntry) o;

        if (getAge() != testEntry.getAge()) return false;
        return getName() != null ? getName().equals(testEntry.getName()) : testEntry.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getAge();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
