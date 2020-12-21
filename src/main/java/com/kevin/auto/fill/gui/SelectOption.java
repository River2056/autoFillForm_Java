package com.kevin.auto.fill.gui;

public class SelectOption {
    private String option;
    private String optionDesc;

    public SelectOption() {
    }

    public SelectOption(String option, String optionDesc) {
        this.option = option;
        this.optionDesc = optionDesc;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOptionDesc() {
        return optionDesc;
    }

    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectOption that = (SelectOption) o;

        if (option != null ? !option.equals(that.option) : that.option != null) return false;
        return optionDesc != null ? optionDesc.equals(that.optionDesc) : that.optionDesc == null;
    }

    @Override
    public int hashCode() {
        int result = option != null ? option.hashCode() : 0;
        result = 31 * result + (optionDesc != null ? optionDesc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getOptionDesc();
    }
}
