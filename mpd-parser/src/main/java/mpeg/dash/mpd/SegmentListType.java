
package mpeg.dash.mpd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._1999.xlink.ActuateType;


/**
 * <p>SegmentListType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SegmentListType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:mpeg:dash:schema:mpd:2011}MultipleSegmentBaseType">
 *       &lt;sequence>
 *         &lt;element name="SegmentURL" type="{urn:mpeg:dash:schema:mpd:2011}SegmentURLType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}href"/>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}actuate"/>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SegmentListType", namespace = "urn:mpeg:dash:schema:mpd:2011", propOrder = {
    "segmentURL"
})
public class SegmentListType
    extends MultipleSegmentBaseType
{

    @XmlElement(name = "SegmentURL", namespace = "urn:mpeg:dash:schema:mpd:2011")
    protected List<SegmentURLType> segmentURL;
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    protected String href;
    @XmlAttribute(name = "actuate", namespace = "http://www.w3.org/1999/xlink")
    protected ActuateType actuate;

    /**
     * Gets the value of the segmentURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the segmentURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSegmentURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SegmentURLType }
     * 
     * 
     */
    public List<SegmentURLType> getSegmentURL() {
        if (segmentURL == null) {
            segmentURL = new ArrayList<SegmentURLType>();
        }
        return this.segmentURL;
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

}
