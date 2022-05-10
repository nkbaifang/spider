
package mpeg.dash.mpd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RepresentationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RepresentationType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:mpeg:dash:schema:mpd:2011}RepresentationBaseType">
 *       &lt;sequence>
 *         &lt;element name="BaseURL" type="{urn:mpeg:dash:schema:mpd:2011}BaseURLType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SubRepresentation" type="{urn:mpeg:dash:schema:mpd:2011}SubRepresentationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SegmentBase" type="{urn:mpeg:dash:schema:mpd:2011}SegmentBaseType" minOccurs="0"/>
 *         &lt;element name="SegmentList" type="{urn:mpeg:dash:schema:mpd:2011}SegmentListType" minOccurs="0"/>
 *         &lt;element name="SegmentTemplate" type="{urn:mpeg:dash:schema:mpd:2011}SegmentTemplateType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{urn:mpeg:dash:schema:mpd:2011}StringNoWhitespaceType" />
 *       &lt;attribute name="bandwidth" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="qualityRanking" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="dependencyId" type="{urn:mpeg:dash:schema:mpd:2011}StringVectorType" />
 *       &lt;attribute name="mediaStreamStructureId" type="{urn:mpeg:dash:schema:mpd:2011}StringVectorType" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RepresentationType", namespace = "urn:mpeg:dash:schema:mpd:2011", propOrder = {
    "baseURL",
    "subRepresentation",
    "segmentBase",
    "segmentList",
    "segmentTemplate"
})
public class RepresentationType
    extends RepresentationBaseType
{

    @XmlElement(name = "BaseURL", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<BaseURLType> baseURL;
    @XmlElement(name = "SubRepresentation", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<SubRepresentationType> subRepresentation;
    @XmlElement(name = "SegmentBase", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentBaseType segmentBase;
    @XmlElement(name = "SegmentList", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentListType segmentList;
    @XmlElement(name = "SegmentTemplate", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentTemplateType segmentTemplate;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "bandwidth", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long bandwidth;
    @XmlAttribute(name = "qualityRanking")
    @XmlSchemaType(name = "unsignedInt")
    protected Long qualityRanking;
    @XmlAttribute(name = "dependencyId")
    protected List<String> dependencyId;
    @XmlAttribute(name = "mediaStreamStructureId")
    protected List<String> mediaStreamStructureId;

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
     * Gets the value of the subRepresentation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subRepresentation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubRepresentation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubRepresentationType }
     * 
     * 
     */
    public List<SubRepresentationType> getSubRepresentation() {
        if (subRepresentation == null) {
            subRepresentation = new ArrayList<SubRepresentationType>();
        }
        return this.subRepresentation;
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
     * 获取bandwidth属性的值。
     * 
     */
    public long getBandwidth() {
        return bandwidth;
    }

    /**
     * 设置bandwidth属性的值。
     * 
     */
    public void setBandwidth(long value) {
        this.bandwidth = value;
    }

    /**
     * 获取qualityRanking属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getQualityRanking() {
        return qualityRanking;
    }

    /**
     * 设置qualityRanking属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setQualityRanking(Long value) {
        this.qualityRanking = value;
    }

    /**
     * Gets the value of the dependencyId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dependencyId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDependencyId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDependencyId() {
        if (dependencyId == null) {
            dependencyId = new ArrayList<String>();
        }
        return this.dependencyId;
    }

    /**
     * Gets the value of the mediaStreamStructureId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mediaStreamStructureId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMediaStreamStructureId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMediaStreamStructureId() {
        if (mediaStreamStructureId == null) {
            mediaStreamStructureId = new ArrayList<String>();
        }
        return this.mediaStreamStructureId;
    }

}
