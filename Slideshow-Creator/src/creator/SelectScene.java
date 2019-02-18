/**
 * SelectScene.java
 * Scene in which users choose which pictures to use
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/17/19
 */
package creator;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import core.*;

public class SelectScene extends Scene
{
    /**
     * a label that shows the directory
     */
    JLabel directoryLabel;

    /**
     * sets up select scene with GUI stuff other than things dependent upon directory
     * 
     * @author austinvickers
     * @author Timothy Couch
     */
    public SelectScene()
    {
        this.setSize(800, 600);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        directoryLabel = new JLabel("Select Scene! Directory: ");
        this.add(directoryLabel);

        JButton testButton = new JButton("Check this bad boi out");
        this.add(testButton);
        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("GET BODIED");
            }
        });
    }

    /**
     * initialize - opens the images and sets up the scene for use
     * 
     * @precondition must run after project directory has been determined
     */
    @Override
    public void initialize()
    {
        directoryLabel.setText(directoryLabel.getText() + SceneHandler.singleton.getDirectory());
    }

    /**
     * show - shows the scene
     */
    @Override
    public void show()
    {
        super.show();
    }
}
