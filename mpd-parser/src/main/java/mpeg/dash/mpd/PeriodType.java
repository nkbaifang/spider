
package mpeg.dash.mpd;

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
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import javax.xml.namespace.QName;
import org.w3._1999.xlink.ActuateType;
import org.w3c.dom.Element;


/**
 * <p>PeriodType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PeriodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BaseURL" type="{urn:mpeg:dash:schema:mpd:2011}BaseURLType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SegmentBase" type="{urn:mpeg:dash:schema:mpd:2011}SegmentBaseType" minOccurs="0"/>
 *         &lt;element name="SegmentList" type="{urn:mpeg:dash:schema:mpd:2011}SegmentListType" minOccurs="0"/>
 *         &lt;element name="SegmentTemplate" type="{urn:mpeg:dash:schema:mpd:2011}SegmentTemplateType" minOccurs="0"/>
 *         &lt;element name="AssetIdentifier" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" minOccurs="0"/>
 *         &lt;element name="EventStream" type="{urn:mpeg:dash:schema:mpd:2011}EventStreamType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AdaptationSet" type="{urn:mpeg:dash:schema:mpd:2011}AdaptationSetType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Subset" type="{urn:mpeg:dash:schema:mpd:2011}SubsetType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SupplementalProperty" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}href"/>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}actuate"/>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="start" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="duration" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="bitstreamSwitching" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeriodType", namespace = "urn:mpeg:dash:schema:mpd:2011", propOrder = {
    "baseURL",
    "segmentBase",
    "segmentList",
    "segmentTemplate",
    "assetIdentifier",
    "eventStream",
    "adaptationSet",
    "subset",
    "supplementalProperty",
    "any"
})
public class PeriodType {

    @XmlElement(name = "BaseURL", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<BaseURLType> baseURL;
    @XmlElement(name = "SegmentBase", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentBaseType segmentBase;
    @XmlElement(name = "SegmentList", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentListType segmentList;
    @XmlElement(name = "SegmentTemplate", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentTemplateType segmentTemplate;
    @XmlElement(name = "AssetIdentifier", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected DescriptorType assetIdentifier;
    @XmlElement(name = "EventStream", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<EventStreamType> eventStream;
    @XmlElement(name = "AdaptationSet", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<AdaptationSetType> adaptationSet;
    @XmlElement(name = "Subset", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<SubsetType> subset;
    @XmlElement(name = "SupplementalProperty", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> supplementalProperty;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    protected String href;
    @XmlAttribute(name = "actuate", namespace = "http://www.w3.org/1999/xlink")
    protected ActuateType actuate;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "start")
    protected Duration start;
    @XmlAttribute(name = "duration")
    protected Duration duration;
    @XmlAttribute(name = "bitstreamSwitching")
    protected Boolean bitstreamSwitching;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the baseURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the baseURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBaseURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BaseURLType }
     * 
     * 
     */
    public List<BaseURLType> getBaseURL() {
        if (baseURL == null) {
            baseURL = new ArrayList<BaseURLType>();
        }
        return this.baseURL;
    }

    /**
     * 获取segmentBase属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SegmentBaseType }
     *     
     */
    public SegmentBaseType getSegmentBase() {
        return segmentBase;
    }

    /**
     * 设置segmentBase属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SegmentBaseType }
     *     
     */
    public void setSegmentBase(SegmentBaseType value) {
        this.segmentBase = value;
    }

    /**
     * 获取segmentList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SegmentListType }
     *     
     */
    public SegmentListType getSegmentList() {
        return segmentList;
    }

    /**
     * 设置segmentList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SegmentListType }
     *     
     */
    public void setSegmentList(SegmentListType value) {
        this.segmentList = value;
    }

    /**
     * 获取segmentTemplate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SegmentTemplateType }
     *     
     */
    public SegmentTemplateType getSegmentTemplate() {
        return segmentTemplate;
    }

    /**
     * 设置segmentTemplate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SegmentTemplateType }
     *     
     */
    public void setSegmentTemplate(SegmentTemplateType value) {
        this.segmentTemplate = value;
    }

    /**
     * 获取assetIdentifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DescriptorType }
     *     
     */
    public DescriptorType getAssetIdentifier() {
        return assetIdentifier;
    }

    /**
     * 设置assetIdentifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptorType }
     *     
     */
    public void setAssetIdentifier(DescriptorType value) {
        this.assetIdentifier = value;
    }

    /**
     * Gets the value of the eventStream property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventStream property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventStream().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventStreamType }
     * 
     * 
     */
    public List<EventStreamType> getEventStream() {
        if (eventStream == null) {
            eventStream = new ArrayList<EventStreamType>();
        }
        return this.eventStream;
    }

    /**
     * Gets the value of the adaptationSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adaptationSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdaptationSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdaptationSetType }
     * 
     * 
     */
    public List<AdaptationSetType> getAdaptationSet() {
        if (adaptationSet == null) {
            adaptationSet = new ArrayList<AdaptationSetType>();
        }
        return this.adaptationSet;
    }

    /**
     * Gets the value of the subset property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subset property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubset().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubsetType }
     * 
     * 
     */
    public List<SubsetType> getSubset() {
        if (subset == null) {
            subset = new ArrayList<SubsetType>();
        }
        return this.subset;
    }

    /**
     * Gets the value of the supplementalProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supplementalProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupplementalProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getSupplementalProperty() {
        if (supplementalProperty == null) {
            supplementalProperty = new ArrayList<DescriptorType>();
        }
        return this.supplementalProperty;
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
     * 获取href属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * 设置href属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * 获取actuate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ActuateType }
     *     
     */
    public ActuateType getActuate() {
        if (actuate == null) {
            return ActuateType.ON_REQUEST;
        } else {
            return actuate;
        }
    }

    /**
     * 设置actuate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ActuateType }
     *     
     */
    public void setActuate(ActuateType value) {
        this.actuate = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * 获取start属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getStart() {
        return start;
    }

    /**
     * 设置start属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setStart(Duration value) {
        this.start = value;
    }

    /**
     * 获取duration属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * 设置duration属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setDuration(Duration value) {
        this.duration = value;
    }

    /**
     * 获取bitstreamSwitching属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isBitstreamSwitching() {
        if (bitstreamSwitching == null) {
            return false;
        } else {
            return bitstreamSwitching;
        }
    }

    /**
     * 设置bitstreamSwitching属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBitstreamSwitching(Boolean value) {
        this.bitstreamSwitching = value;
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
