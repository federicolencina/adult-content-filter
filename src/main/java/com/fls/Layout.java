// #=========================================================================#
//  #               This file is part of Adult Content Filter               #
//  #        https://github.com/federicolencina/adult-content-filter        #
//  #                                                                       #
//  #                      GNU General Public License                       #
//  #                                                                       #
//  #     This program is free software; you can redistribute it and/or     #
//  #    modify it under the terms of the GNU General Public License as     #
//  #    published by the Free Software Foundation; either version 3 of     #
//  #          the License, or at your option any later version.            #
//  #                                                                       #
//  #  This program is distributed in the hope that it will be useful, but  #
//  #       WITHOUT ANY WARRANTY; without even the implied warranty of      #
//  #          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.         #
//  #          See the GNU General Public License for more details.         #
//  #                                                                       #
//  #        You should have received a copy of the GNU General Public      #
//  #              License along with this program. If not, see             #
//  #                    <https://www.gnu.org/licenses/>.                   #
//  #                                                                       #
//  #                    Copyright 2024 Federico Lencina                    #
// #=========================================================================#

package com.fls;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Layout {
    private JPanel Layout;
    private JLabel titleLabel;
    private JCheckBox primaryCheckBox;
    private JCheckBox defaultCheckBox;
    private JButton applySettingsButton;
    private JLabel osLabel;
    private JProgressBar progressBar;

    Main main = new Main();

    public Layout() {
        osLabel.setText(System.getProperty("os.name").toUpperCase());
        primaryCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (primaryCheckBox.isSelected() && defaultCheckBox.isSelected()) {
                    defaultCheckBox.setSelected(false);
                }
                if (primaryCheckBox.isSelected() || defaultCheckBox.isSelected()) {
                    applySettingsButton.setEnabled(true);
                } else {
                    applySettingsButton.setEnabled(false);
                }
            }
        });
        defaultCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (defaultCheckBox.isSelected() && primaryCheckBox.isSelected()) {
                    primaryCheckBox.setSelected(false);
                }
                if (primaryCheckBox.isSelected() || defaultCheckBox.isSelected()) {
                    applySettingsButton.setEnabled(true);
                } else {
                    applySettingsButton.setEnabled(false);
                }
            }
        });
        applySettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (primaryCheckBox.isSelected()) {
                    main.onPrimaryCheckBox(true);
                } else {
                    if (defaultCheckBox.isSelected()) {
                        main.onDefaultCheckbox(true);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        boolean isLinux = System.getProperty("os.name").toLowerCase().startsWith("linux");
        if (isLinux) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            } catch (ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        } else {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        }
        JFrame frame = new JFrame("Layout");
        frame.setContentPane(new Layout().Layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 360);
        frame.setTitle("Adult Content Filter");
    }
}
