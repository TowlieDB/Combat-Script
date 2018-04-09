package org.dreambot.core;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.NPC;

import javax.swing.*;
import java.util.List;

public class GUI extends javax.swing.JFrame {

    private Main s;

    public GUI(Main s) {
        this.s = s;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        this.setVisible(true);
        this.setTitle("Combat Script");

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        loadOpponents = new javax.swing.JButton();
        startBtn = new javax.swing.JButton();
        foodTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);
        loadOpponents.setText("Load opponents");
        loadOpponents.addActionListener(this::loadOpponentsActionPerformed);

        startBtn.setText("Start");
        startBtn.addActionListener(this::startBtnActionPerformed);

        foodTextField.setText("Lobster");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(loadOpponents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(startBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(foodTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(loadOpponents)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(foodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(startBtn))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void loadOpponentsActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultListModel<String> model = new DefaultListModel<>();

        List<NPC> npcs = s.getNpcs().all();
        for (NPC npc : npcs) {
            model.addElement(npc.getName() + " [" + npc.getLevel() + "]");
        }
        jList1.setModel(model);
    }

    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (!jList1.isSelectionEmpty()){
            List<String> monsters = jList1.getSelectedValuesList();
            for (String m : monsters) {
                s.monsterNames.add(m.split(" \\[")[0]);
            }
            s.food = foodTextField.getText();
            s.setRunning(true);
            dispose();
        } else {
            MethodProvider.log("Please select a monster.");
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JTextField foodTextField;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadOpponents;
    private javax.swing.JButton startBtn;
    // End of variables declaration
}
