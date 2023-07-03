package com.xzjie.cms.core.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author vito
 * @since 2023/6/28 11:09 AM
 */
public class XmlConvertUtil {
    public static <T> T convertToBean(String xml, Class<T> t) {
        T obj = null;
        try {
            JAXBContext context = JAXBContext.newInstance(t);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            obj = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

//    public static JSONObject xmlToJson(String xml) {
//        try {
//            org.json.JSONObject object = XML.toJSONObject(xml);
//            String jsonData = object.get("response").toString();
//            JSONObject jsonObject = JSON.parseObject(jsonData);
//            return jsonObject;
//			/*JSONObject result = (JSONObject) jsonObject.get("result");
//			JSONObject upload = (JSONObject) result.get("upload");
//			String uploadUrl = upload.get("url").toString();
//			String fildId = upload.get("upload-file-id").toString();*/
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
