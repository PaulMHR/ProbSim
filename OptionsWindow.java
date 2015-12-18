/*
 * Copyright (C) 2015 Paul Rudmik
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package probability.simulator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class OptionsWindow extends JFrame implements ChangeListener, ActionListener{
    // PANEL 1 COMPONENTS:
        JPanel panel1 = new JPanel();

        static double PSD = 0.5;
        static double PFD = 1 - PSD;
        static int maxTrialsD = 100;
        static double probD = 5; //P(X = probD) tested
        
        static int scale = 10000; //amount of digits simulated in sliders
        static int maxTrialsCap = 500; //maximum cap on trials

        JSlider PS = new JSlider(0, scale, (int)(PSD * scale));
        JSlider PF = new JSlider(0, scale, (int)(PFD * scale));
        JSlider maxTrials = new JSlider(1, maxTrialsCap, maxTrialsD);

        JLabel PSLabel = new JLabel(" P(Success): ", JLabel.RIGHT);
        JLabel PFLabel = new JLabel(" P(Failure):", JLabel.RIGHT);
        JLabel maxTrialsLabel = new JLabel(" # of Trials:", JLabel.RIGHT);

        JTextField PSField = new JTextField("" + PS.getValue()/(double)scale);
        JTextField PFField = new JTextField("" + PF.getValue()/(double)scale);
        JTextField maxTrialsField = new JTextField("" + maxTrials.getValue());
    
    //PANEL 2 COMPONENTS:
        JPanel panel2 = new JPanel();

        JButton okay = new JButton("Okay");
        JButton reset = new JButton("Reset to Default");
    
    // OBJECT COMPONENTS:
        static double[] vals = {PSD, PFD, maxTrialsD};
        boolean closing = false;
        
    
    public OptionsWindow() {
        super("Probability Simulator Options");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        panel1.setLayout(new GridLayout(3, 2, 10, 10));
        
        PS.addChangeListener(this);
        PF.addChangeListener(this);
        maxTrials.addChangeListener(this);  
        
        PSField.setEditable(false);
        PFField.setEditable(false);
        maxTrialsField.setEditable(false);

        panel1.add(PSLabel);
        panel1.add(PS);
        panel1.add(PSField);
        panel1.add(PFLabel);
        panel1.add(PF);
        panel1.add(PFField);
        panel1.add(maxTrialsLabel);
        panel1.add(maxTrials);
        panel1.add(maxTrialsField);
        
        add(panel1);
        
        panel2.setLayout(new FlowLayout());
       
        reset.addActionListener(this);
        panel2.add(okay);
        panel2.add(reset);
        panel2.setSize(20, 10);
        
        add(panel2);
       
        setSize(550, 300);
        
        setVisible(true);
    }
    
    public void setVals(int[] vals) {
        PS.setValue(vals[0]);
        PF.setValue(vals[1]);
        maxTrials.setValue(vals[2]);
    }
    
    public void actionPerformed (ActionEvent event) {
        String cmd = (String) event.getActionCommand();
        if (cmd.equals("Reset to Default"))
            resetValues();
    }
    
    public void resetValues() {
        PS.setValue((int)(PSD * scale));
        PF.setValue(scale - (int)PSD);
        maxTrials.setValue(maxTrialsD);
    }
        
    public void closeWindow() {
        setVisible(false);
    }
    
    public double[] getValues() {
        vals[0] = PS.getValue()/(double)scale;
        vals[1] = PF.getValue()/(double)scale;
        vals[2] = maxTrials.getValue();
        return vals;
    }
    
    public void stateChanged(ChangeEvent event) {
        JSlider source = (JSlider) event.getSource();
        if (source == PS) {
            PSField.setText("" + PS.getValue() / (double)scale);
            PF.setValue(scale - PS.getValue());
            PFField.setText("" + PF.getValue() / (double)scale);
        } else if (source == PF) {
            PFField.setText("" + PF.getValue() / (double)scale);
            PS.setValue(scale - PF.getValue());
            PSField.setText("" + PS.getValue() / (double)scale);
        } else if (source == maxTrials) {
            maxTrialsField.setText("" + maxTrials.getValue());
        }
        
        if (source.getValueIsAdjusting() != true) {
            getValues();
        }
    }
    
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException exc) {
            // ignore error
        }
    }
}
    
