/*******************************************************************************
 * Copyright 2011 Krzysztof Otrebski
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package pl.otros.logview.gui.actions;

import pl.otros.logview.gui.OtrosApplication;
import pl.otros.logview.gui.StatusObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.logging.Logger;

public class CheckForNewVersionAction extends CheckForNewVersionAbstract {

  private static final Logger LOGGER = Logger.getLogger(CheckForNewVersionAction.class.getName());

  public CheckForNewVersionAction(OtrosApplication otrosApplication) {
    super(otrosApplication);
    putValue(Action.NAME, "Check for new version");

  }

  protected void handleError(Exception e) {
    String message = "Problem with checking new version: " + e.getLocalizedMessage();
    JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.ERROR_MESSAGE);
    LOGGER.warning("Error when checking new version" + e.getMessage());
		StatusObserver statusObserver = getOtrosApplication().getStatusObserver();
		if (statusObserver != null) {
      statusObserver.updateStatus("Error when checking new version" + e.getMessage(), StatusObserver.LEVEL_WARNING);
    }
  }

  protected void handleVersionIsUpToDate(String current) {
    Object message = "Your version is up to date!";
    JOptionPane.showMessageDialog(null, message);
  }

  protected void handleNewVersionIsAvailable(String current, String running) {
    Object message = null;
    JPanel panel = new JPanel(new GridLayout(2, 1));
    panel.add(new JLabel("Your version is " + running + ", current version is " + current));
    JButton button = new JButton("Open download page");
    button.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Desktop.getDesktop().browse(new URI("http://code.google.com/p/otroslogviewer/downloads/list?q=label:Featured&src=app"));
        } catch (Exception e1) {
          String msg = "Can't open browser with download page: " + e1.getMessage();
          LOGGER.severe(msg);
          getOtrosApplication().getStatusObserver().updateStatus(msg, StatusObserver.LEVEL_ERROR);
        }
      }
    });
    panel.add(button);
    message = panel;
    JOptionPane.showMessageDialog(null, message);
  }

}
