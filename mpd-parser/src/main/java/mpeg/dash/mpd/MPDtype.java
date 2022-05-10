
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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>MPDtype complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MPDtype">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProgramInformation" type="{urn:mpeg:dash:schema:mpd:2011}ProgramInformationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BaseURL" type="{urn:mpeg:dash:schema:mpd:2011}BaseURLType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Location" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Period" type="{urn:mpeg:dash:schema:mpd:2011}PeriodType" maxOccurs="unbounded"/>
 *         &lt;element name="Metrics" type="{urn:mpeg:dash:schema:mpd:2011}MetricsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EssentialProperty" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SupplementalProperty" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UTCTiming" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="profiles" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" type="{urn:mpeg:dash:schema:mpd:2011}PresentationType" default="static" />
 *       &lt;attribute name="availabilityStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="availabilityEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="publishTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="mediaPresentationDuration" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="minimumUpdatePeriod" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="minBufferTime" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="timeShiftBufferDepth" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="suggestedPresentationDelay" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="maxSegmentDuration" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="maxSubsegmentDuration" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MPDtype", namespace = "urn:mpeg:dash:schema:mpd:2011", propOrder = {
    "programInformation",
    "baseURL",
    "location",
    "period",
    "metrics",
    "essentialProperty",
    "supplementalProperty",
    "utcTiming",
    "any"
})
public class MPDtype {

