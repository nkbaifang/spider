
package mpeg.dash.mpd;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>SegmentBaseType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SegmentBaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Initialization" type="{urn:mpeg:dash:schema:mpd:2011}URLType" minOccurs="0"/>
 *         &lt;element name="RepresentationIndex" type="{urn:mpeg:dash:schema:mpd:2011}URLType" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="timescale" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="presentationTimeOffset" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" />
 *       &lt;attribute name="indexRange" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="indexRangeExact" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="availabilityTimeOffset" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="availabilityTimeComplete" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SegmentBaseType", namespace = "urn:mpeg:dash:schema:mpd:2011", propOrder = {
    "initialization",
    "representationIndex",
    "any"
})
@XmlSeeAlso({
    MultipleSegmentBaseType.class
})
public class SegmentBaseType {

    @XmlElement(name = "Initialization", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected URLType initialization;
    @XmlElement(name = "RepresentationIndex", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected URLType representationIndex;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "timescale")
    @XmlSchemaType(name = "unsignedInt")
    protected Long timescale;
    @XmlAttribute(name = "presentationTimeOffset")
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger presentationTimeOffset;
    @XmlAttribute(name = "indexRange")
    protected String indexRange;
    @XmlAttribute(name = "indexRangeExact")
    protected Boolean indexRangeExact;
    @XmlAttribute(name = "availabilityTimeOffset")
    protected Double availabilityTimeOffset;
    @XmlAttribute(name = "availabilityTimeComplete")
    protected Boolean availabilityTimeComplete;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * 获取initialization属性的值。
     * 
     * @return
     *     possible object is
     *     {@link URLType }
     *     
     */
    public URLType getInitialization() {
        return initialization;
    }

    /**
     * 设置initialization属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link URLType }
     *     
     */
    public void setInitialization(URLType value) {
        this.initialization = value;
    }

    /**
     * 获取representationIndex属性的值。
     * 
     * @return
     *     possible object is
     *     {@link URLType }
     *     
     */
    public URLType getRepresentationIndex() {
        return representationIndex;
    }

    /**
     * 设置representationIndex属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link URLType }
     *     
     */
    public void setRepresentationIndex(URLType value) {
        this.representationIndex = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * 获取timescale属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTimescale() {
        return timescale;
    }

    /**
     * 设置timescale属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTimescale(Long value) {
        this.timescale = value;
    }

    /**
     * 获取presentationTimeOffset属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPresentationTimeOffset() {
        return presentationTimeOffset;
    }

    /**
     * 设置presentationTimeOffset属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPresentationTimeOffset(BigInteger value) {
        this.presentationTimeOffset = value;
    }

    /**
     * 获取indexRange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndexRange() {
        return indexRange;
    }

    /**
     * 设置indexRange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndexRange(String value) {
        this.indexRange = value;
    }

    /**
     * 获取indexRangeExact属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIndexRangeExact() {
        if (indexRangeExact == null) {
            return false;
        } else {
            return indexRangeExact;
        }
    }

    /**
     * 设置indexRangeExact属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIndexRangeExact(Boolean value) {
        this.indexRangeExact = value;
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
