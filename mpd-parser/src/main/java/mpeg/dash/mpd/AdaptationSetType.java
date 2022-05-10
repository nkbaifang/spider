
package mpeg.dash.mpd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._1999.xlink.ActuateType;


/**
 * <p>AdaptationSetType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AdaptationSetType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:mpeg:dash:schema:mpd:2011}RepresentationBaseType">
 *       &lt;sequence>
 *         &lt;element name="Accessibility" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Role" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Rating" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Viewpoint" type="{urn:mpeg:dash:schema:mpd:2011}DescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ContentComponent" type="{urn:mpeg:dash:schema:mpd:2011}ContentComponentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BaseURL" type="{urn:mpeg:dash:schema:mpd:2011}BaseURLType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SegmentBase" type="{urn:mpeg:dash:schema:mpd:2011}SegmentBaseType" minOccurs="0"/>
 *         &lt;element name="SegmentList" type="{urn:mpeg:dash:schema:mpd:2011}SegmentListType" minOccurs="0"/>
 *         &lt;element name="SegmentTemplate" type="{urn:mpeg:dash:schema:mpd:2011}SegmentTemplateType" minOccurs="0"/>
 *         &lt;element name="Representation" type="{urn:mpeg:dash:schema:mpd:2011}RepresentationType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}href"/>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}actuate"/>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="lang" type="{http://www.w3.org/2001/XMLSchema}language" />
 *       &lt;attribute name="contentType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="par" type="{urn:mpeg:dash:schema:mpd:2011}RatioType" />
 *       &lt;attribute name="minBandwidth" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="maxBandwidth" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="minWidth" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="maxWidth" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="minHeight" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="maxHeight" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="minFrameRate" type="{urn:mpeg:dash:schema:mpd:2011}FrameRateType" />
 *       &lt;attribute name="maxFrameRate" type="{urn:mpeg:dash:schema:mpd:2011}FrameRateType" />
 *       &lt;attribute name="segmentAlignment" type="{urn:mpeg:dash:schema:mpd:2011}ConditionalUintType" default="false" />
 *       &lt;attribute name="subsegmentAlignment" type="{urn:mpeg:dash:schema:mpd:2011}ConditionalUintType" default="false" />
 *       &lt;attribute name="subsegmentStartsWithSAP" type="{urn:mpeg:dash:schema:mpd:2011}SAPType" default="0" />
 *       &lt;attribute name="bitstreamSwitching" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdaptationSetType", namespace = "urn:mpeg:dash:schema:mpd:2011", propOrder = {
    "accessibility",
    "role",
    "rating",
    "viewpoint",
    "contentComponent",
    "baseURL",
    "segmentBase",
    "segmentList",
    "segmentTemplate",
    "representation"
})
public class AdaptationSetType
    extends RepresentationBaseType
{

    @XmlElement(name = "Accessibility", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> accessibility;
    @XmlElement(name = "Role", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> role;
    @XmlElement(name = "Rating", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> rating;
    @XmlElement(name = "Viewpoint", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<DescriptorType> viewpoint;
    @XmlElement(name = "ContentComponent", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<ContentComponentType> contentComponent;
    @XmlElement(name = "BaseURL", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<BaseURLType> baseURL;
    @XmlElement(name = "SegmentBase", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentBaseType segmentBase;
    @XmlElement(name = "SegmentList", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentListType segmentList;
    @XmlElement(name = "SegmentTemplate", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentTemplateType segmentTemplate;
    @XmlElement(name = "Representation", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<RepresentationType> representation;
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    protected String href;
    @XmlAttribute(name = "actuate", namespace = "http://www.w3.org/1999/xlink")
    protected ActuateType actuate;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "unsignedInt")
    protected Long id;
    @XmlAttribute(name = "group")
    @XmlSchemaType(name = "unsignedInt")
    protected Long group;
    @XmlAttribute(name = "lang")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;
    @XmlAttribute(name = "contentType")
    protected String contentType;
    @XmlAttribute(name = "par")
    protected String par;
    @XmlAttribute(name = "minBandwidth")
    @XmlSchemaType(name = "unsignedInt")
    protected Long minBandwidth;
    @XmlAttribute(name = "maxBandwidth")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxBandwidth;
    @XmlAttribute(name = "minWidth")
    @XmlSchemaType(name = "unsignedInt")
    protected Long minWidth;
    @XmlAttribute(name = "maxWidth")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxWidth;
    @XmlAttribute(name = "minHeight")
    @XmlSchemaType(name = "unsignedInt")
    protected Long minHeight;
    @XmlAttribute(name = "maxHeight")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxHeight;
    @XmlAttribute(name = "minFrameRate")
    protected String minFrameRate;
    @XmlAttribute(name = "maxFrameRate")
    protected String maxFrameRate;
    @XmlAttribute(name = "segmentAlignment")
    protected String segmentAlignment;
    @XmlAttribute(name = "subsegmentAlignment")
    protected String subsegmentAlignment;
    @XmlAttribute(name = "subsegmentStartsWithSAP")
    protected Long subsegmentStartsWithSAP;
    @XmlAttribute(name = "bitstreamSwitching")
    protected Boolean bitstreamSwitching;

    /**
     * Gets the value of the accessibility property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessibility property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessibility().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getAccessibility() {
        if (accessibility == null) {
            accessibility = new ArrayList<DescriptorType>();
        }
        return this.accessibility;
    }

    /**
     * Gets the value of the role property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the role property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getRole() {
        if (role == null) {
            role = new ArrayList<DescriptorType>();
        }
        return this.role;
    }

    /**
     * Gets the value of the rating property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rating property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRating().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getRating() {
        if (rating == null) {
            rating = new ArrayList<DescriptorType>();
        }
        return this.rating;
    }

    /**
     * Gets the value of the viewpoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the viewpoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getViewpoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptorType }
     * 
     * 
     */
    public List<DescriptorType> getViewpoint() {
        if (viewpoint == null) {
            viewpoint = new ArrayList<DescriptorType>();
        }
        return this.viewpoint;
    }

    /**
     * Gets the value of the contentComponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentComponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContentComponentType }
     * 
     * 
     */
    public List<ContentComponentType> getContentComponent() {
        if (contentComponent == null) {
            contentComponent = new ArrayList<ContentComponentType>();
        }
        return this.contentComponent;
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
     * Gets the value of the representation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the representation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRepresentation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RepresentationType }
     * 
     * 
     */
    public List<RepresentationType> getRepresentation() {
        if (representation == null) {
            representation = new ArrayList<RepresentationType>();
        }
        return this.representation;
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
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * 获取group属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGroup() {
        return group;
    }

    /**
     * 设置group属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGroup(Long value) {
        this.group = value;
    }

    /**
     * 获取lang属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * 设置lang属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * 获取contentType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 设置contentType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * 获取par属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPar() {
        return par;
    }

    /**
     * 设置par属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPar(String value) {
        this.par = value;
    }

    /**
     * 获取minBandwidth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMinBandwidth() {
        return minBandwidth;
    }

    /**
     * 设置minBandwidth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMinBandwidth(Long value) {
        this.minBandwidth = value;
    }

    /**
     * 获取maxBandwidth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxBandwidth() {
        return maxBandwidth;
    }

    /**
     * 设置maxBandwidth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxBandwidth(Long value) {
        this.maxBandwidth = value;
    }

    /**
     * 获取minWidth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMinWidth() {
        return minWidth;
    }

    /**
     * 设置minWidth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMinWidth(Long value) {
        this.minWidth = value;
    }

    /**
     * 获取maxWidth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxWidth() {
        return maxWidth;
    }

    /**
     * 设置maxWidth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxWidth(Long value) {
        this.maxWidth = value;
    }

    /**
     * 获取minHeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMinHeight() {
        return minHeight;
    }

    /**
     * 设置minHeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMinHeight(Long value) {
        this.minHeight = value;
    }

    /**
     * 获取maxHeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxHeight() {
        return maxHeight;
    }

    /**
     * 设置maxHeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxHeight(Long value) {
        this.maxHeight = value;
    }

    /**
     * 获取minFrameRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinFrameRate() {
        return minFrameRate;
    }

    /**
     * 设置minFrameRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinFrameRate(String value) {
        this.minFrameRate = value;
    }

    /**
     * 获取maxFrameRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxFrameRate() {
        return maxFrameRate;
    }

    /**
     * 设置maxFrameRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxFrameRate(String value) {
        this.maxFrameRate = value;
    }

    /**
     * 获取segmentAlignment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentAlignment() {
        if (segmentAlignment == null) {
            return "false";
        } else {
            return segmentAlignment;
        }
    }

    /**
     * 设置segmentAlignment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentAlignment(String value) {
        this.segmentAlignment = value;
    }

    /**
     * 获取subsegmentAlignment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsegmentAlignment() {
        if (subsegmentAlignment == null) {
            return "false";
        } else {
            return subsegmentAlignment;
        }
    }

    /**
     * 设置subsegmentAlignment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsegmentAlignment(String value) {
        this.subsegmentAlignment = value;
    }

    /**
     * 获取subsegmentStartsWithSAP属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getSubsegmentStartsWithSAP() {
        if (subsegmentStartsWithSAP == null) {
            return  0L;
        } else {
            return subsegmentStartsWithSAP;
        }
    }

    /**
     * 设置subsegmentStartsWithSAP属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubsegmentStartsWithSAP(Long value) {
        this.subsegmentStartsWithSAP = value;
    }

    /**
     * 获取bitstreamSwitching属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBitstreamSwitching() {
        return bitstreamSwitching;
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

}
