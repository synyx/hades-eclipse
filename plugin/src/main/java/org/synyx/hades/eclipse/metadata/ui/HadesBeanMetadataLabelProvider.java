package org.synyx.hades.eclipse.metadata.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.springframework.ide.eclipse.beans.ui.BeansUIImages;
import org.springframework.ide.eclipse.beans.ui.model.metadata.IBeanMetadataLabelProvider;


/**
 * Custom {@link IBeanMetadataLabelProvider label provider} to create labels for
 * {@link HadesBeanMetadata}.
 * 
 * @author Oliver Gierke
 */
public class HadesBeanMetadataLabelProvider extends LabelProvider implements
        IBeanMetadataLabelProvider {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object arg0) {

        return BeansUIImages.getImage(BeansUIImages.IMG_OBJS_BEAN);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object arg0) {

        HadesBeanMetadata metadata = (HadesBeanMetadata) arg0;
        return metadata.getValueAsText();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.navigator.IDescriptionProvider#getDescription(java.lang
     * .Object)
     */
    public String getDescription(Object arg0) {

        return "Hades Label Description";
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ide.eclipse.beans.ui.model.metadata.
     * IBeanMetadataLabelProvider#supports(java.lang.Object)
     */
    public boolean supports(Object object) {

        return object instanceof HadesBeanMetadata;
    }
}
