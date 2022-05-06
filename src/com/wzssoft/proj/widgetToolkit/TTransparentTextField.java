package com.wzssoft.proj.widgetToolkit;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TTransparentTextField extends JTextField {
    public static final int ASCII = 0;
    public static final int NUMBER_AND_LETTER = 1;
    public static final int NUMBER = 2;
    public static final int NOTHING = 99;

    private boolean valid = false;  //是否可以使用数据
    private int inputType = 0;
    private ArrayList<Integer> additionalKeyCodeList = new ArrayList<Integer>();   //额外允许的按键

    public TTransparentTextField(int inputType) {
        this.inputType = inputType;

        //可能有副作用
        Action beep = this.getActionMap().get(DefaultEditorKit.deletePrevCharAction);
        beep.setEnabled(false);

        init();
    }

    private void init() {
        this.setEditable(false);
        this.setBorder(null);
    }

    //验证当前输入文本框内容是否合法
    public String checkInputAvailability(String str) {
        try {
            switch (inputType) {
                case 2:
                    return "0123456789".contains(str) ? str : "";

                default:
                    return "";
            }
        } catch (Exception e) {
            System.out.println("TTransparentTextField.checkInputAvailability");
            return "";
        }
    }

    //自动改变数据格式数字
    public String autoConvertNumber(String str) {
        int range = 5;
        //除去字符串开头的0
        if (str.length() > 1 && str.charAt(0) == '0') str = str.substring(1);
        //位数不能超过5位，否则后面均为无效输入,注意这里的subString包前不包后
        if (str.length() > range) str = str.substring(0, range);
        return str;
    }

    public void doBackspace() {
        String text = this.getText();
        int i = text.length();
        if (i > 0) {
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                this.setText("0");
            } else {
                this.setText(text);// 显示新的文本
            }
        }
    }

    public void executeKeyCode(int keyCode) {
        switch (keyCode) {

            //后退键
            case KeyEvent.VK_BACK_SPACE:
                doBackspace();
                break;
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public void setAdditionalKeyCodeList(ArrayList<Integer> additionalKeyCodeList) {
        this.additionalKeyCodeList = additionalKeyCodeList;
    }

    public ArrayList<Integer> getAdditionalKeyCodeList() {
        return additionalKeyCodeList;
    }
}
