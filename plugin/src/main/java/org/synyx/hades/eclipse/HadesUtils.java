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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IType;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;
import org.springframework.ide.eclipse.beans.core.model.IBeansTypedString;
import org.springframework.ide.eclipse.beans.ui.editor.namespaces.NamespaceUtils;
import org.synyx.hades.dao.orm.GenericDaoFactoryBean;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * Utility methods to work with Hades configuration.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public abstract class HadesUtils {

    public static final String NAMESPACE_URI = "http://schemas.synyx.org/hades";
    private static final Class<?> FACTORY_CLASS = GenericDaoFactoryBean.class;


    /**
     * Returns the name of the factory class.
     * 
     * @return
     */
    public static String getFactoryName() {

        return FACTORY_CLASS.getName();
    }


    /**
     * Returns the DAO interface name for the given bean.
     * 
     * @param bean
     * @return
     */
    public static String getDaoInterfaceName(IBean bean) {

        IBeansTypedString property =
                (IBeansTypedString) bean.getProperty("daoInterface").getValue();
        return property.getString();
    }


    /**
     * Returns all bean ids.
     * 
     * @return
     */
    public static Set<String> getDaoBeanIds(IProject project) {

        Set<String> result = new HashSet<String>();

        for (IBean bean : getDaoBeansFor(project)) {

            result.add(bean.getElementName());
        }

        return result;
    }


    /**
     * Returns all DAO beans with the given DAO interface from the given set of
     * {@link IBean}s.
     * 
     * @param beans
     * @param daoInterface
     * @return
     */
    public static Set<IBean> getDaoBeans(Set<IBean> beans, String daoInterface) {

        Set<IBean> result = new HashSet<IBean>();

        for (IBean bean : beans) {

            if (hasDaoInterface(bean, daoInterface)) {

                result.add(bean);
            }
        }

        return result;
    }


    public static boolean hasDaoBean(Set<IBean> beans, String daoInterface) {

        for (IBean bean : beans) {

            if (hasDaoInterface(bean, daoInterface)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Returns whether the given {@link Node} is from Hades namespace.
     * 
     * @param node
     * @return
     */
    public static boolean isHadesElement(Node node) {

        if (Node.ELEMENT_NODE == node.getNodeType()) {
            return NAMESPACE_URI.equals(NamespaceUtils
                    .getNamespaceUri((Element) node));
        }

        return false;
    }


    /**
     * Returns, whether the given bean is a Hades factory bean.
     * 
     * @param bean
     * @return
     */
    public static boolean isHadesDaoBean(IBean bean) {

        return getFactoryName().equals(bean.getClassName());
    }


    /**
     * Returns whether the provided bean is a DAO bean instance with the given
     * interface. Simply returns {@code false} for non DAO beans.
     * 
     * @param bean
     * @param daoInterface
     * @return
     */
    private static boolean hasDaoInterface(IBean bean, String daoInterface) {

        if (null == daoInterface) {
            throw new IllegalArgumentException("daoInterface must not be null!");
        }

        if (!isHadesDaoBean(bean)) {
            return false;
        }

        return daoInterface.equals(getDaoInterfaceName(bean));
    }


    public static Set<IBean> getDaoBeansFor(IProject project) {

        IBeansProject beansProject =
                BeansCorePlugin.getModel().getProject(project);
        return beansProject.getBeans(getFactoryName());
    }


    public static Set<IBean> getDaoBeansFor(IProject project, IType type) {

        Set<IBean> result = new HashSet<IBean>();

        for (IBean bean : getDaoBeansFor(project)) {

            if (type.getFullyQualifiedName().equals(getDaoInterfaceName(bean))) {
                result.add(bean);
            }
        }

        return result;
    }


    public static boolean hasDaoBeanFor(IProject project, IType type) {

        for (IBean bean : getDaoBeansFor(project)) {
            if (type.getFullyQualifiedName().equals(getDaoInterfaceName(bean))) {
                return true;
            }
        }

        return false;
    }
}
