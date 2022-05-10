package io.ziyi.spider.mpd;

import mpeg.dash.mpd.AdaptationSetType;
import mpeg.dash.mpd.MPDtype;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.Duration;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.List;

public class MpdParser {

    public static MPDtype parse(File file) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(MPDtype.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        JAXBElement<MPDtype> element = unmarshaller.unmarshal(new StreamSource(file), MPDtype.class);
        return element.getValue();
    }

    public static void main(String[] args) throws Exception {
        File file = new File("mpd.xml");
        MPDtype mpd = MpdParser.parse(file);
        mpd.getPeriod().forEach(period -> {
            String id = period.getId();
            Duration duration = period.getDuration();
            System.out.printf("Period: id=%s, duration=%d:%d:%d\n", id, duration.getHours(), duration.getMinutes(), duration.getSeconds());

            period.getAdaptationSet().forEach(as -> {
                System.out.printf("Adaptation Set: id=%s, group=%s, contentType=%s, mimeType=%s, codec=%s\n", as.getId(), as.getGroup(), as.getContentType(), as.getMimeType(), as.getCodecs());
            });
        });
    }
}
