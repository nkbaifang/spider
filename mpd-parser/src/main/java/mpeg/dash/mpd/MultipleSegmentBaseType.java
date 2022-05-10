
package mpeg.dash.mpd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MultipleSegmentBaseType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MultipleSegmentBaseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:mpeg:dash:schema:mpd:2011}SegmentBaseType">
 *       &lt;sequence>
 *         &lt;element name="SegmentTimeline" type="{urn:mpeg:dash:schema:mpd:2011}SegmentTimelineType" minOccurs="0"/>
 *         &lt;element name="BitstreamSwitching" type="{urn:mpeg:dash:schema:mpd:2011}URLType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="duration" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="startNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultipleSegmentBaseType", namespace = "urn:mpeg:dash:schema:mpd:2011", propOrder = {
    "segmentTimeline",
    "bitstreamSwitching"
})
@XmlSeeAlso({
    SegmentTemplateType.class,
    SegmentListType.class
})
public class MultipleSegmentBaseType
    extends SegmentBaseType
{

    @XmlElement(name = "SegmentTimeline", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected SegmentTimelineType segmentTimeline;
    @XmlElement(name = "BitstreamSwitching", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected URLType bitstreamSwitching;
    @XmlAttribute(name = "duration")
    @XmlSchemaType(name = "unsignedInt")
    protected Long duration;
    @XmlAttribute(name = "startNumber")
    @XmlSchemaType(name = "unsignedInt")
    protected Long startNumber;

    /**
     * 获取segmentTimeline属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SegmentTimelineType }
     *     
     */
    public SegmentTimelineType getSegmentTimeline() {
        return segmentTimeline;
    }

    /**
     * 设置segmentTimeline属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SegmentTimelineType }
     *     
     */
    public void setSegmentTimeline(SegmentTimelineType value) {
        this.segmentTimeline = value;
    }

    /**
     * 获取bitstreamSwitching属性的值。
     * 
     * @return
     *     possible object is
     *     {@link URLType }
     *     
     */
    public URLType getBitstreamSwitching() {
        return bitstreamSwitching;
    }

    /**
     * 设置bitstreamSwitching属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link URLType }
     *     
     */
    public void setBitstreamSwitching(URLType value) {
        this.bitstreamSwitching = value;
    }

    /**
     * 获取duration属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * 设置duration属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDuration(Long value) {
        this.duration = value;
    }

    /**
     * 获取startNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStartNumber() {
        return startNumber;
    }

    /**
     * 设置startNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStartNumber(Long value) {
        this.startNumber = value;
    }

}
