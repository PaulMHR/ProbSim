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
import java.awt.event.*;

public class ProbEvent implements ActionListener, Runnable {
    
    ProbabilitySimulator gui;
    Thread rolling;
    
    OptionsWindow opt;
    
    double s_prob = OptionsWindow.PSD;
    double f_prob = OptionsWindow.PFD;
    int trials = 0;
    int s_count = 0;
    int f_count = 0;
    int max_trials = OptionsWindow.maxTrialsD;
    
    double[] p_vals = new double[3];
    double[] d_p_vals = new double[3];
    int[] i_vals = new int[3];
    int[] d_i_vals = new int[3];
    
    public ProbEvent (ProbabilitySimulator in) {
        gui = in;
        p_vals[0] = s_prob;
        p_vals[1] = f_prob;
        p_vals[2] = max_trials;
        i_vals[0] = s_count;
        i_vals[1] = f_count;
        i_vals[2] = trials;
        d_p_vals = p_vals;
        d_i_vals = i_vals;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Roll":
                startRolling();
                break;
            case "Stop":
                stopRolling();
                break;
            case "Reset":
                reset();
                break;
            case "Options":
                startOptions();
                break;
            case "Okay":
                closeOptions();
                break;
        }
    }

    private void startRolling() {
        rolling = new Thread(this);
        rolling.start();
        gui.stop.setEnabled(true);
        gui.roll.setEnabled(false);
        gui.reset.setEnabled(false);
    }
    
    private void roll() {
        double roll = Math.random();
        if (roll > p_vals[1]) {
            s_count++;
            gui.sNum.setText("" + s_count);
        } else {
            f_count++;
            gui.fNum.setText("" + f_count);
        }
        trials++;
        gui.trialsField.setText("" + trials);
    }
    
    private void stopRolling() {
        rolling = null;
        gui.stop.setEnabled(false);
        gui.roll.setEnabled(true);
        gui.reset.setEnabled(true);        
    }
    
    private void reset() {
        gui.stop.setEnabled(false);
        gui.roll.setEnabled(true);
        gui.reset.setEnabled(false);
        s_count = 0;
        f_count = 0;
        trials = 0;
        gui.sNum.setText("0");
        gui.fNum.setText("0");
        gui.trialsField.setText("0");
        gui.probSField.setText("" + p_vals[0]);
        gui.probFField.setText("" + p_vals[1]);
    }
    
    private void startOptions() {
        opt = new OptionsWindow();
        opt.okay.addActionListener(this);
        stopRolling();
    }

    private void closeOptions() {
        p_vals = opt.getValues();
        OptionsWindow.PSD = p_vals[0];
        OptionsWindow.PFD = p_vals[1];
        OptionsWindow.maxTrialsD = (int)p_vals[2];
        reset();
        opt.setVisible(false);
    }
    
    @Override
    public void run() {
        while (trials < p_vals[2] && rolling == Thread.currentThread()) {
            roll();
            try {
                Thread.sleep(35);
            } catch (InterruptedException e) {
                // do nothin'
            }
        }
        rolling = null;
    } 
}
