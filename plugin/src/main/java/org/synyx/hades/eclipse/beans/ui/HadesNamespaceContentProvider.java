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

package org.synyx.hades.eclipse.beans.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeanProperty;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;
import org.springframework.ide.eclipse.beans.ui.model.metadata.BeanMetadataReference;
import org.springframework.ide.eclipse.beans.ui.model.metadata.IBeanMetadataContentProvider;
import org.springframework.ide.eclipse.beans.ui.namespaces.DefaultNamespaceContentProvider;
import org.synyx.hades.eclipse.metadata.ui.HadesBeanMetadata;


/**
 * Removes DAO factory properties from the outline view.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class HadesNamespaceContentProvider extends
        DefaultNamespaceContentProvider implements IBeanMetadataContentProvider {

    private static final List<String> FILTER_PROPERTIES = Arrays.asList(
            "daoInterface", "domainClass", "transactionManager");


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.ide.eclipse.beans.ui.model.BeansModelContentProvider
     * #getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object parentElement) {

        // Filter bean properties
        if (parentElement instanceof IBean) {
            return getFilteredProperties((IBean) parentElement).toArray();
        }

        if (parentElement instanceof HadesBeanMetadata) {
            return getFilteredProperties(
                    ((HadesBeanMetadata) parentElement).getValue()).toArray();
        }

        return super.getChildren(parentElement);
    }


    public List<IBeanProperty> getFilteredProperties(IBean bean) {

        List<IBeanProperty> filtered = new ArrayList<IBeanProperty>();

        for (Object child : super.getChildren(bean)) {

            if (child instanceof IBeanProperty) {

                IBeanProperty property = (IBeanProperty) child;
                if (!FILTER_PROPERTIES.contains(property.getElementName())) {
                    filtered.add(property);
                }
            }
        }

        return filtered;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ide.eclipse.beans.ui.model.metadata.
     * IBeanMetadataContentProvider
     * #getBeanMetadataReference(org.springframework.
     * ide.eclipse.beans.core.metadata.model.IBeanMetadata,
     * org.springframework.ide.eclipse.beans.core.model.IBeansProject)
     */
    public BeanMetadataReference getBeanMetadataReference(
            IBeanMetadata metadata, IBeansProject project) {

        return new BeanMetadataReference(project, metadata.getKey());
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ide.eclipse.beans.ui.model.metadata.
     * IBeanMetadataContentProvider#supports(java.lang.Object)
     */
    public boolean supports(Object element) {

        return element instanceof HadesBeanMetadata;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ide.eclipse.beans.ui.namespaces.
     * DefaultNamespaceContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(Object element) {

        return true;
    }
}
