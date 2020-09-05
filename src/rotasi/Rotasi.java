/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rotasi;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author User Sarina
 */
public class Rotasi extends JPanel {
    MyCanvas canvas;
    
    JSlider sliderRotateTheta, sliderRotateX, sliderRotateY, sliderWidth;

    double rotateTheta = 0.0;
    double rotateX = 0.0;
    double rotateY = 0.0;
    double width = 1.0f;
    
public Rotasi() {
    super(new BorderLayout());
    JPanel controlPanel = new JPanel(new GridLayout(3, 3));
    add(controlPanel, BorderLayout.NORTH);
    
    controlPanel.add(new JLabel("Rotate(Theta, ox, oy) :"));
    sliderRotateTheta = setSlider(controlPanel, JSlider.HORIZONTAL, 0, 360, 0, 90, 45);
    
    JPanel subPanel = new JPanel();
    subPanel.setLayout( new GridLayout(1, 2));
    
    sliderRotateX = setSlider(subPanel, JSlider.HORIZONTAL, 0, 300, 150, 150, 50);
    sliderRotateY = setSlider(subPanel, JSlider.HORIZONTAL, 0, 300, 150, 150, 50);
    controlPanel.add(subPanel);
    
    JLabel label4 = new JLabel("Width Control:", JLabel.RIGHT);
    sliderWidth = new JSlider(JSlider.HORIZONTAL, 0, 20, 1);
    sliderWidth.setPaintTicks(true);
    sliderWidth.setMajorTickSpacing(5);
    sliderWidth.setMinorTickSpacing(1);
    sliderWidth.setPaintLabels(true);
    sliderWidth.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e){
            width = sliderWidth.getValue();
            canvas.repaint();
        }
    }); 
    JPanel widthPanel = new JPanel();
    widthPanel.setLayout(new GridLayout(1, 2));
    widthPanel.add(label4);
    widthPanel.add(sliderWidth);
    add(widthPanel, BorderLayout.SOUTH);
    
    canvas = new MyCanvas();
    add(canvas, "Center");
}

public JSlider setSlider(JPanel panel, int orientation, int minimumValue, int maximumValue, int initValue, 
        int majorTickSpacing,int minorTickSpacing) {
    JSlider slider = new JSlider(orientation, minimumValue, maximumValue, initValue);
    slider.setPaintTicks(true);
    slider.setMajorTickSpacing(majorTickSpacing);
    slider.setMinorTickSpacing(minorTickSpacing);
    slider.setPaintLabels(true);
    slider.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            JSlider tempSlider = (JSlider) e.getSource();
            if (tempSlider.equals(sliderRotateTheta)) {
                rotateTheta = sliderRotateTheta.getValue()*Math.PI / 180;
                canvas.repaint();
            } else if(tempSlider.equals(sliderRotateX)) {
                rotateX = sliderRotateX.getValue();
                canvas.repaint();
            } else if(tempSlider.equals(sliderRotateY)) {
                rotateY = sliderRotateX.getValue();
                canvas.repaint();
            } 
        }
    });
        panel.add(slider);
        return slider;
}
    
class MyCanvas extends Canvas {
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        
        g2D.rotate(rotateTheta, rotateX, rotateY);
        
        BasicStroke stroke = new BasicStroke((float) width);
        g2D.setStroke(stroke);
        
        drawHome(g2D);
    }
    public void drawHome(Graphics2D g2D) {
        Line2D line1 = new Line2D.Float(100f, 200f, 200f, 200f);
        Line2D line2 = new Line2D.Float(100f, 200f, 100f, 100f);
        Line2D line3 = new Line2D.Float(100f, 100f, 150f, 50f);
        Line2D line4 = new Line2D.Float(150f, 50f, 200f, 100f);
        Line2D line5 = new Line2D.Float(200f, 100f, 200f, 200f);
        Line2D line6 = new Line2D.Float(140f, 200f, 140f, 150f);
        Line2D line7 = new Line2D.Float(140f, 150f, 160f, 150f);
        Line2D line8 = new Line2D.Float(160f, 150f, 160f, 200f);
        
        g2D.draw(line1);
        g2D.draw(line2);
        g2D.draw(line3);
        g2D.draw(line4);
        g2D.draw(line5);
        g2D.draw(line6);
        g2D.draw(line7);
        g2D.draw(line8);
    
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f = new JFrame();
        f.setTitle("Rotasi House Transformation - Sarina Bt Mansur");
        f.getContentPane().add(new Rotasi());
        f.setDefaultCloseOperation(1);
        f.setSize(900, 600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
}
