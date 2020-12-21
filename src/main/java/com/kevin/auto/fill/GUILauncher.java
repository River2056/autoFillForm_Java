package com.kevin.auto.fill;

import com.kevin.auto.fill.gui.SelectOption;
import com.kevin.auto.fill.gui.SelectOptionModel;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GUILauncher {
    private MainApp app;
    private JPanel panalMain;
    private JButton gen2Btn;
    private JTextPane msgArea;
    private JLabel genTitleLabel;
    private JComboBox tenderWaySelect;
    private JLabel tenderWayLabel;
    private JComboBox genSelect;
    private JButton actionBtn;
    private JLabel awardMultiWay;
    private JComboBox awardWaySelect;
    private JComboBox multiAwardWaySelect;

    public GUILauncher() {
        app = new MainApp();
        SelectOption[] selectOptions = new SelectOption[] {
                new SelectOption("2", "二代"),
                new SelectOption("3", "三代")
        };
        SelectOption[] tenderWayOptions = new SelectOption[] {
                new SelectOption("12", "公開取得電子報價單"),
                new SelectOption("2", "公開取得報價單與企劃書"),
                new SelectOption("1", "公開招標"),
                new SelectOption("4", "限制性招標(經公開評選或公開徵求)"),
                new SelectOption("5", "選擇性招標(建立合格廠商名單)"),
                new SelectOption("7", "選擇性招標(建立合格廠商名單後續邀標)"),
                new SelectOption("3", "選擇性招標(個案)"),
                new SelectOption("10", "電子競價")
        };
        SelectOption[] awardWay = new SelectOption[] {
                new SelectOption("", "請選擇"),
                new SelectOption("1", "最低標"),
                new SelectOption("2", "最高標"),
                new SelectOption("3", "最有利標"),
        };
        SelectOption[] multipleAward = new SelectOption[] {
                new SelectOption("", "請選擇"),
                new SelectOption("Y", "複數決"),
                new SelectOption("N", "非複數決")
        };
        SelectOptionModel selectOptionModel = new SelectOptionModel(selectOptions);
        SelectOptionModel optionsModel = new SelectOptionModel(tenderWayOptions);
        SelectOptionModel awardWayModel = new SelectOptionModel(awardWay);
        SelectOptionModel multipleAwardModel = new SelectOptionModel(multipleAward);
        genSelect.setModel(selectOptionModel);
        tenderWaySelect.setModel(optionsModel);
        awardWaySelect.setModel(awardWayModel);
        multiAwardWaySelect.setModel(multipleAwardModel);
        actionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StyledDocument doc = msgArea.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                MutableAttributeSet attrs = msgArea.getInputAttributes();
                int size = StyleConstants.getFontSize(attrs);
                StyleConstants.setFontSize(attrs, 26);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
                doc.setCharacterAttributes(0, doc.getLength(), attrs, false);

                String generationString = (String) genSelect.getSelectedItem();
                String tenderWayString = (String) tenderWaySelect.getSelectedItem();
                String awardWayString = (String) awardWaySelect.getSelectedItem();
                String multipleAwardWayString = (String) multiAwardWaySelect.getSelectedItem();
                SelectOption selectOption = Arrays.stream(selectOptions).filter(el -> el.getOptionDesc().equals(generationString)).collect(Collectors.toList()).get(0);
                SelectOption tenderWayOption = Arrays.stream(tenderWayOptions).filter(el -> el.getOptionDesc().equals(tenderWayString)).collect(Collectors.toList()).get(0);
                SelectOption awardWayOption = Arrays.stream(awardWay).filter(el -> el.getOptionDesc().equals(awardWayString)).collect(Collectors.toList()).get(0);
                SelectOption multipleAwardWayOption = Arrays.stream(multipleAward).filter(el -> el.getOptionDesc().equals(multipleAwardWayString)).collect(Collectors.toList()).get(0);
                msgArea.setText(String.format("%s填表中...\n\n招標方式: %s\n\n決標方式: %s - 複數決: %s", generationString, tenderWayString, awardWayString, multipleAwardWayString));
                msgArea.setSize(300, 300);
                msgArea.setVisible(true);
                app.runAutoFill2Gen(tenderWayOption.getOption(), awardWayOption.getOption(), multipleAwardWayOption.getOption());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AutoFillForm");
        frame.setContentPane(new GUILauncher().panalMain);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
