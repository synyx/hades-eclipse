package org.synyx.hades.eclipse.metadata.ui;

import org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.core.model.IModelSourceLocation;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.synyx.hades.eclipse.HadesUtils;


/**
 * Custom {@link IBeanMetadata bean metdata} implementation.
 * <p>
 * TODO: remove skinning as soon as custom
 * {@link org.springframework.ide.eclipse.beans.ui.model.metadata.IBeanMetadataLabelProvider}
 * s work.
 * 
 * @author Oliver Gierke
 */
public class HadesBeanMetadata implements IBeanMetadata {

    private static final long serialVersionUID = 5730990435174474138L;

    private final IBean bean;
    private final IModelSourceLocation location;


    /**
     * Creates a new {@link HadesBeanMetadata} instance.
     * 
     * @param bean an {@link IBean} of a Hades repository.
     * @param location
     */
    public HadesBeanMetadata(IBean bean, IModelSourceLocation location) {

        Assert.notNull(location);
        Assert.notNull(bean);
        Assert.isTrue(HadesUtils.isHadesDaoBean(bean));

        this.bean = bean;
        this.location = location;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata
     * #getValueAsText()
     */
    public String getValueAsText() {

        return HadesUtils.asText(bean);
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata
     * #getHandleIdentifier()
     */
    public String getHandleIdentifier() {

        return bean.getElementID();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata
     * #getKey()
     */
    public String getKey() {

        return "Hades repositories";
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata
     * #getValue()
     */
    public Object getValue() {

        return bean;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata
     * #getElementSourceLocation()
     */
    public IModelSourceLocation getElementSourceLocation() {

        return location;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        HadesBeanMetadata that = (HadesBeanMetadata) obj;

        return ObjectUtils.nullSafeEquals(this.bean, that.bean)
                && ObjectUtils.nullSafeEquals(this.location, that.location);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        return ObjectUtils.nullSafeHashCode(bean)
                + ObjectUtils.nullSafeHashCode(location);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        // TODO: move into label provider
        return getValueAsText();
    }
}
