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

import pl.otros.logview.LogData;
import pl.otros.logview.filter.ThreadFilter;
import pl.otros.logview.gui.Icons;
import pl.otros.logview.gui.OtrosApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FocusOnThisThreadAction extends FocusOnThisAbstractAction<ThreadFilter> {

    /**
     *
     */
    private static final long serialVersionUID = -1554157187028762641L;

    public FocusOnThisThreadAction(ThreadFilter threadFilter, JCheckBox filterEnableCheckBox, OtrosApplication otrosApplication) {
        super(threadFilter, filterEnableCheckBox, otrosApplication);
        this.putValue(Action.SMALL_ICON, Icons.FILTER);
        this.putValue(Action.NAME, "Focus on this thread");
    }

    @Override
    public void action(ActionEvent e, ThreadFilter filter, LogData... selectedLogData) {
        String thread = selectedLogData[0].getThread();
        filter.setThreadToFilter(thread);
    }

}
