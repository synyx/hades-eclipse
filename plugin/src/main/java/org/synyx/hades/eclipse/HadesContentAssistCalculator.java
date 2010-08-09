/*
 * Copyright 2002-2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.synyx.hades.eclipse;

import org.springframework.ide.eclipse.beans.ui.editor.contentassist.BeanReferenceContentAssistCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistProposalRecorder;


/**
 * @author Oliver Gierke - gierke@synyx.de
 */
public class HadesContentAssistCalculator extends
        BeanReferenceContentAssistCalculator {

    /*
     * (non-Javadoc)
     * 
     * @seeorg.springframework.ide.eclipse.beans.ui.editor.contentassist.
     * BeanReferenceContentAssistCalculator
     * #computeProposals(org.springframework.
     * ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext,
     * org.springframework
     * .ide.eclipse.beans.ui.editor.contentassist.IContentAssistProposalRecorder
     * )
     */
    @Override
    public void computeProposals(IContentAssistContext context,
            IContentAssistProposalRecorder recorder) {

        super.computeProposals(context, recorder);
    }
}
