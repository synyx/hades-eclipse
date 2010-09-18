package org.synyx.hades.eclipse.metadata.ui;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansModelUtils;
import org.springframework.ide.eclipse.beans.core.metadata.model.BeanMetadataProviderAdapter;
import org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata;
import org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadataProvider;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.core.java.JdtUtils;
import org.springframework.ide.eclipse.core.model.java.JavaModelSourceLocation;
import org.synyx.hades.eclipse.HadesUtils;


/**
 * Custom {@link IBeanMetadataProvider} to create {@link HadesBeanMetadata}
 * instances for Hades repository beans.
 * 
 * @author Oliver Gierke
 */
public class HadesBeanMetadataProvider extends BeanMetadataProviderAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ide.eclipse.beans.core.metadata.model.
     * IBeanMetadataProvider
     * #provideBeanMetadata(org.springframework.ide.eclipse.
     * beans.core.model.IBean,
     * org.springframework.ide.eclipse.beans.core.model.IBeansConfig,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public Set<IBeanMetadata> provideBeanMetadata(final IBean bean,
            IBeansConfig beansConfig, IProgressMonitor progressMonitor) {

        Set<IBeanMetadata> beanMetadata = new HashSet<IBeanMetadata>();

        if (HadesUtils.isHadesDaoBean(bean)) {

            IType type =
                    JdtUtils.getJavaType(
                            bean.getElementResource().getProject(),
                            BeansModelUtils.getBeanClass(bean, null));

            try {

                beanMetadata.add(new HadesBeanMetadata(bean,
                        new JavaModelSourceLocation(type)));

            } catch (JavaModelException e) {

            }
        }

        return beanMetadata;
    }
}
