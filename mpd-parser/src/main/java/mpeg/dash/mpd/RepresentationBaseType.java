
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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>RepresentationBaseType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RepresentationBaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FramePacking" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AudioChannelConfiguration" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ContentProtection" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EssentialProperty" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SupplementalProperty" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="InbandEventStream" type="{urn:mpeg:dash:schema:mpd:2011}EventStreamType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="profiles" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="sar" type="{urn:mpeg:dash:schema:mpd:2011}RatioType" />
 *       &lt;attribute name="frameRate" type="{urn:mpeg:dash:schema:mpd:2011}FrameRateType" />
 *       &lt;attribute name="audioSamplingRate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mimeType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="segmentProfiles" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="codecs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="maximumSAPPeriod" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="startWithSAP" type="{urn:mpeg:dash:schema:mpd:2011}SAPType" />
 *       &lt;attribute name="maxPlayoutRate" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="codingDependency" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="scanType" type="{urn:mpeg:dash:schema:mpd:2011}VideoScanType" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RepresentationBaseType", namespace = "urn:mpeg:dash:schema:mpd:2011", propOrder = {
    "framePacking",
    "audioChannelConfiguration",
    "contentProtection",
    "essentialProperty",
    "supplementalProperty",
    "inbandEventStream",
    "any"
})
@XmlSeeAlso({
    RepresentationType.class,
    AdaptationSetType.class,
    SubRepresentationType.class
})
public class RepresentationBaseType {

    @XmlElement(name = "FramePacking", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> framePacking;
    @XmlElement(name = "AudioChannelConfiguration", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> audioChannelConfiguration;
    @XmlElement(name = "ContentProtection", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> contentProtection;
    @XmlElement(name = "EssentialProperty", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> essentialProperty;
    @XmlElement(name = "SupplementalProperty", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> supplementalProperty;
    @XmlElement(name = "InbandEventStream", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<EventStreamType> inbandEventStream;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "profiles")
    protected String profiles;
    @XmlAttribute(name = "width")
    @XmlSchemaType(name = "unsignedInt")
    protected Long width;
    @XmlAttribute(name = "height")
    @XmlSchemaType(name = "unsignedInt")
    protected Long height;
    @XmlAttribute(name = "sar")
    protected String sar;
    @XmlAttribute(name = "frameRate")
    protected String frameRate;
    @XmlAttribute(name = "audioSamplingRate")
    protected String audioSamplingRate;
    @XmlAttribute(name = "mimeType")
    protected String mimeType;
    @XmlAttribute(name = "segmentProfiles")
    protected String segmentProfiles;
    @XmlAttribute(name = "codecs")
    protected String codecs;
    @XmlAttribute(name = "maximumSAPPeriod")
    protected Double maximumSAPPeriod;
    @XmlAttribute(name = "startWithSAP")
    protected Long startWithSAP;
    @XmlAttribute(name = "maxPlayoutRate")
    protected Double maxPlayoutRate;
    @XmlAttribute(name = "codingDependency")
    protected Boolean codingDependency;
    @XmlAttribute(name = "scanType")
    protected VideoScanType scanType;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the framePacking property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the framePacking property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFramePacking().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getFramePacking() {
        if (framePacking == null) {
            framePacking = new ArrayList<DescriptorType>();
        }
        return this.framePacking;
    }

    /**
     * Gets the value of the audioChannelConfiguration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the audioChannelConfiguration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAudioChannelConfiguration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getAudioChannelConfiguration() {
        if (audioChannelConfiguration == null) {
            audioChannelConfiguration = new ArrayList<DescriptorType>();
        }
        return this.audioChannelConfiguration;
    }

    /**
     * Gets the value of the contentProtection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentProtection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentProtection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getContentProtection() {
        if (contentProtection == null) {
            contentProtection = new ArrayList<DescriptorType>();
        }
        return this.contentProtection;
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
     * Gets the value of the inbandEventStream property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inbandEventStream property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInbandEventStream().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventStreamType }
     * 
     * 
     */
    public List<EventStreamType> getInbandEventStream() {
        if (inbandEventStream == null) {
            inbandEventStream = new ArrayList<EventStreamType>();
        }
        return this.inbandEventStream;
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
     * 获取width属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getWidth() {
        return width;
    }

    /**
     * 设置width属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setWidth(Long value) {
        this.width = value;
    }

    /**
     * 获取height属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHeight() {
        return height;
    }

    /**
     * 设置height属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHeight(Long value) {
        this.height = value;
    }

    /**
     * 获取sar属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSar() {
        return sar;
    }

    /**
     * 设置sar属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSar(String value) {
        this.sar = value;
    }

    /**
     * 获取frameRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrameRate() {
        return frameRate;
    }

    /**
     * 设置frameRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrameRate(String value) {
        this.frameRate = value;
    }

    /**
     * 获取audioSamplingRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudioSamplingRate() {
        return audioSamplingRate;
    }

    /**
     * 设置audioSamplingRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudioSamplingRate(String value) {
        this.audioSamplingRate = value;
    }

    /**
     * 获取mimeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * 设置mimeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * 获取segmentProfiles属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentProfiles() {
        return segmentProfiles;
    }

    /**
     * 设置segmentProfiles属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentProfiles(String value) {
        this.segmentProfiles = value;
    }

    /**
     * 获取codecs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodecs() {
        return codecs;
    }

    /**
     * 设置codecs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodecs(String value) {
        this.codecs = value;
    }

    /**
     * 获取maximumSAPPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximumSAPPeriod() {
        return maximumSAPPeriod;
    }

    /**
     * 设置maximumSAPPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaximumSAPPeriod(Double value) {
        this.maximumSAPPeriod = value;
    }

    /**
     * 获取startWithSAP属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStartWithSAP() {
        return startWithSAP;
    }

    /**
     * 设置startWithSAP属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStartWithSAP(Long value) {
        this.startWithSAP = value;
    }

    /**
     * 获取maxPlayoutRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxPlayoutRate() {
        return maxPlayoutRate;
    }

    /**
     * 设置maxPlayoutRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxPlayoutRate(Double value) {
        this.maxPlayoutRate = value;
    }

    /**
     * 获取codingDependency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCodingDependency() {
        return codingDependency;
    }

    /**
     * 设置codingDependency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCodingDependency(Boolean value) {
        this.codingDependency = value;
    }

    /**
     * 获取scanType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VideoScanType }
     *     
     */
    public VideoScanType getScanType() {
        return scanType;
    }

    /**
     * 设置scanType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VideoScanType }
     *     
     */
    public void setScanType(VideoScanType value) {
        this.scanType = value;
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
