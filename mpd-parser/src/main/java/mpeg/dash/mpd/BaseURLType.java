
package mpeg.dash.mpd;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.namespace.QName;


/**
 * <p>BaseURLType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BaseURLType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *       &lt;attribute name="serviceLocation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="byteRange" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="availabilityTimeOffset" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="availabilityTimeComplete" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseURLType", namespace = "urn:mpeg:dash:schema:mpd:2011", propOrder = {
    "value"
})
public class BaseURLType {

    @XmlValue
    @XmlSchemaType(name = "anyURI")
    protected String value;
    @XmlAttribute(name = "serviceLocation")
    protected String serviceLocation;
    @XmlAttribute(name = "byteRange")
    protected String byteRange;
    @XmlAttribute(name = "availabilityTimeOffset")
    protected Double availabilityTimeOffset;
    @XmlAttribute(name = "availabilityTimeComplete")
    protected Boolean availabilityTimeComplete;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * 获取value属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取serviceLocation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceLocation() {
        return serviceLocation;
    }

    /**
     * 设置serviceLocation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceLocation(String value) {
        this.serviceLocation = value;
    }

    /**
     * 获取byteRange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getByteRange() {
        return byteRange;
    }

    /**
     * 设置byteRange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setByteRange(String value) {
        this.byteRange = value;
    }

    /**
     * 获取availabilityTimeOffset属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAvailabilityTimeOffset() {
        return availabilityTimeOffset;
    }

    /**
     * 设置availabilityTimeOffset属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAvailabilityTimeOffset(Double value) {
        this.availabilityTimeOffset = value;
    }

    /**
     * 获取availabilityTimeComplete属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAvailabilityTimeComplete() {
        return availabilityTimeComplete;
    }

    /**
     * 设置availabilityTimeComplete属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAvailabilityTimeComplete(Boolean value) {
        this.availabilityTimeComplete = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
