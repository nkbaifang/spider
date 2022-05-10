
package mpeg.dash.mpd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SubRepresentationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SubRepresentationType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:mpeg:dash:schema:mpd:2011}RepresentationBaseType">
 *       &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="dependencyLevel" type="{urn:mpeg:dash:schema:mpd:2011}UIntVectorType" />
 *       &lt;attribute name="bandwidth" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="contentComponent" type="{urn:mpeg:dash:schema:mpd:2011}StringVectorType" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubRepresentationType", namespace = "urn:mpeg:dash:schema:mpd:2011")
public class SubRepresentationType
    extends RepresentationBaseType
{

    @XmlAttribute(name = "level")
    @XmlSchemaType(name = "unsignedInt")
    protected Long level;
    @XmlAttribute(name = "dependencyLevel")
    protected List<Long> dependencyLevel;
    @XmlAttribute(name = "bandwidth")
    @XmlSchemaType(name = "unsignedInt")
    protected Long bandwidth;
    @XmlAttribute(name = "contentComponent")
    protected List<String> contentComponent;

    /**
     * 获取level属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLevel() {
        return level;
    }

    /**
     * 设置level属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLevel(Long value) {
        this.level = value;
    }

    /**
     * Gets the value of the dependencyLevel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dependencyLevel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDependencyLevel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getDependencyLevel() {
        if (dependencyLevel == null) {
            dependencyLevel = new ArrayList<Long>();
        }
        return this.dependencyLevel;
    }

    /**
     * 获取bandwidth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBandwidth() {
        return bandwidth;
    }

    /**
     * 设置bandwidth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBandwidth(Long value) {
        this.bandwidth = value;
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
     * {@link String }
     * 
     * 
     */
    public List<String> getContentComponent() {
        if (contentComponent == null) {
            contentComponent = new ArrayList<String>();
        }
        return this.contentComponent;
    }

}