    @XmlElement(name = "ProgramInformation", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<ProgramInformationType> programInformation;
    @XmlElement(name = "BaseURL", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<BaseURLType> baseURL;
    @XmlElement(name = "Location", namespace = "urn:mpeg:dash:schema:mpd:2011")
    @XmlSchemaType(name = "anyURI")
    protected List<String> location;
    @XmlElement(name = "Period", namespace = "urn:mpeg:dash:schema:mpd:2011", required = true)
    protected List<PeriodType> period;
    @XmlElement(name = "Metrics", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<MetricsType> metrics;
    @XmlElement(name = "EssentialProperty", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> essentialProperty;
    @XmlElement(name = "SupplementalProperty", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> supplementalProperty;
    @XmlElement(name = "UTCTiming", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> utcTiming;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "profiles", required = true)
    protected String profiles;
    @XmlAttribute(name = "type")
    protected PresentationType type;
    @XmlAttribute(name = "availabilityStartTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar availabilityStartTime;
    @XmlAttribute(name = "availabilityEndTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar availabilityEndTime;
    @XmlAttribute(name = "publishTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publishTime;
    @XmlAttribute(name = "mediaPresentationDuration")
    protected Duration mediaPresentationDuration;
    @XmlAttribute(name = "minimumUpdatePeriod")
    protected Duration minimumUpdatePeriod;
    @XmlAttribute(name = "minBufferTime", required = true)
    protected Duration minBufferTime;
    @XmlAttribute(name = "timeShiftBufferDepth")
    protected Duration timeShiftBufferDepth;
    @XmlAttribute(name = "suggestedPresentationDelay")
    protected Duration suggestedPresentationDelay;
    @XmlAttribute(name = "maxSegmentDuration")
    protected Duration maxSegmentDuration;
    @XmlAttribute(name = "maxSubsegmentDuration")
    protected Duration maxSubsegmentDuration;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the programInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the programInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProgramInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProgramInformationType }
     * 
     * 
     */
    public List<ProgramInformationType> getProgramInformation() {
        if (programInformation == null) {
            programInformation = new ArrayList<ProgramInformationType>();
        }
        return this.programInformation;
    }

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
     * Gets the value of the location property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the location property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLocation() {
        if (location == null) {
            location = new ArrayList<String>();
        }
        return this.location;
    }

    /**
     * Gets the value of the period property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the period property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeriod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeriodType }
     * 
     * 
     */
    public List<PeriodType> getPeriod() {
        if (period == null) {
            period = new ArrayList<PeriodType>();
        }
        return this.period;
    }

    /**
     * Gets the value of the metrics property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metrics property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetrics().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetricsType }
     * 
     * 
     */
    public List<MetricsType> getMetrics() {
        if (metrics == null) {
            metrics = new ArrayList<MetricsType>();
        }
        return this.metrics;
    }

    /**
     * Gets the value of the essentialProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the essentialProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEssentialProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getEssentialProperty() {
        if (essentialProperty == null) {
            essentialProperty = new ArrayList<DescriptorType>();
        }
        return this.essentialProperty;
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
     * Gets the value of the utcTiming property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the utcTiming property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUTCTiming().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getUTCTiming() {
        if (utcTiming == null) {
            utcTiming = new ArrayList<DescriptorType>();
        }
        return this.utcTiming;
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
     * 获取profiles属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfiles() {
        return profiles;
    }

    /**
     * 设置profiles属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfiles(String value) {
        this.profiles = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PresentationType }
     *     
     */
    public PresentationType getType() {
        if (type == null) {
            return PresentationType.STATIC;
        } else {
            return type;
        }
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PresentationType }
     *     
     */
    public void setType(PresentationType value) {
        this.type = value;
    }

    /**
     * 获取availabilityStartTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAvailabilityStartTime() {
        return availabilityStartTime;
    }

    /**
     * 设置availabilityStartTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAvailabilityStartTime(XMLGregorianCalendar value) {
        this.availabilityStartTime = value;
    }

    /**
     * 获取availabilityEndTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAvailabilityEndTime() {
        return availabilityEndTime;
    }

    /**
     * 设置availabilityEndTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAvailabilityEndTime(XMLGregorianCalendar value) {
        this.availabilityEndTime = value;
    }

    /**
     * 获取publishTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPublishTime() {
        return publishTime;
    }

    /**
     * 设置publishTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPublishTime(XMLGregorianCalendar value) {
        this.publishTime = value;
    }

    /**
     * 获取mediaPresentationDuration属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getMediaPresentationDuration() {
        return mediaPresentationDuration;
    }

    /**
     * 设置mediaPresentationDuration属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setMediaPresentationDuration(Duration value) {
        this.mediaPresentationDuration = value;
    }

    /**
     * 获取minimumUpdatePeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getMinimumUpdatePeriod() {
        return minimumUpdatePeriod;
    }

    /**
     * 设置minimumUpdatePeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setMinimumUpdatePeriod(Duration value) {
        this.minimumUpdatePeriod = value;
    }

    /**
     * 获取minBufferTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getMinBufferTime() {
        return minBufferTime;
    }

    /**
     * 设置minBufferTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setMinBufferTime(Duration value) {
        this.minBufferTime = value;
    }

    /**
     * 获取timeShiftBufferDepth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getTimeShiftBufferDepth() {
        return timeShiftBufferDepth;
    }

    /**
     * 设置timeShiftBufferDepth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setTimeShiftBufferDepth(Duration value) {
        this.timeShiftBufferDepth = value;
    }

    /**
     * 获取suggestedPresentationDelay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getSuggestedPresentationDelay() {
        return suggestedPresentationDelay;
    }

    /**
     * 设置suggestedPresentationDelay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setSuggestedPresentationDelay(Duration value) {
        this.suggestedPresentationDelay = value;
    }

    /**
     * 获取maxSegmentDuration属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getMaxSegmentDuration() {
        return maxSegmentDuration;
    }

    /**
     * 设置maxSegmentDuration属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setMaxSegmentDuration(Duration value) {
        this.maxSegmentDuration = value;
    }

    /**
     * 获取maxSubsegmentDuration属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getMaxSubsegmentDuration() {
        return maxSubsegmentDuration;
    }

    /**
     * 设置maxSubsegmentDuration属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setMaxSubsegmentDuration(Duration value) {
        this.maxSubsegmentDuration = value;
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
