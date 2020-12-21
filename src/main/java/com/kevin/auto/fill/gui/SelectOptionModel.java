package com.kevin.auto.fill.gui;

import javax.swing.*;

public class SelectOptionModel extends DefaultComboBoxModel<SelectOption> {
    public SelectOptionModel(SelectOption[] items) {
        super(items);
    }

    @Override
    public Object getSelectedItem() {
        SelectOption option = (SelectOption) super.getSelectedItem();
        return ((SelectOption) super.getSelectedItem()).getOptionDesc();
    }
}
