
package mpeg.dash.mpd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>VideoScanType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="VideoScanType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="progressive"/>
 *     &lt;enumeration value="interlaced"/>
 *     &lt;enumeration value="unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VideoScanType", namespace = "urn:mpeg:dash:schema:mpd:2011")
@XmlEnum
public enum VideoScanType {

    @XmlEnumValue("progressive")
    PROGRESSIVE("progressive"),
    @XmlEnumValue("interlaced")
    INTERLACED("interlaced"),
    @XmlEnumValue("unknown")
    UNKNOWN("unknown");
    private final String value;

    VideoScanType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VideoScanType fromValue(String v) {
        for (VideoScanType c: VideoScanType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
