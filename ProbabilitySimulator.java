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

/**
 *
 * @author paulrudmik
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ProbabilitySimulator extends JFrame {

    // PROBABILITYSIMULATOR COMPONENTS
        ProbEvent probSim = new ProbEvent(this);
        int[] defVals = new int[2];
    
    // ROW 1 COMPONENTS
        JPanel row1 = new JPanel();
        JLabel probSLabel = new JLabel("P(S): ");
        JTextField probSField = new JTextField(5);
        JLabel probFLabel = new JLabel("P(F): ");
        JTextField probFField = new JTextField(5);
    
    // ROW 2 COMPONENTS
        JPanel row2 = new JPanel();
        JLabel trialsLabel = new JLabel("Number of trials: ");
        JTextField trialsField = new JTextField(2);        
        
    // ROW 3 COMPONENTS
        JPanel row3 = new JPanel();
        JButton roll = new JButton("Roll");
        JButton stop = new JButton("Stop");
        JButton reset = new JButton("Reset");        
        
    // ROW 4 COMPONENTS
        JPanel row4 = new JPanel();
        JLabel yieldLabel = new JLabel("Your Roll Yielded: ");
        JTextField sNum = new JTextField(2);
        JLabel sLabel = new JLabel("successes and ");
        JTextField fNum = new JTextField(2);
        JLabel fLabel = new JLabel("failures.");
        
    
    // ROW 5 COMPONENTS
        JPanel row5 = new JPanel();
        JButton options = new JButton("Options");
    
    public ProbabilitySimulator() {
        super("Binomial Simulator!");
        
        
        //setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout theGrid = new GridLayout(5, 1, 1, 1);
        setLayout(theGrid);
       
        // LOTTOEVENT OBJECTS
            stop.addActionListener(probSim);
            roll.addActionListener(probSim);
            reset.addActionListener(probSim);
            options.addActionListener(probSim);
                
        // ROW 1 INITIALIZES
            //FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 1, 1);
            //row1.setLayout(layout1);
            row1.add(probSLabel);
            row1.add(probSField);
            row1.add(probFLabel);
            row1.add(probFField);
            probSField.setEditable(false);
            probFField.setEditable(false);
            add(row1);
            
            probSField.setText("" + probSim.s_prob);
            probFField.setText("" + probSim.f_prob);
        
        // ROW 2 INTIALIZES
            //row2.setLayout(layout1);
            row2.add(trialsLabel);
            row2.add(trialsField);
            trialsField.setEditable(false);
            add(row2);
            
            trialsField.setText("" + probSim.trials);
        
        // ROW 3 INITIALIZES
            //row3.setLayout(layout1);
            stop.setEnabled(false);
            row3.add(roll);
            row3.add(stop);
            row3.add(reset);
            add(row3);
        
        // ROW 4 INITIALIZES
            //GridLayout layout4 = new GridLayout(2, 3, 20, 10);
            //row4.setLayout(layout1);
            row4.add(yieldLabel);
            row4.add(sNum);
            row4.add(sLabel);
            row4.add(fNum);
            row4.add(fLabel);
            sNum.setEditable(false);
            fNum.setEditable(false);
            add(row4);
            
            sNum.setText("" + probSim.s_count);
            fNum.setText("" + probSim.f_count);
        
        // ROW 5 INTIALIZES
            //row5.setLayout(layout1);
            row5.add(options);
            add(row5);
            
        setSize(400, 250);
        setVisible(true);
    }  
        
    public static void main(String[] args) {
        ProbabilitySimulator frame = new ProbabilitySimulator();
    }
    
}
