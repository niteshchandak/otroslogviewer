/*
 * Copyright 2012 Krzysztof Otrebski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.otros.logview.gui.actions;

import pl.otros.logview.gui.Icons;
import pl.otros.logview.gui.LogDataTableModel;
import pl.otros.logview.gui.LogViewPanelWrapper;
import pl.otros.logview.gui.OtrosApplication;
import pl.otros.logview.gui.table.TableColumns;
import pl.otros.logview.logging.GuiJulHandler;

import java.awt.event.ActionEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

public class ShowOlvLogs extends OtrosAction {

  private LogViewPanelWrapper logViewPanelWrapper;

  public ShowOlvLogs(OtrosApplication otrosApplication) {
    super(otrosApplication);
    putValue(NAME, "Show internal logs");
    putValue(SHORT_DESCRIPTION, "Show internal OLV logs");
    putValue(SMALL_ICON, Icons.LEVEL_INFO);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
		if (logViewPanelWrapper == null) {
      LogDataTableModel dataTableModel = new LogDataTableModel();
      dataTableModel.setDataLimit(10000);
      logViewPanelWrapper = new LogViewPanelWrapper("Olv logs", null, TableColumns.JUL_COLUMNS, dataTableModel,getOtrosApplication());
      logViewPanelWrapper.goToLiveMode();

      logViewPanelWrapper.addHierarchyListener(new HierarchyListener() {

        @Override
        public void hierarchyChanged(HierarchyEvent e) {
          if (e.getChangeFlags() == 1 && e.getChanged().getParent() == null) {
            GuiJulHandler.stop();
          }
        }
      });
    }

    getOtrosApplication().addClosableTab("OLV internal Logs","OLV internal Logs",Icons.LEVEL_INFO,logViewPanelWrapper,true);
    GuiJulHandler.start(logViewPanelWrapper.getDataTableModel(), logViewPanelWrapper.getConfiguration());
  }
}
