package org.synyx.hades.eclipse.metadata.ui;

import org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;
import org.springframework.ide.eclipse.beans.ui.BeansUIImages;
import org.springframework.ide.eclipse.beans.ui.model.metadata.BeanMetadataNode;
import org.springframework.ide.eclipse.beans.ui.model.metadata.BeanMetadataReference;
import org.springframework.ide.eclipse.beans.ui.model.metadata.IBeanMetadataContentProvider;


/**
 * {@link IBeanMetadataContentProvider Content provider} for
 * {@link HadesBeanMetadata}.
 * 
 * @author Oliver Gierke
 */
public class HadesBeanMetadataContentProvider implements
        IBeanMetadataContentProvider {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ide.eclipse.beans.ui.model.metadata.
     * IBeanMetadataContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {

        return true;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ide.eclipse.beans.ui.model.metadata.
     * IBeanMetadataContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object element) {

        HadesBeanMetadata metadata = (HadesBeanMetadata) element;

        BeanMetadataNode node =
                new BeanMetadataNode(metadata.getHandleIdentifier());
        node.setLabel(metadata.getValueAsText());
        node.setImage(BeansUIImages.getImage(BeansUIImages.IMG_OBJS_TX));
        node.setLocation(metadata.getElementSourceLocation());

        return new Object[] { node };

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
    public boolean supports(Object object) {

        return object instanceof HadesBeanMetadata;
    }
}
